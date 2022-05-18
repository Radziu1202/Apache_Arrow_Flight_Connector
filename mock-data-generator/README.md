# Mock source data generator

## Requirements
- Maven

```
sudo apt install maven
```

- Java 17
```
sudo add-apt-repository ppa:linuxuprising/java
sudo apt update
sudo apt install oracle-java17-installer
```

## Build
```
mvn clean install
```

## Run
```
java -cp target/MockDataGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar com.example.MongoDataGenerator
java -cp target/MockDataGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar com.example.PostgresDataGenerator
java -cp target/MockDataGenerator-1.0-SNAPSHOT-jar-with-dependencies.jar com.example.S3DataGenerator 
```
