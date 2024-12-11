package com.example.webapp.sql;

public class SqlConstants {
    // 查询所有老厂
    public static final String SELECT_LC = "SELECT t.id,\n" +
            "        DECODE(t.orgcde,'03','温森一期二期','05','温森三期','00','大溪老厂') AS FFactory,  --厂区\n" +
            "        'U' AS FGXSTATUS,   --t.opmode\n" +
            "        t.serial AS FBILLNO,\n" +
            "        t.mkpcde AS FBJBILLNO,   -- 报价单\n" +
            "        --ptdate AS 日期,\n" +
            "        d.bwidth AS FFF,        --门幅#\n" +
            "        t.updated AS  FGXDATE,   --时间\n" +
            "        t.clntcde AS FKHNUMBER, --客户代码\n" +
            "        DECODE(t.objtyp,'CB','箱片','CT','纸箱','CL','彩盒','CC','数码平板','CD','数码','CJ','精品盒',t.objtyp) || '-' || SUBSTR(b.crrcde, 1, 1) || '-' ||\n" +
            "        REPLACE(REGEXP_SUBSTR(b.matcde, '[^-]+', 1, 1), '/', '') || '+' ||\n" +
            "        SUBSTR(b.crrcde, 2) WLMC,\n" +
            "        (SELECT i.clntnme FROM pb_clnt i WHERE i.orgcde=t.orgcde AND i.clntcde=t.clntcde AND ROWNUM=1) AS FKHNAME,   -- 客户t.clntnme\n" +
            "        (SELECT \n" +
            "                CASE t.objtyp WHEN 'CB' THEN \n" +
            "                        CASE WHEN i.smpnme LIKE '%快印包%' THEN i.jurnme || '（快印包）'\n" +
            "                        ELSE i.jurnme \n" +
            "                        END\n" +
            "                ELSE \n" +
            "                        CASE WHEN i.smpnme LIKE '%快印包%' THEN  '（快印包）' || i.clntnme\n" +
            "                        ELSE i.clntnme \n" +
            "                        END\n" +
            "                END \n" +
            "        FROM pb_clnt i WHERE i.orgcde=t.orgcde AND i.clntcde = t.clntcde) AS FKHYWYName, --客户业务员 #子账户\n" +
            "        (SELECT i.agntcde FROM pb_clnt_atta i WHERE i.orgcde=t.orgcde AND i.clntcde = t.clntcde AND ROWNUM=1) AS FYWYNUMBER,  --销售员代码\n" +
            "        --agntcde AS FYWYNUMBER,  --销售员代码\n" +
            "        (SELECT d.empnme FROM pb_clnt_atta i,pb_dept_member d WHERE i.clientid = t.clientid AND i.orgcde = t.orgcde AND i.clntcde = t.clntcde\n" +
            "            AND i.agntcde = d.empcde\n" +
            "            AND ROWNUM=1 ) AS FYWYNAME, --销售员名称\n" +
            "        --agntnme AS FYWYNAME, --销售员名称\n" +
            "        t.objtyp AS FCPLX,  --类型\n" +
            "        t.prdnme AS FNAME,b.OSIZEH AS high, --高\n" +
            "        b.OSIZEL AS length, --长\n" +
            "        b.OSIZEW AS wide, --宽 \n" +
            "        b.PRSTYP AS yx, --压线 \n" +
            "        b.ordtyp AS ddlx, --订单类型 \n" +
            "        d.tptype AS tptype, --模板类型 \n" +
            "        t.brand AS brand, --品牌 \n" +
            "        b.spcnum AS spcnum, --专色数 \n" +
            "        b.colnum AS colnum, --色素数 \n" +
            "        b.ctlnum AS ctlnum, --数量控制 \n" +
            "        b.tmpcde AS tmpcde, --模板编号 \n" +
            "        b.spcshw AS spcshw, --稿袋号 \n" +
            "        d.fsizel AS fsizel, --长用料表达式 \n" +
            "        d.fsizew AS fsizew, --宽用料表达式 \n" +
            "        d.ssizel AS ssizel, --长用料 \n" +
            "        d.ssizew AS ssizew, --宽用料 \n" +
            "        d.makeupl AS makeupl, --长拼版 \n" +
            "        d.makeupw AS makeupw, --宽平版 \n" +
            "        d.infixl AS infixl, --长刀缝 \n" +
            "        d.infixw AS infixw, --宽刀缝 \n" +
            "        d.msizel AS msizel, --长模板 \n" +
            "        d.msizew AS msizew, --宽模板 \n" +
            "        d.gripl1 AS gripl1, --长叼口1 \n" +
            "        d.gripw1 AS gripw1, --宽叼口1 \n" +
            "        d.gripl2 AS gripl2, \n" +
            "        d.gripw2 AS gripw2, \n" +
            "        d.vsizel AS vsizel, --印张尺寸长 \n" +
            "        d.vsizew AS vsizew, --印张尺寸宽 \n" +
            "        d.fringl AS fringl, --纸板尺寸 \n" +
            "        d.fringw AS fringw, --纸板尺寸 \n" +
            "        d.splitl AS splitl, --分切长 \n" +
            "        d.splitw AS splitw, --分切宽 \n" +
            "        d.bleedl AS bleedl, --修边长 \n" +
            "        d.bleedw AS bleedw, --修边宽 \n" +
            "        d.bsizel AS bsizel, --切长 \n" +
            "        d.bwidth AS bwidth, --纸幅 \n" +
            "        b.maktyp AS maktyp, --打订方式 \n" +
            "        (SELECT i.typnme FROM pb_type i WHERE i.orgcde=t.orgcde AND i.typcde=b.typcde AND ROWNUM=1) AS FCPXX,  --箱型 t.typnme\n" +
            "        b.matcde AS FCZ,   --材质\n" +
            "        b.crrcde AS FKX,  --坑型\n" +
            "        t.accnum AS FSECQTY, --数量,\n" +
            "        (CASE t.objtyp WHEN 'CB' THEN ROUND(b.pacreage,6) ELSE ROUND(b.acreage,6) END) AS FHSL, --单个面积,t.acreage\n" +
            "        (CASE T.OBJTYP \n" +
            "            WHEN 'CB' THEN ROUND(t.accnum * ROUND(b.pacreage,6),6)\n" +
            "            ELSE ROUND(t.accnum * ROUND(b.acreage,6),6)\n" +
            "            END)  AS FAUXQTY, -- 合计面积, t.sumacr\n" +
            "        t.prices AS FPRICE, --单价,\n" +
            "        t.inprice AS FSquarePrice,-- 平方价\n" +
            "        --t.accamt AS FAMOUNT,--金额,\n" +
            "        (CASE t.objtyp WHEN 'CB' THEN ROUND(ROUND(t.accnum * ROUND(b.pacreage,6),6) * t.inprice,2) \n" +
            "            ELSE t.accamt \n" +
            "            END) AS FAMOUNT,--金额,\n" +
            "        d.fringl || 'x' || d.fringw AS FZBCC,--纸板尺寸,\n" +
            "        b.ctpono AS FPO,  --客户PONO\n" +
            "        ROUND(t.accnum * b.acreage *\n" +
            "            (SELECT i.ratios\n" +
            "                FROM pb_corrugate i\n" +
            "                WHERE i.clientid = t.clientid\n" +
            "                AND i.orgcde = t.orgcde\n" +
            "                AND i.crrcde = b.crrcde),0)  AS FBZMJ, -- 折算面积\n" +
            "        --b.osizel || '*' || b.osizew || '*' || b.osizeh FCKG,   -- 规格\n" +
            "        CASE t.objtyp \n" +
            "            WHEN 'CB' THEN  '平板' || ' ' || REPLACE(b.spcshw, 'x', '*')\n" +
            "            ELSE  b.cspecs || ' ' || REPLACE(t.specs, 'x', '*')\n" +
            "            END FCKG,   -- 规格 \n" +
            "        b.cspecs AS FPricingSize, -- 计价尺寸\n" +
            "        t.specs AS fcustsize,--t.specs 客户规格\n" +
            "        t.created AS fdate --时间\n" +
            "       ,'N' AS issync\n" +
            "       ,c.status\n" +
            "       ,b.address AS FDeliveryAddress\n" +
            "       ,(SELECT i.distance FROM pb_clnt_addr i WHERE i.clientid = t.clientid AND i.orgcde=t.orgcde AND i.clntcde=t.clntcde AND i.address=b.address AND ROWNUM=1 ) AS fkm\n" +
            "       ,(SELECT i.contact FROM pb_clnt_addr i WHERE i.clientid = t.clientid AND i.orgcde=t.orgcde AND i.clntcde=t.clntcde AND i.address=b.address AND ROWNUM=1 ) AS FContactPerson  --联系人\n" +
            "       ,(SELECT i.mobile FROM pb_clnt_addr i WHERE i.clientid = t.clientid AND i.orgcde=t.orgcde AND i.clntcde=t.clntcde AND i.address=b.address AND ROWNUM=1 ) AS  FContactNumber  --联系方式\n" +
            "       ,(CASE WHEN t.orgcde = '05' AND t.objtyp = 'CB' THEN '三期2.8米生产线'\n" +
            "            WHEN t.orgcde = '05' AND t.objtyp <> 'CB' THEN '三期水印联动线组'\n" +
            "            WHEN t.orgcde = '03' AND t.objtyp = 'CL' THEN '胶印车间'\n" +
            "            WHEN t.orgcde = '03' AND t.objtyp IN ('CC','CD','CT') THEN '水印车间'\n" +
            "            WHEN t.orgcde = '03' AND t.objtyp = 'CB' THEN \n" +
            "                (SELECT (CASE WHEN a.maccde = '1' THEN '七层生产线'\n" +
            "                    WHEN a.maccde = '3' THEN '一期2.5米新生产线'\n" +
            "                    WHEN a.maccde = '5' THEN '二层生产线'\n" +
            "                    END) \n" +
            "                    FROM  ord_processes a WHERE a.orgcde=t.orgcde AND a.serial=t.serial AND prctyp='01' AND ROWNUM=1)\n" +
            "            END)  FDeliveryDept   -- 交货单位\n" +
            "       ,b.ctpono || ' ' || b.sendrk  FNote   --送货备注\n" +
            "       ,(SELECT i.shdate FROM ord_ct_dat i WHERE i.orgcde=t.orgcde AND i.serial=t.serial AND ROWNUM=1) FDeliveryDate  --交货日期\n" +
            "       ,(SELECT i.smpnme FROM pb_clnt i WHERE i.orgcde=t.orgcde AND i.clntcde=t.clntcde AND ROWNUM=1) FCustShortName  --客户简称\n" +
            "FROM ord_bas t \n" +
            "    LEFT JOIN ord_ct b ON b.clientid=t.clientid AND b.orgcde=t.orgcde AND b.serial=t.serial\n" +
            "    LEFT JOIN ord_ct_sts c ON c.clientid=t.clientid AND c.orgcde=t.orgcde AND c.serial=t.serial\n" +
            "    LEFT JOIN ord_ct_cal d ON d.clientid=t.clientid AND d.orgcde=t.orgcde AND d.serial=t.serial \n" +
            "WHERE t.updated>sysdate-1 and t.orgcde='00'";
    // 温森
    public static final String SELECT_WS = "SELECT t.id,\n" +
            "        DECODE(t.orgcde,'03','温森一期二期','05','温森三期','00','大溪老厂') AS FFactory,  --厂区\n" +
            "        'U' AS FGXSTATUS,   --t.opmode\n" +
            "        t.serial AS FBILLNO,\n" +
            "        t.mkpcde AS FBJBILLNO,   -- 报价单\n" +
            "        --ptdate AS 日期,\n" +
            "        d.bwidth AS FFF,        --门幅#\n" +
            "        t.updated AS  FGXDATE,   --时间\n" +
            "        t.clntcde AS FKHNUMBER, --客户代码\n" +
            "        DECODE(t.objtyp,'CB','箱片','CT','纸箱','CL','彩盒','CC','数码平板','CD','数码','CJ','精品盒',t.objtyp) || '-' || SUBSTR(b.crrcde, 1, 1) || '-' ||\n" +
            "        REPLACE(REGEXP_SUBSTR(b.matcde, '[^-]+', 1, 1), '/', '') || '+' ||\n" +
            "        SUBSTR(b.crrcde, 2) WLMC,\n" +
            "        (SELECT i.clntnme FROM pb_clnt i WHERE i.orgcde=t.orgcde AND i.clntcde=t.clntcde AND ROWNUM=1) AS FKHNAME,   -- 客户t.clntnme\n" +
            "        (SELECT \n" +
            "                CASE t.objtyp WHEN 'CB' THEN \n" +
            "                        CASE WHEN i.smpnme LIKE '%快印包%' THEN i.jurnme || '（快印包）'\n" +
            "                        ELSE i.jurnme \n" +
            "                        END\n" +
            "                ELSE \n" +
            "                        CASE WHEN i.smpnme LIKE '%快印包%' THEN  '（快印包）' || i.clntnme\n" +
            "                        ELSE i.clntnme \n" +
            "                        END\n" +
            "                END \n" +
            "        FROM pb_clnt i WHERE i.orgcde=t.orgcde AND i.clntcde = t.clntcde) AS FKHYWYName, --客户业务员 #子账户\n" +
            "        (SELECT i.agntcde FROM pb_clnt_atta i WHERE i.orgcde=t.orgcde AND i.clntcde = t.clntcde AND ROWNUM=1) AS FYWYNUMBER,  --销售员代码\n" +
            "        --agntcde AS FYWYNUMBER,  --销售员代码\n" +
            "        (SELECT d.empnme FROM pb_clnt_atta i,pb_dept_member d WHERE i.clientid = t.clientid AND i.orgcde = t.orgcde AND i.clntcde = t.clntcde\n" +
            "            AND i.agntcde = d.empcde\n" +
            "            AND ROWNUM=1 ) AS FYWYNAME, --销售员名称\n" +
            "        --agntnme AS FYWYNAME, --销售员名称\n" +
            "        t.objtyp AS FCPLX,  --类型\n" +
            "        t.prdnme AS FNAME,b.OSIZEH AS high, --高\n" +
            "        b.OSIZEL AS length, --长\n" +
            "        b.OSIZEW AS wide, --宽 \n" +
            "        b.PRSTYP AS yx, --压线 \n" +
            "        b.ordtyp AS ddlx, --订单类型 \n" +
            "        d.tptype AS tptype, --模板类型 \n" +
            "        t.brand AS brand, --品牌 \n" +
            "        b.spcnum AS spcnum, --专色数 \n" +
            "        b.colnum AS colnum, --色素数 \n" +
            "        b.ctlnum AS ctlnum, --数量控制 \n" +
            "        b.tmpcde AS tmpcde, --模板编号 \n" +
            "        b.spcshw AS spcshw, --稿袋号 \n" +
            "        d.fsizel AS fsizel, --长用料表达式 \n" +
            "        d.fsizew AS fsizew, --宽用料表达式 \n" +
            "        d.ssizel AS ssizel, --长用料 \n" +
            "        d.ssizew AS ssizew, --宽用料 \n" +
            "        d.makeupl AS makeupl, --长拼版 \n" +
            "        d.makeupw AS makeupw, --宽平版 \n" +
            "        d.infixl AS infixl, --长刀缝 \n" +
            "        d.infixw AS infixw, --宽刀缝 \n" +
            "        d.msizel AS msizel, --长模板 \n" +
            "        d.msizew AS msizew, --宽模板 \n" +
            "        d.gripl1 AS gripl1, --长叼口1 \n" +
            "        d.gripw1 AS gripw1, --宽叼口1 \n" +
            "        d.gripl2 AS gripl2, \n" +
            "        d.gripw2 AS gripw2, \n" +
            "        d.vsizel AS vsizel, --印张尺寸长 \n" +
            "        d.vsizew AS vsizew, --印张尺寸宽 \n" +
            "        d.fringl AS fringl, --纸板尺寸 \n" +
            "        d.fringw AS fringw, --纸板尺寸 \n" +
            "        d.splitl AS splitl, --分切长 \n" +
            "        d.splitw AS splitw, --分切宽 \n" +
            "        d.bleedl AS bleedl, --修边长 \n" +
            "        d.bleedw AS bleedw, --修边宽 \n" +
            "        d.bsizel AS bsizel, --切长 \n" +
            "        d.bwidth AS bwidth, --纸幅 \n" +
            "        b.maktyp AS maktyp, --打订方式 \n" +
            "        (SELECT i.typnme FROM pb_type i WHERE i.orgcde=t.orgcde AND i.typcde=b.typcde AND ROWNUM=1) AS FCPXX,  --箱型 t.typnme\n" +
            "        b.matcde AS FCZ,   --材质\n" +
            "        b.crrcde AS FKX,  --坑型\n" +
            "        t.accnum AS FSECQTY, --数量,\n" +
            "        (CASE t.objtyp WHEN 'CB' THEN ROUND(b.pacreage,6) ELSE ROUND(b.acreage,6) END) AS FHSL, --单个面积,t.acreage\n" +
            "        (CASE T.OBJTYP \n" +
            "            WHEN 'CB' THEN ROUND(t.accnum * ROUND(b.pacreage,6),6)\n" +
            "            ELSE ROUND(t.accnum * ROUND(b.acreage,6),6)\n" +
            "            END)  AS FAUXQTY, -- 合计面积, t.sumacr\n" +
            "        t.prices AS FPRICE, --单价,\n" +
            "        t.inprice AS FSquarePrice,-- 平方价\n" +
            "        --t.accamt AS FAMOUNT,--金额,\n" +
            "        (CASE t.objtyp WHEN 'CB' THEN ROUND(ROUND(t.accnum * ROUND(b.pacreage,6),6) * t.inprice,2) \n" +
            "            ELSE t.accamt \n" +
            "            END) AS FAMOUNT,--金额,\n" +
            "        d.fringl || 'x' || d.fringw AS FZBCC,--纸板尺寸,\n" +
            "        b.ctpono AS FPO,  --客户PONO\n" +
            "        ROUND(t.accnum * b.acreage *\n" +
            "            (SELECT i.ratios\n" +
            "                FROM pb_corrugate i\n" +
            "                WHERE i.clientid = t.clientid\n" +
            "                AND i.orgcde = t.orgcde\n" +
            "                AND i.crrcde = b.crrcde),0)  AS FBZMJ, -- 折算面积\n" +
            "        --b.osizel || '*' || b.osizew || '*' || b.osizeh FCKG,   -- 规格\n" +
            "        CASE t.objtyp \n" +
            "            WHEN 'CB' THEN  '平板' || ' ' || REPLACE(b.spcshw, 'x', '*')\n" +
            "            ELSE  b.cspecs || ' ' || REPLACE(t.specs, 'x', '*')\n" +
            "            END FCKG,   -- 规格 \n" +
            "        b.cspecs AS FPricingSize, -- 计价尺寸\n" +
            "        t.specs AS fcustsize,--t.specs 客户规格\n" +
            "        t.created AS fdate --时间\n" +
            "       ,'N' AS issync\n" +
            "       ,c.status\n" +
            "       ,b.address AS FDeliveryAddress\n" +
            "       ,(SELECT i.distance FROM pb_clnt_addr i WHERE i.clientid = t.clientid AND i.orgcde=t.orgcde AND i.clntcde=t.clntcde AND i.address=b.address AND ROWNUM=1 ) AS fkm\n" +
            "       ,(SELECT i.contact FROM pb_clnt_addr i WHERE i.clientid = t.clientid AND i.orgcde=t.orgcde AND i.clntcde=t.clntcde AND i.address=b.address AND ROWNUM=1 ) AS FContactPerson  --联系人\n" +
            "       ,(SELECT i.mobile FROM pb_clnt_addr i WHERE i.clientid = t.clientid AND i.orgcde=t.orgcde AND i.clntcde=t.clntcde AND i.address=b.address AND ROWNUM=1 ) AS  FContactNumber  --联系方式\n" +
            "       ,(CASE WHEN t.orgcde = '05' AND t.objtyp = 'CB' THEN '三期2.8米生产线'\n" +
            "            WHEN t.orgcde = '05' AND t.objtyp <> 'CB' THEN '三期水印联动线组'\n" +
            "            WHEN t.orgcde = '03' AND t.objtyp = 'CL' THEN '胶印车间'\n" +
            "            WHEN t.orgcde = '03' AND t.objtyp IN ('CC','CD','CT') THEN '水印车间'\n" +
            "            WHEN t.orgcde = '03' AND t.objtyp = 'CB' THEN \n" +
            "                (SELECT (CASE WHEN a.maccde = '1' THEN '七层生产线'\n" +
            "                    WHEN a.maccde = '3' THEN '一期2.5米新生产线'\n" +
            "                    WHEN a.maccde = '5' THEN '二层生产线'\n" +
            "                    END) \n" +
            "                    FROM  ord_processes a WHERE a.orgcde=t.orgcde AND a.serial=t.serial AND prctyp='01' AND ROWNUM=1)\n" +
            "            END)  FDeliveryDept   -- 交货单位\n" +
            "       ,b.ctpono || ' ' || b.sendrk  FNote   --送货备注\n" +
            "       ,(SELECT i.shdate FROM ord_ct_dat i WHERE i.orgcde=t.orgcde AND i.serial=t.serial AND ROWNUM=1) FDeliveryDate  --交货日期\n" +
            "       ,(SELECT i.smpnme FROM pb_clnt i WHERE i.orgcde=t.orgcde AND i.clntcde=t.clntcde AND ROWNUM=1) FCustShortName  --客户简称\n" +
            "FROM ord_bas t \n" +
            "    LEFT JOIN ord_ct b ON b.clientid=t.clientid AND b.orgcde=t.orgcde AND b.serial=t.serial\n" +
            "    LEFT JOIN ord_ct_sts c ON c.clientid=t.clientid AND c.orgcde=t.orgcde AND c.serial=t.serial\n" +
            "    LEFT JOIN ord_ct_cal d ON d.clientid=t.clientid AND d.orgcde=t.orgcde AND d.serial=t.serial \n" +
            "WHERE t.updated>sysdate-1 and t.orgcde in ('03','05')";
}
