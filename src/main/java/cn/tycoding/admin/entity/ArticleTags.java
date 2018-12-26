package cn.tycoding.admin.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auther TyCoding
 * @date 2018/10/22
 */
public class ArticleTags implements Serializable {

    private long id; //编号
    @NotNull
    private long articleId; //文章ID
    @NotNull
    private long tagsId; //标签ID

    public ArticleTags() {
    }

    public ArticleTags(long articleId, long tagsId) {
        this.articleId = articleId;
        this.tagsId = tagsId;
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

    public long getTagsId() {
        return tagsId;
    }

    public void setTagsId(long tagsId) {
        this.tagsId = tagsId;
    }

    @Override
    public String toString() {
        return "ArticleTags{" +
                "id=" + id +
                ", articleId=" + articleId +
                ", tagsId=" + tagsId +
                '}';
    }
}
