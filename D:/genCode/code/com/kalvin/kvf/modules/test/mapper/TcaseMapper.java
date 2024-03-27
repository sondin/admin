package com.kalvin.kvf.modules.test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import com.kalvin.kvf.modules.test.entity.Tcase;

import java.util.List;

/**
 * <p>
 * 测试用例表 Mapper 接口
 * </p>
 * @since 2024-03-26 23:57:32
 */
public interface TcaseMapper extends BaseMapper<Tcase> {

    /**
     * 查询列表(分页)
     * @param tcase 查询参数
     * @param page 分页参数
     * @return list
     */
    List<Tcase> selectTcaseList(@Param("tcase") Tcase tcase, IPage page);

}
