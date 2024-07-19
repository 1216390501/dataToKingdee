package com.example.webapp.service;


import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import com.alibaba.fastjson.JSONObject;
import com.example.webapp.apiRequest.KingdeeApi;
import com.example.webapp.entity.DataEntity;
import com.example.webapp.entity.ExecuteBillQuery;
import com.example.webapp.entity.Form;
import com.example.webapp.entity.Pojo.KFBill;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class SelectForm {
    @Autowired
    private KingdeeApi kingdeeApi;


    public JSONArray selectWL(String code) {
        ExecuteBillQuery executeBillQuery=new ExecuteBillQuery();
        executeBillQuery.setFormId("BD_MATERIAL");
        executeBillQuery.setFieldKeys("FNumber");
        executeBillQuery.setFilterString("FNumber!='"+code+"'");
        executeBillQuery.setOrderString("");
        executeBillQuery.setTopRowCount(0);
        executeBillQuery.setStartRow(0);
        executeBillQuery.setLimit(0);
        Form form =new Form();
        form.setFormid("BD_MATERIAL");
        form.setData(executeBillQuery);
        String barCodeJson = kingdeeApi.kingdeeExecuteBillQuery(form);
        JSONArray jaData = JSON.parseArray(barCodeJson);
        return jaData;
    }

    public void  billSave(){

        Form form = new Form();
        form.setFormid("TYNG_KaiFeiBill");
        DataEntity dataEntity = new DataEntity();
        KFBill kfBill = new KFBill();
        dataEntity.setModel(kfBill);

        long currenMil = System.currentTimeMillis();
        kfBill.setName("NAME"+currenMil);
        kfBill.setFNumber(Convert.toStr(currenMil));

        form.setData(dataEntity);

        JSONObject resultSaveJson = kingdeeApi.kingdeeSave(form);
        JSONObject jsonObject = resultSaveJson.getJSONObject("Result").getJSONObject("ResponseStatus");
        if (jsonObject.getBoolean("IsSuccess")) {
            log.info("测试数据" + form.toString() + "保存成功");



        } else {
            JSONArray jsonArray = jsonObject.getJSONArray("Errors");
            log.error("测试数据" + form.toString() + "保存失败" + jsonArray.toJSONString());
        }
    }

}
