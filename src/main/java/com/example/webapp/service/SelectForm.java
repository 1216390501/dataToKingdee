package com.example.webapp.service;


import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.alibaba.fastjson.JSONObject;
import com.example.webapp.apiRequest.KingdeeApi;
import com.example.webapp.entity.*;
import com.example.webapp.entity.Pojo.KFBill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class SelectForm {
    @Autowired
    private KingdeeApi kingdeeApi;


    public JSONArray selectisExist(String code) {
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("SAL_SaleOrder");
        executeBillQuery.setFieldKeys("FBillNo");
        executeBillQuery.setFilterString("FBillNo='"+code+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("SAL_SaleOrder");
        form.setData(executeBillQuery);
        String barCodeJson = kingdeeApi.kingdeeExecuteBillQuery(form);
        JSONArray jaData = JSON.parseArray(barCodeJson);
        return jaData;
    }

    public Boolean  UnAuditAndDelectBill(String code){
        UnAudit unAudit = new UnAudit();
        unAudit.setIds("");
        unAudit.setCreateOrgId(0);
        unAudit.setInterationFlags("");
        unAudit.setNumbers(new ArrayList<String>(){{add(code);}});
        unAudit.setNetworkCtrl("");
        unAudit.setIsVerifyProcInst("");
        Form form =new Form();
        form.setFormid("SAL_SaleOrder");
        form.setData(unAudit);
        String barCodeJson = kingdeeApi.kingdeeUnAudit(form);
        JSONObject jsonObject = JSON.parseObject(barCodeJson);

        JSONObject jsonObject2 = jsonObject.getJSONObject("Result").getJSONObject("ResponseStatus");
        if (jsonObject2.getBoolean("IsSuccess")) {
            Delect delect = new Delect();
            delect.setNumbers(new ArrayList<String>(){{add(code);}});
            delect.setIds("");
            delect.setNetworkCtrl("");
            delect.setCreateOrgId(0);
            Form form2 =new Form();
            form2.setFormid("SAL_SaleOrder");
            form2.setData(delect);
            String delectJson = kingdeeApi.kingdeeUnAudit(form2);
            JSONObject delectJsonOb = JSON.parseObject(delectJson);
            JSONObject delectJsonObzi = delectJsonOb.getJSONObject("Result").getJSONObject("ResponseStatus");
            if (delectJsonObzi.getBoolean("IsSuccess")) {
                return true;
            }
        }
        return false;
    }

}
