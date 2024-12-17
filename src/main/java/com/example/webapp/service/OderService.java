package com.example.webapp.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.webapp.apiRequest.KingdeeApi;
import com.example.webapp.entity.DataEntity;
import com.example.webapp.entity.ExecuteBillQuery;
import com.example.webapp.entity.Form;
import com.example.webapp.entity.Pojo.PojoEntry.*;
import com.example.webapp.entity.Pojo.wsyj.Instok;
import com.example.webapp.entity.Pojo.wsyj.OrderDetail;
import com.example.webapp.entity.Pojo.wsyj.OrderInfo;
import com.example.webapp.entity.Pojo.wsyj.outstock;
import com.example.webapp.entity.YourEntity;
import com.example.webapp.mapper.OrderDetailMapper;
import com.example.webapp.mapper.OrderMapper;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import java.math.BigDecimal;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.Date;

import static cn.hutool.core.date.DateUtil.formatDateTime;
import static com.example.webapp.sql.SqlConstants.SELECT_LC;
import static com.example.webapp.sql.SqlConstants.SELECT_WS;

@Slf4j
@Service
public class OderService {
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderDetailMapper orderDetailMapper;
    @Autowired
    private KingdeeApi kingdeeApi;
    @Autowired
    private RDBConnectionUtil  ConnectionUtil;
    @Autowired
    private RDBConnectionUtil2  ConnectionUtil2;

    //星空api
    @Autowired
    private SelectForm selectfrom;

    public void findWithPagination() {
        int pageSize = 10; // 每页记录数
        int currentPage = 1; // 当前页码
        List<OrderInfo> addressAgntcdeAsscdeWithLimit = orderMapper.findAddressAgntcdeAsscdeWithLimit(5);
        System.out.println("addressAgntcdeAsscdeWithLimit: " +addressAgntcdeAsscdeWithLimit.toString());
//
//        Page<Object[]> page = new Page<>(currentPage, pageSize);
//        orderMapper.findAddressAgntcdeAsscdeWithPagination(page);
//
//        // 处理查询结果
//        System.out.println("Total Records: " + page.getTotal());
//        System.out.println("Total Pages: " + page.getPages());
//        System.out.println("Current Page: " + page.getCurrent());
//        System.out.println("Data: " + page.getRecords());
    }
    public void findOderData() {
        Date date = new Date();
        // 创建 SimpleDateFormat 对象，并指定日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // 将 Date 对象转换为字符串
        String dateString = sdf.format(date);
        // 创建 Calendar 对象并设置为当前时间
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 减去十分钟
        calendar.add(Calendar.MINUTE, -1440);
        // 获取调整后的时间
        Date tenMinutesAgo = calendar.getTime();
        // 将时间转换为 "yyyy-MM-dd HH:mm:ss" 格式的字符串
        String formattedTime = formatDateTime(tenMinutesAgo);

        // 创建开始时间的 Date 对象
        LocalDateTime startLocalDateTime = LocalDateTime.of(2024, 8, 27, 0, 0);
        Date startDate = Date.from(startLocalDateTime.atZone(ZoneId.systemDefault()).toInstant());
        try {
//            List<Map<String, Object>> maps = saleBillDaoImplementation.queryDataFromOracle();
            List<Map<String, String>> maps = new ArrayList<>();

            Connection conn = ConnectionUtil.getRDBConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(SELECT_LC);
            List<Map<String, String>> resultMap = ConnectionUtil.resultSetToList(rs);
// 关闭第一个数据源相关资源（良好的资源管理习惯）
            conn.close();
            stat.close();
            rs.close();
            Connection conn2 = ConnectionUtil2.getRDBConnection();
            Statement stat2 = conn2.createStatement();
            ResultSet rs2 = stat2.executeQuery(SELECT_WS);
            List<Map<String, String>> resultMap2 = ConnectionUtil.resultSetToList(rs2);
            conn2.close();
            stat2.close();
            rs2.close();
            maps.addAll(resultMap);
            maps.addAll(resultMap2);
//            List<OrderDetail> data = orderMapper.findData(formattedTime,dateString);
//            OrderDetail orderDetail = data.get(1);
            for (Map orderDetailMap: maps) {
            log.info("orderDetailMap: "+orderDetailMap.toString());
            String fBillNo = (String) orderDetailMap.get("FBILLNO"); // 订单号



            Form form=new Form();
            DataEntity  dataEntity=new DataEntity();

            form.setFormid("SAL_SaleOrder");
            form.setData(dataEntity);
            SaleBillForm saleBillForm = new SaleBillForm();

            dataEntity.setNeedUpDateFields(new ArrayList());
            dataEntity.setNeedReturnFields( new ArrayList());
            dataEntity.setIsDeleteEntry("true");
            dataEntity.setSubSystemId("");
            dataEntity.setIsVerifyBaseDataField("false");
            dataEntity.setIsEntryBatchFill("true");
            dataEntity.setValidateFlag("true");
            dataEntity.setNumberSearch("true");
            dataEntity.setIsAutoAdjustField("false");
            dataEntity.setInterationFlags("");
            dataEntity.setIgnoreInterationFlag("");
            dataEntity.setModel(saleBillForm);

                // 从Map中获取各个值
                String id = (String) orderDetailMap.get("ID"); // 订单ID
                String fFactory = (String) orderDetailMap.get("FFACTORY"); // 厂区 (通过orgcde解码得到)
                String org = (String) orderDetailMap.get("ORG"); // 组织
                if (org.equals("00")){
                    saleBillForm.setFSaleOrgId(new FNumberForm("100"));
                }else{
                    saleBillForm.setFSaleOrgId(new FNumberForm("101"));
                }
                String fGxStatus = (String) orderDetailMap.get("FGXSTATUS"); // 订单状态 (此处固定为'U')
                String chang = (String) orderDetailMap.get("LENGTH"); // 长
                String kuan = (String) orderDetailMap.get("WIDE"); // 宽
                String gao = (String) orderDetailMap.get("HIGH"); // 高
                String yx = (String) orderDetailMap.get("YX"); // 压线
                String fBjBillNo = (String) orderDetailMap.get("FBJBILLNO"); // 报价单号
                String fFf = (String) orderDetailMap.get("FFF"); // 门幅宽度
                String fGxDate = (String) orderDetailMap.get("FGXDATE"); // 最后修改日期
                String fKhNumber = (String) orderDetailMap.get("FKHNUMBER"); // 客户编号
                String fKhName = (String) orderDetailMap.get("FKHNAME"); // 客户名称
                String fKhYwyName = (String) orderDetailMap.get("FKHYWYNAME"); // 客户业务员名称
                String fYwyNumber = (String) orderDetailMap.get("FYWYNUMBER"); // 销售员编号
                String fYwyName = (String) orderDetailMap.get("FYWYNAME"); // 销售员名称
                String fCplx = (String) orderDetailMap.get("FCPLX"); // 品牌类型
                String wlmc = (String) orderDetailMap.get("WLMC"); // 产品名称---如果没有这个物料需要新增
                String fCpxx = (String) orderDetailMap.get("FCPXX"); // 箱型名称
                String fCz = (String) orderDetailMap.get("FCZ"); // 材质代码
                String fKx = (String) orderDetailMap.get("FKX"); // 坑型代码(楞型编码)
                String ddlx = (String) orderDetailMap.get("DDLX"); // 订单类型
                String brand = (String) orderDetailMap.get("BRAND");// 品牌
                String colnum = (String) orderDetailMap.get("COLNUM");// 色数
                String spcnum = (String) orderDetailMap.get("SPCNUM");// 专色数
                String ctlnum = (String) orderDetailMap.get("CTLNUM");// 数量控制
                String tmpcde = (String) orderDetailMap.get("TMPCDE");// 模板编号
                String spcshw = (String) orderDetailMap.get("SPCSHW");// 稿袋号
                String fsizel = (String) orderDetailMap.get("FSIZEL");// 长用料表达式
                String fsizew = (String) orderDetailMap.get("FSIZEW");// 宽用料表达式
                String tptype = (String) orderDetailMap.get("TPTYPE");// 模板类型
                String maktyp = (String) orderDetailMap.get("MAKTYP");// 打订方式
                String ssizel = (String) orderDetailMap.get("SSIZEL"); // 长用料相关值获取并赋值给ssizel变量
                String ssizew = (String) orderDetailMap.get("SSIZEW"); // 宽用料相关值获取并赋值给ssizew变量
                String makeupl = (String) orderDetailMap.get("MAKEUPL"); // 长拼版相关值获取并赋值给makeupl变量
                String makeupw = (String) orderDetailMap.get("MAKEUPW"); // 宽平版相关值获取并赋值给makeupw变量
                String infixl = (String) orderDetailMap.get("INFIXL"); // 长刀缝相关值获取并赋值给infixl变量
                String infixw = (String) orderDetailMap.get("INFIXW"); // 宽刀缝相关值获取并赋值 给infixw变量
                String msizel = (String) orderDetailMap.get("MSIZEL"); // 长模板相关值获取并赋值给msizel变量
                String msizew = (String) orderDetailMap.get("MSIZEW"); // 宽模板相关值获取并赋值给msizew变量
                String gripl1 = (String) orderDetailMap.get("GRIPL1"); // 长叼口1相关值获取并赋值给gripl1变量
                String gripw1 = (String) orderDetailMap.get("GRIPW1"); // 宽叼口1相关值获取并赋值给gripw1变量
                String gripl2 = (String) orderDetailMap.get("GRIPL2"); // 长叼口2相关值获取并赋值给gripl2变量
                String gripw2 = (String) orderDetailMap.get("GRIPW2"); // 宽叼口2相关值获取并赋值给gripw2变量
                String vsizel = (String) orderDetailMap.get("VSIZEL"); // 印张尺寸长相关值获取并赋值给vsizel变量
                String vsizew = (String) orderDetailMap.get("VSIZEW"); // 印张尺寸宽相关值获取并赋值给vsizew变量
                String fringl = (String) orderDetailMap.get("FRINGL"); // 纸板尺寸相关值获取并赋值给fringl变量
                String fringw = (String) orderDetailMap.get("FRINGW"); // 纸板尺寸相关值获取并赋值给fringw变量
                String splitl = (String) orderDetailMap.get("SPLITL"); // 分切长相关值获取并赋值给splitl变量
                String splitw = (String) orderDetailMap.get("SPLITW"); // 分切宽相关值获取并赋值给splitw变量
                String bleedl = (String) orderDetailMap.get("BLEEDL"); // 修边长相关值获取并赋值给bleedl变量
                String bleedw = (String) orderDetailMap.get("BLEEDW"); // 修边宽相关值获取并赋值给bleedw变量
                String bsizel = (String) orderDetailMap.get("BSIZEL"); // 切长相关值获取并赋值给bsizel变量
                String bwidth = (String) orderDetailMap.get("BSIZEL"); // 纸幅

                String fSecQty = (String) orderDetailMap.get("FSECQTY"); // 订单数量
//                String fHsl = (String) orderDetailMap.get("FHSL"); // 单个产品面积
                BigDecimal fHsl;
                if (orderDetailMap.get("FHSL") == null) {
                    // 根据业务需求设置默认值，这里假设默认值为0
                    fHsl = new BigDecimal(0);
                } else {
                    fHsl = new BigDecimal((String) orderDetailMap.get("FHSL"));
                }
                String fAuxQty = (String) orderDetailMap.get("FAUXQTY"); // 总面积
                String fPrice = (String) orderDetailMap.get("FPRICE"); // 单价
                String fSquarePrice = (String) orderDetailMap.get("FSQUAREPRICE"); // 平方米价格
                String fAmount = (String) orderDetailMap.get("FAMOUNT"); // 金额
                String fzbcc = (String) orderDetailMap.get("FZBCC"); // 纸板尺寸
                String fPo = (String) orderDetailMap.get("FPO"); // 客户订单号
                String fbzmj = (String) orderDetailMap.get("FBZMJ"); // 折算面积
                String fCkg = (String) orderDetailMap.get("FCKG"); // 规格
                String fPricingSize = (String) orderDetailMap.get("FPRICINGSIZE"); // 计价尺寸
                String fCustSize = (String) orderDetailMap.get("FCUSTSIZE"); // 客户规格
                String fDate = (String) orderDetailMap.get("FDATE"); // 创建日期
                String issync = (String) orderDetailMap.get("ISSYNC"); // 同步标志
                String status = (String) orderDetailMap.get("STATUS"); // 订单状态
                String fDeliveryAddress = (String) orderDetailMap.get("FDELIVERYADDRESS"); // 送货地址
                String fKm = (String) orderDetailMap.get("FKM"); // 距 离
                String fContactPerson = (String) orderDetailMap.get("FCONTACTPERSON"); // 联系人
                String fContactNumber = (String) orderDetailMap.get("FCONTACTNUMBER"); // 联系电话
                String fDeliveryDept = (String) orderDetailMap.get("FDELIVERYDEPT"); // 交货部门
                String fNote = (String) orderDetailMap.get("FNOTE"); // 备注
                String fDeliveryDate = (String) orderDetailMap.get("FDELIVERYDATE"); // 交货日期
                String fCustShortName = (String) orderDetailMap.get("FCUSTSHORTNAME"); // 客户简称
            
            // 假设这是在一个Java方法内部，orderDetail是一个已经填充了数据的对象
//            Integer id = orderDetail.getId(); // 订单ID
//            String fFactory = orderDetail.getFFactory(); // 厂区 (通过orgcde解码得到)
//            String fGxStatus = orderDetail.getFGXSTATUS(); // 订单状态 (此处固定为'U')
//            String fBillNo = orderDetail.getFBILLNO(); // 订单号
//            String chang = orderDetail.getLength(); // 长
//            String kuan = orderDetail.getWide(); // 宽
//            String gao = orderDetail.getHigh(); // 高
//            String yx = orderDetail.getYx(); // 压线
//            String fBjBillNo = orderDetail.getFBJBILLNO(); // 报价单号
//            BigDecimal fFf = orderDetail.getFFF(); // 门幅宽度
//            Timestamp fGxDate = orderDetail.getFGXDATE(); // 最后修改日期
//            String fKhNumber = orderDetail.getFKHNUMBER(); // 客户编号
//            String fKhName = orderDetail.getFKHNAME(); // 客户名称
//            String fKhYwyName = orderDetail.getFKHYWYName(); // 客户业务员名称
//            String fYwyNumber = orderDetail.getFYWYNUMBER(); // 销售员编号
//            String fYwyName = orderDetail.getFYWYNAME(); // 销售员名称
//            String fCplx = orderDetail.getFCPLX(); // 品牌类型
//            String wlmc = orderDetail.getWLMC(); // 产品名称---如果没有这个物料需要新增
//            String fCpxx = orderDetail.getFCPXX(); // 箱型名称
//            String fCz = orderDetail.getFCZ(); // 材质代码
//            String fKx = orderDetail.getFKX(); // 坑型代码(楞型编码)
//            String ddlx = orderDetail.getDdlx(); // 订单类型
//            String brand = orderDetail.getBrand();// 品牌
//            String colnum = orderDetail.getColnum();// 色数
//            String spcnum = orderDetail.getSpcnum();// 专色数
//            String ctlnum = orderDetail.getCtlnum();// 数量控制
//            String tmpcde = orderDetail.getTmpcde();// 模板编号
//            String spcshw = orderDetail.getSpcshw();// 稿袋号
//            String fsizel = orderDetail.getFsizel();// 长用料表达式
//            String fsizew = orderDetail.getFsizew();// 宽用料表达式
//            String tptype = orderDetail.getTptype();// 模板类型
//            String maktyp = orderDetail.getMaktyp();// 打订方式
//            String ssizel = orderDetail.getSsizel(); // 长用料相关值获取并赋值给ssizel变量
//            String ssizew = orderDetail.getSsizew(); // 宽用料相关值获取并赋值给ssizew变量
//            String makeupl = orderDetail.getMakeupl(); // 长拼版相关值获取并赋值给makeupl变量
//            String makeupw = orderDetail.getMakeupw(); // 宽平版相关值获取并赋值给makeupw变量
//            String infixl = orderDetail.getInfixl(); // 长刀缝相关值获取并赋值给infixl变量
//            String infixw = orderDetail.getInfixw(); // 宽刀缝相关值获取并赋值给infixw变量
//            String msizel = orderDetail.getMSIZEL(); // 长模板相关值获取并赋值给msizel变量
//            String msizew = orderDetail.getMSIZEW(); // 宽模板相关值获取并赋值给msizew变量
//            String gripl1 = orderDetail.getGripl1(); // 长叼口1相关值获取并赋值给gripl1变量
//            String gripw1 = orderDetail.getGripw1(); // 宽叼口1相关值获取并赋值给gripw1变量
//            String gripl2 = orderDetail.getGripl2(); // 长叼口2相关值获取并赋值给gripl2变量
//            String gripw2 = orderDetail.getGripw2(); // 宽叼口2相关值获取并赋值给gripw2变量
//            String vsizel = orderDetail.getVsizel(); // 印张尺寸长相关值获取并赋值给vsizel变量
//            String vsizew = orderDetail.getVsizew(); // 印张尺寸宽相关值获取并赋值给vsizew变量
//            String fringl = orderDetail.getFringl(); // 纸板尺寸相关值获取并赋值给fringl变量
//            String fringw = orderDetail.getFringw(); // 纸板尺寸相关值获取并赋值给fringw变量
//            String splitl = orderDetail.getSplitl(); // 分切长相关值获取并赋值给splitl变量
//            String splitw = orderDetail.getSplitw(); // 分切宽相关值获取并赋值给splitw变量
//            String bleedl = orderDetail.getBleedl(); // 修边长相关值获取并赋值给bleedl变量
//            String bleedw = orderDetail.getBleedw(); // 修边宽相关值获取并赋值给bleedw变量
//            String bsizel = orderDetail.getBsizel(); // 切长相关值获取并赋值给bsizel变量
//            String bwidth = orderDetail.getBsizel(); // 纸幅
//
//            Integer fSecQty = orderDetail.getFSECQTY(); // 订单数量
//            BigDecimal fHsl = orderDetail.getFHSL(); // 单个产品面积
//            BigDecimal fAuxQty = orderDetail.getFAUXQTY(); // 总面积
//            BigDecimal fPrice = orderDetail.getFPRICE(); // 单价
//            BigDecimal fSquarePrice = orderDetail.getFSquarePrice(); // 平方米价格
//            BigDecimal fAmount = orderDetail.getFAMOUNT(); // 金额
//            String fzbcc = orderDetail.getFZBCC(); // 纸板尺寸
//            String fPo = orderDetail.getFPO(); // 客户订单号
//            Integer fbzmj = orderDetail.getFBZMJ(); // 折算面积
//            String fCkg = orderDetail.getFCKG(); // 规格
//            String fPricingSize = orderDetail.getFPricingSize(); // 计价尺寸
//            String fCustSize = orderDetail.getFcustsize(); // 客户规格
//            Timestamp fDate = orderDetail.getFdate(); // 创建日期
//            String issync = orderDetail.getIssync(); // 同步标志
//            String status = orderDetail.getStatus(); // 订单状态
//            String fDeliveryAddress = orderDetail.getFDeliveryAddress(); // 送货地址
//            Integer fKm = orderDetail.getFkm(); // 距离
//            String fContactPerson = orderDetail.getFContactPerson(); // 联系人
//            String fContactNumber = orderDetail.getFContactNumber(); // 联系电话
//            String fDeliveryDept = orderDetail.getFDeliveryDept(); // 交货部门
//            String fNote = orderDetail.getFNote(); // 备注
//            Timestamp fDeliveryDate = orderDetail.getFDeliveryDate(); // 交货日期
//            String fCustShortName = orderDetail.getFCustShortName(); // 客户简称

            saleBillForm.setFBillNo(fBillNo);//订单号--单据编号
            saleBillForm.setFDate(fDate.toString());//创建日期--日期
            saleBillForm.setF_tyng_leixing (fCplx);//品牌类型--品牌类型
            saleBillForm.setF_tyng_shengchancaizhi(fCz);//创建日期--日期
//            saleBillForm.setF_tyng_xx(fCpxx);// 箱型名称---箱型基础资料
            saleBillForm.setF_tyng_chang(chang);// 尺寸长
            saleBillForm.setF_tyng_kuan(kuan);// 尺寸宽
            saleBillForm.setF_tyng_gao(gao);// 尺寸高
            saleBillForm.setF_tyng_kehupo(fPo);//客户po
            saleBillForm.setFnote(fNote);//备注
            saleBillForm.setF_tyng_lx(new FNumberForm(fKx));//楞型
            saleBillForm.setF_tyng_yaxian(yx);//压线
            saleBillForm.setF_tyng_hanshuileixing(ddlx);//订单类型
            saleBillForm.setF_tyng_pinpai(brand);//品牌
            saleBillForm.setF_tyng_zhuanse(colnum);//色数
            saleBillForm.setF_tyng_zs(spcnum);//专色数
            saleBillForm.setF_fhsl(fHsl);//单个面积
            saleBillForm.setF_tyng_shuliangkongzhi(ctlnum);//数量控制
            saleBillForm.setF_tyng_moban(new FNumberForm(tmpcde));// 模板编号
//            saleBillForm.setF_tyng_moban(new FNumberForm("C706"));// 模板编号
            saleBillForm.setF_spcshw(spcshw);// 稿袋号
//            saleBillForm.setF_lenthexpression(fsizel);// 长用料表达式
//            saleBillForm.setF_wideexpression(fsizew);// 宽用料表达式
            saleBillForm.setF_tyng_mobanleixing(tptype);// 模板类型
            saleBillForm.setF_tyng_dadingfangshi(maktyp);// 打订方式
                saleBillForm.setF_tyng_changbiaoda(fsizel);// 长用料表达式
                saleBillForm.setF_tyng_kuanbiaoda(fsizew);// 宽用料表达式
                saleBillForm.setF_tyng_changyongliao(ssizel);//长用料
                saleBillForm.setF_tyng_kuanyongliao(ssizew);//宽用料
                saleBillForm.setF_tyng_changpingban(makeupl);//长拼版
                saleBillForm.setF_tyng_kuanpingban(makeupw);//宽拼版

                saleBillForm.setF_tyng_changdaofeng(infixl);//长刀缝
                saleBillForm.setF_tyng_kuandaofeng(infixw);//宽刀缝
                saleBillForm.setF_tyng_changmoban(msizel);//长模板
                saleBillForm.setF_tyng_kuanmoban(msizew);//宽模板
                saleBillForm.setF_tyng_changdiaokou(gripl1);//长叼口1
                saleBillForm.setF_tyng_kuandiaokou(gripw1);//宽叼口1
                saleBillForm.setF_tyng_changyin(vsizel);//印张尺寸长
                saleBillForm.setF_tyng_kuanyin(vsizew);//印张尺寸宽
                saleBillForm.setF_tyng_changzhi(fringl);//长纸板尺寸
                saleBillForm.setF_tyng_kuanzhi(fringw);//宽纸板尺寸
                saleBillForm.setF_tyng_changfenqie(splitl);//分切长
                saleBillForm.setF_tyng_kuanfenqie(splitw);//分切宽
                saleBillForm.setF_tyng_changxiubian(bleedl);//修边长
                saleBillForm.setF_tyng_kuanxiubian(bleedw);//修边宽
                saleBillForm.setF_tyng_changbianfu(bsizel);//切长
                saleBillForm.setF_tyng_kuanbianfu(bwidth);//纸幅



            String khnb = this.getFNumber("BD_Customer", fKhName);
            saleBillForm.setFCustId(new FNumberForm(khnb));//客户编码
//            saleBillForm.setFCustId(new FNumberForm("01000002"));//客户编码
            saleBillForm.setFSaleDeptId(new FNumberForm("BM000021"));

            String xsynb = this.getFNumber("BD_OPERATOR", fYwyName);//业务员
            saleBillForm.setFSalerId(new FNumberForm(xsynb));
            saleBillForm.setFContactNumber(fContactNumber);
            saleBillForm.setFContactPerson(fContactPerson);
            saleBillForm.setFDeliveryAddress(fDeliveryAddress);
                saleBillForm.setF_xx(fCpxx);// 箱型名称
//            saleBillForm.setFSalerId(new FNumberForm("13058884733_GW000002_1"));


            ArrayList<SaleEntry> saleEntries = new ArrayList<SaleEntry>() {
            };
            //分录数据
            SaleEntry saleEntry = new SaleEntry();//new FNumberForm("01.01.01.095.1.0800"), 1)

            String wlnb = this.getFNumber("BD_MATERIAL", wlmc);
            saleEntry.setFMaterialId(new FNumberForm(wlnb));
//            saleEntry.setFMaterialId(new FNumberForm("01.01.01.095.1.0800"));
//            saleEntry.setFUnitID(new FNumberForm("kg"));
            saleEntry.setFQty(Integer.valueOf(fSecQty));//订单数量--销售数量
                saleEntry.setTaxPrice(new BigDecimal(fPrice));//单价
            saleEntries.add(saleEntry);
            saleBillForm.setFEntity(saleEntries);

//            StringBuffer sb = new StringBuffer();
//            if(StringUtils.isEmpty(khnb) ){
//                sb.append("客户'"+fKhNumber+"'不存在");
//            }
//            if(StringUtils.isEmpty(wlnb) ){
//                sb.append("物料'"+fName+"'不存在");
//            }
//            if(StringUtils.isEmpty(xsynb) ){
//                sb.append("业务员'"+fYwyName+"'不存在");
//            }
                String dataEntityStr = JSON.toJSONString(dataEntity);

                JSONArray objects = selectfrom.selectisExist(fBillNo);
                if (objects.size()>0){//修改单据
                    Boolean aBoolean = selectfrom.UnAuditAndDelectBill(fBillNo);
                    if (!aBoolean){
                        SaveLogeBill("A",org,fBillNo,dataEntityStr,"单据已在流程无法修改，请手动删除下游单据并删除销售订单",wlmc,fKhName,fYwyName);
                        continue;
                    }
                }

            JSONObject jsonObject = kingdeeApi.kingdeeSave(form);
            System.out.println(jsonObject);
            JSONObject jsonObject2 = jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus");
            if (jsonObject2.getBoolean("IsSuccess")) {

            } else {
                List<String> errorList = new ArrayList<>();
                JSONArray errors = jsonObject2.getJSONArray("Errors");
                for (int i = 0; i < errors.size(); i++) {
                    JSONObject rowjson = errors.getJSONObject(i);
                    String message = rowjson.getString("Message");
                    errorList.add(message);

                }
                SaveLogeBill("A",org,fBillNo,dataEntityStr,errorList.toString(),wlmc,fKhName,fYwyName);
//                Form form2=new Form();
//                ForReastLog forReastLog = new ForReastLog();
//                DataEntity  dataEntity2=new DataEntity();
//
//                form2.setFormid("TYNG_ForestLOG");
//                form2.setData(dataEntity2);
//
//                dataEntity.setModel(form2);
//                dataEntity2.setModel(forReastLog);
//                forReastLog.setBillType("A");
//                forReastLog.setTextLog(dataEntityStr);
//                forReastLog.setErrorinfo(sb.toString());
//
//                JSONObject logObject = kingdeeApi.kingdeeSave(form2);

            }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    /*C简单生产退库单
    B简单生产入库单
     */
    public void instockData(){
        List<Instok> data = orderMapper.findinstockData("","");
        for (Instok instok: data) {

            Integer id = instok.getId();
            String billno = instok.getFNumber();// 入库单号
            String time = instok.getDDTime();// 单据日期
            String org = instok.getOrgcde();// 组织
            String clientid = instok.getClientid();// 厂区
            String wlname = instok.getWlName();// 物料名称
            String khName = instok.getKhName();// 客户名称
            String type = instok.getType();// 类型
            String proNo = instok.getProNo();// 生产单号
            String proline = instok.getProline();// 生产线
            String lastIn = instok.getLastIn();// 最后入库时间
            String unit = instok.getUnit();// 交货单位
            BigDecimal qty = instok.getQty();
            Form form=new Form();
            DataEntity  dataEntity=new DataEntity();
            form.setFormid("SP_InStock");
            form.setData(dataEntity);
            InStock InStockBill = new InStock();
            dataEntity.setModel(InStockBill);
            InStockBill.setBillno(billno);
            InStockBill.setDate(time);//日期

            String khNo = getDynamicObject("SAL_OUTSTOCK", khName);

            String khnb = this.getFNumber("BD_Customer", khName);
            InStockBill.setKu(new FNumberForm(khnb));//客户
//            InStockBill.setKu(new FNumberForm("01000001"));//客户

            InStockBill.setStockOrgId(new FNumberForm("100"));//组织
//            InStockBill.setProductNo(proNo);//生产订单
            InStockBill.setBoxtype(type);//类型
            InStockBill.setProductLin(proline);//生产线
            InStockEntry instockentry = new InStockEntry();
//            String dynamicObject = getDynamicObject("BD_MATERIAL", "测试");
//            String dynamicObject2 = getDynamicObject("BD_MATERIAL", wlname);

            String wlnb = this.getFNumber("BD_MATERIAL", wlname);
            instockentry.setMaterialId(new FNumberForm(wlnb));
            instockentry.setRealQty(qty);


            instockentry.setStockId(new FNumberForm("CK001"));//仓库


            instockentry.setWorkShopId(new FNumberForm("BM000020"));//车间
            ArrayList<InStockEntry> inStockEntries = new ArrayList<>();
            inStockEntries.add(instockentry);
            InStockBill.setEntry(inStockEntries);

            if (qty.compareTo(BigDecimal.ZERO) < 0){
                BigDecimal abs = qty.abs();//取反
                instockentry.setRealQty(BigDecimal.ZERO);
                instockentry.setOutQty(abs);//实退数量
                form.setFormid("SP_OUTSTOCK");
            }


//            StringBuffer sb = new StringBuffer();
//            if(StringUtils.isEmpty(khnb) ){
//                sb.append("客户'"+khName+"'不存在");
//            }
//            if(StringUtils.isEmpty(wlnb) ){
//                sb.append("物料'"+wlnb+"'不存在");
//            }
            JSONObject jsonObject = kingdeeApi.kingdeeSave(form);
            String formStr= JSON.toJSONString(form);//接口form
            String dataEntityStr= JSON.toJSONString(dataEntity);
            System.out.println(jsonObject);
            JSONObject jsonObject2 = jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus");
            if (jsonObject2.getBoolean("IsSuccess")) {

            } else {
                List<String> errorList = new ArrayList<>();
                JSONArray errors = jsonObject2.getJSONArray("Errors");
                for (int i = 0; i < errors.size(); i++) {
                    JSONObject rowjson = errors.getJSONObject(i);
                    String message = rowjson.getString("Message");
                    errorList.add(message);

                }
                if(qty.compareTo(BigDecimal.ZERO) < 0){
                    SaveLogeBill("C",org,billno,dataEntityStr,errorList.toString(),wlname,khName,"");
                }else {
                    SaveLogeBill("B",org,billno,dataEntityStr,errorList.toString(),wlname,khName,"");
                }

//                List<String> errorList = new ArrayList<>();
//                Form form2=new Form();
//                ForReastLog forReastLog = new ForReastLog();
//                DataEntity  dataEntity2=new DataEntity();
//B
//                form2.setFormid("TYNG_ForestLOG");
//                form2.setData(dataEntity2);
//
//                dataEntity.setModel(form2);
//                dataEntity2.setModel(forReastLog);
//
//                forReastLog.setTextLog(dataEntityStr);
//                forReastLog.setErrorinfo(sb.toString());
//
//
//                JSONArray errors = jsonObject2.getJSONArray("Errors");
//                for (int i = 0; i < errors.size(); i++) {
//                    JSONObject rowjson = errors.getJSONObject(i);
//                    String message = rowjson.getString("Message");
//                    if(message.contains("物料编码")){
//                        errorList.add("需要维护物料["+wlname+"]");
//                    }else if (message.contains("客户")){
//                        errorList.add("需要维护客户["+khName+"]");
//                    }else{
//                        errorList.add(message);
//                    }
//
//                }
//                forReastLog.setSrcNo(billno);
//                forReastLog.setCause(String.join(", ", errorList));
//                JSONObject logObject = kingdeeApi.kingdeeSave(form2);
            }
        }
    }
    public void outstockData(){
        List<outstock> data = orderMapper.findOutstockData();
        for (outstock Outstok: data) {
            Integer id = Outstok.getId();
            String time = Outstok.getDDTime();// 单据日期
            String org = Outstok.getOrgcde();// 组织
            String clientid = Outstok.getClientid();// 厂区
            String khName = Outstok.getKhName();// 客户名称
            String type = Outstok.getType();// 类型
            String pono = Outstok.getPono();//送货单号
            String proName = Outstok.getProName();//产品名称
            String remark = Outstok.getRemark();//备注
            String batch = Outstok.getBatch();//批号
            String proNo = Outstok.getProNo();// 生产单号
            String ywyName = Outstok.getYwyName();// 业务员
            BigDecimal price = Outstok.getPrice();// 金额
            BigDecimal bjPrice = Outstok.getBjPrice();//报价单价
            BigDecimal totalArea = Outstok.getTotalArea();// 合计面积
            BigDecimal amount = Outstok.getAmount();// 数量
            String lx = Outstok.getLx();// 楞型
            String texture = Outstok.getTexture();// 材质
            String xh = Outstok.getXh();// 型号

            boolean isNotExist = this.getBillFNumber("BD_Customer", pono);

            if (isNotExist) {
                Form form = new Form();
                DataEntity dataEntity = new DataEntity();
                form.setFormid("SAL_OUTSTOCK");
                form.setData(dataEntity);
                outStock outStock = new outStock();
                outStockEntry outStockEntry = new outStockEntry();
                dataEntity.setModel(outStock);
                outStock.setBillno(pono);
                outStock.setDate(time);
                outStock.setFSaleOrgId(new FNumberForm("100"));//组织

                String khnb = this.getFNumber("BD_Customer", khName);
                outStock.setKu(new FNumberForm(khnb));//客户
//            outStock.setKu(new FNumberForm("01000001"));//客户
                outStock.setNote(remark);

                String xsynb = this.getFNumber("BD_OPERATOR", ywyName);//业务员
                outStock.setFSalesManID(new FNumberForm(xsynb));

                outStockEntry.setStock(new FNumberForm("CK001"));//仓库

                String wlnb = this.getFNumber("BD_MATERIAL", proName);
                outStockEntry.setMaterialId(new FNumberForm(wlnb));//产品
//            outStockEntry.setMaterialId(new FNumberForm(proNo));//产品
//                outStockEntry.setRealQty(amount);
                outStockEntry.setLot(new FNumberForm(batch));
                outStock.setEntry(Arrays.asList(outStockEntry));

                JSONObject jsonObject = kingdeeApi.kingdeeSave(form);
                String formStr = JSON.toJSONString(form);//接口form
                String dataEntityStr = JSON.toJSONString(dataEntity);
                System.out.println(jsonObject);
                JSONObject jsonObject2 = jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus");
                if (jsonObject2.getBoolean("IsSuccess")) {

                } else {
                    List<String> errorList = new ArrayList<>();
                    JSONArray errors = jsonObject2.getJSONArray("Errors");
                    for (int i = 0; i < errors.size(); i++) {
                        JSONObject rowjson = errors.getJSONObject(i);
                        String message = rowjson.getString("Message");
                        errorList.add(message);

                    }

                    SaveLogeBill("D",org,pono,dataEntityStr,errorList.toString(),proName,khName,ywyName);


                }
            }
        }
    }

    public String getDynamicObject(String formid,String Name){
        Form form = new Form();
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId(formid);
        executeBillQuery.setFieldKeys("FNumber");
        executeBillQuery.setFilterString("FName ='"+Name+"'");

        form.setFormid(formid);
        form.setData(executeBillQuery);
        String barCodeJson = kingdeeApi.kingdeeExecuteBillQuery(form);
        JSONArray jaData = JSON.parseArray(barCodeJson);

        if(jaData.size()>0) {
            JSONArray jaData2 = jaData.getJSONArray(0);
            String baseNumber = jaData2.getString(0);
            return baseNumber;
        }else{
            return "";
        }

    }
    public String getFNumber(String formid,String Name){
        ExecuteBillQuery executeBillQuery = new ExecuteBillQuery();
        executeBillQuery.setFormId(formid);
        executeBillQuery.setFieldKeys("FName,FNumber");//
        executeBillQuery.setFilterString("FName ='"+Name+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form = new Form();
        form.setFormid(formid);
        form.setData(executeBillQuery);
        try {
            String resultJson = kingdeeApi.kingdeeExecuteBillQuery(form);

            JSONArray jaData = JSON.parseArray(resultJson);
            if(jaData.size()<=0){

                return "@"+formid;
            }else{
                return jaData.getJSONArray(0).getString(1);
            }

        }catch (Exception e){
            return "@"+formid;
        }
    }
    public boolean getBillFNumber(String formid,String Number){
        ExecuteBillQuery executeBillQuery = new ExecuteBillQuery();
        executeBillQuery.setFormId(formid);
        executeBillQuery.setFieldKeys("FNumber");//
        executeBillQuery.setFilterString("FNumber ='"+Number+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form = new Form();
        form.setFormid(formid);
        form.setData(executeBillQuery);

            String resultJson = kingdeeApi.kingdeeExecuteBillQuery(form);

            JSONArray jaData = JSON.parseArray(resultJson);
            if(jaData.size()<=0){

                return true;
            }else{
                return false;
            }


    }

    /**
     * 保存日志单据的方法。
     * @param billtype 单据类型
     * @param SrcNo 单据编号
     * @param textLog 同步报文,json数据
     * @param Cause 错误原因
     */
    public void SaveLogeBill(String billtype ,String org,String SrcNo, String textLog,String Cause,String wlname,String khname,String ywyname){
        Form form2 = new Form();
        ForReastLog forReastLog = new ForReastLog();

        form2.setFormid("TYNG_ForestLOG");
        DataEntity dataEntity = new DataEntity();

        form2.setData(dataEntity);
        dataEntity.setModel(forReastLog);
        forReastLog.setBillno(SrcNo);
        forReastLog.setOrg(org);
        forReastLog.setBillType(billtype);
        forReastLog.setTextLog(textLog);//同步报文
//        forReastLog.setErrorinfo(Errorinfo);//报错日志
        forReastLog.setSrcNo(SrcNo);//源单号
        forReastLog.setCause(Cause);//原因
        forReastLog.setWlname(wlname);//物料
        forReastLog.setKhname(khname);//客户
        forReastLog.setYwyname(ywyname);//业务员
        JSONObject logObject = kingdeeApi.kingdeeSave(form2);
    }
}
