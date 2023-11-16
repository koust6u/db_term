package edu.pnu.pnumenuselector.web.mvc.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.utils.DataUtils;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class BookServiceTest {

    @Autowired BookService bookService;

    @Autowired MemberService memberService;


    @Test
    @DisplayName("책 등록")
    @Transactional
    void regBook() throws Exception{
        //given
        Long memberId = DataUtils.randomSave(memberService);
        Member member = memberService.findOne(memberId);
        Book book = getBook(member);
        //when
        Long bookId = bookService.registrationBook(book);
        Book findBook = bookService.findOne(bookId);
        //then
        assertThat(findBook).isEqualTo(book);
     }

    private static Book getBook(Member member) {
        Book book = Book.builder()
                .owner(member)
                .price(1000)
                .stockQuantity(10)
                .title("해커와 화가")
                .description("잼민 혐오자가 쓴글입니다.")
                .author("폴 그레이엄")
                .categories(DataUtils.genCategoryList())
                .build();
        return book;
    }


    @Test
    @Rollback(false)
     @DisplayName("책 이름으로 등록 책 리스트 조회")
     void searchAllBookByTitle() throws Exception{
         //given
        Member member1 = DataUtils.randomMember();
        Member member2 = DataUtils.randomMember();
        //when
        Book book1 = getBook(member1);
        Book book2 = getBook(member2);
        memberService.signUp(member1);
        memberService.signUp(member2);
        bookService.registrationBook(book1);
        bookService.registrationBook(book2);
        List<Book> hacker = bookService.searchAllBookByTitle("해커와 화가");
        //then
        assertThat(hacker.size()).isEqualTo(2);

      }
}