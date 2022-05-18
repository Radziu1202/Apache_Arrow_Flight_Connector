package com.example;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DataEntity {

    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private List<String> phoneNumbers;
    private double income;
    private HouseLocation houseLocation;

    @Data
    @Builder
    public static class HouseLocation {
        private double longitude;
        private double latitude;

        @Override
        public String toString() {
            return "{\"longitude\": %s, \"latitude\": %s}"
                    .formatted(longitude, latitude);
        }
    }

}
