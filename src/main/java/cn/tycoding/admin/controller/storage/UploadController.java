package cn.tycoding.admin.controller.storage;

import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.utils.IdWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author TyCoding
 * @date 2018/10/19
 */
@RestController
@RequestMapping("/storage")
public class UploadController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    IdWorker idWorker = new IdWorker();

    /**
     * 文件上传
     *
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/upload")
    public ResponseCode upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws FileNotFoundException {
        try {
            //获取文件在服务器的储存位置
            File path = new File(ResourceUtils.getURL("classpath:").getPath());
            File filePath = new File(path.getAbsolutePath(), "static/upload/");
            logger.info("文件的保存路径：" + filePath.getAbsolutePath());
            if (!filePath.exists() && !filePath.isDirectory()) {
                logger.info("目录不存在，创建目录:" + filePath);
                filePath.mkdir();
            }

            //获取原始文件名称(包含格式)
            String originalFileName = file.getOriginalFilename();
            logger.info("原始文件名称：" + originalFileName);

            //获取文件类型，以最后一个`.`为标识
            String type = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
            logger.info("文件类型：" + type);

            //设置文件新名称，IdWorker生成
            String fileName = idWorker.nextId() + "." + type;
            logger.info("新文件名称：" + fileName);

            //在指定路径下创建一个文件
            File targetFile = new File(filePath, fileName);

            //将文件保存到服务器指定位置
            file.transferTo(targetFile);
            logger.info("上传成功");
            //将文件在服务器的存储路径返回

            Map map = new HashMap<>();
            map.put("name", fileName);
            map.put("url", "/upload/" + fileName);

            return ResponseCode.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }
}