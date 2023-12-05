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
    private int stockQuantity;

    private CategoryRegWithBookDto categoryRegDto;
    public Book toEntity(Member member){
        return Book.builder()
                .title(title)
                .author(author)
                .price(price)
                .description(description)
                .owner(member)
                .categories(new ArrayList<>())
                .stockQuantity(stockQuantity)
                .build();
    }
}
