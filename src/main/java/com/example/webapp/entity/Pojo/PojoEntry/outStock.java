package com.example.webapp.entity.Pojo.PojoEntry;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Anker
 * 销售出库单
 * @date 2024年09月23日 15:36
 * @Description：
 */
@NoArgsConstructor
@Data
public class outStock {
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
     * FSaleOrgId
     */
    @JSONField(name = "FSaleOrgId",ordinal = 3)
    private FNumberForm FSaleOrgId;

    /**
     * FSalesManID
     */
    @JSONField(name = "FSalesManID",ordinal = 4)
    private FNumberForm FSalesManID;


    /**
     * FCustomerID客户
     */
    @JSONField(name = "FCustomerID",ordinal = 4)
    private FNumberForm ku;

    /**
     * FNote 备注
     */
    @JSONField(name = "FNote",ordinal = 5)
    private String Note;

    /**
     * FEntity
     */
    @JSONField(name = "FEntity",ordinal = 10)
    private List<outStockEntry> entry;

}
