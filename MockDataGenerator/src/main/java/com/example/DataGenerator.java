package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class DataGenerator {
    private static final String[] FIRST_NAMES = {
            "Noah", "Emma", "Liam", "Olivia", "Jacob", "Sophia", "William", "Isabella", "Mason", "Ava", "Ethan", "Mia",
            "Michael", "Abigail", "Alexander", "Emily", "James", "Charlotte", "Elijah", "Madison", "Benjamin", "Elizabeth",
            "Daniel", "Amelia", "Aiden", "Evelyn", "Logan", "Ella", "Jayden", "Chloe", "Matthew", "Harper", "Lucas",
            "Avery", "David", "Sofia", "Jackson", "Grace", "Joseph", "Addison", "Anthony", "Victoria", "Samuel", "Lily",
            "Joshua", "Natalie", "Gabriel", "Aubrey", "Andrew", "Lillian", "John", "Zoey", "Christopher", "Hannah",
            "Oliver", "Layla", "Dylan", "Brooklyn", "Carter", "Scarlett", "Isaac", "Zoe", "Luke", "Camila", "Henry",
            "Samantha", "Owen", "Riley", "Ryan", "Leah", "Nathan", "Aria", "Wyatt", "Savannah", "Caleb", "Audrey",
            "Sebastian", "Anna", "Jack", "Allison", "Christian", "Gabriella", "Jonathan", "Claire", "Julian", "Hailey",
            "Landon", "Penelope", "Levi", "Aaliyah", "Isaiah", "Sarah", "Hunter", "Nevaeh", "Aaron", "Kaylee", "Charles",
            "Stella", "Thomas", "Mila", "Eli", "Nora", "Jaxon", "Ellie", "Connor", "Bella", "Nicholas", "Lucy", "Jeremiah"
            , "Alexa", "Grayson", "Arianna", "Cameron", "Violet", "Brayden", "Ariana", "Adrian", "Genesis", "Evan",
            "Alexis", "Jordan", "Eleanor", "Josiah", "Maya", "Angel", "Caroline", "Robert", "Peyton", "Gavin", "Skylar",
            "Tyler", "Madelyn", "Austin", "Serenity", "Colton", "Kennedy", "Jose", "Taylor", "Dominic", "Alyssa",
            "Brandon", "Autumn", "Ian", "Paisley", "Lincoln", "Ashley", "Hudson", "Brianna", "Kevin", "Sadie", "Zachary",
            "Naomi", "Adam", "Kylie", "Mateo", "Julia", "Jason", "Sophie", "Chase", "Mackenzie", "Nolan", "Eva", "Ayden",
            "Gianna", "Cooper", "Luna", "Parker", "Katherine", "Xavier", "Hazel", "Asher", "Khloe", "Carson", "Ruby",
            "Jace", "Melanie", "Easton", "Piper", "Justin", "Lydia", "Leo", "Aubree", "Bentley", "Madeline", "Jaxson",
            "Aurora", "Nathaniel", "Faith", "Blake", "Alexandra", "Elias", "Alice", "Theodore", "Kayla", "Kayden",
            "Jasmine", "Luis", "Maria", "Tristan", "Annabelle", "Bryson", "Lauren", "Ezra", "Reagan", "Juan", "Elena",
            "Brody", "Rylee", "Vincent", "Isabelle", "Micah", "Bailey", "Miles", "Eliana", "Santiago", "Sydney", "Cole",
            "Makayla", "Ryder", "Cora", "Carlos", "Morgan", "Damian", "Natalia", "Leonardo", "Kimberly", "Roman", "Vivian",
            "Max", "Quinn", "Sawyer", "Valentina", "Jesus", "Andrea", "Diego", "Willow", "Greyson", "Clara", "Alex",
            "London", "Maxwell", "Jade", "Axel", "Liliana", "Eric", "Jocelyn", "Wesley", "Trinity", "Declan", "Kinsley",
            "Giovanni", "Brielle", "Ezekiel", "Mary", "Braxton", "Molly", "Ashton", "Hadley", "Ivan", "Delilah", "Hayden",
            "Emilia", "Camden", "Josephine", "Silas", "Brooke", "Bryce", "Ivy", "Weston", "Lilly", "Harrison", "Adeline",
            "Jameson", "Payton", "George", "Lyla", "Antonio", "Isla", "Timothy", "Jordyn", "Kaiden", "Paige", "Jonah",
            "Isabel", "Everett", "Mariah", "Miguel", "Mya", "Steven", "Nicole", "Richard", "Valeria", "Emmett", "Destiny",
            "Victor", "Rachel", "Kaleb", "Ximena", "Kai", "Emery", "Maverick", "Everly", "Joel", "Sara", "Bryan",
            "Angelina", "Maddox", "Adalynn", "Kingston", "Kendall", "Aidan", "Reese", "Patrick", "Aliyah", "Edward",
            "Margaret", "Emmanuel", "Juliana", "Jude", "Melody", "Alejandro", "Amy", "Preston", "Eden", "Luca", "Mckenzie",
            "Bennett", "Laila", "Jesse", "Vanessa", "Colin", "Ariel", "Jaden", "Gracie", "Malachi", "Valerie", "Kaden",
            "Adalyn", "Jayce", "Brooklynn", "Alan", "Gabrielle", "Kyle", "Kaitlyn", "Marcus", "Athena", "Brian", "Elise",
            "Ryker", "Jessica", "Grant", "Adriana", "Jeremy", "Leilani", "Abel", "Ryleigh", "Riley", "Daisy", "Calvin",
            "Nova", "Brantley", "Norah", "Caden", "Eliza", "Oscar", "Rose", "Abraham", "Rebecca", "Brady", "Michelle",
            "Sean", "Alaina", "Jake", "Catherine", "Tucker", "Londyn", "Nicolas", "Summer", "Mark", "Lila", "Amir",
            "Jayla", "Avery", "Katelyn", "King", "Daniela", "Gael", "Harmony", "Kenneth", "Alana", "Bradley", "Amaya",
            "Cayden", "Emerson", "Xander", "Julianna", "Graham", "Cecilia", "Rowan", "Izabella"
    };

    private static final String[] LAST_NAMES = {
            "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Anderson",
            "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia", "Martinez", "Robinson", "Clark",
            "Rodriguez", "Lewis", "Lee", "Walker", "Hall", "Allen", "Young", "Hernandez", "King", "Wright", "Lopez",
            "Hill", "Scott", "Green", "Adams", "Baker", "Gonzalez", "Nelson", "Carter", "Mitchell", "Perez", "Roberts",
            "Turner", "Phillips", "Campbell", "Parker", "Evans", "Edwards", "Collins", "Stewart", "Sanchez", "Morris",
            "Rogers", "Reed", "Cook", "Morgan", "Bell", "Murphy", "Bailey", "Rivera", "Cooper", "Richardson", "Cox",
            "Howard", "Ward", "Torres", "Peterson", "Gray", "Ramirez", "James", "Watson", "Brooks", "Kelly", "Sanders",
            "Price", "Bennett", "Wood", "Barnes", "Ross", "Henderson", "Coleman", "Jenkins", "Perry", "Powell", "Long",
            "Patterson", "Hughes", "Flores", "Washington", "Butler", "Simmons", "Foster", "Gonzales", "Bryant",
            "Alexander", "Russell", "Griffin", "Diaz", "Hayes"
    };

    private final Random random;

    public DataGenerator() {
        this.random = new Random();
    }

    List<DataEntity> generate(int size) {
        return IntStream.rangeClosed(0, size - 1)
                        .parallel()
                        .mapToObj(this::generateDataEntity)
                        .toList();
    }

    private DataEntity generateDataEntity(int id) {
        return DataEntity.builder()
                         .id(id)
                         .firstName(FIRST_NAMES[random.nextInt(FIRST_NAMES.length)])
                         .lastName(LAST_NAMES[random.nextInt(LAST_NAMES.length)])
                         .phoneNumbers(generatePhoneNumbers())
                         .age(random.nextInt(120))
                         .income(random.nextDouble(100_000))
                         .houseLocation(DataEntity.HouseLocation.builder()
                                                                .longitude(random.nextDouble(100))
                                                                .latitude(random.nextDouble(100))
                                                                .build())
                         .build();
    }

    private List<String> generatePhoneNumbers() {
        int count = random.nextInt(1, 5);
        var phoneNumbers = new ArrayList<String>(5);
        for (int i = 0; i < count; i++) {
            int number = random.nextInt(200_000_000) + 500_000_000;
            phoneNumbers.add("+48" + number);
        }
        return phoneNumbers;
    }

}