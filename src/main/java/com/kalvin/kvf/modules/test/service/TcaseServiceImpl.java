package com.kalvin.kvf.modules.test.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.kalvin.kvf.modules.test.entity.Tcase;
import com.kalvin.kvf.modules.test.mapper.TcaseMapper;

import java.util.List;

/**
 * <p>
 * 测试用例表 服务实现类
 * </p>
 * @since 2024-03-26 23:57:32
 */
@Service
public class TcaseServiceImpl extends ServiceImpl<TcaseMapper, Tcase> implements TcaseService {

    @Override
    public Page<Tcase> listTcasePage(Tcase tcase) {
        Page<Tcase> page = new Page<>(tcase.getCurrent(), tcase.getSize());
        List<Tcase> tcases = baseMapper.selectTcaseList(tcase, page);
        return page.setRecords(tcases);
    }

}
