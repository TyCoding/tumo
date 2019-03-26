package cn.tycoding.admin.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tycoding
 * @date 2019-03-09
 */
@Data
public class QueryPage implements Serializable {

    private int pageCode;
    private int pageSize;
}
