package com.rogerhr.boatapp.config;

import com.rogerhr.boatapp.model.Boat;
import com.rogerhr.boatapp.repository.BoatRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class BoatDataLoader implements CommandLineRunner {

  private final BoatRepository boatRepository;

  public BoatDataLoader(BoatRepository boatRepository) {
    this.boatRepository = boatRepository;
  }

  @Override
  public void run(String... args) throws Exception {
    // Optionally clear the existing data
    boatRepository.deleteAll(); // Uncomment to delete existing boats before adding new ones

    // Create and save sample boats
    Boat boat1 = new Boat();
    boat1.setName("Sailboat One");
    boat1.setDescription("A beautiful sailboat.");
    boat1.setYear(2010);
    boat1.setLength(25.0);
    boat1.setOwnerName("Alice Smith");
    boat1.setPrice(15000.0);
    boat1.setRegistrationNumber("REG12345");

    Boat boat2 = new Boat();
    boat2.setName("Motorboat Two");
    boat2.setDescription("Fast motorboat.");
    boat2.setYear(2015);
    boat2.setLength(30.0);
    boat2.setOwnerName("Bob Johnson");
    boat2.setPrice(30000.0);
    boat2.setRegistrationNumber("REG67890");

    Boat boat3 = new Boat();
    boat3.setName("Yacht Three");
    boat3.setDescription("Luxury yacht.");
    boat3.setYear(2021);
    boat3.setLength(60.0);
    boat3.setOwnerName("Carol Davis");
    boat3.setPrice(150000.0);
    boat3.setRegistrationNumber("REG11223");

    // Add more boats up to twenty
    Boat boat4 = new Boat();
    boat4.setName("Fishing Boat Four");
    boat4.setDescription("Best for fishing trips.");
    boat4.setYear(2012);
    boat4.setLength(20.0);
    boat4.setOwnerName("David Brown");
    boat4.setPrice(10000.0);
    boat4.setRegistrationNumber("REG54321");

    Boat boat5 = new Boat();
    boat5.setName("Cruise Boat Five");
    boat5.setDescription("Spacious cruise boat.");
    boat5.setYear(2020);
    boat5.setLength(40.0);
    boat5.setOwnerName("Emma Wilson");
    boat5.setPrice(75000.0);
    boat5.setRegistrationNumber("REGABCDE");

    // You can similarly create more boats
    Boat boat6 = new Boat();
    boat6.setName("Sailboat Six");
    boat6.setDescription("Another sailboat.");
    boat6.setYear(2008);
    boat6.setLength(22.0);
    boat6.setOwnerName("Frank Martinez");
    boat6.setPrice(20000.0);
    boat6.setRegistrationNumber("REG67845");

    Boat boat7 = new Boat();
    boat7.setName("Luxury Yacht Seven");
    boat7.setDescription("High-end yacht experience.");
    boat7.setYear(2021);
    boat7.setLength(80.0);
    boat7.setOwnerName("Grace Lee");
    boat7.setPrice(300000.0);
    boat7.setRegistrationNumber("REG23456");

    Boat boat8 = new Boat();
    boat8.setName("Speed Boat Eight");
    boat8.setDescription("Quick and agile.");
    boat8.setYear(2019);
    boat8.setLength(28.0);
    boat8.setOwnerName("Hannah Young");
    boat8.setPrice(50000.0);
    boat8.setRegistrationNumber("REG13579");

    Boat boat9 = new Boat();
    boat9.setName("Barge Nine");
    boat9.setDescription("Cargo barge.");
    boat9.setYear(2010);
    boat9.setLength(100.0);
    boat9.setOwnerName("Ian Kim");
    boat9.setPrice(25000.0);
    boat9.setRegistrationNumber("REG24680");

    Boat boat10 = new Boat();
    boat10.setName("Dinghy Ten");
    boat10.setDescription("Small and light.");
    boat10.setYear(2022);
    boat10.setLength(15.0);
    boat10.setOwnerName("Jack Robinson");
    boat10.setPrice(3000.0);
    boat10.setRegistrationNumber("REG35791");

    Boat boat11 = new Boat();
    boat11.setName("Catamaran Eleven");
    boat11.setDescription("Two hulls for stability.");
    boat11.setYear(2018);
    boat11.setLength(35.0);
    boat11.setOwnerName("Kathy Hall");
    boat11.setPrice(85000.0);
    boat11.setRegistrationNumber("REG11882");

    Boat boat12 = new Boat();
    boat12.setName("Trawler Twelve");
    boat12.setDescription("Good for long voyages.");
    boat12.setYear(2015);
    boat12.setLength(50.0);
    boat12.setOwnerName("Leo Bennett");
    boat12.setPrice(65000.0);
    boat12.setRegistrationNumber("REG98244");

    Boat boat13 = new Boat();
    boat13.setName("Houseboat Thirteen");
    boat13.setDescription("Live aboard your boat.");
    boat13.setYear(2016);
    boat13.setLength(30.0);
    boat13.setOwnerName("Mia White");
    boat13.setPrice(120000.0);
    boat13.setRegistrationNumber("REG44683");

    Boat boat14 = new Boat();
    boat14.setName("Viking Ship Fourteen");
    boat14.setDescription("Replicated historical design.");
    boat14.setYear(2007);
    boat14.setLength(40.0);
    boat14.setOwnerName("Noah Clark");
    boat14.setPrice(200000.0);
    boat14.setRegistrationNumber("REG66774");

    Boat boat15 = new Boat();
    boat15.setName("Battle Ship Fifteen");
    boat15.setDescription("For historical exhibitions.");
    boat15.setYear(2013);
    boat15.setLength(80.0);
    boat15.setOwnerName("Olivia Smith");
    boat15.setPrice(500000.0);
    boat15.setRegistrationNumber("REG99871");

    Boat boat16 = new Boat();
    boat16.setName("Barquentine Sixteen");
    boat16.setDescription("Classic sailing ship.");
    boat16.setYear(2005);
    boat16.setLength(45.0);
    boat16.setOwnerName("Paul Turner");
    boat16.setPrice(150000.0);
    boat16.setRegistrationNumber("REG13457");

    Boat boat17 = new Boat();
    boat17.setName("Cutter Seventeen");
    boat17.setDescription("Perfect for smaller sail trips.");
    boat17.setYear(2019);
    boat17.setLength(30.0);
    boat17.setOwnerName("Quinn Anderson");
    boat17.setPrice(30000.0);
    boat17.setRegistrationNumber("REG00976");

    Boat boat18 = new Boat();
    boat18.setName("Clipper Eighteen");
    boat18.setDescription("Fast sailing.");
    boat18.setYear(2014);
    boat18.setLength(50.0);
    boat18.setOwnerName("Riley Thomas");
    boat18.setPrice(100000.0);
    boat18.setRegistrationNumber("REG27579");

    Boat boat19 = new Boat();
    boat19.setName("Drifter Nineteen");
    boat19.setDescription("Lightweight and swift.");
    boat19.setYear(2020);
    boat19.setLength(20.0);
    boat19.setOwnerName("Sophie Taylor");
    boat19.setPrice(25000.0);
    boat19.setRegistrationNumber("REG08637");

    Boat boat20 = new Boat();
    boat20.setName("Longliner Twenty");
    boat20.setDescription("Designed for catching large fish.");
    boat20.setYear(2021);
    boat20.setLength(65.0);
    boat20.setOwnerName("Tyler Lewis");
    boat20.setPrice(120000.0);
    boat20.setRegistrationNumber("REG49382");

    Boat boat21 = new Boat();
    boat21.setName("Explorer Twenty-One");
    boat21.setDescription("An adventurous explorer yacht.");
    boat21.setYear(2018);
    boat21.setLength(45.0);
    boat21.setOwnerName("Ursula King");
    boat21.setPrice(95000.0);
    boat21.setRegistrationNumber("REG72101");

    Boat boat22 = new Boat();
    boat22.setName("Driftwood Twenty-Two");
    boat22.setDescription("Perfect for relaxing weekend getaways.");
    boat22.setYear(2017);
    boat22.setLength(22.0);
    boat22.setOwnerName("Victor Shaw");
    boat22.setPrice(22000.0);
    boat22.setRegistrationNumber("REG72102");

    Boat boat23 = new Boat();
    boat23.setName("Sea Breeze Twenty-Three");
    boat23.setDescription("Enjoy the sea breeze with ease.");
    boat23.setYear(2010);
    boat23.setLength(28.0);
    boat23.setOwnerName("Wendy Palmer");
    boat23.setPrice(35000.0);
    boat23.setRegistrationNumber("REG72103");

    Boat boat24 = new Boat();
    boat24.setName("Regatta Twenty-Four");
    boat24.setDescription("Competitively designed for racing.");
    boat24.setYear(2019);
    boat24.setLength(30.0);
    boat24.setOwnerName("Xavier Cole");
    boat24.setPrice(60000.0);
    boat24.setRegistrationNumber("REG72104");

    Boat boat25 = new Boat();
    boat25.setName("Mariner Twenty-Five");
    boat25.setDescription("A boat for the serious mariner.");
    boat25.setYear(2015);
    boat25.setLength(35.0);
    boat25.setOwnerName("Yvonne Scott");
    boat25.setPrice(40000.0);
    boat25.setRegistrationNumber("REG72105");

    Boat boat26 = new Boat();
    boat26.setName("Nautilus Twenty-Six");
    boat26.setDescription("Explore the depths with Nautilus.");
    boat26.setYear(2021);
    boat26.setLength(50.0);
    boat26.setOwnerName("Zachary Allen");
    boat26.setPrice(80000.0);
    boat26.setRegistrationNumber("REG72106");

    Boat boat27 = new Boat();
    boat27.setName("Titan Twenty-Seven");
    boat27.setDescription("Titan is made for strength and durability.");
    boat27.setYear(2020);
    boat27.setLength(60.0);
    boat27.setOwnerName("Anita Bishop");
    boat27.setPrice(200000.0);
    boat27.setRegistrationNumber("REG72107");

    Boat boat28 = new Boat();
    boat28.setName("Freedom Twenty-Eight");
    boat28.setDescription("Experience the freedom of the sea.");
    boat28.setYear(2016);
    boat28.setLength(35.0);
    boat28.setOwnerName("Bobbie Page");
    boat28.setPrice(55000.0);
    boat28.setRegistrationNumber("REG72108");

    Boat boat29 = new Boat();
    boat29.setName("Blue Water Twenty-Nine");
    boat29.setDescription("Stylish and comfortable for long voyages.");
    boat29.setYear(2014);
    boat29.setLength(55.0);
    boat29.setOwnerName("Carlos Martinez");
    boat29.setPrice(120000.0);
    boat29.setRegistrationNumber("REG72109");

    Boat boat30 = new Boat();
    boat30.setName("Speedster Thirty");
    boat30.setDescription("Experience speed like never before.");
    boat30.setYear(2019);
    boat30.setLength(28.0);
    boat30.setOwnerName("Diana Rizwan");
    boat30.setPrice(75000.0);
    boat30.setRegistrationNumber("REG72110");

    Boat boat31 = new Boat();
    boat31.setName("Adventure Thirty-One");
    boat31.setDescription("Adventure awaits on the Adventure boat.");
    boat31.setYear(2022);
    boat31.setLength(40.0);
    boat31.setOwnerName("Ethan Cooper");
    boat31.setPrice(85000.0);
    boat31.setRegistrationNumber("REG72111");

    Boat boat32 = new Boat();
    boat32.setName("Voyager Thirty-Two");
    boat32.setDescription("Voyage around the world.");
    boat32.setYear(2018);
    boat32.setLength(38.0);
    boat32.setOwnerName("Fiona Lee");
    boat32.setPrice(60000.0);
    boat32.setRegistrationNumber("REG72112");

    Boat boat33 = new Boat();
    boat33.setName("Seafarer Thirty-Three");
    boat33.setDescription("A reliable companion for sea voyages.");
    boat33.setYear(2020);
    boat33.setLength(42.0);
    boat33.setOwnerName("George Patterson");
    boat33.setPrice(90000.0);
    boat33.setRegistrationNumber("REG72113");

    Boat boat34 = new Boat();
    boat34.setName("Ocean Star Thirty-Four");
    boat34.setDescription("Bright and beautiful, just like the ocean.");
    boat34.setYear(2021);
    boat34.setLength(30.0);
    boat34.setOwnerName("Helen Garcia");
    boat34.setPrice(65000.0);
    boat34.setRegistrationNumber("REG72114");

    Boat boat35 = new Boat();
    boat35.setName("Heritage Thirty-Five");
    boat35.setDescription("Classic design meets modern technology.");
    boat35.setYear(2015);
    boat35.setLength(36.0);
    boat35.setOwnerName("Isaac Carter");
    boat35.setPrice(78000.0);
    boat35.setRegistrationNumber("REG72115");

    Boat boat36 = new Boat();
    boat36.setName("Lighthouse Thirty-Six");
    boat36.setDescription("Guiding you home.");
    boat36.setYear(2005);
    boat36.setLength(50.0);
    boat36.setOwnerName("Jennifer Wilson");
    boat36.setPrice(30000.0);
    boat36.setRegistrationNumber("REG72116");

    Boat boat37 = new Boat();
    boat37.setName("The Explorer Thirty-Seven");
    boat37.setDescription("Meet all adventurers' needs.");
    boat37.setYear(2021);
    boat37.setLength(70.0);
    boat37.setOwnerName("Karl Mitchell");
    boat37.setPrice(150000.0);
    boat37.setRegistrationNumber("REG72117");

    Boat boat38 = new Boat();
    boat38.setName("Rescue Boat Thirty-Eight");
    boat38.setDescription("Always ready to help those in need.");
    boat38.setYear(2010);
    boat38.setLength(20.0);
    boat38.setOwnerName("Lisa Reed");
    boat38.setPrice(25000.0);
    boat38.setRegistrationNumber("REG72118");

    Boat boat39 = new Boat();
    boat39.setName("Charter Boat Thirty-Nine");
    boat39.setDescription("For cozy and memorable water trips.");
    boat39.setYear(2016);
    boat39.setLength(45.0);
    boat39.setOwnerName("Mike Anderson");
    boat39.setPrice(55000.0);
    boat39.setRegistrationNumber("REG72119");

    Boat boat40 = new Boat();
    boat40.setName("Bounty Forty");
    boat40.setDescription("The boat that fulfills your wildest dreams.");
    boat40.setYear(2019);
    boat40.setLength(65.0);
    boat40.setOwnerName("Natalie Phillips");
    boat40.setPrice(120000.0);
    boat40.setRegistrationNumber("REG72120");

    // Save all boats into the repository
    boatRepository.saveAll(Arrays.asList(
        boat1, boat2, boat3, boat4, boat5,
        boat6, boat7, boat8, boat9, boat10,
        boat11, boat12, boat13, boat14, boat15,
        boat16, boat17, boat18, boat19, boat20,
        boat21, boat22, boat23, boat24, boat25,
        boat26, boat27, boat28, boat29, boat30,
        boat31, boat32, boat33, boat34, boat35,
        boat36, boat37, boat38, boat39, boat40));
  }
}
