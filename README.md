# Pet-Care-Website
<div>
    <img src="https://github.com/kingstonduy/Pet-Care-Website/blob/main/DATA/images/Dog-logo.png?raw=true" width='40%' height='40%'/>
    <img src="https://raw.githubusercontent.com/kingstonduy/Pet-Care-Website/84c250c723b7bf84503958a6bf33b01b8074d7d1/DATA/images/Logo-HCMIU.svg" width='40%' height='40%'/>
 </div>

---------------
## Requirement
+ [java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
+ [javac 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
+ [mysql](https://www.mysql.com/)
+ [npm](https://www.npmjs.com/)

If you are using Ubuntu or Linux distribution
```
sudo apt-get update
sudo apt-get upgrade
sudo apt install openjdk-17-jdk openjdk-17-jre
sudo apt install nodejs
sudo apt install npm
sudo apt install mysql-server
sudo systemctl start mysql.service
```
Then reffer to this [blog](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04) to set up mysql

---------------
## How to run
+ Clone the repo
+ Config the database port, username, password at 
[application.properties](/API/petcare/src/main/resources/application.properties)

+ We havent figue out the way to auto generate schema and tables in springboot for mysql. So please create schema "petcare" then paste the prepared queries at 
[data.sql](/API/petcare/src/main/resources/data.sql) into query of mysql 

+ Then in the CLI:

    ```
        # start front end
        cd petcareapp
        npm install
        npm install react-scripts --save
        npm start

    ```

    ```
        # start bacnkend
        cd API/petcare
        mvn clean install package
        java -jar target/petcare-0.0.1-SNAPSHOT.jar

    ```

---------------

## Highlights
- Provide a large quantity of products consisting of various types (food, toys, fashion), targeting different kinds of pets (dog, cat, bird).
- Provide booking service everywhere everytime.
- The information of the products is described clealy.
- Discover the ultimate pet care experience with our innovative web application, designed to cater to all your furry friends' needs
- Experience the convenience of managing your pet's health, nutrition, and activities effortlessly through our intuitive web application, dedicated to simplifying pet care.
- Join a thriving community of pet lovers and unlock a world of pet care possibilities with our cutting-edge web application, empowering you to give your beloved companions the best care they deserve.
-----------------

## Tech stack
- [Spring Boot](https://spring.io/projects/spring-boot/): framework back-end helps building web application and restful API.
- [ReactJS](https://reactjs.org): A JavaScript library helps to develope user interface.
- [Figma](https://www.figma.com/file/Df5p9OXAeNaJZ2zIOWXiEb/mew-Pet-care-landing-page-(Community)?type=design&node-id=0%3A1&t=jZTtFVkTb2W9ajBI-1): A tool design UX/UI - develop prototype interface.
- [Talent API Tester](https://chrome.google.com/webstore/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm): API manipulation tool - helps test various APIs.
- [Postman](https://www.postman.com/): API manipulation tool - helps test various APIs.
- [My SQL](https://www.mysql.com/): "Database management system - facilitates website data management."
- [Swagger UI](https://swagger.io/tools/swagger-ui/): Provide document for api making it easy for back end implementation and client side consumption.

-----------------

## Web Architucture

- #### Web Architucture
![WebArchitucture](https://github.com/kingstonduy/Pet-Care-Website/blob/main/DATA/images/Architecture.png?raw=true)

-----------------


## Some hightlights user interface

- #### HomePage
![homepage](https://github.com/kingstonduy/Pet-Care-Website/blob/main/DATA/images/Homepage.png?raw=true)

<br/>

- #### Service
![Service](https://github.com/kingstonduy/Pet-Care-Website/blob/main/DATA/images/Service.png?raw=true)

<br/>

- #### Product Shop
![Product Shop](https://github.com/kingstonduy/Pet-Care-Website/blob/main/DATA/images/Products.png?raw=true)

-----------------


- #### Cart
![Cart](https://github.com/kingstonduy/Pet-Care-Website/blob/main/DATA/images/Cart.png?raw=true)


-----------------


- #### Detailed Product
![Detailed Product](https://github.com/kingstonduy/Pet-Care-Website/blob/main/DATA/images/Detail-Product.png?raw=true)





