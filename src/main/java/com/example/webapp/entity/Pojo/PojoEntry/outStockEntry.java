package com.example.webapp.entity.Pojo.PojoEntry;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Anker
 * @date 2024年09月23日 15:38
 * @Description：
 */
@NoArgsConstructor
@Data
public class outStockEntry {
    /**
     * FMaterialID物料编码
     */
    @JSONField(name = "FMaterialID",ordinal = 1)
    private FNumberForm materialId;

    /**
     * FRealQty 实发数量
     */
    @JSONField(name = "FRealQty",ordinal = 2)
    private BigDecimal realQty;
    /**
     * FStockID  仓库
     */
    @JSONField(name = "FStockID",ordinal = 3)
    private FNumberForm stock;
    /**
     * FLot  批号
     */
    @JSONField(name = "FLot",ordinal = 4)
    private FNumberForm lot;

}
