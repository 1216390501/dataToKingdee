package com.example.webapp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.webapp.entity.Pojo.wsyj.*;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.Date;
import java.util.List;
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    @Select("SELECT address, agntcde, asscde FROM ord_ct WHERE ROWNUM <= #{limit}")
    List<OrderInfo> findAddressAgntcdeAsscdeWithLimit(int limit);

    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "FFactory", column = "FFactory"),
            @Result(property = "FGXSTATUS", column = "FGXSTATUS"),
            @Result(property = "FBILLNO", column = "FBILLNO"),
            @Result(property = "FBJBILLNO", column = "FBJBILLNO"),
            @Result(property = "FFF", column = "FFF"),
            @Result(property = "FGXDATE", column = "FGXDATE"),
            @Result(property = "FKHNUMBER", column = "FKHNUMBER"),
            @Result(property = "WLMC", column = "WLMC"),
            @Result(property = "FKHNAME", column = "FKHNAME"),
            @Result(property = "FKHYWYName", column = "FKHYWYName"),
            @Result(property = "FYWYNUMBER", column = "FYWYNUMBER"),
            @Result(property = "FYWYNAME", column = "FYWYNAME"),
            @Result(property = "FCPLX", column = "FCPLX"),
            @Result(property = "FNAME", column = "FNAME"),
            @Result(property = "high", column = "high"),
            @Result(property = "length", column = "length"),
            @Result(property = "wide", column = "wide"),
            @Result(property = "yx", column = "yx"),
            @Result(property = "ddlx", column = "ddlx"),
            @Result(property = "tptype", column = "tptype"),
            @Result(property = "brand", column = "brand"),
            @Result(property = "spcnum", column = "spcnum"),
            @Result(property = "colnum", column = "colnum"),
            @Result(property = "ctlnum", column = "ctlnum"),
            @Result(property = "tmpcde", column = "tmpcde"),
            @Result(property = "spcshw", column = "spcshw"),
            @Result(property = "fsizel", column = "fsizel"),
            @Result(property = "fsizew", column = "fsizew"),
            @Result(property = "ssizel", column = "ssizel"),
            @Result(property = "ssizew", column = "ssizew"),
            @Result(property = "makeupl", column = "makeupl"),
            @Result(property = "makeupw", column = "makeupw"),
            @Result(property = "infixl", column = "infixl"),
            @Result(property = "infixw", column = "infixw"),
            @Result(property = "MSIZEL", column = "MSIZEL"),
            @Result(property = "MSIZEW", column = "MSIZEW"),
            @Result(property = "gripl1", column = "gripl1"),
            @Result(property = "gripw1", column = "gripw1"),
            @Result(property = "gripl2", column = "gripl2"),
            @Result(property = "gripw2", column = "gripw2"),
            @Result(property = "vsizel", column = "vsizel"),
            @Result(property = "vsizew", column = "vsizew"),
            @Result(property = "fringl", column = "fringl"),
            @Result(property = "fringw", column = "fringw"),
            @Result(property = "splitl", column = "splitl"),
            @Result(property = "splitw", column = "splitw"),
            @Result(property = "bleedl", column = "bleedl"),
            @Result(property = "bleedw", column = "bleedw"),
            @Result(property = "bsizel", column = "bsizel"),
            @Result(property = "bwidth", column = "bwidth"),
            @Result(property = "maktyp", column = "maktyp"),
            @Result(property = "FCPXX", column = "FCPXX"),
            @Result(property = "FCZ", column = "FCZ"),
            @Result(property = "FKX", column = "FKX"),
            @Result(property = "FSECQTY", column = "FSECQTY"),
            @Result(property = "FHSL", column = "FHSL"),
            @Result(property = "FAUXQTY", column = "FAUXQTY"),
            @Result(property = "FPRICE", column = "FPRICE"),
            @Result(property = "FSquarePrice", column = "FSquarePrice"),
            @Result(property = "FAMOUNT", column = "FAMOUNT"),
            @Result(property = "FZBCC", column = "FZBCC"),
            @Result(property = "FPO", column = "FPO"),
            @Result(property = "FBZMJ", column = "FBZMJ"),
            @Result(property = "FCKG", column = "FCKG"),
            @Result(property = "FPricingSize", column = "FPricingSize"),
            @Result(property = "fcustsize", column = "fcustsize"),
            @Result(property = "fdate", column = "fdate"),
            @Result(property = "issync", column = "issync"),
            @Result(property = "status", column = "status"),
            @Result(property = "FDeliveryAddress", column = "FDeliveryAddress"),
            @Result(property = "fkm", column = "fkm"),
            @Result(property = "FContactPerson", column = "FContactPerson"),
            @Result(property = "FContactNumber", column = "FContactNumber"),
            @Result(property = "FDeliveryDept", column = "FDeliveryDept"),
            @Result(property = "FNote", column = "FNote"),
            @Result(property = "FDeliveryDate", column = "FDeliveryDate"),
            @Result(property = "FCustShortName", column = "FCustShortName")
    })
    @ResultMap("orderDetailResultMap")
    @Select("select t.id,\n" +
            "\t\t\t decode(t.orgcde,'03','温森一期二期','05','温森三期') as FFactory,  --厂区\n" +
            "       'U' as FGXSTATUS,   --t.opmode\n" +
            "       t.serial as FBILLNO,\n" +
            "       t.mkpcde as FBJBILLNO,   -- 报价单\n" +
            "       --ptdate as 日期,\n" +
            "\t\t\t d.bwidth as FFF,        --门幅#\n" +
            "       t.updated as  FGXDATE,   --时间\n" +
            "       t.clntcde as FKHNUMBER, --客户代码\n" +
            "        decode(t.objtyp,'CB','箱片','CT','纸箱','CL','彩盒','CC','数码平板','CD','数码',t.objtyp) || '-' || substr(b.crrcde, 1, 1) || '-' ||\n" +
            "\t replace(regexp_substr(b.matcde, '[^-]+', 1, 1), '/', '') || '+' ||\n" +
            "\t substr(b.crrcde, 2) WLMC,\n" +
            "       (select i.clntnme from pb_clnt i where i.orgcde=t.orgcde and i.clntcde=t.clntcde and rownum=1) as FKHNAME,   -- 客户t.clntnme\n" +
            "\t\t\t (select  \n" +
            "\t\t\t\t\t\t\tcase t.objtyp when 'CB' then \n" +
            "\t\t\t\t\t\t\t\t\t\tcase when i.smpnme like '%快印包%' then i.jurnme || '（快印包）'\n" +
            "\t\t\t\t\t\t\t\t\t\telse i.jurnme \n" +
            "\t\t\t\t\t\t\t\t\t\tend\n" +
            "\t\t\t\t\t\t\telse \n" +
            "\t\t\t\t\t\t\t\t\t\tcase when i.smpnme like '%快印包%' then  '（快印包）' || i.clntnme\n" +
            "\t\t\t\t\t\t\t\t\t\telse i.clntnme \n" +
            "\t\t\t\t\t\t\t\t\t\tend\n" +
            "\t\t\t\t\t\t\tend \n" +
            "\n" +
            "\t\t\t\t\t\tfrom pb_clnt i where i.orgcde=t.orgcde and i.clntcde = t.clntcde) as FKHYWYName, --客户业务员 #子账户\n" +
            "\t\t\t (select i.agntcde from pb_clnt_atta i where i.orgcde=t.orgcde and i.clntcde = t.clntcde and rownum=1) as FYWYNUMBER,  --销售员代码\n" +
            "       --agntcde as FYWYNUMBER,  --销售员代码\n" +
            "\t\t\t (select d.empnme from pb_clnt_atta i,pb_dept_member d where i.clientid = t.clientid and i.orgcde = t.orgcde and i.clntcde = t.clntcde\n" +
            "           and i.agntcde = d.empcde\n" +
            "           and rownum=1 ) as FYWYNAME, --销售员名称\n" +
            "       --agntnme as FYWYNAME, --销售员名称\n" +
            "       t.objtyp as FCPLX,  --类型\n" +
            "       t.prdnme as FNAME,b.OSIZEH as high, --高\n" +
            "       b.OSIZEL as length, --长\n" +
            "       b.OSIZEW as wide, --宽 \n" +
            "       b.PRSTYP as yx, --压线 \n" +
            "       b.ordtyp as ddlx, --订单类型 \n" +
            "       d.tptype as tptype, --模板类型 \n" +
            "       t.brand as brand, --品牌 \n" +
            "       b.spcnum as spcnum, --专色数 \n" +
            "       b.colnum as colnum, --色素数 \n" +
            "       b.ctlnum as ctlnum, --数量控制 \n" +
            "       b.tmpcde as tmpcde, --模板编号 \n" +
            "       b.spcshw as spcshw, --稿袋号 \n" +
            "       d.fsizel as fsizel, --长用料表达式 \n" +
            "       d.fsizew as fsizew, --宽用料表达式 \n" +

            "       d.ssizel as ssizel, --长用料 \\n" +
            "       d.ssizew as ssizew, --宽用料 \\n" +
            "       d.makeupl as makeupl, --长拼版 \\n" +
            "       d.makeupw as makeupw, --宽平版 \\n" +
            "       d.infixl as infixl, --长刀缝 \\n" +
            "       d.infixw as infixw, --宽刀缝 \\n" +
            "       d.msizel as msizel, --长模板 \\n" +
            "       d.msizew as msizew, --宽模板 \\n" +
            "       d.gripl1 as gripl1, --长叼口1 \\n" +
            "       d.gripw1 as gripw1, --宽叼口1 \\n" +
            "       d.gripl2 as gripl2, \\n" +
            "       d.gripw2 as gripw2, \\n" +
            "       d.vsizel as vsizel, --印张尺寸长 \\n" +
            "       d.vsizew as vsizew, --印张尺寸宽 \\n" +
            "       d.fringl as fringl, --纸板尺寸 \\n" +
            "       d.fringw as fringw, --纸板尺寸 \\n" +
            "       d.splitl as splitl , --分切长 \\n" +
            "       d.splitw as splitw, --分切宽 \\n" +
            "       d.bleedl as bleedl, --修边长 \\n" +
            "       d.bleedw as bleedw, --修边宽 \\n" +
            "       d.bsizel as bsizel, --切长 \\n" +
            "       d.bwidth as bwidth, --纸幅 \\n" +

            "       b.maktyp as maktyp, --打订方式 \n" +
            "       (select i.typnme from pb_type i where i.orgcde=t.orgcde and i.typcde=b.typcde and rownum=1) as FCPXX,  --箱型 t.typnme\n" +
            "       b.matcde as FCZ,   --材质\n" +
            "       b.crrcde as FKX,  --坑型\n" +
            "       t.accnum as FSECQTY, --数量,\n" +
            "       (case t.objtyp when 'CB' then round(b.pacreage,6) else round(b.acreage,6) end) as FHSL, --单个面积,t.acreage\n" +
            "\t\t\t (CASE T.OBJTYP \n" +
            "\t\t\t\tWHEN 'CB' then round(t.accnum * round(b.pacreage,6),6)\n" +
            "\t\t\t\telse round(t.accnum * round(b.acreage,6),6)\n" +
            "\t\t\t\tend)  as FAUXQTY, -- 合计面积, t.sumacr\n" +
            "\n" +
            "       t.prices as FPRICE, --单价,\n" +
            "\t\t\t t.inprice as FSquarePrice,-- 平方价\n" +
            "       --t.accamt as FAMOUNT,--金额,\n" +
            "\t\t\t (case t.objtyp when 'CB' then round(round(t.accnum * round(b.pacreage,6),6) * t.inprice,2) \n" +
            "\t\t\t\telse t.accamt \n" +
            "\t\t\t\tend) as FAMOUNT,--金额,\n" +
            "       d.fringl || 'x' || d.fringw as FZBCC,--纸板尺寸,\n" +
            "\t\t\t b.ctpono as FPO,  --客户PONO\n" +
            "\t\t\tround(t.accnum * b.acreage *\n" +
            "\t\t\t   (select i.ratios\n" +
            "                from pb_corrugate i\n" +
            "               where i.clientid = t.clientid\n" +
            "                 and i.orgcde = t.orgcde\n" +
            "                 and i.crrcde = b.crrcde),0)  as FBZMJ , -- 折算面积\n" +
            "\t\t\t  --b.osizel || '*' || b.osizew || '*' || b.osizeh FCKG,   -- 规格\n" +
            "\t\t\t\tcase t.objtyp \n" +
            "\t\t\t\t\t\twhen 'CB' then  '平板' || ' ' || replace(b.spcshw, 'x', '*')\n" +
            "\t\t\t\t\t\telse  b.cspecs || ' ' || replace(t.specs, 'x', '*')\n" +
            "\t\t\t\tend \tFCKG,   -- 规格 \n" +
            "\t\t\t  b.cspecs as FPricingSize, -- 计价尺寸\t\n" +
            "\t\t\t\tt.specs as fcustsize,--t.specs 客户规格\n" +
            "\t\t\t\tt.created as fdate --时间\n" +
            "\t\t\t\t,'N' as issync\n" +
            "\t\t\t\t,c.status\n" +
            "\t\t\t\t,b.address as FDeliveryAddress\n" +
            "\t\t\t\t,(select i.distance from pb_clnt_addr i where i.clientid = t.clientid and i.orgcde=t.orgcde and i.clntcde=t.clntcde and i.address=b.address and rownum=1 ) as fkm\n" +
            "\t\t\t\t,(select i.contact from pb_clnt_addr i where i.clientid = t.clientid and i.orgcde=t.orgcde and i.clntcde=t.clntcde and i.address=b.address and rownum=1 ) as FContactPerson  --联系人\n" +
            "\t\t\t\t,(select i.mobile from pb_clnt_addr i where i.clientid = t.clientid and i.orgcde=t.orgcde and i.clntcde=t.clntcde and i.address=b.address and rownum=1 ) as  FContactNumber  --联系方式\n" +
            "\n" +
            "\t\t\t\t,(case when t.orgcde = '05' and t.objtyp = 'CB' then '三期2.8米生产线'\n" +
            "\t\t\t\t\t\t\t when t.orgcde = '05' and t.objtyp <> 'CB' then '三期水印联动线组'\n" +
            "\t\t\t\t\t\t\t when t.orgcde = '03' and t.objtyp = 'CL' then '胶印车间'\n" +
            "\t\t\t\t\t\t\t when t.orgcde = '03' and t.objtyp in ('CC','CD','CT') then '水印车间'\n" +
            "\t\t\t\t\t\t\t when t.orgcde = '03' and t.objtyp = 'CB' then \n" +
            "\t\t\t\t\t\t\t\t\t\t\t(select (case when a.maccde = '1' then '七层生产线'\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\twhen a.maccde = '3' then '一期2.5米新生产线'\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\twhen a.maccde = '5' then '二层生产线'\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tend) \n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n" +
            "\t\t\t\t\t\t\t\t\t\t\t\t\t\tfrom  ord_processes a where a.orgcde=t.orgcde and a.serial=t.serial and prctyp='01' and rownum=1)\n" +
            "\t\t\t\tend)  FDeliveryDept   -- 交货单位\n" +
            "\t\t\t\t\n" +
            "\t\t\t\t,b.ctpono || ' ' || b.sendrk  FNote   --送货备注\n" +
            "\t\t\t\t,(select i.shdate from ord_ct_dat i where i.orgcde=t.orgcde and i.serial=t.serial and rownum=1) FDeliveryDate  --交货日期\n" +
            "\t\t\t\t,(select i.smpnme from pb_clnt i where i.orgcde=t.orgcde and i.clntcde=t.clntcde and rownum=1) FCustShortName  --客户简称\n" +
            "\tfrom ord_bas t \n" +
            "\t\t\tleft join ord_ct b on b.clientid=t.clientid and b.orgcde=t.orgcde and b.serial=t.serial\n" +
            "\t\t\tleft join ord_ct_sts c on c.clientid=t.clientid and c.orgcde=t.orgcde and c.serial=t.serial\n" +
            "\t\t\tleft join ord_ct_cal d on d.clientid=t.clientid and d.orgcde=t.orgcde and d.serial=t.serial " +
            "WHERE t.updated>sysdate-1 and rownum<10"
//            "WHERE t.updated BETWEEN TO_DATE(#{startdate}, 'YYYY-MM-DD HH24:MI:SS') AND TO_DATE(#{enddate}, 'YYYY-MM-DD HH24:MI:SS')"
    )
    List<OrderDetail> findData(@Param("startdate")String startdate, @Param("enddate")String enddate);


    @Select(
            "select t.id\n" +
                    "\t\t\t\t,t.clientid\n" +
                    "\t\t\t\t,t.orgcde\n" +
                    "\t\t\t\t\n" +
                    "\t\t\t\t,(select i.clntnme from pb_clnt i where i.orgcde=t.orgcde and i.clntcde=t.clntcde)  as khName\n" +
                    "\t\t\t\t,t.objtyp as type --类型\n" +
                    "\t\t\t\t,t.pono as FNumber --入库单号\n" +
                    "\t\t\t\t,t.serial as proNo --生产单号\n" +
                    "\t\t\t\t,(select decode(i.objtyp,\n" +
                    "                      'CB',\n" +
                    "                      '箱片',\n" +
                    "                      'CT',\n" +
                    "                      '纸箱',\n" +
                    "                      'CL',\n" +
                    "                      '彩盒',\n" +
                    "                      i.objtyp) || '-' || substr(i.crrcde, 1, 1) || '-' ||\n" +
                    "               replace(regexp_substr(i.matcde, '[^-]+', 1, 1), '/', '') || '+' ||\n" +
                    "               substr(i.crrcde, 2)\n" +
                    "          from v_ord i\n" +
                    "         where i.clientid = t.CLIENTID\n" +
                    "           and i.orgcde = t.orgcde\n" +
                    "           and i.serial = t.SERIAL) as wlName --物料名称\n" +
                    "\t\t\t\t ,(select to_char(i.ptdate, 'yyyy-mm-dd')\n" +
                    "          from ord_ct_dat i\n" +
                    "         where i.serial = t.serial\n" +
                    "           and i.orgcde = t.orgcde) as DDTime --订单日期\n" +
                    "\t\t\t\t\t , to_char(t.ptdate, 'yyyy-mm-dd') as lastIn--最后入库\n" +
                    "\t\t\n" +
                    "\t\t\t\t\t ,t.accnuml as qty\t\n" +
                    "\t\t\t\t\t ,(select decode(c.dptcde,\n" +
                    "                      '901',\n" +
                    "                      '2.2米生产线',\n" +
                    "                      '902',\n" +
                    "                      '2.5米生产线',\n" +
                    "                      '903',\n" +
                    "                      '2.2米生产线C班',\n" +
                    "                      '904',\n" +
                    "                      'E瓦线A班',\n" +
                    "                      '905',\n" +
                    "                      'B瓦线A班',\n" +
                    "                      '906',\n" +
                    "                      'F瓦线A班',\n" +
                    "                      '907',\n" +
                    "                      '二车间',\n" +
                    "                      '909',\n" +
                    "                      '二层线')\n" +
                    "          from pb_head_ct c\n" +
                    "         where c.pono = t.pono\n" +
                    "           and c.orgcde = t.orgcde\n" +
                    "           and rownum = 1) unit--交货单位\n" +
                    "\t\t\t\t,(select p.macnme\n" +
                    "          from pb_machine p, ord_processes z\n" +
                    "         where p.clientid = z.clientid\n" +
                    "           and p.orgcde = z.orgcde\n" +
                    "           and z.clientid = t.clientid \n" +
                    "           and z.orgcde = t.orgcde\n" +
                    "           and p.maccde = z.maccde\n" +
                    "           and z.serial = t.serial\n" +
                    "           and z.prctyp = '01'\n" +
                    "           and rownum = 1) as proline--生产线\n" +
//                    "from pb_bcdr_ct t where  rownum<2 "
                    " from pb_bcdr_ct t where rownum<10 ORDER BY t.created DESC"
//                    "from pb_bcdr_ct t where  rownum<2 and t.pono='CTRA220117026' ORDER BY t.created DESC"
//                    "from pb_bcdr_ct t where t.created>sysdate-1  and rownum<110 "
    )
    List<Instok> findinstockData();


    @Select("SELECT\n" +
            "    t.id,\n" +
            "    t.clientid,\n" +
            "    t.orgcde,\n" +
            "    decode( a.ordtyp, 'CB', '箱片', 'CT', '纸箱', 'CL', '彩盒', 'ZG', '纸管', a.ordtyp ) type,--类型\n" +
            "    b.smpnme khjname,--客户简称\n" +
            "    b.clntnme khName,--客户全称\n" +
            "    t.pono pono,--送货单号\n" +
            "    t.serial proNo,--生产单号\n" +
            "    t.remark remark,--备注\n" +
            "    to_char( t.ptdate, 'yyyy-MM-dd' ) DDTime,--送货日期\n" +
            "    CASE\n" +
            "        a.ordtyp\n" +
            "        WHEN 'CB' THEN\n" +
            "                t.SERIAL || ' ' || '平张' || ' ' || REPLACE ( a.spcshw, 'x', '*' ) ELSE t.SERIAL || ' ' || a.cspecs || ' ' || REPLACE ( c.SPECS, 'x', '*' )\n" +
            "        END batch,--批次号\n" +
            "    decode( c.objtyp, 'CB', '箱片', 'CT', '纸箱', 'CL', '彩盒', 'CD', '数码', c.objtyp ) || '-' || substr( t.crrcde, 1, 1 ) || '-' || REPLACE ( regexp_substr( a.matcde, '[^-]+', 1, 1 ), '/', '' ) || '+' || substr( t.crrcde, 2 ) 物料名称,\n" +
            "    c.PRDNME proName,--产品名称\n" +
            "    (\n" +
            "        SELECT\n" +
            "            d.empnme\n" +
            "        FROM\n" +
            "            pb_clnt_atta i,\n" +
            "            pb_dept_member d\n" +
            "        WHERE\n" +
            "                i.clientid = t.clientid\n" +
            "          AND i.orgcde = t.orgcde\n" +
            "          AND i.clntcde = t.clntcde\n" +
            "          AND i.agntcde = d.empcde\n" +
            "          AND ROWNUM = 1\n" +
            "    ) AS ywyName,--业务员\n" +
            "    round( t.prices * t.accnumr, 2 ) AS price,--金额\n" +
            "    CASE\n" +
            "        c.objtyp\n" +
            "        WHEN 'CL' THEN\n" +
            "            (\n" +
            "                SELECT\n" +
            "                    i.prc1\n" +
            "                FROM\n" +
            "                    mk_ct_ann i\n" +
            "                WHERE\n" +
            "                        i.clientid = t.clientid\n" +
            "                  AND i.orgcde = t.orgcde\n" +
            "                  AND i.serial = t.serial\n" +
            "            ) ELSE (\n" +
            "        SELECT\n" +
            "            i.prc1\n" +
            "        FROM\n" +
            "            mk_ct_ann i\n" +
            "        WHERE\n" +
            "                i.clientid = t.clientid\n" +
            "          AND i.orgcde = t.orgcde\n" +
            "          AND i.serial = c.mkpcde\n" +
            "    )\n" +
            "        END bjPrice,--报价单价\n" +
            "    CASE\n" +
            "        a.ordtyp\n" +
            "        WHEN 'CB' THEN\n" +
            "            round( c.inprice, 4 ) ELSE decode( t.acreage, 0, 0, round( t.PRICES / t.acreage, 2 ) )\n" +
            "        END AS 平方单价,\n" +
            "    CASE\n" +
            "        a.ordtyp\n" +
            "        WHEN 'CB' THEN\n" +
            "            round( a.pacreage, 4 ) ELSE round( a.acreage, 4 )\n" +
            "        END AS 单位面积,\n" +
            "    CASE\n" +
            "        a.ordtyp\n" +
            "        WHEN 'CB' THEN\n" +
            "            round( a.pacreage * t.ACCNUMR, 4 ) ELSE round( a.acreage * t.ACCNUMR, 4 )\n" +
            "        END AS totalArea,--合计面积\n" +
            "\n" +
            "    t.ACCNUMR amount,--数量\n" +
            "    t.crrcde lx,--楞型\n" +
            "    REPLACE ( regexp_substr( a.matcde, '[^-]+', 1, 1 ), '/', '' ) || '-' || t.crrcde texture,--材质\n" +
            "    b.smpnme || ' ' || c.PRDNME || ' ' || c.SPECS xh --型号\n" +
            "FROM\n" +
            "    pb_bcdx_dv t\n" +
            "        LEFT JOIN ord_ct a ON a.clientid = t.clientid\n" +
            "        AND a.orgcde = t.orgcde\n" +
            "        AND a.serial = t.serial\n" +
            "        LEFT JOIN pb_clnt b ON b.clientid = t.clientid\n" +
            "        AND b.orgcde = t.orgcde\n" +
            "        AND b.clntcde = t.clntcde\n" +
            "        LEFT JOIN ord_bas c ON c.clientid = t.clientid\n" +
            "        AND c.orgcde = t.orgcde\n" +
            "        AND c.serial = t.serial\n" +
            "WHERE\n" +
            "        t.invtyp IN ( 'DV' )\n" +
//            "  AND ROWNUM < 2\n")
            "  AND t.ptdate >sysdate-1\n")
    List<outstock> findOutstockData();



}
