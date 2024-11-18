package com.example.webapp.entity.Pojo.wsyj;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class OrderDetail {

    private Integer id;
    private String FFactory; // 厂区
    private String FGXSTATUS; // 状态
    private String FBILLNO; // 单号
    private String FBJBILLNO; // 报价单
    private BigDecimal FFF; // 门幅
    private Timestamp FGXDATE; // 时间
    private String FKHNUMBER; // 客户代码
    private String WLMC; // 客户代码
    private String FKHNAME; // 客户名称
    private String FKHYWYName; // 客户业务员
    private String FYWYNUMBER; // 销售员代码
    private String FYWYNAME; // 销售员名称
    private String FCPLX; // 类型
    private String high; // 高
    private String length; // 长
    private String wide; // 宽
    private String yx; // 压线
    private String ddlx; // 订单类型
    private String tptype; // 模板类型
    private String brand; // 品牌
    private String spcnum; // 专色数
    private String colnum; //色素数
    private String ctlnum; //数量控制
    private String tmpcde; //模板编号
    private String spcshw; //稿袋号
    private String fsizel; //长用料表达式
    private String fsizew; //宽用料表达式

    private String ssizel; //长用料
    private String ssizew; //宽用料
    private String makeupl; //长拼版
    private String makeupw; //宽平版
    private String infixl; //长刀缝
    private String infixw; //宽刀缝
    private String MSIZEL; //长模板
    private String MSIZEW; //宽模板
    private String gripl1; //长叼口1
    private String gripw1; //宽叼口1
    private String gripl2; //长叼口2
    private String gripw2; //宽叼口2
    private String vsizel; //印张尺寸长
    private String vsizew; //印张尺寸宽
    private String fringl; //纸板尺寸
    private String fringw; //纸板尺寸
    private String splitl; //分切长
    private String splitw; //分切宽
    private String bleedl; //修边长
    private String bleedw; //修边宽
    private String bsizel; //切长
    private String bwidth; //纸幅


    private String maktyp; //打订方式
    private String FNAME; // 产品
    private String FCPXX; // 箱型
    private String FCZ; // 材质
    private String FKX; // 坑型
    private Integer FSECQTY; // 数量
    private BigDecimal FHSL; // 单个面积
    private BigDecimal FAUXQTY; // 合计面积
    private BigDecimal FPRICE; // 单价
    private BigDecimal FSquarePrice; // 平方价
    private BigDecimal FAMOUNT; // 金额
    private String FZBCC; // 纸板尺寸
    private String FPO; // 客户PONO
    private Integer FBZMJ; // 折算面积
    private String FCKG; // 规格
    private String FPricingSize; // 计价尺寸
    private String fcustsize; // 客户规格


    private Timestamp fdate; // 创建时间
    private String issync; // 同步状态
    private String status; // 单据状态
    private String FDeliveryAddress; // 交付地址
    private Integer fkm; // 公里数
    private String FContactPerson; // 联系人
    private String FContactNumber; // 联系方式
    private String FDeliveryDept; // 交货单位
    private String FNote; // 备注
    private Timestamp FDeliveryDate; // 交货日期
    private String FCustShortName; // 客户简称

}
