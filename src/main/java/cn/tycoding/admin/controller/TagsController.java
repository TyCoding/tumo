package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.Result;
import cn.tycoding.admin.dto.StatusCode;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.enums.ResultEnums;
import cn.tycoding.admin.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @auther TyCoding
 * @date 2018/10/18
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/tags")
public class TagsController {

    @Autowired
    private TagsService tagsService;

    @RequestMapping(value = "/findAllCount", method = RequestMethod.GET)
    public Result findAllCount(){
        return new Result(StatusCode.SUCCESS, tagsService.findAllCount());
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Result findAll() {
        return new Result(StatusCode.SUCCESS, tagsService.findAll());
    }

    @RequestMapping(value = "/findByPage", method = RequestMethod.POST)
    public Result findByPage(Tags tags,
                             @RequestParam(value = "pageCode", required = false) Integer pageCode,
                             @RequestParam(value = "pageSize", required = false) Integer pageSize) {
        return new Result(StatusCode.SUCCESS, tagsService.findByPage(tags, pageCode, pageSize));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET)
    public Result findById(@RequestParam("id") Long id) {
        return new Result(StatusCode.SUCCESS, tagsService.findById(id));
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody Tags tags) {
        try {
            tagsService.save(tags);
            return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@RequestBody Tags tags) {
        try {
            tagsService.update(tags);
            return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Result delete(@RequestBody Long... ids) {
        try {
            tagsService.delete(ids);
            return new Result(StatusCode.SUCCESS, ResultEnums.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(StatusCode.ERROR, ResultEnums.ERROR);
        }
    }
}
