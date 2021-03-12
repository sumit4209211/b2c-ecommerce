package com.lucenesearchecommerce.config;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories(basePackages = {"com.lucenesearchecommerce.repository"})
public class MongoDbConfig {

}