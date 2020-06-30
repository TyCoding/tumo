package cn.tycoding.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息实体类
 *
 * @author tycoding
 * @date 2020/6/27
 */
@Data
@Accessors(chain = true)
@TableName(value = "tb_user")
public class SysUser implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;
    private String password;
    private String avatar;
    private String email;
    private String des;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
}
