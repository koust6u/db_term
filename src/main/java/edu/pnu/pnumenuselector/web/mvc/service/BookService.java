package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.order.Order;
import edu.pnu.pnumenuselector.domain.data.entity.order.OrderStatus;
import edu.pnu.pnumenuselector.domain.data.entity.order.Receipt;
import edu.pnu.pnumenuselector.web.exception.BookNotFoundException;
import edu.pnu.pnumenuselector.web.exception.MemberNotFoundException;
import edu.pnu.pnumenuselector.web.mvc.repository.BookRepository;
import java.util.List;
import java.util.Optional;

import edu.pnu.pnumenuselector.web.mvc.repository.MemberRepository;
import edu.pnu.pnumenuselector.web.mvc.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    private final MemberRepository memberRepository;

    private final OrderRepository orderRepository;
    @Transactional
    public Long registrationBook(Book book, String lender){
        Member lendMember = memberRepository.findByUserId(lender)
                .orElseThrow(MemberNotFoundException::new);
        Order order = Order.builder()
                .book(book)
                .lender(lendMember)
                .status(OrderStatus.READY)
                .receipt(Receipt.ALL)
                .build();
        bookRepository.save(book);
        orderRepository.save(order);

        return book.getId();
    }

    public Book findOne(Long id){
        return bookRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }


    public List<Book> searchAllBookByTitle(String title){
        return bookRepository.findBookByTitle(title)
                .orElseThrow(BookNotFoundException::new);

    }


    public List<Book> searchAllBook(){
        return bookRepository.findAll();
    }

    public List<Book> searchBorrowBooks(String ownerId){
        Member owner = memberRepository.findByUserId(ownerId)
                .orElseThrow(MemberNotFoundException::new);
        return bookRepository.findBooksByOwner(owner).get();
    }

    @Transactional
    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

}
