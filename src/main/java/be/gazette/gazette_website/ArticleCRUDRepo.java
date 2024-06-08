package be.gazette.gazette_website;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ArticleCRUDRepo extends CrudRepository<Article, Long> {
    Article findByTitle(String title);
    List<Article> findByTitleContaining(String title);
}
