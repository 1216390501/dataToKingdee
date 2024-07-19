package com.example.webapp.entity.Pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.HashMap;

@NoArgsConstructor
@Data
public class BarCodeModel {
    /**
     * fid
     */
    @JSONField(name = "fid",ordinal = 0)
    private long fId;
    /**
     * FNumber编码
     */
    @JSONField(name = "FNumber",ordinal = 0)
    private String fNumber;
    /**
     * fBarCode条形码
     */
    @JSONField(name = "FBarCode",ordinal = 0)
    private String fBarCode;
    /**
     * fBarCodeRule条码规则
     */
    @JSONField(name = "FBarCodeRule",ordinal = 1)
    private HashMap fBarCodeRule;


    /**
     * fBarCodeRule条码规则
     */
    @JSONField(name = "FCreateOrgId",ordinal = 1)
    private HashMap FCreateOrgId;

    /**
     * fMaterialId物料编码
     */
    @JSONField(name = "FMaterialId",ordinal = 2)
    private HashMap fMaterialId;
    /**
     * fMaterialName物料名称
     */
    @JSONField(name = "FMaterialName",ordinal = 3)
    private String fMaterialName;
    /**
     * fMaterialModel规格型号
     */
    @JSONField(name = "FMaterialModel",ordinal = 4)
    private String fMaterialModel;
    /**
     * fAuxPropId辅助属性
     */
    @JSONField(name = "FAuxPropId",ordinal = 5)
    private Integer fAuxPropId;
    /**
     * fLot批号
     */
    @JSONField(name = "FLot",ordinal = 6)
    private HashMap fLot;
    /**
     * fAuxiliaryUnitId辅助单位
     */
    @JSONField(name = "FAuxiliaryUnitId",ordinal = 7)
    private HashMap fAuxiliaryUnitId;
    /**
     * fAuxiliaryQty辅助数量
     */
    @JSONField(name = "FAuxiliaryQty",ordinal = 8)
    private BigDecimal fAuxiliaryQty;
    /**
     * fBaseUnitId基本单位
     */
    @JSONField(name = "FBaseUnitId",ordinal = 9)
    private HashMap fBaseUnitId;
    /**
     * fBaseQty基本数量
     */
    @JSONField(name = "FBaseQty",ordinal = 10)
    private BigDecimal fBaseQty;
    /**
     * fQty数量
     */
    @JSONField(name = "FQty",ordinal = 11)
    private BigDecimal fQty;
    /**
     * fSupplierId供应商
     */
    @JSONField(name = "FSupplierId",ordinal = 12)
    private HashMap fSupplierId;
    /**
     * F_JH卷号
     */
    @JSONField(name = "F_JH",ordinal = 13)
    private String F_JH;
    /**
     * FRemark备注
     */
    @JSONField(name = "FRemark",ordinal = 13)
    private String fRemark;
    /**
     * F_CDZF 疵点总分
     */
    @JSONField(name = "F_CDZF",ordinal = 14)
    private BigDecimal F_CDZF;

    /**
     * F_WSL_Color 颜色
     */
    @JSONField(name = "F_WSL_Color",ordinal = 14)
    private String F_WSL_Color;
}
