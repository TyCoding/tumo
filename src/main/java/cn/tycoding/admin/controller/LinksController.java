package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.ModifyResult;
import cn.tycoding.admin.dto.PageBean;
import cn.tycoding.admin.entity.Links;
import cn.tycoding.admin.enums.ModifyEnums;
import cn.tycoding.admin.service.LinksService;
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
@RequestMapping("/links")
public class LinksController {

    @Autowired
    private LinksService linksService;

    @ResponseBody
    @RequestMapping("/findAllCount")
    public Long findAllCount(){
        return linksService.findAllCount();
    }

    /**
     * 查询所有
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/findAll")
    public List<Links> findAll() {
        return linksService.findAll();
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
    public PageBean findByPage(Links links,
                               @RequestParam(value = "pageCode", required = false) Integer pageCode,
                               @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return linksService.findByPage(links, pageCode, pageSize);
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/findById")
    public Links findById(@RequestParam("id") Long id) {
        return linksService.findById(id);
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/save")
    public ModifyResult save(@RequestBody Links links) {
        try {
            linksService.save(links);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }

    @RequiresUser
    @ResponseBody
    @RequestMapping("/update")
    public ModifyResult update(@RequestBody Links links) {
        try {
            linksService.update(links);
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
            linksService.delete(ids);
            return new ModifyResult(true, ModifyEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModifyResult(false, e.getMessage());
        }
    }
}
