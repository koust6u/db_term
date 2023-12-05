package edu.pnu.pnumenuselector.web.mvc.controller;

import edu.pnu.pnumenuselector.domain.data.dto.book.CategoryRegDto;
import edu.pnu.pnumenuselector.domain.data.dto.book.CategoryRegWithBookDto;
import edu.pnu.pnumenuselector.domain.data.entity.book.Category;
import edu.pnu.pnumenuselector.web.mvc.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> registrationOfCategory(@RequestBody CategoryRegDto dto){
        String parentName = dto.getParentName();
        if(parentName != null){
            Category parent = categoryService.searchCategoryByName(parentName);
            dto.toEntity(parent);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        categoryService.regCategory(dto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
