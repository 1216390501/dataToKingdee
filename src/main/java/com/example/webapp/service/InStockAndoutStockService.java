package com.example.webapp.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.webapp.apiRequest.KingdeeApi;
import com.example.webapp.entity.DataEntity;
import com.example.webapp.entity.ExecuteBillQuery;
import com.example.webapp.entity.Form;
import com.example.webapp.entity.Pojo.PojoEntry.*;
import com.example.webapp.entity.Pojo.wsyj.Instok;
import com.example.webapp.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.example.webapp.sql.SqlConstants.*;

@Slf4j
@Service
public class InStockAndoutStockService {
    @Autowired
    private KingdeeApi kingdeeApi;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RDBConnectionUtil  ConnectionUtil;
    @Autowired
    private RDBConnectionUtil2  ConnectionUtil2;
    /*C简单生产退库单
   B简单生产入库单
    */
    public long instockData(String startDate,String endDate) {
        try {
//
            List<Map<String, String>> maps = new ArrayList<>();

            Connection conn = ConnectionUtil.getRDBConnection();
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery(SELECT_LC_RK+" where t.created BETWEEN TO_DATE('"+startDate+"', 'YYYY-MM-DD\"T\"HH24:MI:SS') AND TO_DATE('"+endDate+"', 'YYYY-MM-DD\"T\"HH24:MI:SS')");
            List<Map<String, String>> resultMap = ConnectionUtil.resultSetToList(rs);
// 关闭第一个数据源相关资源（良好的资源管理习惯）
            conn.close();
            stat.close();
            rs.close();
//            Connection conn2 = ConnectionUtil2.getRDBConnection();
//            Statement stat2 = conn2.createStatement();
//            ResultSet rs2 = stat2.executeQuery(SELECT_WS);
//            List<Map<String, String>> resultMap2 = ConnectionUtil.resultSetToList(rs2);
//            conn2.close();
//            stat2.close();
//            rs2.close();
            maps.addAll(resultMap);
//            maps.addAll(resultMap2);

//            List<Instok> data = orderMapper.findinstockData(startDate, endDate);
//            log.info("测试入库数据：" + data.size());

            for (Map orderDetailMap: maps) {
                InStock InStockBill = new InStock();

                String id = (String) orderDetailMap.get("ID"); // 订单ID
                String clientid = (String) orderDetailMap.get("CLIENTID");
                String org = (String) orderDetailMap.get("ORGCDE");
                String khName = (String) orderDetailMap.get("KHNAME");
                String type = (String) orderDetailMap.get("TYPE");
                String billno = (String) orderDetailMap.get("FNUMBER");
                String proNo = (String) orderDetailMap.get("PRONO");
                String wlname = (String) orderDetailMap.get("WLNAME");
                String time = (String) orderDetailMap.get("DDTIME");
                String lastIn = (String) orderDetailMap.get("LASTIN");
                String qty1 = (String)orderDetailMap.get("QTY");
                BigDecimal qty = new BigDecimal(qty1) ;
                String unit = (String) orderDetailMap.get("UNIT");
                String proline = (String) orderDetailMap.get("PROLINE");
                String batchNo = (String) orderDetailMap.get("PCH");
                String bm = (String) orderDetailMap.get("FDELIVERYDEPT");
                String ck = (String) orderDetailMap.get("CK");

                
//                String billno = instok.getFNumber();// 入库单号
//                String time = instok.getDDTime();// 单据日期
//                String org = instok.getOrgcde();// 组织
                if (org.equals("00")) {
                    InStockBill.setStockOrgId(new FNumberForm("100"));//组织
                } else {
                    InStockBill.setStockOrgId(new FNumberForm("101"));//组织

                }
//                String clientid = instok.getClientid();// 厂区
//                String wlname = instok.getWlName();// 物料名称
//                String khName = instok.getKhName();// 客户名称
//                String type = instok.getType();// 类型
//                String proNo = instok.getProNo();// 生产单号
//                String proline = instok.getProline();// 生产线
//                String lastIn = instok.getLastIn();// 最后入库时间
//                String unit = instok.getUnit();// 交货单位
//                BigDecimal qty = instok.getQty();
                Form form = new Form();
                DataEntity dataEntity = new DataEntity();
                form.setFormid("SP_InStock");
                form.setData(dataEntity);
                dataEntity.setModel(InStockBill);
                InStockBill.setBillno(billno);
                InStockBill.setDate(time);//日期

//            String khNo = getDynamicObject("SAL_OUTSTOCK", khName);

                String khnb = this.getFNumber("BD_Customer", khName);
                InStockBill.setKu(new FNumberForm(khnb));//客户
//            InStockBill.setKu(new FNumberForm("01000001"));//客户


                InStockBill.setBoxtype(type);//类型
                InStockBill.setProductLin(proline);//生产线
                InStockEntry instockentry = new InStockEntry();
//            String dynamicObject = getDynamicObject("BD_MATERIAL", "测试");
//            String dynamicObject2 = getDynamicObject("BD_MATERIAL", wlname);

                String wlnb = this.getFNumber("BD_MATERIAL", wlname);

                String gXparameter ="";
                if(!wlnb.contains("@")){
                     gXparameter = this.getGXparameter(wlnb, time);
                }
                instockentry.setTechnology(new FNumberForm(gXparameter));
                instockentry.setMaterialId(new FNumberForm(wlnb));
                instockentry.setRealQty(qty);
                instockentry.setLot(new FNumberForm(batchNo));//批号

                String cknb = this.getFNumber("BD_STOCK", ck);
                instockentry.setStockId(new FNumberForm(cknb));//仓库
//                instockentry.setStockId(new FNumberForm("CK001"));//仓库
                String bmnb = this.getFNumber("BD_Department", bm);
                instockentry.setWorkShopId(new FNumberForm(bmnb));//部门
//                instockentry.setWorkShopId(new FNumberForm("BM000020"));//车间

                instockentry.setProductNo(proNo);//生产订单(销售订单号)


                ArrayList<InStockEntry> inStockEntries = new ArrayList<>();
                inStockEntries.add(instockentry);
                InStockBill.setEntry(inStockEntries);

                if (qty.compareTo(BigDecimal.ZERO) < 0) {
                    BigDecimal abs = qty.abs();//取反
                    instockentry.setRealQty(BigDecimal.ZERO);
                    instockentry.setOutQty(abs);//实退数量
                    form.setFormid("SP_OUTSTOCK");
                }
                String formStr = JSON.toJSONString(form);//接口form

                JSONObject jsonObject = kingdeeApi.kingdeeSave(form);
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
                    if (qty.compareTo(BigDecimal.ZERO) < 0) {
                        SaveLogeBill("C", org, billno, dataEntityStr, errorList.toString(), wlname, khName, "");
                    } else {
                        SaveLogeBill("B", org, billno, dataEntityStr, errorList.toString(), wlname, khName, "");
                    }


                }
            }
            return maps.size();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }
    public long outstockData(String startDate,String endDate) {
        try{
        List<Map<String, String>> maps = new ArrayList<>();

        Connection conn = ConnectionUtil.getRDBConnection();
        Statement stat = conn.createStatement();
        String sql=SELECT_LC_CK+" AND t.ptdate BETWEEN TO_DATE('"+startDate+"', 'YYYY-MM-DD\"T\"HH24:MI:SS') AND TO_DATE('"+endDate+"', 'YYYY-MM-DD\"T\"HH24:MI:SS')";
        ResultSet rs = stat.executeQuery(sql);
        List<Map<String, String>> resultMap = ConnectionUtil.resultSetToList(rs);
// 关闭第一个数据源相关资源（良好的资源管理习惯）
        conn.close();
        stat.close();
        rs.close();
            Connection conn2 = ConnectionUtil2.getRDBConnection();
            Statement stat2 = conn2.createStatement();
            ResultSet rs2 = stat2.executeQuery(sql);

            List<Map<String, String>> resultMap2 = ConnectionUtil.resultSetToList(rs2);
            conn2.close();
            stat2.close();
            rs2.close();
        maps.addAll(resultMap);
            maps.addAll(resultMap2);

            List<Instok> data = orderMapper.findinstockData(startDate, endDate);
            log.info("测试入库数据：" + data.size());

        for (Map orderDetailMap: maps) {
            // 获取订单ID
            String id = (String) orderDetailMap.get("ID"); // 订单ID
// 获取客户ID
            String clientid = (String) orderDetailMap.get("CLIENTID"); // 客户ID
// 获取其他字段（根据您的需求）
            String org = (String) orderDetailMap.get("ORGCDE"); // 组织代码
            String type = (String) orderDetailMap.get("TYPE"); // 类型
            String khjname = (String) orderDetailMap.get("KHJNAME"); // 客户简称
            String khName = (String) orderDetailMap.get("KHNAME"); // 客户名称
            String pono = (String) orderDetailMap.get("PONO"); // 采购订单号
            String proNo = (String) orderDetailMap.get("PRONO"); // 生产编号
            String remark = (String) orderDetailMap.get("REMARK"); // 备注
            String time = (String) orderDetailMap.get("DDTIME"); // 交货日期
            String batch = (String) orderDetailMap.get("BATCH"); // 批次
            String wlrm = (String) orderDetailMap.get("WLRM"); // 物料名称
            String proName = (String) orderDetailMap.get("PRONAME"); // 产品名称
            String ywyName = (String) orderDetailMap.get("YWYNAME"); // 业务员名称
            Double price = (Double) orderDetailMap.get("PRICE"); // 价格
            Double bjPrice = (Double) orderDetailMap.get("BJPRICE"); // 标准价格
            Double sqdj = (Double) orderDetailMap.get("SQDJ"); // 平方单价
            Double dwmj = (Double) orderDetailMap.get("DWMJ"); // 单位面积
            Double zjMj = (Double) orderDetailMap.get("ZJMJ"); // 总面积
            Double amount = (Double) orderDetailMap.get("AMOUNT"); // 数量
            String lx = (String) orderDetailMap.get("LX"); // 类型代码
            String texture = (String) orderDetailMap.get("TEXTURE"); // 纹理
            String xh = (String) orderDetailMap.get("XH"); // 型号

            Form form = new Form();
            DataEntity dataEntity = new DataEntity();
            form.setFormid("SAL_OUTSTOCK");
            form.setData(dataEntity);
            outStock outStock = new outStock();
            outStockEntry outStockEntry = new outStockEntry();
            dataEntity.setModel(outStock);
            outStock.setBillno(pono);
            outStock.setDate(time);
            if (org.equals("00")) {
                outStock.setFSaleOrgId(new FNumberForm("100"));//组织
            } else {
                outStock.setFSaleOrgId(new FNumberForm("101"));//组织

            }

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
            outStockEntry.setRealQty(amount);
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
        return 0;
    } catch (Exception e) {
        e.printStackTrace();
        return 0;
    }
    }
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
    public String getGXparameter(String wlno,String date){
        try {

            ExecuteBillQuery executeBillQuery = new ExecuteBillQuery();
            executeBillQuery.setFormId("VPRD_GXCP");
            executeBillQuery.setFieldKeys("F_JSCS2.Fnumber");//
            executeBillQuery.setFilterString("F_WL.FNUMBER='"+wlno+"' AND F_STARTDATE2 <'"+date+"' AND F_ENDDATE2 >'"+date+"' ");
            executeBillQuery.setOrderString("");
            executeBillQuery.setTopRowCount(0);
            executeBillQuery.setStartRow(0);
            executeBillQuery.setLimit(0);
            Form form = new Form();
            form.setFormid("VPRD_GXCP");
            form.setData(executeBillQuery);

            String resultJson = kingdeeApi.kingdeeExecuteBillQuery(form);

            JSONArray jaData = JSON.parseArray(resultJson);
            if(jaData.getJSONArray(0).getString(0)==null){//不存在
                ExecuteBillQuery executeBillQuery2 = new ExecuteBillQuery();
                executeBillQuery2.setFormId("BD_MATERIAL");
                executeBillQuery2.setFieldKeys("FMaterialGroup.FNUMBER");//
                executeBillQuery2.setFilterString("FNumber='"+wlno+"'");
                executeBillQuery2.setOrderString("");
                executeBillQuery2.setTopRowCount(0);
                executeBillQuery2.setStartRow(0);
                executeBillQuery2.setLimit(0);
                Form form2 = new Form();
                form2.setFormid("BD_MATERIAL");
                form2.setData(executeBillQuery2);
                String resultJson2 = kingdeeApi.kingdeeExecuteBillQuery(form2);
                JSONArray jaData2 = JSON.parseArray(resultJson2);
                if(jaData2.size()>0){
                    String wlfz = jaData2.getJSONArray(0).getString(0);
                    ExecuteBillQuery executeBillQuery3 = new ExecuteBillQuery();
                    executeBillQuery3.setFormId("VPRD_GXCP");
                    executeBillQuery3.setFieldKeys("F_jscs.Fnumber");//
                    executeBillQuery3.setFilterString("F_FZ.FNUMBER='"+wlfz+"' AND F_startDate <'"+date+"' AND F_endDate >'"+date+"' ");
                    executeBillQuery3.setOrderString("");
                    executeBillQuery3.setTopRowCount(0);
                    executeBillQuery3.setStartRow(0);
                    executeBillQuery3.setLimit(0);
                    Form form3 = new Form();
                    form3.setFormid("VPRD_GXCP");
                    form3.setData(executeBillQuery3);

                    String resultJson3 = kingdeeApi.kingdeeExecuteBillQuery(form3);

                    JSONArray jaData3 = JSON.parseArray(resultJson3);
                    if(jaData3.size()>0){
                        return jaData3.getJSONArray(0).getString(0);
                    }
                }
                return "";
            }else{
                return jaData.getJSONArray(0).getString(0);
            }

        }catch (Exception e){
            return "";
        }
    }
}
