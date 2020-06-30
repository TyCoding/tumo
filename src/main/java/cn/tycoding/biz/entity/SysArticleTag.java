package cn.tycoding.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author tycoding
 * @date 2020/6/27
 */
@Data
@TableName("tb_article_tag")
public class SysArticleTag implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("article_id")
    private Long articleId;

    @TableField("tag_id")
    private Long tagId;

    public SysArticleTag(Long articleId, Long tagId) {
        this.articleId = articleId;
        this.tagId = tagId;
    }
}
