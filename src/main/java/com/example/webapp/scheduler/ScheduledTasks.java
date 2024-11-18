package com.example.webapp.scheduler;

import com.alibaba.fastjson.JSONArray;
import com.example.webapp.apiRequest.KingdeeApi;
import com.example.webapp.entity.Pojo.wsyj.OrderDetail;
import com.example.webapp.entity.Pojo.wsyj.OrderInfo;
import com.example.webapp.entity.YourEntity;
import com.example.webapp.service.OderService;
import com.example.webapp.service.SelectForm;
import com.example.webapp.service.YourEntityService;
import com.kingdee.bos.webapi.sdk.K3CloudApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class ScheduledTasks {
    //星空api
    @Autowired
    private SelectForm selectfrom;

    @Autowired
    private OderService oderService;

    //数据库
    private final YourEntityService yourEntityService;


    @Autowired
    public ScheduledTasks(YourEntityService yourEntityService) {
        this.yourEntityService = yourEntityService;
    }

    @Scheduled(fixedRate = 1000*60*60*24) // 每5秒执行一次
    public void fetchDataAndSend() throws Exception {
//        System.out.println("我是输出");


        oderService.findOderData();//销售订单
//        oderService.instockData();//简单生产入库
//        oderService.outstockData();//销售出库
        //数据库
//        List<YourEntity> entities = yourEntityService.fetchEntities();
//        oderService.findWithPagination();
        //接口
//        JSONArray objects = selectfrom.selectWL("1");
//        selectfrom.billSave();
//        selectfrom.billSave();
//        selectfrom.billSave();


//        K3CloudApi api = new K3CloudApi();
//        String data = "{\"CreateOrgId\":0,\"Number\":\"1.01.001\",\"Id\":\"\",\"IsSortBySeq\":\"false\"}";
//        String bd_material = api.view("BD_MATERIAL", data);
//
//
//        log.info("Success :" + bd_material);
    }


    // 可以将发送数据到外部API的逻辑封装成独立方法
    private void sendToExternalApi(List<YourEntity> entities) {
        // 实现发送数据的逻辑
    }
    private static void writeToFile(String filePath, String content) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            writer.write(content);
            writer.newLine(); // 换行
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
