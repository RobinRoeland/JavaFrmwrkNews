package be.gazette.gazette_website.Controllers;

import be.gazette.gazette_website.Article;
import be.gazette.gazette_website.ArticleCRUDRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleCRUDRepo repo;

    @GetMapping("/about")
    public String about() {
        return "about";
    }
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("list", repo.findTop10ByOrderByDateCreatedDesc());
        return "index";
    }

    @GetMapping("/indexall")
    public String index10(Model model) {
        model.addAttribute("list", repo.findAll());
        return "index";
    }

    @GetMapping("/listByTitle")
    public List<Article> getAllArticlesByTitle(@RequestParam(name="title", required=false, defaultValue="World") String title) {
        return repo.findByTitleContaining(title);
    }

    @GetMapping("/new")
    public String add(Model model){
        model.addAttribute("article", new Article());
        return "new";
    }

    @GetMapping("/newsave")
    public String addArticle(@Valid @ModelAttribute("article") Article article, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
        repo.save(article);
        return "redirect:/articles/index";
    }

    @GetMapping("/details")
    public String article(@RequestParam(name="articleId") Long articleId, Model model){
        if (repo.findById(articleId).isPresent()){
            Article article = repo.findById(articleId).get();
            model.addAttribute("article", article);
            return "details";
        }
        return "redirect:/articles/index";
    }

}
