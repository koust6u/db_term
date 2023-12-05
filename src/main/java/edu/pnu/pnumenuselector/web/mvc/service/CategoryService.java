package edu.pnu.pnumenuselector.web.mvc.service;

import edu.pnu.pnumenuselector.domain.data.entity.book.Book;
import edu.pnu.pnumenuselector.domain.data.entity.book.Category;
import edu.pnu.pnumenuselector.web.exception.BookNotFoundException;
import edu.pnu.pnumenuselector.web.exception.CategoryNotFoundException;
import edu.pnu.pnumenuselector.web.mvc.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void regCategory(Category category){

        categoryRepository.save(category);
    }

    @Transactional
    public void addNewBook(Category category,Book book){
        category.regNewBook(book);
    }
    public Category findOne(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(BookNotFoundException::new);
    }

    public Category searchCategoryByName(String name){
        return categoryRepository.findCategoryByName(name)
                .orElseThrow(() -> new CategoryNotFoundException(name));
    }

    @Transactional
    public void updateCategoryName(Category category, String name){
        category.updateName(name);
    }


}
