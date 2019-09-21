package cn.tycoding.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author TyCoding
 * @date 2018/10/17
 */
@Data
@TableName("tb_comment")
public class SysComment implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    @TableField("p_id")
    private Long pId;

    @TableField("c_id")
    private Long cId;

    @TableField("article_title")
    private String articleTitle;

    @TableField("article_id")
    private Long articleId;

    @NotNull
    private String name;

    @TableField("c_name")
    private String cName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time;

    private String content;
    private String email;
    private String url;
    private String ip;
    private String device;
    private String address;

    /**
     * 评论分类：0 Article, 1 Archives, 2 About
     */
    private Integer sort;
}
