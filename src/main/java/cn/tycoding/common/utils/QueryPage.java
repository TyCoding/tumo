package cn.tycoding.common.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 分页参数定义
 *
 * @author tycoding
 * @date 2020/6/27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QueryPage implements Serializable {

    /**
     * 当前页
     */
    private int page;

    /**
     * 每页的记录数
     */
    private int limit;
}
