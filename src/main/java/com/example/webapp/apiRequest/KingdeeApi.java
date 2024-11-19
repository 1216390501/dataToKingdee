package com.example.webapp.apiRequest;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import com.example.webapp.entity.Form;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;

@Slf4j
@Component
public class KingdeeApi {
    @Value("${kingdee.acctId}")
    private String acctId;
    @Value("${kingdee.username}")
    private String username;
    @Value("${kingdee.password}")
    private String password;
    @Value("${kingdee.lcid}")
    private String lcid;
    @Value("${kingdee.serverurl}")
    private String serverurl;


//登录 ，十分钟登陆一次
    public String login() {
        Date dNow = new Date( );
        if (Cookie.getCookStr()==null||Cookie.getNowDate()==null|| dNow.getTime()-  Cookie.getNowDate().getTime()>1000000)
        {
            loginWait();
        }

        return   Cookie.getCookStr();
    }

    public  void loginWait(){
//        String name3 = getPasswordByName("name");
//        String password3 = getPasswordByName("password");

        HashMap<String, Object> loginMsg = new HashMap<>();
        loginMsg.put("acctId", acctId);
        loginMsg.put("username", username);
        loginMsg.put("password", password);
        loginMsg.put("lcid", lcid);

        String loginStr=JSON.toJSONString(loginMsg);

        HttpResponse result2=null;
        try{
            result2 = HttpRequest.post(serverurl + "Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc")
                    .body(loginStr).execute();}
        catch (Exception e) {
            log.info("金蝶登录接口出现异常 ："+e.toString()+e.getMessage());

        }

        String cookieString = result2.getCookies().toString();
        log.info("金蝶登录接口返回得cookie"+cookieString);

        JSONObject jsonObject= JSON.parseObject(result2.body());

        if (jsonObject.getLong("LoginResultType")==1){
            log.info("登录成功");
            Cookie.setCookStr(null);
//            String cookieStr = result2.getCookies().get(0).toString() + ";" + result2.getCookies().get(1).toString();
            String cookieStr =  result2.getCookies().get(1).toString();
            Cookie.setCookStr(cookieStr);
            Cookie.setNowDate(new Date());
            System.out.println("CookStr:"+Cookie.getCookStr());
        }else{
            log.error("登录失败");

        }
    }


    public String loginResult(String username2,String password2){
        HashMap<String, Object> loginMsg = new HashMap<>();
        loginMsg.put("acctId", acctId);
        loginMsg.put("username", username2);
        loginMsg.put("password", password2);
        loginMsg.put("lcid", lcid);

        String loginStr=JSON.toJSONString(loginMsg);

        HttpResponse result2=null;
        try{
            result2 = HttpRequest.post(serverurl + "Kingdee.BOS.WebApi.ServicesStub.AuthService.ValidateUser.common.kdsvc")
                    .body(loginStr).execute();}
        catch (Exception e) {
            log.info("金蝶登录接口出现异常："+e.toString()+e.getMessage());
            return "登录异常";
        }

        JSONObject jsonObject= JSON.parseObject(result2.body());

        if (jsonObject.getLong("LoginResultType")==1){
            log.info("初始登录成功");
//            saveUserInfo("name",username2);
//            saveUserInfo("password",password2);
            return "登录成功";
        }else{
            log.error("初始登录失败");
            return "登录错误";
        }
    }
//    public void saveUserInfo(String key, String value) {
//        redisTemplate.opsForValue().set(key, value);
//    }
//    public String getPasswordByName(String key) {
//        Object password = redisTemplate.opsForValue().get(key);
//        if (password != null) {
//            return password.toString();
//        } else {
//            // 如果密码为空，则进行相应处理
//            return "密码为空";
//        }
//    }
    //金蝶保存接口
    public JSONObject kingdeeSave(Form form){
        login();
        String formStr=JSON.toJSONString(form);
        log.info("保存接口json："+formStr);
        HttpResponse formSave = HttpRequest.post(serverurl+"Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Save.common.kdsvc")
                .cookie(Cookie.getCookStr()).body(formStr).execute();
        JSONObject formSavejsonObject = JSON.parseObject(formSave.body());
        return formSavejsonObject;

    }




    //金蝶暂存接口
    public String kingdeeDraft(Form form){
        login();
        log.info("金蝶暂存接口开始工作。。。。。。。。");
        String formStr=JSON.toJSONString(form);
        HttpResponse form1Save = HttpRequest.post(serverurl+"Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Draft.common.kdsvc")
                .cookie(Cookie.getCookStr()).body(formStr).execute();


        return form1Save.body();


    }
    //金蝶提交接口
    public String kingdeeSubmit(Form form){
        login();
        log.info("金蝶提交接口开始工作。。。。。。。。");
        String formStr=JSON.toJSONString(form);
        HttpResponse form1Save = HttpRequest.post(serverurl+"Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Submit.common.kdsvc")
                .cookie(Cookie.getCookStr()).body(formStr).execute();

        log.info("金蝶提交的响应json: "+form1Save.body());
        return form1Save.body();


    }
    //金蝶审核接口Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Audit.common.kdsvc
    public String kingdeeAudit(Form form){
        login();
        log.info("金蝶审核接口开始工作。。。。。。。。");
        String formStr=JSON.toJSONString(form);
        log.info("条码审核from" + formStr + "审核成功");
        HttpResponse form1Save = HttpRequest.post(serverurl+"Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Audit.common.kdsvc")
                .cookie(Cookie.getCookStr()).body(formStr).execute();

        log.info("金蝶审核的响应json: "+form1Save.body());
        return form1Save.body();
    }
    public String kingdeeUnAudit(Form form){
        login();
        log.info("金蝶反审核接口开始工作。。。。。。。。");
        String formStr=JSON.toJSONString(form);
        HttpResponse form1Save = HttpRequest.post(serverurl+"Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.UnAudit.common.kdsvc")
                .cookie(Cookie.getCookStr()).body(formStr).execute();

        log.info("金蝶反审核的响应json: "+form1Save.body());
        return form1Save.body();
    }
    public String kingdeeDelete(Form form){
        login();
        log.info("金蝶删除接口开始工作。。。。。。。。");
        String formStr=JSON.toJSONString(form);
        log.info("金蝶删除接口form:"+formStr);
        HttpResponse form1Save = HttpRequest.post(serverurl+"Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Delete.common.kdsvc")
                .cookie(Cookie.getCookStr()).body(formStr).execute();

        log.info("金蝶删除的响应json: "+form1Save.body());
        return form1Save.body();
    }
    //金蝶下推接口
    public String kingdeePush(Form form){
        login();
        log.info("金蝶下推接口开始工作。。。。。。。。");
        String formStr=JSON.toJSONString(form);
        HttpResponse form1Save = HttpRequest.post(serverurl+"Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Push.common.kdsvc")
                .cookie(Cookie.getCookStr()).body(formStr).execute();

        log.info("金蝶下推的响应json: "+form1Save.body());
        return form1Save.body();


    }
    //金蝶单据查询接口
    public String kingdeeExecuteBillQuery(Form form){
        login();
        log.info("金蝶单据查询接口开始工作。。。。。。。。");
        String formStr=JSON.toJSONString(form);
        HttpResponse form1Save = HttpRequest.post(serverurl+"Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.ExecuteBillQuery.common.kdsvc")
                .cookie(Cookie.getCookStr()).body(formStr).execute();

        log.info("金蝶单据查询的响应json: "+form1Save.body());
        return form1Save.body();


    }
    //Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Allocate.common.kdsvc
      //分配接口
    public String kingdeeAllocate(Form form){
        login();
        log.info("金蝶分配接口开始工作。。。。。。。。");
        String formStr=JSON.toJSONString(form);
        HttpResponse form1Save = HttpRequest.post(serverurl+"Kingdee.BOS.WebApi.ServicesStub.DynamicFormService.Allocate.common.kdsvc")
                .cookie(Cookie.getCookStr()).body(formStr).execute();

        log.info("金蝶分配接口接口的响应json: "+form1Save.body());
        return form1Save.body();


    }
}





