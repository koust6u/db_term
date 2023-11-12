package edu.pnu.pnumenuselector.web.mvc.service;

import static org.junit.jupiter.api.Assertions.*;

import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.book.Category;
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
class CategoryServiceTest {

    @Autowired CategoryService categoryService;

    @Autowired MemberService memberService;
    @Autowired BookService bookService;
    @Test
    @Transactional
    @DisplayName(value = "카테고리 등록")
    void regCategory() throws Exception{
        //given
        Category category = DataUtils.genCategory();
        //when
        categoryService.regCategory(category);
        Category origin = categoryService.findOne(category.getId());
        //then
        Assertions.assertThat(origin).isEqualTo(category);
    }

    @Test
    @Transactional
    @Rollback(false)
    @DisplayName("카테고리와 책 연관관계 설정")
    void regBookWithCategory() throws Exception{
        //given
        Long id = DataUtils.randomSave(memberService);
        Member member = memberService.findOne(id);
        List<Book> books = DataUtils.genBookList(member);
        for (Book book : books) {
            bookService.registrationBook(book);
        }
        Category category = Category.builder()
                .books(books)
                .name("기술 서적")
                .build();
        //when
        categoryService.regCategory(category);
        //then
     }
}