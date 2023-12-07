package edu.pnu.pnumenuselector.domain.data.dto.book;

import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import java.util.ArrayList;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookRegDto {
    private String title;
    private String author;
    private String description;
    private int price;

    private String borrower;
    private String photo;

    private String tel;
    private String ownerNickName;
    private CategoryRegWithBookDto categoryRegDto;
    public Book toEntity(Member member){
        return Book.builder()
                .title(title)
                .author(author)
                .price(price)
                .url(photo)
                .description(description)
                .owner(member)
                .categories(new ArrayList<>())
                .build();
    }
}
