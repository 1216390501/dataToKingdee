package com.example.webapp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class Delect {
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
     * networkCtrl
     */
    @JSONField(name = "NetworkCtrl",ordinal=7)
    private String networkCtrl;
}
