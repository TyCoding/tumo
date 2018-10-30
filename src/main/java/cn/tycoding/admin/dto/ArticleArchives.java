package cn.tycoding.admin.dto;

import cn.tycoding.admin.entity.Article;

import java.io.Serializable;
import java.util.List;

/**
 * 用于封装Article表按时间归档的DTO
 *
 * @auther TyCoding
 * @date 2018/10/28
 */
public class ArticleArchives implements Serializable {

    private String date;
    private List<Article> articles;

    public ArticleArchives() {
    }

    public ArticleArchives(String date, List<Article> articles) {
        this.date = date;
        this.articles = articles;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "ArticleArchives{" +
                "date='" + date + '\'' +
                ", articles=" + articles +
                '}';
    }
}
