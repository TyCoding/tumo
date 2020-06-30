package cn.tycoding.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author tycoding
 * @date 2020/6/27
 */
@Data
@TableName("tb_comment")
public class SysComment implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private Long articleId;
    private String nickname;
    private String content;
    private String email;
    private String ip;
    private String device;
    private String address;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
