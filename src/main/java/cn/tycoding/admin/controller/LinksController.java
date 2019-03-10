package cn.tycoding.admin.controller;

import cn.tycoding.admin.dto.QueryPage;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.entity.Links;
import cn.tycoding.admin.exception.GlobalException;
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
public class LinksController extends BaseController {

    @Autowired
    private LinksService linksService;

    @GetMapping(value = "/findAllCount")
    public ResponseCode findAllCount() {
        return ResponseCode.success(linksService.findAllCount());
    }

    @GetMapping(value = "/findAll")
    public ResponseCode findAll() {
        return ResponseCode.success(linksService.findAllCount());
    }

    @PostMapping(value = "/findByPage")
    public ResponseCode findByPage(QueryPage queryPage, Links links) {
        return ResponseCode.success(super.selectByPageNumSize(queryPage, () -> linksService.findByPage(links)));
    }

    @GetMapping(value = "/findById")
    public ResponseCode findById(@RequestParam("id") Long id) {
        return ResponseCode.success(linksService.findById(id));
    }

    @PostMapping(value = "/save")
    public ResponseCode save(@RequestBody Links links) {
        try {
            linksService.save(links);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PutMapping(value = "/update")
    public ResponseCode update(@RequestBody Links links) {
        try {
            linksService.update(links);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    @PostMapping(value = "/delete")
    public ResponseCode delete(@RequestBody Long... ids) {
        try {
            linksService.delete(ids);
            return ResponseCode.success();
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}
