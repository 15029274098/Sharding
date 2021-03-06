package com.mayikt.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.dangdang.ddframe.rdb.sharding.api.ShardingDataSourceFactory;
import com.dangdang.ddframe.rdb.sharding.api.rule.DataSourceRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.ShardingRule;
import com.dangdang.ddframe.rdb.sharding.api.rule.TableRule;
import com.dangdang.ddframe.rdb.sharding.api.strategy.database.DatabaseShardingStrategy;
import com.dangdang.ddframe.rdb.sharding.id.generator.IdGenerator;
import com.dangdang.ddframe.rdb.sharding.id.generator.self.CommonSelfIdGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 数据源相关配置信息
@Configuration
public class DataSourceConfig {
	@Value("${spring.jdbc.db0.className}")
	private String className;
	@Value("${spring.jdbc.db0.url}")
	private String url;
	@Value("${spring.jdbc.db0.username}")
	private String username;
	@Value("${spring.jdbc.db0.password}")
	private String password;

	@Bean
	public IdGenerator getIdGenerator() {
		return new CommonSelfIdGenerator();
	}

	@Bean
	public DataSource getDataSource() {
		return buildDataSource();
	}

	private DataSource buildDataSource() {
		// 1.设置分库映射
		Map<String, DataSource> dataSourceMap = new HashMap<>(2);
		dataSourceMap.put("userdb_0", createDataSource("userdb_0"));
		dataSourceMap.put("userdb_1", createDataSource("userdb_1"));
		// 设置默认db为ds_0，也就是为那些没有配置分库分表策略的指定的默认库
		// 如果只有一个库，也就是不需要分库的话，map里只放一个映射就行了，只有一个库时不需要指定默认库，
		// 但2个及以上时必须指定默认库，否则那些没有配置策略的表将无法操作数据
		DataSourceRule rule = new DataSourceRule(dataSourceMap, "userdb_1");
		// 2.设置分表映射，将t_order_0和t_order_1两个实际的表映射到t_order逻辑表
		TableRule orderTableRule = TableRule.builder("t_order").dataSourceRule(rule).build();
		// 3.具体的分库分表策略
		ShardingRule shardingRule = ShardingRule.builder().dataSourceRule(rule)
				.tableRules(Arrays.asList(orderTableRule))
				.databaseShardingStrategy(new DatabaseShardingStrategy("user_id", new DatabaseShardingAlgorithm()))
				.build();
		// 创建数据源
		DataSource dataSource = ShardingDataSourceFactory.createDataSource(shardingRule);
		return dataSource;
	}

	private DataSource createDataSource(String dataSourceName) {
		// 使用druid连接数据库
		DruidDataSource druidDataSource = new DruidDataSource();
		druidDataSource.setDriverClassName(className);
		druidDataSource.setUrl(String.format(url, dataSourceName));
		druidDataSource.setUsername(username);
		druidDataSource.setPassword(password);
		return druidDataSource;
	}
}