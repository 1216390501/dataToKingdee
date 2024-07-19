package com.example.webapp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Audit {
    /**
     * createOrgId
     */
    @JSONField(name = "CreateOrgId",ordinal=0)
    private Integer createOrgId;
    /**
     * numbers
     */
    @JSONField(name = "Numbers",ordinal=1)
    private List<String> numbers;
    /**
     * ids
     */
    @JSONField(name = "Ids",ordinal=4)
    private String ids;
    /**
     * interationFlags
     */
    @JSONField(name = "InterationFlags",ordinal=5)
    private String interationFlags;
    /**
     * networkCtrl
     */
    @JSONField(name = "NetworkCtrl",ordinal=7)
    private String networkCtrl;
    /**
     * isVerifyProcInst
     */
    @JSONField(name = "IsVerifyProcInst",ordinal=9)
    private String isVerifyProcInst;
}
