package cn.tycoding.admin.config;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author tycoding
 * @date 2019-03-25
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
