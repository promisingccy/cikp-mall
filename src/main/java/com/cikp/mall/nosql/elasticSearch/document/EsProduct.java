package com.cikp.mall.nosql.elasticSearch.document;

/**
 * @ClassName EsProduct
 * @Description //搜索中的商品信息
 * @Author ccy
 * @Date 2020/12/22 14:53
 * @Version 1.0
 **/
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Document(indexName = "product", type = "_doc",shards = 1,replicas = 0)
public class EsProduct implements Serializable {
    private static final long serialVersionUID = -1L;
    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String productSn;
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    private Long productCategoryId;
    @Field(type = FieldType.Keyword)
    private String productCategoryName;
    private String pic;
    @Field(type = FieldType.Text)
    private String name;
    @Field(type = FieldType.Text)
    private String subTitle;
    @Field(type = FieldType.Text)
    private String keywords;
    private BigDecimal price;
    private Integer sale;
    private Integer newStatus;
    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;
    @Field(type =FieldType.Nested)
    private List<EsProductAttributeValue> attrValueList;

    //省略了所有getter和setter方法
}
