package com.example.webapp.entity.Pojo.PojoEntry;
import com.alibaba.fastjson.annotation.JSONField;
import com.example.webapp.entity.Pojo.PojoEntry.FInStockEntry;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;

/*
*简单生产入库单和退库单
*
* */
@NoArgsConstructor
@Data
public class InStock {
    /**
     * fid
     */
    @JSONField(name = "FID",ordinal = 0)
    private long fid;
    /**
     * FBillNo编码
     */
    @JSONField(name = "FBillNo",ordinal = 1)
    private String billno;

    /**
     * FDate
     */
    @JSONField(name = "FDate",ordinal = 2)
    private String date;

    /**
     * FStockOrgId入库组织
     */
    @JSONField(name = "FStockOrgId",ordinal = 3)
    private FNumberForm StockOrgId;


    /**
     * F_TYNG_KH客户
     */
    @JSONField(name = "F_TYNG_KH",ordinal = 4)
    private FNumberForm ku;

    /**
     * F_boxtype 箱型
     */
    @JSONField(name = "F_boxtype",ordinal = 5)
    private String boxtype;

    /**
     * F_productNo  生产订单
     */
    @JSONField(name = "F_productNo",ordinal = 6)
    private String productNo;

    /**
     * F_productLin  生产线
     */
    @JSONField(name = "F_productLin",ordinal = 6)
    private String productLin;

    /**
     * FEntity
     */
    @JSONField(name = "FEntity",ordinal = 10)
    private List<InStockEntry> entry;


}
