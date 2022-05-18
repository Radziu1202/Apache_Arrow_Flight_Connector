# Mock source data generator

## Requirements
- `maven`

## Build
```
mvn clean install
```

## Run
```
java -cp com.example.MongoDataGenerator -jar target/MockDataGenerator-1.0-SNAPSHOT.jar
java -cp com.example.PostgresDataGenerator -jar target/MockDataGenerator-1.0-SNAPSHOT.jar
java -cp com.example.S3DataGenerator -jar target/MockDataGenerator-1.0-SNAPSHOT.jar
```
