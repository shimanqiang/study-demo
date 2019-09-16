package me.shimanqiang.mybatis.mapper;

import me.shimanqiang.mybatis.po.TestPo;

/**
 * @author shimanqiang
 * @since 2019/9/6 10:37
 */
public interface TestMapper {
    TestPo selectByPrimaryKey(Long id);
}
