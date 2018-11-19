package cn.tycoding.admin.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
public class Comments implements Serializable {

    private long id; //编号
    private long pId; //父级ID，给哪个留言回复
    private long cId; //子级ID，给哪个留言评论
    private String articleTitle; //文章标题
    private long articleId; //文章ID
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getpId() {
        return pId;
    }

    public void setpId(long pId) {
        this.pId = pId;
    }

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public long getArticleId() {
        return articleId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getSort() {
        return sort;
    }

    public void setSort(long sort) {
        this.sort = sort;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Comments{" +
                "id=" + id +
                ", pId=" + pId +
                ", cId=" + cId +
                ", articleTitle='" + articleTitle + '\'' +
                ", articleId=" + articleId +
                ", author='" + author + '\'' +
                ", authorId='" + authorId + '\'' +
                ", email='" + email + '\'' +
                ", content='" + content + '\'' +
                ", time=" + time +
                ", url='" + url + '\'' +
                ", sort=" + sort +
                ", state='" + state + '\'' +
                '}';
    }
}
