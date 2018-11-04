package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.CommentsDTO;
import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Comments;
import cn.tycoding.admin.enums.ModifyEnums;
import cn.tycoding.admin.exception.ModifyException;
import cn.tycoding.admin.mapper.CommentsMapper;
import cn.tycoding.admin.service.CommentsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public PageBean findByPage(Comments comments, int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);

        Page<Comments> page = commentsMapper.findByPage(comments);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public PageBean findByPageForFilter(int pageCode, int pageSize, int articleId) {
        PageHelper.startPage(pageCode, pageSize);

        Page<Comments> page = commentsMapper.findByPageForFilter(articleId);
        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public PageBean findCommentsList(int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);

        Page<Comments> page = commentsMapper.findAllId();

        List<Comments> list = commentsMapper.findCommentsList();

        List<CommentsDTO> commentsDTOS = new ArrayList<CommentsDTO>();

        /**
         * 封装结果类型结构：
         *      [{{Comments-Parent}, [{Comments-Children}, {Comments-Children}...]}, {{}, [{}, {}, {}...]}]
         */
        for (Comments comments : list) {
            List<Comments> commentsList = new ArrayList<Comments>();
            if (comments.getpId() == 0 && comments.getcId() == 0) {
                //说明是顶层的文章留言信息
                for (Comments parent : list) {
                    if (parent.getpId() != 0) {
                        if (parent.getpId() == comments.getId()) {
                            //说明属于当前父节点
                            commentsList.add(parent);
                        }
                    }
                }
                commentsDTOS.add(new CommentsDTO(comments, commentsList));
            }
        }
        if (page.getTotal() < (pageCode * pageSize) - 1) {
            return new PageBean(page.getTotal(), commentsDTOS.subList((pageCode - 1) * pageSize, commentsDTOS.size()));
        } else {
            return new PageBean(page.getTotal(), commentsDTOS.subList((pageCode - 1) * pageSize, (pageCode * pageSize) - 1));
        }
    }

    @Override
    public Long findCountByArticle(long articleId) {
        return commentsMapper.findCountByArticleId(articleId);
    }

    @Override
    public Comments findById(long id) {
        return commentsMapper.findById(id);
    }

    @Override
    public void save(Comments comments) {
        try {
            int saveCount = commentsMapper.save(comments);
            if (saveCount <= 0) {
                throw new ModifyException(ModifyEnums.INNER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.ERROR);
        }
    }

    @Override
    public void update(Comments comments) {
        try {
            int updateCount = commentsMapper.update(comments);
            if (updateCount <= 0) {
                throw new ModifyException(ModifyEnums.INNER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.ERROR);
        }
    }

    @Override
    public void delete(Long... ids) {
        try {
            for (long id : ids) {
                int deleteCount = commentsMapper.delete(id);
                if (deleteCount <= 0) {
                    throw new ModifyException(ModifyEnums.INNER_ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ModifyException(ModifyEnums.ERROR);
        }
    }


}
