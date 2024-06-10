package be.gazette.gazette_website;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.sql.Date;

@EnableAutoConfiguration
@Entity
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id = 0L;
    @NotNull(message = "Title cannot be empty!")
    private String title;
    @NotNull(message = "Category cannot be empty!")
    private String category;
    @NotNull(message = "Author cannot be empty!")
    private String author;
    @NotNull(message = "Email cannot be empty!")
    private String email;
    @NotNull(message = "Content cannot be empty!")
    @Column(columnDefinition = "MEDIUMTEXT", nullable = false)
    private String content;
    @NotNull(message = "DateCreated cannot be empty!")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;

    public Article() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Date getDateCreated() { return dateCreated; }
    public void setDateCreated(Date dateCreated) { this.dateCreated = dateCreated; }
}