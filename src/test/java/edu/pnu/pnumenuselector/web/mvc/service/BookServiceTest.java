package edu.pnu.pnumenuselector.web.mvc.service;

import static org.junit.jupiter.api.Assertions.*;

import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.utils.DataUtils;
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
    @Rollback(false)
    void regBook() throws Exception{
        //given
        Long aLong = DataUtils.randomSave(memberService);
        Member member = memberService.findOne(aLong);
        Book book = Book.builder()
                .owner(member)
                .price(1000)
                .stockQuantity(10)
                .title("해커와 화가")
                .description("잼민 혐오자가 쓴글입니다.")
                .author("폴 그레이엄")
                .categories(DataUtils.genCategoryList())
                .build();
        //when
        bookService.registrationBook(book);
        //then

     }
}