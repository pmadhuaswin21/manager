# manager
a sample spring-boot application to manage product and sales

# Tech-stacks used
* Java21
* Spring-Boot 3.3.2
* mysql

## How to start the application?
* start a mysql server in local.If you are using docker use docker run --name manager-app -p 3306:3306 -e MYSQL_ROOT_PASSWORD=<password> -d mysql:latest
replace your password here https://github.com/pmadhuaswin21/manager/blob/master/src/main/resources/application.yml#L13
* Create a schema named ProductSales
* Build the application mvn clean install
* Run the application mvn spring-boot:run

## How to test
* Integrated with open api, we can test it directly using swagger ui with url http://localhost:8080/sales-manager/swagger-ui/index.html
* Test credentials can be found here https://github.com/pmadhuaswin21/manager/blob/master/src/main/java/com/sales/manager/config/SecurityConfiguration.java#L21