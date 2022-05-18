package com.example;

import java.sql.DriverManager;
import java.sql.SQLException;


public class PostgresDataGenerator {

    private static final String URL = "jdbc:postgresql://localhost:5432/arrow";
    private static final String USER = "arrow";
    private static final String PASSWORD = "mysecretpassword";
    private static final int OBJECTS_NUMBER = 10_000;


    public static void main(String[] args) {
        var sqlInsert = """
                INSERT INTO arrow(id, first_name, last_name, age, phone_numbers, income, house_location)
                VALUES(?, ?, ?, ?, ?, ?, ?)
                """;

        var dataGenerator = new DataGenerator();
        var dataEntities = dataGenerator.generate(OBJECTS_NUMBER);

        try (var connection = DriverManager.getConnection(URL, USER, PASSWORD);
             var preparedStatement = connection.prepareStatement(sqlInsert)) {

            for (var dataEntity : dataEntities) {
                preparedStatement.setInt(1, dataEntity.getId());
                preparedStatement.setString(2, dataEntity.getFirstName());
                preparedStatement.setString(3, dataEntity.getLastName());
                preparedStatement.setInt(4, dataEntity.getAge());
                preparedStatement.setArray(5, connection.createArrayOf("varchar", dataEntity.getPhoneNumbers().toArray()));
                preparedStatement.setDouble(6, dataEntity.getIncome());
                preparedStatement.setString(7, dataEntity.getHouseLocation().toString());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        System.out.println("Finish");
    }
}
