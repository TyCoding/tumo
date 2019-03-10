package cn.tycoding.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
@Data
public class Article implements Serializable {

    private long id; //编号
    @NotNull
    private String title; //标题
    private String titlePic; //封面图片
    private String category; //分类
    private String tags; //标签
    private String author; //作者
    private String content; //内容
    private String contentMd; //留言内容-Markdown格式
    private String origin; //来源
    private String state; //状态
    private long eyeCount; //浏览量

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date publishTime; //发布时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date editTime; //最后编辑时间

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime; //创建时间

    public Article() {
    }

    public Article(String category) {
        this.category = category;
    }

    public Article(long id, String category) {
        this.id = id;
        this.category = category;
    }

    public Article(String title, String category) {
        this.title = title;
        this.category = category;
    }
}
