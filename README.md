# hotelbookingbackend
hotelbookingbackend a Java Springboot Demo REST Api for Hotelbooking

## General Info ====

  1) clone the Springboot Java8 REST project and go to the root folder containing the mvn pom and run:
  2) mvn clean install -Dmaven.test.skip=true
  3) mvn spring-boot:run

  The server runs on port 8085, and try the next url: http://localhost:8085/client
  
  There are three Spring Profiles: dev, prod and test
  
  The application profile is set to test, running on an H2 database with some preloaded data
  
  In case you'd like to go for another profile (dev or prod) you have to install a DB. See the src\main\resources\application.properties and the src\main\java\com\artsgard\hotelbookingbackend\DBConfig class for futher configuration.
  
  Note also the img repository path at application.properties file.upload-path=/Users/artsgard/Documents/upload-img.
  
  

The counterpart of this application is the Angular9 frontend application:

## hotelbookerfrontend

  
 
