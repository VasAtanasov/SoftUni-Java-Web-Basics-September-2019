# Lab: Introduction to Java EE

## West Compass Dealer Shop

West Compass Dealer Shop is an application in which you register cars, with several properties.

You will have to create a simple multi-Servlet application which has several pages and 1 object entity.

1 Data

This is the data layer of the application. There is 1 data object for you to implement.

Car

Create a Car class, which holds the following properties:

 - model – a String.
 - brand – a String.
 - year – an Integer.
 - engine – a String.
 
2 Home Servlet

Implement a Servlet, which listens on the index route (“/”). 

It should support only a GET request.

It should return the following HTML page, upon a `GET` request.

![site overview](../../z_resources/javaee-intro/media/image1.png)
 
3 Car Create Servlet

Implement a Servlet, which listens on route (“/create”). 

It should support `GET` & `POST` requests.

It should return the following HTML page, upon a `GET` request.

![site overview](../../z_resources/javaee-intro/media/image2.png)
 
4 All Cars Servlet

Implement a Servlet, which listens on route (“/all”).

It should support only a `GET` request.

It should return the following HTML page, upon a `GET` request.

![site overview](../../z_resources/javaee-intro/media/image3.png)

Each car show

![site overview](../../z_resources/javaee-intro/media/image4.png)

Document with tasks description: [Java-Web-Development-Basics-Introduction-to-Java-EE-Lab](../../z_resources/javaee-intro/05.Java-Web-Development-Basics-Introduction-to-Java-EE-Lab)
