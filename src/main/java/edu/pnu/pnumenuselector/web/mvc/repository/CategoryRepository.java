package edu.pnu.pnumenuselector.web.mvc.repository;

import edu.pnu.pnumenuselector.domain.data.entity.book.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findCategoryByName(String name);
}
