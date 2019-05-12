package cn.tycoding.admin.controller.storage;

import cn.tycoding.admin.controller.storage.entity.Storage;
import cn.tycoding.admin.dto.ResponseCode;
import cn.tycoding.admin.exception.GlobalException;
import cn.tycoding.admin.utils.IdWorker;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 对七牛云对象储存的操作接口
 * 关于七牛云开放的Java-API文档请看：https://developer.qiniu.com/kodo/sdk/1662/java-sdk-6
 * 文件上传的DEMO请看 /test/java/cn/tycoding/UploadDemo.java 测试类。
 * 请先到七牛云个人控制中心查看Access key 和 Secret Key
 *
 * @auther TyCoding
 * @date 2018/12/16
 */
@RestController
@RequestMapping("/storage/qiniu")
public class QiniuController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    IdWorker idWorker = new IdWorker(); //分布式ID生成器，用于设定上传文件的名称

    //设置好账号的ACCESS_KEY和SECRET_KEY
    @Value("${qiniu.access_key}")
    private String ACCESS_KEY;

    @Value("${qiniu.secret_key}")
    private String SECRET_KEY;

    //要上传的空间
    @Value("${qiniu.bucket_name}")
    private String BUCKETNAME;

    //个人七牛云对象储存外链域名地址
    @Value("${qiniu.url}")
    private String url;

    /**
     * 获取七牛云个人储存空间域名地址
     *
     * @return
     */
    @GetMapping(value = "/domain")
    public ResponseCode domain() {
        return ResponseCode.success(url);
    }

    /**
     * 七牛云开放API接口：获取空间文件列表接口，详细的文档请看：https://developer.qiniu.com/kodo/sdk/1239/java#rs-list
     *
     * @return
     */
    @GetMapping(value = "/list")
    public ResponseCode list() {
        try {
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone0());
            //...其他参数参考类注释
            Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
            BucketManager bucketManager = new BucketManager(auth, cfg);
            //文件名前缀
            String prefix = "";
            //每次迭代的长度限制，最大1000，推荐值 1000
            int limit = 1000;
            //指定目录分隔符，列出所有公共前缀（模拟列出目录效果）。缺省值为空字符串
            String delimiter = "";
            //列举空间文件列表
            BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(BUCKETNAME, prefix, limit, delimiter);
            List<Storage> list = new ArrayList<>();
            while (fileListIterator.hasNext()) {
                //处理获取的file list结果
                FileInfo[] items = fileListIterator.next();
                for (FileInfo item : items) {
                    Storage storage = new Storage(item.hash, item.key, item.mimeType, item.fsize, url + item.key);
                    list.add(storage);
                }
            }
            Map map = new HashMap();
            map.put("total", list.size());
            map.put("rows", list);
            return ResponseCode.success(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 文件上传接口，调用七牛云开放的API
     *
     * @param file 上传的文件
     * @return 返回成功上传的文件：1.名称、2.外链地址
     * <p>
     * 注意：我们指定分布式ID生成器IdWorker工具类生成的随机数作为文件名称；
     * 外链：如果你是第一次使用七牛云，你需要先了解一下如何为自己的七牛云对象储存配置外链地址，因为官方提供的测试外链有时间限制
     * <p>
     * 此部分我后续会写文档讲解
     */
    @RequestMapping("/upload")
    @RequiresPermissions("qiniu:upload")
    public ResponseCode upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
        if (!file.isEmpty()) {

            //上传文件路径
            String FilePath = "";
            //上传到七牛后保存的文件名
            String key = idWorker.nextId() + "";

            try {
                //将MutipartFile对象转换为File对象，相当于需要以本地作为缓冲区暂时储存文件
                //获取文件在服务器的储存位置
                File path = new File(ResourceUtils.getURL("classpath:").getPath());
                File filePath = new File(path.getAbsolutePath(), "upload/");
                if (!filePath.exists() && !filePath.isDirectory()) {
                    logger.info("目录不存在，创建目录===========>" + filePath);
                    filePath.mkdir();
                }
                String filename = file.getOriginalFilename(); //获取原始文件名称
                key += filename.substring(filename.lastIndexOf(".")); //获取文件类型

                File localFile = new File(filePath, key);
                file.transferTo(localFile); //写入磁盘
                logger.info("文件原始路径=========>" + filePath);
                logger.info("新文件名称===========>" + key);
                FilePath = filePath + "/" + key;

                //密钥配置
                Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
                //第二种方式: 自动识别要上传的空间(bucket)的存储区域是华东、华北、华南。
                Zone z = Zone.autoZone();
                Configuration c = new Configuration(z);
                //创建上传对象
                UploadManager uploadManager = new UploadManager(c);
                //调用put方法上传
                Response res = uploadManager.put(FilePath, key, auth.uploadToken(BUCKETNAME));
                //打印返回的信息
                //res.bodyString() 返回数据格式： {"hash":"FlHXdiArTIzeNy94EOxzlCQC7pDS","key":"1074213185631420416.png"}
                logger.info("文件上传成功============>" + res.bodyString());
                Map map = new HashMap<>();
                map.put("name", key);
                map.put("url", url + key);

                if (localFile.exists()) {
                    localFile.delete(); //删除本地缓存的文件
                }
                return ResponseCode.success(map);
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("文件上传失败============>" + e.getMessage());
                throw new GlobalException(e.getMessage());
            }
        }
        return ResponseCode.error();
    }

    /**
     * 文件下载
     *
     * @param name 文件名称
     * @return 返回文件在七牛云储存的地址：外链/文件名  前端处理下载
     */
    @RequestMapping(value = "/download")
    public ResponseEntity<byte[]> download(@RequestParam("name") String name, HttpServletResponse response) {
        try {
            String encodedFileName = URLEncoder.encode(name, "utf-8"); //获取文件名，防止乱码
            String fileUrl = String.format("%s%s", url, encodedFileName); //拼接得到文件的连接地址
            //获取外部文件流
            URL url = new URL(fileUrl);
            //打开一个连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(3 * 1000);

            //获取输入流
            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); //创建一个字节数组缓冲区对象
            byte[] buffer = new byte[1024];

            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                //三个参数：b:字节数组 off:起始位置 len:写入字节长度
                outputStream.write(buffer, 0, len); //将文件输入流中的字节一次read读取并写入到字节缓冲区对象ByteArrayOutputStream中
            }

            //设置请求头格式
            HttpHeaders headers = new HttpHeaders();
            //告诉浏览器以"attachment"方式打开文件
            headers.setContentDispositionFormData("attachment", fileUrl);
            //设置请求头的媒体格式类型为 application/octet-stream(二进制流数据)
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            System.out.println(fileUrl);
            return new ResponseEntity<byte[]>(outputStream.toByteArray(), headers, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 七牛云开放API接口: 文件删除
     *
     * @param name 删除的文件名称，在七牛云API中，对应：key
     * @return
     */
    @RequestMapping("/delete")
    @RequiresPermissions("qiniu:delete")
    public ResponseCode delete(@RequestParam("name") String name) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(BUCKETNAME, name);
            return ResponseCode.success();
        } catch (QiniuException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 七牛云开放API接口：文件名称更新
     *
     * @param oldname 文件原始名称
     * @param newname 文件新名称
     * @return
     */
    @GetMapping("/update")
    public ResponseCode update(@RequestParam("oldname") String oldname, @RequestParam("newname") String newname) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.move(BUCKETNAME, oldname, BUCKETNAME, newname);
            return ResponseCode.success();
        } catch (QiniuException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

    /**
     * 七牛云开放API接口：单个文件查询
     *
     * @param name 要查询的文件名称
     * @return
     */
    @GetMapping("/find")
    public ResponseCode find(@RequestParam("name") String name) {
        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            FileInfo fileInfo = bucketManager.stat(BUCKETNAME, name);
            Storage storage = new Storage(fileInfo.hash, name, fileInfo.mimeType, fileInfo.fsize, url + name);
            List<Storage> list = new ArrayList<>();
            list.add(storage);
            return ResponseCode.success(list);
        } catch (QiniuException e) {
            e.printStackTrace();
            throw new GlobalException(e.getMessage());
        }
    }

}
