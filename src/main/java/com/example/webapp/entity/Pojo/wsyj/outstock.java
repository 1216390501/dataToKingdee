package com.example.webapp.entity.Pojo.wsyj;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author Anker
 * 销售出库数据库字段
 * @date 2024年09月23日 15:03
 * @Description：
 */
@Data
public class outstock {
    private Integer id;
    private String clientid; // 厂区
    private String orgcde; // 组织
    private String khName; // 客户名称
    private String type; // 类型
    private String pono;//送货单号
    private String proNo; // 生产单号
    private String remark; // 备注
    private String DDTime; // 单据日期
    private String batch; // 批号
    private String proName; // 产品名称
    private String ywyName; // 业务员
    private BigDecimal price; // 金额
    private BigDecimal bjPrice; // 报价单价
    private BigDecimal totalArea; // 合计面积
    private BigDecimal amount; // 数量
    private String lx; // 楞型
    private String texture; // 材质
    private String xh; // 型号


}
