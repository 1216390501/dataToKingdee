package com.example.webapp.entity.Pojo.PojoEntry;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

@NoArgsConstructor
@Data
public class FInStockEntry {
    /**
     * fMaterialId
     */
    @JSONField(name = "FMaterialId",ordinal = 1)
    private HashMap fMaterialId;
    /**
     * FRealQty实收数量
     */
    @JSONField(name = "FRealQty",ordinal = 2)
    private long realqty;
    /**
     * 计价数量FPriceUnitQty
     */
    @JSONField(name = "FPriceUnitQty ",ordinal = 3)
    private long fpriceunitqty;
}
