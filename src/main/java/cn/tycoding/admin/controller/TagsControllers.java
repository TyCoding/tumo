package cn.tycoding.admin.controller;

import cn.tycoding.admin.annotation.Log;
import cn.tycoding.admin.dto.QueryPage;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.service.TagsService;
import cn.tycoding.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author TyCoding
 * @date 2018/10/18
 */
@RestController
@SuppressWarnings("all")
@RequestMapping("/tags")
public class TagsControllers extends BaseController {

    @Autowired
    private TagsService tagService;

    @GetMapping(value = "/findAllCount")
    public ResponseCode findAllCount() {
        return ResponseCode.success(tagService.findAllCount());
    }

    @GetMapping(value = "/findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(tagService.findAll());
    }

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, Tags tag) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> tagService.findByPage(tag)));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id) {
        return ResponseCode.success(tagService.findById(id));
    }

    @PostMapping(value = "/save")
    @Log("新增标签")
    public ResponseCode save(@RequestBody Tags tag) {
        try {
            tagService.save(tag);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    @Log("更新标签")
    public ResponseCode update(@RequestBody Tags tag) {
        try {
            tagService.update(tag);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    @Log("删除标签")
    public ResponseCode delete(@RequestBody List<Long> ids) {
        try {
            tagService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
