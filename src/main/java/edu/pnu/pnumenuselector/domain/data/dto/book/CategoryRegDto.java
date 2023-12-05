package edu.pnu.pnumenuselector.domain.data.dto.book;

import edu.pnu.pnumenuselector.domain.data.entity.book.Category;
import java.util.ArrayList;
import lombok.Data;

@Data
public class CategoryRegDto {

    private String name;

    private String parentName;

    public Category toEntity(Category parent){
        Category build = Category.builder()
                .name(this.name)
                .books(new ArrayList<>())
                .child(new ArrayList<>())
                .build();
        build.initializeParent(parent);
        return build;
    }

    public Category toEntity(){
        return Category.builder()
                .name(this.name)
                .books(new ArrayList<>())
                .child(new ArrayList<>())
                .build();
    }
}
