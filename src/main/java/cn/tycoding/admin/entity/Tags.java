package cn.tycoding.admin.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
public class Tags implements Serializable {

    private long id; //编号
    @NotNull
    private String name; //标签名称

    public Tags() {
    }

    public Tags(String name) {
        this.name = name;
    }

    public Tags(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
