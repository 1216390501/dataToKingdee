package com.example.webapp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ExecuteBillQuery {
    /**
     * formId
     */
    @JSONField(name = "FormId",ordinal = 0)
    private String formId;
    /**
     * fieldKeys
     */
    @JSONField(name = "FieldKeys",ordinal = 1)
    private String fieldKeys;
    /**
     * filterString
     */
    @JSONField(name = "FilterString",ordinal = 2)
    private String filterString;
    /**
     * orderString
     */
    @JSONField(name = "OrderString",ordinal = 3)
    private String orderString;
    /**
     * topRowCount
     */
    @JSONField(name = "TopRowCount",ordinal = 4)
    private Integer topRowCount;
    /**
     * startRow
     */
    @JSONField(name = "StartRow",ordinal = 5)
    private Integer startRow;
    /**
     * limit
     */
    @JSONField(name = "Limit",ordinal = 6)
    private Integer limit;
}
