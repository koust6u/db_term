package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.web.exception.BookNotFoundException;
import edu.pnu.pnumenuselector.web.mvc.repository.BookRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public Long registrationBook(Book book){
        bookRepository.save(book);
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
    public void deleteBook(Book book){
        bookRepository.delete(book);
    }

}
