package other.spring.ioc;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@PropertySource(value = {"classpath:db.properties","classpath:db_druid.properties"})
@Configuration
public class DataSourceConfig {
    private final long slowSqlMillis = 1000;
    private final Logger logger = Logger.getLogger(toString());

    @Resource
    private Environment env;

    @Bean(name = "comboPooledDataSource")
    public ComboPooledDataSource getComboPooledDataSource(){
        ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
        try {
            comboPooledDataSource.setUser(env.getProperty("jdbc.user"));
            comboPooledDataSource.setPassword(env.getProperty("jdbc.password"));
            comboPooledDataSource.setJdbcUrl(env.getProperty("jdbc.url"));
            comboPooledDataSource.setDriverClass(env.getProperty("jdbc.driver"));
            comboPooledDataSource.setMinPoolSize(Integer.parseInt(env.getProperty("jdbc.minPoolSize")));
            comboPooledDataSource.setInitialPoolSize(Integer.parseInt(env.getProperty("jdbc.initialPoolSize")));
            comboPooledDataSource.setMaxPoolSize(Integer.parseInt(env.getProperty("jdbc.maxPoolSize")));
            comboPooledDataSource.setMaxIdleTime(Integer.parseInt(env.getProperty("jdbc.maxIdleTime")));
        }catch (Exception e){
            logger.info(""+e.getMessage());
            e.printStackTrace();
        }
        return comboPooledDataSource;
    }

    @Bean(name = "druidDataSource")
    public DruidDataSource getDruidDataSource(){
        DruidDataSource dataSource = new DruidDataSource();
        List<Filter> filters = new ArrayList<Filter>();
        filters.add(getStatFilter());
        dataSource.setPassword(env.getProperty("druid.password"));
        dataSource.setUsername(env.getProperty("druid.user"));
        dataSource.setUrl(env.getProperty("druid.url"));
        dataSource.setDriverClassName(env.getProperty("druid.driverClassName"));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("druid.maxActive")));
        dataSource.setInitialSize(Integer.parseInt(env.getProperty("druid.initialSize")));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("druid.minIdle")));
        dataSource.setMaxWait(Integer.parseInt(env.getProperty("druid.maxWait")));
        dataSource.setTimeBetweenEvictionRunsMillis(Long.parseLong(env.getProperty("druid.timeBetweenEvictionRunsMillis")));
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(env.getProperty("druid.maxPoolPreparedStatementPerConnectionSize")));
        dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(env.getProperty("druid.minEvictableIdleTimeMillis")));
        dataSource.setProxyFilters(filters);
        return dataSource;
    }

    private StatFilter getStatFilter(){
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(slowSqlMillis);
        statFilter.setLogSlowSql(true);
        return statFilter;
    }

}
