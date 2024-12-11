package com.example.webapp.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.webapp.apiRequest.KingdeeApi;
import com.example.webapp.entity.DataEntity;
import com.example.webapp.entity.ExecuteBillQuery;
import com.example.webapp.entity.Form;
import com.example.webapp.entity.Pojo.PojoEntry.FNumberForm;
import com.example.webapp.entity.Pojo.PojoEntry.ForReastLog;
import com.example.webapp.entity.Pojo.PojoEntry.InStock;
import com.example.webapp.entity.Pojo.PojoEntry.InStockEntry;
import com.example.webapp.entity.Pojo.wsyj.Instok;
import com.example.webapp.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class InStockAndoutStockService {
    @Autowired
    private KingdeeApi kingdeeApi;
    @Autowired
    private OrderMapper orderMapper;
    /*C简单生产退库单
   B简单生产入库单
    */
    public void instockData(String startDate,String endDate){

        List<Instok> data = orderMapper.findinstockData( startDate, endDate);
        for (Instok instok: data) {

            Integer id = instok.getId();
            String billno = instok.getFNumber();// 入库单号
            String time = instok.getDDTime();// 单据日期
            String Orgcde = instok.getOrgcde();// 组织
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
            DataEntity dataEntity=new DataEntity();
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
            InStockBill.setProductNo(proNo);//生产订单
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
                    SaveLogeBill("C",billno,dataEntityStr,errorList.toString(),wlname,khName,"");
                }else {
                    SaveLogeBill("B",billno,dataEntityStr,errorList.toString(),wlname,khName,"");
                }


            }
        }
    }
    public void SaveLogeBill(String billtype ,String SrcNo, String textLog,String Cause,String wlname,String khname,String ywyname){
        Form form2 = new Form();
        ForReastLog forReastLog = new ForReastLog();

        form2.setFormid("TYNG_ForestLOG");
        DataEntity dataEntity = new DataEntity();

        form2.setData(dataEntity);
        dataEntity.setModel(forReastLog);
        forReastLog.setBillno(SrcNo);
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
}
