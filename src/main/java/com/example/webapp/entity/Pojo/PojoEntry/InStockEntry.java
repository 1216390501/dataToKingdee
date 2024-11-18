package com.example.webapp.entity.Pojo.PojoEntry;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author Anker
 * @date 2024年09月13日 15:45
 * @Description：
 */
@NoArgsConstructor
@Data
public class InStockEntry {
    /**
     * FMaterialId物料编码
     */
    @JSONField(name = "FMaterialId",ordinal = 1)
    private FNumberForm materialId;

    /**
     * FRealQty 实收数量
     */
    @JSONField(name = "FRealQty",ordinal = 2)
    private BigDecimal realQty;
    /**
     * FOutQty 实退数量
     */
    @JSONField(name = "FOutQty",ordinal = 2)
    private BigDecimal outQty;

    /**
     * FStockId 仓库
     */
    @JSONField(name = "FStockId",ordinal = 3)
    private FNumberForm stockId;

    /**
     * FWorkShopId1 生产车间
     */
    @JSONField(name = "FWorkShopId1",ordinal = 4)
    private FNumberForm workShopId;
}
