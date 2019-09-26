package cn.tycoding.system.service.impl;

import cn.tycoding.common.constants.CommonConstant;
import cn.tycoding.common.constants.SiteConstant;
import cn.tycoding.common.exception.GlobalException;
import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.common.utils.TreeUtil;
import cn.tycoding.system.entity.SysComment;
import cn.tycoding.system.entity.dto.Tree;
import cn.tycoding.system.mapper.CommentMapper;
import cn.tycoding.system.service.CommentService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
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
public class CommentServiceImpl extends ServiceImpl<CommentMapper, SysComment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<SysComment> findAll() {
        return commentMapper.findAll(CommonConstant.DEFAULT_RELEASE_STATUS, new QueryPage(0, SiteConstant.COMMENT_PAGE_LIMIT));
    }

    @Override
    public IPage<SysComment> list(SysComment comment, QueryPage queryPage) {
        IPage<SysComment> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(SysComment::getId);
        queryWrapper.like(StringUtils.isNotBlank(comment.getName()), SysComment::getName, comment.getName());
        queryWrapper.like(StringUtils.isNotBlank(comment.getUrl()), SysComment::getUrl, comment.getUrl());
        return commentMapper.selectPage(page, queryWrapper);
    }

    @Override
    public Map<String, Object> findCommentsList(QueryPage queryPage, String articleId, int sort) {
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(articleId), SysComment::getArticleId, articleId);
        queryWrapper.eq(SysComment::getSort, sort);
        queryWrapper.orderByDesc(SysComment::getId);
        //这里暂时先采用：先查询所有、再分页的方式
        List<SysComment> list = commentMapper.selectList(queryWrapper);
        List<Tree<SysComment>> trees = new ArrayList<>();
        list.forEach(c -> {
            Tree<SysComment> tree = new Tree<>();
            tree.setId(c.getId());
            tree.setPId(c.getPId());
            tree.setAId(c.getArticleId());
            tree.setContent(c.getContent());
            tree.setName(c.getName());
            tree.setTarget(c.getCName());
            tree.setUrl(c.getUrl());
            tree.setDevice(c.getDevice());
            tree.setTime(c.getTime());
            tree.setSort(c.getSort());
            trees.add(tree);
        });
        Map<String, Object> map = new HashMap<>();
        try {
            List<Tree<SysComment>> treeList = TreeUtil.build(trees);

            if (treeList == null) {
                treeList = new ArrayList<>();
            }

            if (treeList.size() == 0) {
                map.put("rows", new ArrayList<>());
            } else {
                int start = (queryPage.getPage() - 1) * queryPage.getLimit();
                int end = queryPage.getPage() * queryPage.getLimit();
                if (queryPage.getPage() * queryPage.getLimit() >= treeList.size()) {
                    end = treeList.size();
                }
                map.put("rows", treeList.subList(start, end));
            }
            map.put("count", list.size());
            map.put("total", treeList.size());
            map.put("current", queryPage.getPage());
            map.put("pages", (int) Math.ceil((double) treeList.size() / (double) queryPage.getLimit()));
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
        return map;
    }

    @Override
    public int findCountByArticle(Long articleId) {
        IPage<SysComment> page = new Page<>(0, 8);
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysComment::getArticleId, articleId);
        return commentMapper.selectCount(queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysComment comment) {
        commentMapper.insert(comment);
    }

    @Override
    @Transactional
    public void update(SysComment comment) {
        commentMapper.updateById(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentMapper.deleteById(id);
    }

}
