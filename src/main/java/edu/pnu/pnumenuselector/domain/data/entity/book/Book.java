package edu.pnu.pnumenuselector.domain.data.entity.book;

import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Book {

    @Id
    @GeneratedValue
    @Column(name = "BOOK_ID")
    private Long id;

    private String title;

    private String author;

    @Lob
    @Column(name = "DESC")
    private String description;

    @ManyToMany(mappedBy = "books")
    private List<Category> categories = new ArrayList<>();

    private int price;

    private int stockQuantity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member owner;

}

