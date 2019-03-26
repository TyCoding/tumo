package cn.tycoding.admin.entity;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author TyCoding
 * @date 2018/10/17
 */
@Data
@Table(name = "tb_links")
public class Links implements Serializable {

    @Id
    private Long id;
    @NotNull
    private String name;
    private String url;
}
