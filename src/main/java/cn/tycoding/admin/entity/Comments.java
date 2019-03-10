package cn.tycoding.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@Data
public class Comments implements Serializable {

    private long id; //编号
    private long pId; //父级ID，给哪个留言回复
    private long cId; //子级ID，给哪个留言评论
    private String articleTitle; //文章标题
    private long articleId; //文章ID
    @NotNull
    private String author; //留言人
    private String authorId; //给谁回复，即`@`谁
    private String email; //留言邮箱
    private String content; //留言内容

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date time; //留言日期

    private String url; //链接URL
    private long sort; //分类：0:默认文章列详情页，1:友链页，2:关于我页
    private String state; //状态
}
