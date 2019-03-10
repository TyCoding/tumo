package cn.tycoding.admin.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@Data
public class Category implements Serializable {

    private long id; //编号
    @NotNull
    private String name; //分类名称

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }
}
