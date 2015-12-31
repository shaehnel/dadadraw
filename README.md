# dada draw

Interactive group drawing

## Usage

Prerequisites: 
- Production: Java 8
- Development: Java 8, Maven 3

During development, run

    mvn spring-boot:run
    
Now visit http://localhost:8080/.

To package for production, run

    mvn package

For production use, run

    java -jar target/dadadraw-0.0.1-SNAPSHOT.jar

Now visit http://localhost:8080/.

## Issues

- (DONE) initial setup
- (OPEN) web application with one page where a user can draw in one color
- (OPEN) 2nd page where all current drawings are mirrored
- (OPEN) another page with configuration options such as available colors
- (OPEN) description on how this can be run in a dedicated WLAN

## Tech stack

Server part: Spring Boot
Drawing Part: Zwibbler
