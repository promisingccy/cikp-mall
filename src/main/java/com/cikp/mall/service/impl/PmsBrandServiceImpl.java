package com.cikp.mall.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.cikp.mall.dto.PmsBrandDto;
import com.cikp.mall.exception.Asserts;
import com.cikp.mall.mybatisFile.mapper.PmsBrandMapper;
import com.cikp.mall.mybatisFile.model.PmsBrand;
import com.cikp.mall.mybatisFile.model.PmsBrandExample;
import com.cikp.mall.service.PmsBrandService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName PmsBrandServiceImpl
 * @Description //TODO
 * @Author ccy
 * @Date 2020/12/15 16:15
 * @Version 1.0
 **/
@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper brandMapper;

    @Override
    public List<PmsBrand> listAllBrand() {
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public void createBrand(PmsBrandDto dto) {
        PmsBrand pmsBrand = new PmsBrand();
        BeanUtil.copyProperties(dto, pmsBrand);
        int count = brandMapper.insertSelective(pmsBrand);
        if(count < 1){
            Asserts.fail("操作失败");
        }
    }

    @Override
    public void updateBrand(Long id, PmsBrandDto dto) {
        PmsBrand brand = new PmsBrand();
        BeanUtil.copyProperties(dto, brand);
        brand.setId(id);
        int count = brandMapper.updateByPrimaryKeySelective(brand);
        if(count < 1){
            Asserts.fail("操作失败");
        }
    }

    @Override
    public int deleteBrand(Long id) {
        return brandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return brandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }
}