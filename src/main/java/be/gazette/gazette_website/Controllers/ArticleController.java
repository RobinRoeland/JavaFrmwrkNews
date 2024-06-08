package be.gazette.gazette_website.Controllers;

import be.gazette.gazette_website.Article;
import be.gazette.gazette_website.ArticleCRUDRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleCRUDRepo repo;

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", repo.findAll());
        return "index";
    }

    @GetMapping("/listByTitle")
    public List<Article> getAllArticlesByTitle(@RequestParam(name="title", required=false, defaultValue="World") String title) {
        return repo.findByTitleContaining(title);
    }

    @GetMapping("/getArticleByTitle")
    public Article getArticleByTitle(@RequestParam(name="title", required=false, defaultValue="World") String title) {
        return repo.findByTitle(title);
    }

    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("article", new Article());
        return "addpage";
    }

    @GetMapping("/addsave")
    public String addArticle(Article article){
        repo.save(article);
        return "redirect:/articles/index";
    }

    @GetMapping("/article")
    public String article(@RequestParam(name="articleId") Long articleId, Model model){
        if (repo.findById(articleId).isPresent()){
            Article article = repo.findById(articleId).get();
            model.addAttribute("article", article);
            return "article";
        }
        return "redirect:/articles/index";
    }

}
