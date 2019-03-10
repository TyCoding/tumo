package cn.tycoding.admin.service.impl;

import cn.tycoding.admin.dto.ArticleArchives;
import cn.tycoding.admin.entity.*;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.mapper.ArticleMapper;
import cn.tycoding.admin.service.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @auther TyCoding
 * @date 2018/10/16
 */
@Service
@SuppressWarnings("all")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private TagsService tagsService;

    @Autowired
    private ArticleCategoryService articleCategoryService;

    @Autowired
    private ArticleTagsService articleTagsService;

    @Override
    public Long findAllCount() {
        return articleMapper.findAllCount();
    }

    @Override
    public List<Article> findAll() {
        return articleMapper.findAll();
    }

    @Override
    public List<Article> findByPage(Article article) {
        List<Article> articleList = articleMapper.findByPage(article);
        findInit(articleList);
        return articleList;
    }

    private void findInit(List<Article> list) {
        if (!list.isEmpty()) {
            list.forEach(article -> {
                List<Category> categoryList = categoryService.findByArticleId(article.getId());
                if (categoryList.size() > 0) {
                    article.setCategory(categoryList.get(0).getName());
                }
                List<Tags> tagsList = tagsService.findByArticleId(article.getId());
                List<String> stringList = new ArrayList<>();
                tagsList.forEach(tags -> {
                    stringList.add(tags.getName());
                });
                article.setTags(JSON.toJSONString(tagsList));
            });
        }
    }

    @Override
    public Map<String, Object> findByPageForSite(int pageCode, int pageSize) {
        PageHelper.startPage(pageCode, pageSize);
        Page<Article> page = articleMapper.findByPageForSite();
        List<Article> articleList = page.getResult();
        findInit(articleList);
        Map<String, Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("data", articleList);
        return map;
    }

    @Override
    public Article findById(Long id) {
        if (!id.equals(null) && id != 0) {
            Article article = articleMapper.findById(id);
            List<Article> articleList = new ArrayList<>();
            articleList.add(article);
            findInit(articleList);
            return article;
        } else {
            return new Article();
        }
    }

    @Override
    @Transactional
    public void save(Article article) {
        try {
            if (article.getState() == "1") {
                article.setPublishTime(new Date());
            }
            article.setEditTime(new Date());
            articleMapper.save(article);
            article.setId(articleMapper.getLastId());
            updateArticleCategoryTags(article);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 更新文章-分类-标签，三表间的关联
     *
     * @param article
     * @param id
     */
    @Transactional
    private void updateArticleCategoryTags(Article article) {
        if (article.getId() != 0) {
            if (article.getCategory() != null) {
                //证明新插入的文章有分类信息，将这个文章分类保存到分类表中
                categoryService.save(new Category(article.getCategory()));

                //保存了分类信息再保存分类-文章的关联信息
                Category category = categoryService.findByName(article.getCategory());
                articleCategoryService.save(new ArticleCategory(article.getId(), category.getId()));
            }
            if (article.getTags() != null) {
                //证明新插入的文章有标签数据，将标签数据保存到标签表中
                List<String> list = (List) JSONArray.parse(article.getTags()); //前端传来的标签是JSON字符串格式的标签名称
                if (list.size() > 0) {
                    list.forEach(name -> {
                        tagsService.save(new Tags(name));
                        Tags tags = tagsService.findByName(name); //因为标签是多个的，需要依次将标签信息保存到标签表中

                        if (tags != null) {
                            //说明该标签插入成功或已存在，建立标签-文章关联信息
                            articleTagsService.save(new ArticleTags(article.getId(), tags.getId()));
                        }
                    });
                }
            }
        }
    }

    @Override
    @Transactional
    public void update(Article article) {
        try {
            articleMapper.update(article);
            updateArticleCategoryTags(article);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public void delete(Long... ids) {
        if (ids.length > 0 && !ids.equals(null)) {
            try {
                for (long id : ids) {
                    articleMapper.delete(id);
                    //删除文章-分类表的关联
                    articleCategoryService.deleteByArticleId(id);
                    //删除文章-标签表的关联
                    articleTagsService.deleteByArticleId(id);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }

    @Override
    public List<Article> findByCategory(String category) {
        return articleMapper.findByCategory(category);
    }

    @Override
    public List<ArticleArchives> findArchives() {
        List<ArticleArchives> articleArchivesList = new ArrayList<ArticleArchives>();
        try {
            List<String> dates = articleMapper.findArchivesDates();
            dates.forEach(date -> {
                List<Article> articleList = articleMapper.findArchivesByDate(date);
                ArticleArchives articleArchives = new ArticleArchives(date, articleList);
                articleArchivesList.add(articleArchives);
            });
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
        return articleArchivesList;
    }

    @Override
    public List<Article> findFuzzyByTitle(String title) {
        if (!title.isEmpty()) {
            return articleMapper.findFuzzyByTitle(title);
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    @Transactional
    public void addEyeCount(Long id) {
        if (!id.equals(null) && id != 0) {
            try {
                articleMapper.addEyeCount(id);
            } catch (Exception e) {
                e.printStackTrace();
                throw new GlobalException(e.getMessage());
            }
        }
    }
}
