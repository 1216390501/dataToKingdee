package com.example.webapp.entity.Pojo.PojoEntry;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
public class SaleBillForm {

    /**
     * FBillNo  订单号
     */
    @JSONField(name = "FBillNo ", ordinal = 0)
    private String fBillNo;
    /**
     * FDate    日期
     */
    @JSONField(name = "FDate", ordinal = 0)
    private String fDate;

    /**
     * FSaleDeptId 销售部门
     */
    @JSONField(name = "FSaleDeptId", ordinal = 2)
    private FNumberForm fSaleDeptId;

    /**
     * FSalerId 销售员
     */
    @JSONField(name = "FSalerId", ordinal = 3)
    private FNumberForm fSalerId;

    /**
     * FCustId 客户
     */
    @JSONField(name = "FCustId", ordinal = 4)
    private FNumberForm FCustId;
    /**
     * F_TYNG_LEIXING  品牌类型
     */
    @JSONField(name = "F_TYNG_LEIXING", ordinal = 8)
    private String f_tyng_leixing;

    /**
     * F_TYNG_PINPAI  品牌
     */
    @JSONField(name = "F_TYNG_PINPAI", ordinal = 8)
    private String f_tyng_pinpai;

    /**
     * F_TYNG_CHICUNLEIXING   尺寸类型
     */
    @JSONField(name = "F_TYNG_CHICUNLEIXING ", ordinal = 9)
    private String f_tyng_chicunleixing ;

    /**
     * F_TYNG_MOBANLEIXING   模板类型
     */
    @JSONField(name = "F_TYNG_MOBANLEIXING ", ordinal = 9)
    private String f_tyng_mobanleixing ;

    /**
     * F_TYNG_SHENGCHANCAIZHI    生产材质
     */
    @JSONField(name = "F_TYNG_SHENGCHANCAIZHI", ordinal = 10)
    private String f_tyng_shengchancaizhi ;
    /**
     * F_XX     箱型
     */
    @JSONField(name = "F_XX", ordinal = 11)
    private String f_xx;

    /**
     * F_TYNG_CHANG   长
     */
    @JSONField(name = "F_TYNG_CHANG", ordinal = 12)
    private String f_tyng_chang;

    /**
     * F_TYNG_KUAN    宽
     */
    @JSONField(name = "F_TYNG_KUAN", ordinal = 13)
    private String f_tyng_kuan ;

    /**
     * F_TYNG_GAO    高
     */
    @JSONField(name = "F_TYNG_GAO", ordinal = 14)
    private String f_tyng_gao;

    /**
     * F_TYNG_KEHUPO    客户po
     */
    @JSONField(name = "F_TYNG_KEHUPO", ordinal = 15)
    private String f_tyng_kehupo;

    /**
     * FNote     备注
     */
    @JSONField(name = "FNote", ordinal = 16)
    private String fnote;

    /**
     * F_TYNG_LX      楞型
     */
    @JSONField(name = "F_TYNG_LX", ordinal = 17)
    private FNumberForm f_tyng_lx;

    /**
     * F_TYNG_YAXIAN      压线
     */
    @JSONField(name = "F_TYNG_YAXIAN", ordinal = 18)
    private String f_tyng_yaxian;

    /**
     * F_TYNG_HANSHUILEIXING       订单类型
     */
    @JSONField(name = "F_TYNG_HANSHUILEIXING", ordinal = 19)
    private String f_tyng_hanshuileixing;



    /**
     * F_TYNG_ZS       专色(专色数)
     */
    @JSONField(name = "F_TYNG_ZS", ordinal = 20)
    private String f_tyng_zs ;

    /**
     * F_TYNG_ZHUANSE       专色(色数)
     */
    @JSONField(name = "F_TYNG_ZHUANSE", ordinal = 21)
    private String f_tyng_zhuanse ;

    /**
     * F_TYNG_SHULIANGKONGZHI       专色(色数)
     */
    @JSONField(name = "F_TYNG_SHULIANGKONGZHI", ordinal = 22)
    private String f_tyng_shuliangkongzhi ;


    /**
     * F_TYNG_MOBAN       模板编号
     */
    @JSONField(name = "F_TYNG_MOBAN", ordinal = 23)
    private FNumberForm f_tyng_moban ;


    /**
     * F_spcshw       稿袋号
     */
    @JSONField(name = "F_spcshw", ordinal = 24)
    private String f_spcshw ;

//
//    /**
//     * F_LenthExpression       长用料表达式
//     */
//    @JSONField(name = "F_LenthExpression", ordinal = 25)
//    private String f_lenthexpression ;
//
//
//    /**
//     * F_WideExpression       宽用料表达式
//     */
//    @JSONField(name = "F_WideExpression", ordinal = 26)
//    private String f_wideexpression ;

    /**
     * F_TYNG_DADINGFANGSHI       打订方式
     */
    @JSONField(name = "F_TYNG_DADINGFANGSHI", ordinal = 27)
    private String f_tyng_dadingfangshi ;

    /**
     * F_FHSL       单个面积
     */
    @JSONField(name = "F_FHSL", ordinal = 28)
    private BigDecimal f_fhsl ;


    /**
     * FDeliveryAddress       送货地址
     */
    @JSONField(name = "FDeliveryAddress", ordinal = 29)
    private String FDeliveryAddress ;


    /**
     * FContactPerson       联系人
     */
    @JSONField(name = "FContactPerson", ordinal = 30)
    private String FContactPerson ;


    /**
     * FContactNumber       联系人电话
     */
    @JSONField(name = "FContactNumber", ordinal = 31)
    private String FContactNumber ;

    /**
     * F_TYNG_CHANGBIAODA       长用料表达式
     */
    @JSONField(name = "F_TYNG_CHANGBIAODA", ordinal = 31)
    private String f_tyng_changbiaoda ;

    /**
     * F_TYNG_KUANBIAODA       宽用料表达式
     */
    @JSONField(name = "F_TYNG_KUANBIAODA", ordinal = 31)
    private String f_tyng_kuanbiaoda ;

    /**
     * F_TYNG_CHANGYONGLIAO       长用料
     */
    @JSONField(name = "F_TYNG_CHANGYONGLIAO", ordinal = 31)
    private String f_tyng_changyongliao ;

    /**
     * F_TYNG_KUANYONGLIAO       宽用料
     */
    @JSONField(name = "F_TYNG_KUANYONGLIAO", ordinal = 31)
    private String f_tyng_kuanyongliao ;

    /**
     * F_TYNG_CHANGPINGBAN       长拼版
     */
    @JSONField(name = "F_TYNG_CHANGPINGBAN", ordinal = 31)
    private String f_tyng_changpingban ;

    /**
     * F_TYNG_KUANPINGBAN       宽拼版
     */
    @JSONField(name = "F_TYNG_KUANPINGBAN", ordinal = 31)
    private String f_tyng_kuanpingban ;

    /**
     * F_TYNG_CHANGDAOFENG       长刀缝
     */
    @JSONField(name = "F_TYNG_CHANGDAOFENG", ordinal = 31)
    private String f_tyng_changdaofeng ;

    /**
     * F_TYNG_KUANDAOFENG       宽刀缝
     */
    @JSONField(name = "F_TYNG_KUANDAOFENG", ordinal = 31)
    private String f_tyng_kuandaofeng ;

    /**
     * F_TYNG_CHANGMOBAN       长模板
     */
    @JSONField(name = "F_TYNG_CHANGMOBAN", ordinal = 31)
    private String f_tyng_changmoban ;

    /**
     * F_TYNG_KUANMOBAN       宽模板
     */
    @JSONField(name = "F_TYNG_KUANMOBAN", ordinal = 31)
    private String f_tyng_kuanmoban ;

    /**
     * F_TYNG_CHANGDIAOKOU       长叼口
     */
    @JSONField(name = "F_TYNG_CHANGDIAOKOU", ordinal = 31)
    private String f_tyng_changdiaokou ;

    /**
     * F_TYNG_KUANDIAOKOU       宽叼口
     */
    @JSONField(name = "F_TYNG_KUANDIAOKOU", ordinal = 31)
    private String f_tyng_kuandiaokou ;

    /**
     * F_TYNG_CHANGYIN       长印章尺寸
     */
    @JSONField(name = "F_TYNG_CHANGYIN", ordinal = 31)
    private String f_tyng_changyin ;

    /**
     * F_TYNG_KUANYIN       宽印章尺寸
     */
    @JSONField(name = "F_TYNG_KUANYIN", ordinal = 31)
    private String f_tyng_kuanyin ;

    /**
     * F_TYNG_CHANGZHI       长纸板尺寸
     */
    @JSONField(name = "F_TYNG_CHANGZHI", ordinal = 31)
    private String f_tyng_changzhi ;

    /**
     * F_TYNG_KUANZHI       宽纸板尺寸
     */
    @JSONField(name = "F_TYNG_KUANZHI", ordinal = 31)
    private String f_tyng_kuanzhi ;


    /**
     * F_TYNG_CHANGFENQIE       长分切
     */
    @JSONField(name = "F_TYNG_CHANGFENQIE", ordinal = 31)
    private String f_tyng_changfenqie ;


    /**
     * F_TYNG_KUANFENQIE       宽分切
     */
    @JSONField(name = "F_TYNG_KUANFENQIE", ordinal = 31)
    private String f_tyng_kuanfenqie ;

    /**
     * F_TYNG_CHANGXIUBIAN       长修边
     */
    @JSONField(name = "F_TYNG_CHANGXIUBIAN", ordinal = 31)
    private String f_tyng_changxiubian ;

    /**
     * F_TYNG_KUANXIUBIAN       宽修边
     */
    @JSONField(name = "F_TYNG_KUANXIUBIAN", ordinal = 31)
    private String f_tyng_kuanxiubian ;

    /**
     * F_TYNG_CHANGBIANFU       长纸长/边幅
     */
    @JSONField(name = "F_TYNG_CHANGBIANFU", ordinal = 31)
    private String f_tyng_changbianfu ;

    /**
     * F_TYNG_KUANBIANFU       宽纸长/边幅
     */
    @JSONField(name = "F_TYNG_KUANBIANFU", ordinal = 31)
    private String f_tyng_kuanbianfu ;



    /**
     * fEntity
     */
    @JSONField(name = "FSaleOrderEntry",ordinal = 90)
    private List<SaleEntry> fEntity;
}

