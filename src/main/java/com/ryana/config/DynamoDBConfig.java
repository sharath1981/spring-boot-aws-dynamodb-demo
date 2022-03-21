package com.ryana.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class DynamoDBConfig {

    private final Environment environment;

    // @Value("${aws.dynamodb.endpoint}")
    // public String SERVICE_ENDPOINT;
    // @Value("${aws.dynamodb.region}")
    // public String REGION;
    // @Value("${aws.dynamodb.accessKey}")
    // public String ACCESS_KEY;
    // @Value("${aws.dynamodb.secretKey}")
    // public String SECRET_KEY;

    @Bean
    public DynamoDBMapper mapper() {
        return new DynamoDBMapper(amazonDynamoDBConfig());
    }

    private AmazonDynamoDB amazonDynamoDBConfig() {
        final var endpoint = environment.getProperty("aws.dynamodb.endpoint");
        final var region = environment.getProperty("aws.dynamodb.region");
        final var accessKey = environment.getProperty("aws.dynamodb.accessKey");
        final var secretKey = environment.getProperty("aws.dynamodb.secretKey");
        return AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endpoint, region))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();
    }
}
