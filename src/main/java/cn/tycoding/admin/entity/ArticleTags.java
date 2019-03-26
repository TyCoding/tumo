package cn.tycoding.admin.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author TyCoding
 * @date 2018/10/22
 */
@Data
@Table(name = "tb_article_tags")
public class ArticleTags implements Serializable {

    @Id
    private Long id;
    @NotNull
    @Column(name = "article_id")
    private Long articleId;
    @NotNull
    @Column(name = "tag_id")
    private Long tagId;

    public ArticleTags() {
    }

    public ArticleTags(Long articleId, Long tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
