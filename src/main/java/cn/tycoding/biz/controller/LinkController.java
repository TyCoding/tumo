package cn.tycoding.biz.controller;

import cn.tycoding.biz.entity.SysLink;
import cn.tycoding.biz.service.LinkService;
import cn.tycoding.common.annotation.Log;
import cn.tycoding.common.controller.BaseController;
import cn.tycoding.common.exception.GlobalException;
import cn.tycoding.common.utils.QueryPage;
import cn.tycoding.common.utils.R;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author tycoding
 * @date 2020/6/27
 */
@RestController
@RequestMapping("/link")
@Api(value = "LinkController", tags = {"友链管理接口"})
public class LinkController extends BaseController {

    @Autowired
    private LinkService linkService;

    @PostMapping("/filter/list")
    public R list(@RequestBody SysLink sysLink) {
        return new R<>(linkService.list(sysLink));
    }

    @PostMapping("/list")
    public R list(@RequestBody SysLink sysLink, QueryPage queryPage) {
        return new R<>(super.getData(linkService.list(sysLink, queryPage)));
    }

    @GetMapping("/{id}")
    public R findById(@PathVariable Long id) {
        return new R<>(linkService.getById(id));
    }

    @PostMapping
    @Log("新增友链")
    public R save(@RequestBody SysLink sysLink) {
        try {
            linkService.add(sysLink);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping
    @Log("更新友链")
    public R update(@RequestBody SysLink sysLink) {
        try {
            linkService.update(sysLink);
            return new R();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Log("删除友链")
    public R delete(@PathVariable Long id) {
        try {
            linkService.delete(id);
            return new R();
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }
}
