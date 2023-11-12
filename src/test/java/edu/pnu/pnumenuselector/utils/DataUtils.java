package edu.pnu.pnumenuselector.utils;

import edu.pnu.pnumenuselector.domain.data.dto.member.JoinForm;
import edu.pnu.pnumenuselector.domain.data.dto.member.LoginForm;
import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.book.Category;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.web.mvc.service.MemberService;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class DataUtils {

    public static Long defaultSave(MemberService memberService) {
        return memberService.signUp(dummyJoinForm().toEntity());
    }


    public static Long randomSave(MemberService memberService) {
        return memberService.signUp(randomMember());
    }


    public static Category genCategory() {
        return Category.builder()
                .name("인문학").build();
    }

    public static List<Category> genCategoryList() {
        Category 인문학 = Category.builder()
                .name("인문학")
                .build();

        Category 세계사 = Category.builder()
                .name("세계사")
                .build();

        Category 전쟁사 = Category.builder()
                .name("전쟁사")
                .build();
        Category 역사 = Category.builder()
                .name("역사")
                .build();
        세계사.initializeParent(역사);
        전쟁사.initializeParent(역사);
        역사.initializeParent(인문학);
        return List.of(인문학, 전쟁사, 세계사, 역사);
    }

    public static List<Book> genBookList(Member member){
        Book ORM표준JPA = Book.builder()
                .author("김영환")
                .title("JAVA ORM Standard JPA")
                .price(100)
                .description("혁명적인 책")
                .stockQuantity(10)
                .owner(member)
                .build();

        Book 토비의SPRING = Book.builder()
                .title("SPRING 바이블")
                .author("이일민")
                .price(75)
                .description("바이블")
                .stockQuantity(7)
                .owner(member)
                .build();
        return List.of(ORM표준JPA, 토비의SPRING);
    }

    public static Member randomMember() {
        return Member.builder()
                .username("aa")
                .birthDay(LocalDate.now())
                .password("bb")
                .userId(UUID.randomUUID().toString())
                .email("aaa@bbbb.cccc")
                .build();
    }

    public static LoginForm dummyLoginForm() {
        return LoginForm.builder()
                .userId("dummyDummy")
                .password("dummyDummy")
                .build();
    }

    public static JoinForm dummyJoinForm() {
        return JoinForm.builder()
                .username("dummy:)")
                .userId("dummyDummy")
                .password("dummyDummy")
                .birthday(LocalDate.now())
                .email("dummy@dummy.com")
                .build();
    }

    public static Member dummyMember1() {
        JoinForm form = JoinForm.builder()
                .username("name")
                .birthday(LocalDate.now())
                .email("aa@bb.cc")
                .password("bb")
                .userId("abc")
                .build();
        return form.toEntity();
    }

    public static Member dummyMember2() {
        JoinForm form = JoinForm.builder()
                .username("name2")
                .birthday(LocalDate.now())
                .email("aa2@bb.cc")
                .password("bb2")
                .userId("abc2")
                .build();
        return form.toEntity();
    }
}
