package com.example.webapp.entity.Pojo.wsyj;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Anker
 * @date 2024年09月13日 15:09
 * @Description：
 */
@Data
public class Instok {
    private Integer id;
    private String clientid; // 厂区
    private String orgcde; // 组织
    private String khName; // 客户名称
    private String type; // 类型
    private String FNumber; // 入库单号
    private String proNo; // 生产单号
    private String wlName; // 物料名称
    private String DDTime; // 单据日期
    private String lastIn; // 最后入库时间
    private String unit; // 交货单位
    private String proline; // 生产线
    private String bm; // 生产线
    private String ck; // 生产线
    private BigDecimal qty; // 数量

}
