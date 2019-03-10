package cn.tycoding.admin.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auther TyCoding
 * @date 2018/10/22
 */
@Data
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
}
