package edu.pnu.pnumenuselector.web.mvc.controller;

import static edu.pnu.pnumenuselector.web.WebConstant.SESSION_ID;
import static org.springframework.http.HttpStatus.CREATED;

import edu.pnu.pnumenuselector.domain.data.dto.book.BookRegDto;
import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.web.mvc.service.BookService;
import edu.pnu.pnumenuselector.web.mvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    private final CategoryService categoryService;

    @PostMapping("/registration")
    public ResponseEntity<?> bookRegistration(@SessionAttribute(SESSION_ID) Member member,
                                              @RequestBody BookRegDto regDto){
        Book book = regDto.toEntity(member);
        bookService.registrationBook(book);
        regDto.getCategoryRegDto().getNames()
                .stream()
                .map(categoryService::searchCategoryByName)
                .forEach(category -> categoryService.addNewBook(category, book));
        return ResponseEntity.status(CREATED).build();
    }

}
