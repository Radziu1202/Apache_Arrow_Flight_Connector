package com.example;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.pojo.PojoCodecProvider;

import java.util.List;

import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class MongoDataGenerator {

    private static final String USER = "arrow";
    private static final String PASSWORD = "mysecretpassword";
    private static final String HOST = "localhost";
    private static final int PORT = 27017;
    private static final String DATABASE = "arrow";
    private static final String COLLECTION = "arrow";
    private static final int OBJECTS_NUMBER = 10_000;

    public static void main(String[] args) {
        var dataGenerator = new DataGenerator();
        var dataEntities = dataGenerator.generate(OBJECTS_NUMBER);

        var codecProvider = PojoCodecProvider.builder().register("com.example").build();
        var codecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(codecProvider));
        var credential = MongoCredential.createCredential(USER, DATABASE, PASSWORD.toCharArray());
        var setting = MongoClientSettings.builder()
                                         .applyToClusterSettings(builder -> builder.hosts(List.of(new ServerAddress(HOST, PORT))))
                                         .credential(credential)
                                         .codecRegistry(codecRegistry)
                                         .build();

        try (MongoClient mongoClient = MongoClients.create(setting)) {
            MongoDatabase database = mongoClient.getDatabase(DATABASE);
            MongoCollection<DataEntity> collection = database.getCollection(COLLECTION, DataEntity.class);
            collection.insertMany(dataEntities);
        }
        System.out.println("Finish");
    }
}
