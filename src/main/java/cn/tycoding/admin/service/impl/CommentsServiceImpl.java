package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.CommentsDTO;
import cn.tycoding.admin.entity.Comments;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.CommentsMapper;
import cn.tycoding.admin.service.CommentsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther TyCoding
 * @date 2018/10/17
 */
@Service
@SuppressWarnings("all")
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsMapper commentsMapper;

    @Override
    public Long findAllCount() {
        return commentsMapper.findAllCount();
    }

    @Override
    public List<Comments> findAll() {
        return commentsMapper.findAll();
    }

    @Override
    public List<Comments> findByPage(Comments comments) {
        return commentsMapper.findByPage(comments);
    }

    @Override
    public Map<String, Object> findCommentsList(Integer pageCode, Integer pageSize, Integer articleId, Integer sort) {
        Map<String, Object> map = new HashMap<>();
        PageHelper.startPage(pageCode, pageSize);
        Page<Comments> page = commentsMapper.findAllId(articleId, sort);
        List<Comments> list = commentsMapper.findCommentsList(articleId, sort);
        List<CommentsDTO> commentsDTOS = new ArrayList<CommentsDTO>();

        /**
         * 封装结果类型结构：
         *      [{{Comments-Parent}, [{Comments-Children}, {Comments-Children}...]}, {{}, [{}, {}, {}...]}]
         */
        list.forEach(comments -> {
            List<Comments> commentsList = new ArrayList<Comments>();
            if (comments.getPId() == 0 && comments.getCId() == 0) {
                //说明是顶层的文章留言信息
                list.forEach(parent -> {
                    if (parent.getPId() != 0) {
                        if (parent.getPId() == comments.getId()) {
                            //说明属于当前父节点
                            commentsList.add(parent);
                        }
                    }
                });
                commentsDTOS.add(new CommentsDTO(comments, commentsList));
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
            return commentsMapper.findCountByArticleId(articleId);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    public Comments findById(Long id) {
        if (!id.equals(null) && id != 0) {
            return commentsMapper.findById(id);
        } else {
            throw new GlobalException("参数错误");
        }
    }

    @Override
    public void save(Comments comments) {
        try {
            commentsMapper.save(comments);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void update(Comments comments) {
        if (comments.getId() != 0) {
            try {
                commentsMapper.update(comments);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public void delete(Long... ids) {
        if (ids.length > 0 && !ids.equals(null)) {
            try {
                for (long id : ids) {
                    commentsMapper.delete(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }


}
