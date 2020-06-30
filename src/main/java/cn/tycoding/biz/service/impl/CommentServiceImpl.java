package cn.tycoding.biz.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.tycoding.biz.entity.SysComment;
import cn.tycoding.biz.mapper.CommentMapper;
import cn.tycoding.biz.service.CommentService;
import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.common.utils.SplineChart;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tycoding
 * @date 2020/6/27
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, SysComment> implements CommentService {

    @Autowired
    private CommentMapper commentMapper;

    @Override
    public List<SysComment> findByArticleId(Long id) {
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SysComment::getArticleId, id);
        queryWrapper.orderByDesc(SysComment::getId);
        return commentMapper.selectList(queryWrapper);
    }

    @Override
    public IPage<SysComment> list(SysComment comment, QueryPage queryPage) {
        IPage<SysComment> page = new Page<>(queryPage.getPage(), queryPage.getLimit());
        LambdaQueryWrapper<SysComment> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(comment.getContent()), SysComment::getContent, comment.getContent());
        queryWrapper.orderByDesc(SysComment::getId);
        return commentMapper.selectPage(page, queryWrapper);
    }

    @Override
    @Transactional
    public void add(SysComment comment) {
        commentMapper.insert(comment);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        commentMapper.deleteById(id);
    }

    @Override
    public List<Long[]> chart() {
        List<Long[]> splineChart = new ArrayList<>();
        List<SplineChart> splineChartList = commentMapper.chart();
        if (splineChartList.size() > 0) {
            splineChartList.forEach(item -> {
                Long[] d = {DateUtil.parse(item.getTime(), "yyyy-MM-dd").getTime(), item.getNum()};
                splineChart.add(d);
            });
        }
        return splineChart;
    }
}
