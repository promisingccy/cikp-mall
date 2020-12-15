package com.cikp.mall.service;

import com.cikp.mall.mybatisFile.model.PmsBrand;

import java.util.List;

/**
 * @ClassName PmsBrandService
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/15 16:14
 * @Version 1.0
 **/
public interface PmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
