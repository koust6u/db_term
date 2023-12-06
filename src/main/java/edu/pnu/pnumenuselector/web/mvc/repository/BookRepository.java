package edu.pnu.pnumenuselector.web.mvc.repository;

import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import java.util.List;
import java.util.Optional;

import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<List<Book>> findBookByTitle(String title);

    Optional<List<Book>> findBooksByOwner(Member owner);
}
