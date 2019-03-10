package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.QueryPage;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.entity.Tags;
import cn.tycoding.admin.exception.GlobalException;
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
public class TagsController extends BaseController {

    @Autowired
    private TagsService tagsService;

    @GetMapping(value = "/findAllCount")
    public ResponseCode findAllCount() {
        return ResponseCode.success(tagsService.findAllCount());
    }

    @GetMapping(value = "/findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(tagsService.findAll());
    }

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, Tags tags) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> tagsService.findByPage(tags)));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id) {
        return ResponseCode.success(tagsService.findById(id));
    }

    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody Tags tags) {
        try {
            tagsService.save(tags);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseCode update(@RequestBody Tags tags) {
        try {
            tagsService.update(tags);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody Long... ids) {
        try {
            tagsService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
