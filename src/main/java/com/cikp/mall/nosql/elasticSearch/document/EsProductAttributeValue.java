package com.cikp.mall.nosql.elasticSearch.document;

/**
 * @ClassName EsProductAttributeValue
 * @Description //搜索中的商品属性信息
 * @Author ccy
 * @Date 2020/12/22 15:06
 * @Version 1.0
 **/
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

@Data
public class EsProductAttributeValue implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;
    private Long productAttributeId;
    //属性值
    @Field(type = FieldType.Keyword)
    private String value;
    //属性参数：0->规格；1->参数
    private Integer type;
    //属性名称
    @Field(type=FieldType.Keyword)
    private String name;
}
