package edu.pnu.pnumenuselector.domain.data.dto.book;

import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.book.Category;
import edu.pnu.pnumenuselector.domain.data.entity.member.Member;
import java.util.List;
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

    private List<Category> categories;

    public Book toEntity(Member member){
        return Book.builder()
                .title(title)
                .author(author)
                .price(price)
                .description(description)
                .categories(categories)
                .owner(member)
                .stockQuantity(stockQuantity)
                .build();
    }
}
