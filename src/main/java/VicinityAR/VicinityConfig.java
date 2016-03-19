package VicinityAR;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by varuna on 3/18/16.
 */
@org.springframework.context.annotation.Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableScheduling
@PropertySource(value = {"classpath:/mongodb.properties"},ignoreResourceNotFound = false)
public class VicinityConfig {

    @Value("${mongo.uri}")
    String mongoURI;

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoClientURI uri = new MongoClientURI(mongoURI);
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(new MongoClient(uri), "vicinity");
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory);
        return mongoTemplate;
    }
}
