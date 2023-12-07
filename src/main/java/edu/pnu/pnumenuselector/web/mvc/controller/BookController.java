package edu.pnu.pnumenuselector.web.mvc.controller;

import static edu.pnu.pnumenuselector.web.WebConstant.SESSION_ID;
import static org.springframework.http.HttpStatus.CREATED;

import edu.pnu.pnumenuselector.domain.data.dto.book.BookLentDto;
import edu.pnu.pnumenuselector.domain.data.dto.book.BookRegDto;
import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.order.Order;
import edu.pnu.pnumenuselector.domain.data.entity.order.OrderStatus;
import edu.pnu.pnumenuselector.web.mvc.service.BookService;
import edu.pnu.pnumenuselector.web.mvc.service.CategoryService;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import edu.pnu.pnumenuselector.web.mvc.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    private final CategoryService categoryService;

    private final MemberService memberService;

    private final OrderService orderService;

    @PostMapping("/registration")
    public ResponseEntity<?> bookRegistration(@SessionAttribute(name = SESSION_ID) Member member,
                                              @RequestBody BookRegDto regDto){
        Book book = regDto.toEntity(member);
        bookService.registrationBook(book, member.getUserId());
        regDto.getCategoryRegDto().getNames()
                .stream()
                .map(categoryService::regBook)
                .forEach(category -> categoryService.addNewBook(category, book));
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<?> searchAllBookList(){
        List<BookRegDto> collect = bookService.searchAllBook()
                .stream()
                .map(Book::convertToDto)
                .toList();
        return ResponseEntity.ok(collect);
    }

    @GetMapping("/borrow")
    public ResponseEntity<?> borrowBookList(@SessionAttribute(name = SESSION_ID)Member member){
        List<BookLentDto> bookLentDtos = orderService.searchMyBorrowBook(member)
                .stream()
                .map(e -> mapping(e, e.getBook()))
                .toList();
        return ResponseEntity.ok(bookLentDtos);
    }


    private BookLentDto mapping(Order order, Book book){
        return BookLentDto.builder()
                .img(book.getUrl())
                .desc(book.getDescription())
                .owner(order.getLender().getUserId())
                .title(book.getTitle())
                .period(order.getPeriod())
                .author(book.getAuthor())
                .status(order.getStatus())
                .build();
    }
    @GetMapping
    public ResponseEntity<?> searchMyBookList(@SessionAttribute(name = SESSION_ID)Member member){

        Member findMember = memberService.searchMemberByUserId(member.getUserId());
        List<BookRegDto> bookRegDtos = findMember.getBooks()
                .stream()
                .map(Book::convertToDto)
                .toList();

        return ResponseEntity.ok(bookRegDtos);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteBook(@SessionAttribute(name = SESSION_ID) Member member,
                                        @RequestParam(name = "title") String title){
        Member findMember = memberService.searchMemberByUserId(member.getUserId());
        Order order = orderService.searchOneMyOrder(findMember, title);
        order = orderService.findOne(order.getId());

        bookService.deleteBook(order.getBook());

        return ResponseEntity.ok().build();
    }

    @GetMapping("/latest")
    public ResponseEntity<?> getLatest3Books(){
        List<BookRegDto> bookRegDtos = bookService.search3LatestBook()
                .stream()
                .map(Book::convertToDto)
                .toList();

        return ResponseEntity.ok(bookRegDtos);
    }

    @GetMapping("/count")
    public ResponseEntity<?> count(){
        return ResponseEntity.ok(bookService.count());
    }
}
