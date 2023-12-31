package edu.pnu.pnumenuselector.domain.data.entity.member;

import static jakarta.persistence.CascadeType.REMOVE;

import edu.pnu.pnumenuselector.domain.data.dto.authority.MemberView;
import edu.pnu.pnumenuselector.domain.data.dto.member.MemberResponse;
import edu.pnu.pnumenuselector.domain.data.dto.member.UpdateForm;
import edu.pnu.pnumenuselector.domain.data.entity.BaseEntity;
import edu.pnu.pnumenuselector.domain.data.entity.account.Account;
import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.order.Order;
import edu.pnu.pnumenuselector.web.exception.WrongPasswordException;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;


    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, name = "NAME")
    private String username;

    private String email;

    @OneToOne(mappedBy = "member", cascade = REMOVE)
    private Authority authority;
    @OneToOne(mappedBy = "member", cascade = REMOVE)
    private Profile profile;

    @OneToOne(mappedBy = "member", cascade = REMOVE)
    private Account account;


    private LocalDate birthDay;

    @OneToMany(mappedBy = "lender", cascade = REMOVE)
    private List<Order> lendList = new ArrayList<>();

    @OneToMany(mappedBy = "borrower", cascade = REMOVE)
    private List<Order> borrowList = new ArrayList<>();

    @OneToMany(mappedBy = "owner", cascade = REMOVE)
    private List<Book> books = new ArrayList<>();

    public void initializeRelation(Account account, Profile profile, Authority authority){
        this.account = account;
        this.profile = profile;
        this.authority = authority;
    }

    public void matchPassword(String inputPassword){
        if (!this.password.equals(inputPassword)){
            throw new WrongPasswordException();
        }
    }

    public MemberResponse toResponse(){
        return MemberResponse.builder()
                .username(this.username)
                .email(this.email)
                .userId(this.userId)
                .birthday(this.birthDay)
                .build();
    }

    public void updateInfo(UpdateForm form){
        this.password = form.getPassword();
        this.email = form.getEmail();
        this.birthDay = form.getBirthday();
        this.lastModifiedBy = form.getModifyBy();
    }

    public MemberView convertToMemberView(){
        return MemberView.builder()
                .tel(this.getProfile().getTel())
                .name(this.getUsername())
                .url(this.profile.getProfilePhotoUrl())
                .userId(this.userId)
                .role(this.authority.getRole())
                .bannedTime(this.getAuthority().getBannedTime())
                .pardonTime(this.getAuthority().getPardonTime())
                .signUpTime(this.getCreatedAt())
                .bookCount(this.getBooks().size())
                .build();
    }
}
