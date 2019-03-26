package cn.tycoding.admin.controller.storage.entity;

import java.io.Serializable;

/**
 * OS 文件相关属性
 * @auther TyCoding
 * @date 2018/12/31
 */
public class Storage implements Serializable {

    private String key; //对象KEY
    private String name; //对象名称
    private String type; //对象类型
    private long size; //对象大小
    private String url; //对象链接

    public Storage() {
    }

    public Storage(String key, String name, String type, long size, String url) {
        this.key = key;
        this.name = name;
        this.type = type;
        this.size = size;
        this.url = url;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
