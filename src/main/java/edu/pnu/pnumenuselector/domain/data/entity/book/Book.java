package edu.pnu.pnumenuselector.domain.data.entity.book;

import edu.pnu.pnumenuselector.domain.data.dto.book.BookRegDto;
import edu.pnu.pnumenuselector.domain.data.dto.book.CategoryRegWithBookDto;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import edu.pnu.pnumenuselector.domain.data.entity.order.Order;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @ManyToMany(mappedBy = "books", cascade = CascadeType.REMOVE)
    private List<Category> categories = new ArrayList<>();

    @OneToOne(mappedBy = "book", cascade = CascadeType.REMOVE)
    private Order order;

    private int price;

    private String url;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member owner;


    public void addNewCategory(Category category){
        this.categories.add(category);
    }

    public BookRegDto convertToDto(){
        return BookRegDto.builder()
                .photo(this.url)
                .categoryRegDto(new CategoryRegWithBookDto(this.categories
                        .stream()
                        .map(Category::getName)
                        .collect(Collectors.toList())
                        )
                )
                .ownerNickName(this.owner.getUserId())
                .author(this.author)
                .price(this.price)
                .description(this.description)
                .title(this.title)
                .build();
    }

}

