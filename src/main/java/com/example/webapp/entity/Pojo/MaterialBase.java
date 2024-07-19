package com.example.webapp.entity.Pojo;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@Data
public class MaterialBase {
    /**
     * fid
     */
    @JSONField(name = "FMATERIALID",ordinal = 0)
    private long fmaterialid;
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
}
