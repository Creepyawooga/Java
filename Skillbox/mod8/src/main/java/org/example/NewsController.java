package org.example;

import org.example.CustomUserDetails;
import org.example.NewsService;
import org.example.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.AccessDeniedException;


import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {

    private final NewsService newsService;
    private final UserService userService;

    public NewsController(NewsService newsService, UserService userService) {
        this.newsService = newsService;
        this.userService = userService;
    }

    @GetMapping
    public List<News> getAllNews() {
        return newsService.getAllNews();
    }

    @GetMapping("/{id}")
    public News getNewsById(@PathVariable Long id) {
        return newsService.getNewsById(id);
    }

    @PostMapping
    public News createNews(@RequestBody News news, @AuthenticationPrincipal CustomUserDetails currentUser) {
        return newsService.createNews(news, currentUser.getUser());  // Use CustomUserDetails to get User
    }

    @PutMapping("/{id}")
    public News updateNews(@PathVariable Long id, @RequestBody News news, @AuthenticationPrincipal CustomUserDetails currentUser) {
        News existingNews = newsService.getNewsById(id);
        if (!existingNews.getAuthor().equals(currentUser.getUser())) {
            throw new AccessDeniedException("You can only update your own news");
        }
        return newsService.updateNews(id, news);
    }

    @DeleteMapping("/{id}")
    public void deleteNews(@PathVariable Long id, @AuthenticationPrincipal CustomUserDetails currentUser) {
        News existingNews = newsService.getNewsById(id);
        if (!existingNews.getAuthor().equals(currentUser.getUser())) {
            throw new AccessDeniedException("You can only delete your own news");
        }
        newsService.deleteNews(id);
    }
}
