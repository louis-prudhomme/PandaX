# PandaX

This is the project we have been asked to realize for our **Application Interoperability with Web Services** course @ Efrei Paris.

It is a fairly simple application we created in about two weeks ; it essentially is a user media repository REST API in which users can add their possessions and comments.

# Technical requirements

This projects needs
- [Java > 11](https://www.oracle.com/technetwork/java/javase/downloads/jdk13-downloads-5672538.html) 
  - the project is configured to target version 11
- A MySQL database with the [PandaX script](./resources/pandax.sql)
  - a user must be provided ; `persistence.xml` file expects `test|password` as a userfor the database `pandax`
- [Tomcat > 8.5](https://tomcat.apache.org/download-90.cgi)
  - previous versions have compatibility issues with hibernate

# Tech

We made several choices regarding our technical stack ;
- Use Maven to ease interoperability between team members, OSes and IDEs
- Use Java 11, the current Java LTS version
- Use Tomcat
- Use Hibernate as a JPA provider
- Use [JJWT](https://github.com/jwtk/jjwt) as our JWT provider
- Use Postman
- Use MySQL
- Use JAX-RS

# What our project does

It is possible to CRUD publishers, media types, users, medias, comments and user possessions.

Some more requests where made to select a certain type of resource rergarding another (like all medias created by a certain user).

Finally, all our routes were successfully tested on Postman.

A token must be requested and issued **before each request** to authenticate the user (see [the appropriate section](#postman)).

# Postman

All the Postman requests are sorted by resource.

They are made to be chained and thus they memorize their result (one way or another) into variables.

At the beginning, there are three variables :
- `URL`, which represents the REST API URL
- `Login`, which reprents the target user’s login (in the `USER` table)
- `Password`, which reprents the target user’s password (in the `USER` table)

The first request that should be executed is `Security → Auth – Obtain token` ; it will save the obtained token into the `token` postman variable (using the test script).

The requests do have an order that needs to be observed to obtain the desired results from Postman the `Create one` and `Get one` should always be executed firsthand, **before any other request** because
- `Create one` will ask the API to create a resource and return the id, which will be saved (used in `Get one`, `Delete one`)
- `Get one` will use the previously saved id to get the resource and save it in another variable (used in `Modify one`)

Moreover, the resources themselves must be created / delete in a certain order as a consequence of the database constraints ;
- MediaType & Publisher & User
- Media
- Comment

The order to delete is reversed.