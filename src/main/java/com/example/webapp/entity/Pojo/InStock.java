package com.example.webapp.entity.Pojo;
import com.alibaba.fastjson.annotation.JSONField;
import com.example.webapp.entity.Pojo.PojoEntry.FInStockEntry;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;


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
    private String fbillno;
    /**
     * FStockOrgId收料组织
     */
    @JSONField(name = "FStockOrgId",ordinal = 2)
    private HashMap orgid;

    /**
     * FInStockEntry明细
     */
    @JSONField(name = "FInStockEntry",ordinal = 11)
    private List<FInStockEntry> fEntity;
}
