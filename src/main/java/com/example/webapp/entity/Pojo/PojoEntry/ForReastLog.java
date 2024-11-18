package com.example.webapp.entity.Pojo.PojoEntry;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Anker
 * @date 2024年09月18日 17:28
 * @Description：
 */
/*
 *森林接口同步失败日志
 *
 * */
@NoArgsConstructor
@Data
public class ForReastLog {
    /**
     * FBillNo编码
     */
    @JSONField(name = "FBillNo",ordinal = 1)
    private String billno;

    /**
     * F_BillType
     */
    @JSONField(name = "F_BillType",ordinal = 2)
    private String billType;

    /**
     * F_textLog
     */
    @JSONField(name = "F_textLog",ordinal = 3)
    private String textLog;

    /**
     * F_textLog2
     */
    @JSONField(name = "F_textLog2",ordinal = 4)
    private String errorinfo;
    /**
     * F_Cause
     */
    @JSONField(name = "F_Cause",ordinal = 5)
    private String cause;

    /**
     * F_SRCNo
     */
    @JSONField(name = "F_SRCNo",ordinal = 6)
    private String srcNo;

    /**
     * F_wlname物料名称
     */
    @JSONField(name = "F_wlname",ordinal = 7)
    private String wlname;

    /**
     * F_khname客户名称
     */
    @JSONField(name = "F_khname",ordinal = 8)
    private String khname;

    /**
     * F_ywyname业务员名称
     */
    @JSONField(name = "F_ywyname",ordinal = 9)
    private String ywyname;
}
