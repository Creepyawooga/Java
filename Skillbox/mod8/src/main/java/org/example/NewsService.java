package org.example;

import org.example.News;
import org.example.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    private List<News> newsList = new ArrayList<>(); // Это временная база данных для новостей

    // Получить все новости
    public List<News> getAllNews() {
        return newsList;
    }

    // Получить новость по ID
    public News getNewsById(Long id) {
        return newsList.stream()
                .filter(news -> news.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("News not found"));
    }

    // Создать новость
    public News createNews(News news, User currentUser) {
        news.setAuthor(currentUser); // Устанавливаем текущего пользователя как автора новости
        newsList.add(news);
        return news;
    }

    // Обновить новость
    public News updateNews(Long id, News updatedNews) {
        News existingNews = getNewsById(id);
        existingNews.setTitle(updatedNews.getTitle());
        existingNews.setContent(updatedNews.getContent());
        return existingNews;
    }

    // Удалить новость
    public void deleteNews(Long id) {
        News existingNews = getNewsById(id);
        newsList.remove(existingNews);
    }
}
