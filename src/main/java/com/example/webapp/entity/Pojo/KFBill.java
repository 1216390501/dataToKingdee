package com.example.webapp.entity.Pojo;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class KFBill {
    /**
     * fid
     */
    @JSONField(name = "FID",ordinal = 0)
    private long fid;
    /**
     * FNumber编码
     */
    @JSONField(name = "FNumber",ordinal = 1)
    private String fNumber;
    /**
     * FNumber编码
     */
    @JSONField(name = "FName",ordinal = 2)
    private String name;
}
