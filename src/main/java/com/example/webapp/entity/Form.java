package com.example.webapp.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class Form {
    @JSONField(name = "formid",ordinal = 0)
    private String formid;
    @JSONField(name = "data",ordinal = 1)
    private Object data;
}
