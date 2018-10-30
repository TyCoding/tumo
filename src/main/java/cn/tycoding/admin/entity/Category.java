package cn.tycoding.admin.entity;

import java.io.Serializable;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
public class Category implements Serializable {

    private long id; //编号
    private String cName; //分类名称

    public Category() {
    }

    public Category(String cName) {
        this.cName = cName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", cName='" + cName + '\'' +
                '}';
    }
}
