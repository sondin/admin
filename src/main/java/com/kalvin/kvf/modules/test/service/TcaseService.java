package com.kalvin.kvf.modules.test.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.kalvin.kvf.modules.test.entity.Tcase;

/**
 * <p>
 * 测试用例表 服务类
 * </p>
 * @since 2024-03-26 23:57:32
 */
public interface TcaseService extends IService<Tcase> {

    /**
     * 获取列表。分页
     * @param tcase 查询参数
     * @return page
     */
    Page<Tcase> listTcasePage(Tcase tcase);

}
