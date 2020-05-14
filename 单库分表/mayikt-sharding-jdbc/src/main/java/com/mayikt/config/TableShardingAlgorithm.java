package com.mayikt.config;

import java.util.Collection;

import com.dangdang.ddframe.rdb.sharding.api.ShardingValue;
import com.dangdang.ddframe.rdb.sharding.api.strategy.table.SingleKeyTableShardingAlgorithm;

/**
 * @author Administrator
 */ // 表分片算法
public class TableShardingAlgorithm implements SingleKeyTableShardingAlgorithm<Long> {

	// sql 中关键字 匹配符为 =的时候，表的路由函数
	public String doEqualSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {
		for (String tableName : availableTargetNames) {
			if (tableName.endsWith(shardingValue.getValue() % 2 + "")) {
				return tableName;
			}
		}
		throw new IllegalArgumentException();
	}

	@Override
	public Collection<String> doInSharding(Collection<String> availableTargetNames, ShardingValue<Long> shardingValue) {

		return null;
	}

	@Override
	public Collection<String> doBetweenSharding(Collection<String> availableTargetNames,
			ShardingValue<Long> shardingValue) {

		return null;
	}

}
