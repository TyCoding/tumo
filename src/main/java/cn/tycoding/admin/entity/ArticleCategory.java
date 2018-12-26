package cn.tycoding.admin.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auther TyCoding
 * @date 2018/10/22
 */
public class ArticleCategory implements Serializable {

    private long id; //编号
    @NotNull
    private long articleId; //文章ID
    @NotNull
    private long categoryId; //分类ID

    public ArticleCategory() {
    }

    public ArticleCategory(long articleId, long categoryId) {
        this.articleId = articleId;
        this.categoryId = categoryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "ArticleCategory{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", categoryId=" + categoryId +
                '}';
    }
}
