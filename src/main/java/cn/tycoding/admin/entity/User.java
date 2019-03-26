package cn.tycoding.admin.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author  TyCoding
 * @date 2018/10/17
 */
@Data
@Table(name = "tb_user")
public class User implements Serializable {

    @Id
    private Long id;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String salt;
    private String avatar;
    private String introduce;
    private String remark;

    @Transient
    private String checkPass;
}
