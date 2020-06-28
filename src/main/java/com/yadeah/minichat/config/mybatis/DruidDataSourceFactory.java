package com.yadeah.minichat.config.mybatis;

import com.alibaba.druid.pool.DruidDataSource;
import com.yadeah.minichat.common.utils.CryptoUtils;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

public class DruidDataSourceFactory implements DataSourceFactory {
    private Properties props;

    @Override
    public void setProperties(Properties props) {
        this.props = props;
    }

    @Override
    public DataSource getDataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setDriverClassName(this.props.getProperty("driver"));
        druidDataSource.setUrl(this.props.getProperty("url"));
        druidDataSource.setUsername(this.props.getProperty("username"));
        druidDataSource.setPassword(CryptoUtils.AESDecrypt(this.props.getProperty("password")));
        int initialSize = Integer.parseInt(this.props.getProperty("initialSize", "30"));
        druidDataSource.setInitialSize(initialSize);

        int maxActive = Integer.parseInt(this.props.getProperty("maxActive", "30"));
        druidDataSource.setMaxActive(maxActive);

        int minIdle = Integer.parseInt(this.props.getProperty("minIdle", "5"));
        druidDataSource.setMinIdle(minIdle);
        try {
            druidDataSource.init();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return druidDataSource;
    }
}
