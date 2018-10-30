package cn.tycoding.admin.entity;

import java.io.Serializable;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
public class Links implements Serializable {

    private long id; //编号
    private String lName; //连接名称
    private String url; //连接URL

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Links{" +
                "id=" + id +
                ", lName='" + lName + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
