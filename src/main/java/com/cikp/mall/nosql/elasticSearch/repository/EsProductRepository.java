package com.cikp.mall.nosql.elasticSearch.repository;

/**
 * @ClassName EsProductRepository
 * @Description //商品ES操作类
 * @Author ccy
 * @Date 2020/12/22 14:55
 * @Version 1.0
 **/
import com.cikp.mall.nosql.elasticSearch.document.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {
    /**
     * 搜索查询
     *
     * @param name              商品名称
     * @param subTitle          商品标题
     * @param keywords          商品关键字
     * @param page              分页信息
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);

}
