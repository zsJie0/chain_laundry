package com.laundry.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootTest
public class DataSourceTest {
    @Autowired
    DataSource dataSource;
    @Test
    void test() throws SQLException {
        System.out.println("获取数据库的连接为："+dataSource.getConnection());
    }
}
