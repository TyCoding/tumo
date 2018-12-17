package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.Result;
import cn.tycoding.admin.dto.StatusCode;
import cn.tycoding.admin.entity.Links;
import cn.tycoding.admin.enums.ResultEnums;
import cn.tycoding.admin.service.LinksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/links")
public class LinksController {

    @Autowired
    private LinksService linksService;

    @RequestMapping(value = "/findAllCount", method = RequestMethod.GET)
    public Result findAllCount() {
        return new Result(StatusCode.SUCCESS, linksService.findAllCount());
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAll() {
        return new Result(StatusCode.SUCCESS, linksService.findAllCount());
    }

    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public Result findByPage(Links links,
                             @RequestParam(value = "pageCode", required = false) Integer pageCode,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return new Result(StatusCode.SUCCESS, linksService.findByPage(links, pageCode, pageSize));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {
        return new Result(StatusCode.SUCCESS, linksService.findById(id));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Links links) {
        try {
            linksService.save(links);
            return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody Links links) {
        try {
            linksService.update(links);
            return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody Long... ids) {
        try {
            linksService.delete(ids);
            return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        }
    }
}
