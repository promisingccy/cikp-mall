package com.cikp.mall.dao;

/**
 * @ClassName EsProductDao
 * @Description //搜索系统中的商品管理自定义Dao
 * @Author ccy
 * @Date 2020/12/22 15:05
 * @Version 1.0
 **/
import com.cikp.mall.nosql.elasticSearch.document.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}