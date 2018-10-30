package cn.tycoding.admin.entity;

import java.io.Serializable;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
public class Tags implements Serializable {

    private long id; //编号
    private String tName; //标签名称

    public Tags() {
    }

    public Tags(String tName) {
        this.tName = tName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", tName='" + tName + '\'' +
                '}';
    }
}
