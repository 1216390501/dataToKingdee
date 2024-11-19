package com.example.webapp.entity.Pojo.PojoEntry;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Anker
 * @date 2024年09月02日 10:02
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleEntry {
    /**
     * fMaterialId
     */
    @JSONField(name = "FMaterialId", ordinal = 1)
    private FNumberForm fMaterialId;

    /**
     * FQty 数量
     */
    @JSONField(name = "FQty", ordinal = 2)
    private int fQty;

    /**
     * FTaxPrice 价格
     */
    @JSONField(name = "FTaxPrice", ordinal = 3)
    private BigDecimal TaxPrice;
    /**
     * FUnitID 销售单位
     */
    @JSONField(name = "FUnitID", ordinal = 4)
    private FNumberForm fUnitID;

//    /**
//     * FQty 数量
//     */
//    @JSONField(name = "FQty", ordinal = 2)
//    private FNumberForm fQty;
}
