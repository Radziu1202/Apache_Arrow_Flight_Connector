package com.example;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3DataGenerator {

    private static final String ACCESS_KEY = "";
    private static final String SECRET_KEY = "";
    private static final String BUCKET_NAME = "";
    private static final String S3_CATALOG = "arrow/";
    private static final int OBJECTS_NUMBER = 10_000;
    private static final int ROW_IN_FILE = 10;

    public static void main(String[] args) {
        var credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

        var s3client = AmazonS3ClientBuilder.standard()
                                            .withCredentials(new AWSStaticCredentialsProvider(credentials))
                                            .withRegion(Regions.EU_CENTRAL_1)
                                            .build();
        var dataGenerator = new DataGenerator();
        var dataEntities = dataGenerator.generate(OBJECTS_NUMBER);

        int i = 0;
        StringBuilder sb = new StringBuilder();
        for (var dataEntity : dataEntities) {
            if (i == 0) {
                sb = new StringBuilder();
                sb.append("id,firstName,lastName,age,phoneNumbers,income,houseLocation\n");
            }
            String csv = dataEntity.toString();
            sb.append(csv);
            i++;
            if (i == ROW_IN_FILE) {
                s3client.putObject(BUCKET_NAME, S3_CATALOG + (dataEntity.getId() - ROW_IN_FILE), sb.toString());
            }
        }
        System.out.println("Finish");
    }
}
