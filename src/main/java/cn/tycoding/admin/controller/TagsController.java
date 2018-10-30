package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.ModifyResult;
import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.enums.ModifyEnums;
import cn.tycoding.admin.service.TagsService;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@Controller
@SuppressWarnings("all")
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @ResponseBody
    @RequestMapping("/findAllCount")
    public Long findAllCount(){
        return tagsService.findAllCount();
    }

    /**
     * 查询所有
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findAll")
    public List<Tags> findAll() {
        return tagsService.findAll();
    }

    /**
     * 分页查询
     *
     * @param comments  查询条件
     * @param pageCode 当前页
     * @param pageSize 每页显示的记录数
     * @return
     */
    @ResponseBody
    @RequestMapping("/findByPage")
    public PageBean findByPage(Tags tags,
                                  @RequestParam(value = "pageCode", required = false) Integer pageCode,
                                  @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return tagsService.findByPage(tags, pageCode, pageSize);
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/findById")
    public Tags findById(@RequestParam("id") Long id) {
        return tagsService.findById(id);
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/save")
    public ModifyResult save(@RequestBody Tags tags) {
        try {
            tagsService.save(tags);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/update")
    public ModifyResult update(@RequestBody Tags tags) {
        try {
            tagsService.update(tags);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/delete")
    public ModifyResult delete(@RequestBody Long... ids) {
        try {
            tagsService.delete(ids);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }
}
