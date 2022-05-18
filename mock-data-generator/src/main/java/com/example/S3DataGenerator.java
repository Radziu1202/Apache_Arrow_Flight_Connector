package com.example;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class S3DataGenerator {

    private static final String ACCESS_KEY = "";
    private static final String SECRET_KEY = "";
    private static final String BUCKET_NAME = "";
    private static final String S3_CATALOG = "arrow/";
    private static final int OBJECTS_NUMBER = 100;

    public static void main(String[] args) throws JsonProcessingException {
        var credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

        var s3client = AmazonS3ClientBuilder.standard()
                                            .withCredentials(new AWSStaticCredentialsProvider(credentials))
                                            .withRegion(Regions.EU_CENTRAL_1)
                                            .build();
        var objectMapper = new ObjectMapper();
        var dataGenerator = new DataGenerator();
        var dataEntities = dataGenerator.generate(OBJECTS_NUMBER);

        for (var dataEntity : dataEntities) {
            String json = objectMapper.writeValueAsString(dataEntity);
            s3client.putObject(BUCKET_NAME, S3_CATALOG + dataEntity.getId(), json);
        }
    }
}
