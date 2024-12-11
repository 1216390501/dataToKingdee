package com.example.webapp.service;

import com.example.webapp.Dao.saleBillDaoInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Component
public class RDBConnectionUtil2 {
//
//    @Autowired
//    private DataSource dataSource;

    public Connection getRDBConnection() {
        String driverName = null;
        String url = null;

        driverName = "oracle.jdbc.OracleDriver";
        url = "jdbc:oracle:thin:@//36.134.103.106:1521/dbms?currentSchema=FERP";

        // 如果密码需要解密等处理，可参考之前示例代码添加相应逻辑
        // 此处假设密码无需额外处理，直接使用配置中的密码
        return init(driverName, url, "read", "ejsh.read");
    }

    private Connection init(String driverName, String url, String user, String password) {
        Connection conn = null;
        try {
            Class.forName(driverName);
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public List<Map<String, String>> resultSetToList(ResultSet rs) throws java.sql.SQLException {
        if (rs == null)
            return Collections.emptyList();
        ResultSetMetaData md = rs.getMetaData(); // 得到结果集(rs)的结构信息，比如字段数、字段名等
        int columnCount = md.getColumnCount(); // 返回此 ResultSet 对象中的列数
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> rowData;
        while (rs.next()) {
            rowData = new HashMap<>(columnCount);
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i).toUpperCase(), rs.getString(i));
            }
            list.add(rowData);
        }
        return list;
    }
}
