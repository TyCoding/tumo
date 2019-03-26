package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.CommentsDTO;
import cn.tycoding.admin.entity.Article;
import cn.tycoding.admin.entity.Comments;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.CommentsMapper;
import cn.tycoding.admin.service.CommentsService;
import cn.tycoding.common.service.impl.BaseServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@Service
@SuppressWarnings("all")
public class CommentsServiceImpl extends BaseServiceImpl<Comments> implements CommentsService {

    @Autowired
    private CommentsMapper commentMapper;

    @Override
    public Long findAllCount() {
        return Long.valueOf(commentMapper.selectCount(new Comments()));
    }

    @Override
    public List<Comments> findAll() {
        Example example = new Example(Article.class);
        example.setOrderByClause("`id` desc");
        return commentMapper.selectByExampleAndRowBounds(example, new RowBounds(0, 8));
    }

    @Override
    public List<Comments> findByPage(Comments comment) {
        Example example = new Example(Comments.class);
        if (!StringUtils.isEmpty(comment.getName())) {
            example.createCriteria().andLike("name", "%" + comment.getName() + "%");
        }
        return commentMapper.selectByExample(example);
    }

    @Override
    public Map<String, Object> findCommentsList(Integer pageCode, Integer pageSize, Integer articleId, Integer sort) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageCode, pageSize);
        Page<Comments> page = commentMapper.findAllId(articleId, sort);
        List<Comments> list = commentMapper.findCommentsList(articleId, sort);
        List<CommentsDTO> commentsDTOS = new ArrayList<CommentsDTO>();

        /**
         * 封装结果类型结构：
         *      [{{Comments-Parent}, [{Comments-Children}, {Comments-Children}...]}, {{}, [{}, {}, {}...]}]
         */
        list.forEach(comments -> {
            List<Comments> commentList = new ArrayList<Comments>();
            if (comments.getPId() == 0 && comments.getCId() == 0) {
                //说明是顶层的文章留言信息
                list.forEach(parent -> {
                    if (parent.getPId() != 0) {
                        if (parent.getPId().equals(comments.getId())) {
                            //说明属于当前父节点
                            commentList.add(parent);
                        }
                    }
                });
                commentsDTOS.add(new CommentsDTO(comments, commentList));
            }
        });
        if (page.getTotal() < (pageCode * pageSize) - 1) {
            map.put("total", page.getTotal());
            map.put("rows", commentsDTOS.subList((pageCode - 1) * pageSize, commentsDTOS.size()));
            return map;
        } else {
            map.put("total", page.getTotal());
            map.put("rows", commentsDTOS.subList((pageCode - 1) * pageSize, (pageCode * pageSize) - 1));
            return map;
        }
    }

    @Override
    public Long findCountByArticle(Long articleId) {
        if (!articleId.equals(null) && articleId != 0) {
            Comments comment = new Comments();
            comment.setArticleId(articleId);
            return Long.valueOf(commentMapper.selectCount(comment));
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    public Comments findById(Long id) {
        if (!id.equals(null) && id != 0) {
            return commentMapper.selectByPrimaryKey(id);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    public void save(Comments comment) {
        try {
            commentMapper.insert(comment);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Comments comment) {
        if (comment.getId() != 0) {
            try {
                this.updateNotNull(comment);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void delete(List<Long> ids) {
        if (!ids.isEmpty()) {
            try {
                this.batchDelete(ids, "id", Comments.class);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }


}
