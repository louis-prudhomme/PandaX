# PandaX

Because this is our REST project !! \o/

This is the project we have been asked to realize for our **Application Interoperability with Web Services** course @ Efrei Paris.

It is a fairly simple application we created in about two weeks ; it essentially is a user media repository REST API in which users can add their possessions and comments.

The name comes from the passion of one of the team’s members for the namesaked-animal (with a touch of bullshit-marketing-reclame-lookscool slapped on it).

# Tech

We made several choices regarding our technical stack ;
- Use Maven to ease interoperability between team members, OSes and IDEs
- Use Java 11, the current Java LTS version (plz drop 1.8 people, it’s already ded)
- Use Tomcat > 8.5 (this was a part of our assignement)
- Use Hibernate as a JPA provider
- Use [JJWT](https://github.com/jwtk/jjwt) as our JWT provider
- Use Postman
- Use MySQL
- Use JAX-RS

# The work that remains

- [x] Part I – Attack of the Configs
  - [x] Build the database
    - [x] design it
    - [x] put the script on git
  - [x] implement jpa
  - [x] implement security tokens
  - [x] postman test for the general use case
- [x] Part II – Revenge of the REST
  - [x] plan routes
  - [x] implement rest routes

# Guidelines (trespassers will be shot)

- Never commit a non-functionnal feature on *master*
- Try to bundle one feature per commit 
- Always provide the postman test case for your REST route