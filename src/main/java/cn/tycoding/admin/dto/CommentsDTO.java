package cn.tycoding.admin.dto;

import cn.tycoding.admin.entity.Comments;

import java.io.Serializable;
import java.util.List;

/**
 * 封装评论信息的DTO
 *
 * @auther TyCoding
 * @date 2018/11/2
 */
public class CommentsDTO implements Serializable {

    private Comments parent; //父级留言信息
    private List<Comments> childrenList; //所有子级回复、评论列表

    public CommentsDTO() {
    }

    public CommentsDTO(Comments parent, List<Comments> childrenList) {
        this.parent = parent;
        this.childrenList = childrenList;
    }

    public Comments getParent() {
        return parent;
    }

    public void setParent(Comments parent) {
        this.parent = parent;
    }

    public List<Comments> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<Comments> childrenList) {
        this.childrenList = childrenList;
    }

    @Override
    public String toString() {
        return "CommentsDTO{" +
                "parent=" + parent +
                ", childrenList=" + childrenList +
                '}';
    }
}
