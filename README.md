# LibraryAPI (Work in Progress!)

This Spring Boot project is a Library API that has 4 endpoitns (currently).

* List all books within Library (Get Mapping)(/books/)
* Update the details of an existing Book (Put Mapping)(/books/)
* Load a new book to Library (Post Mapping)(/books/)
* List all books that are available for checkout (Get Mapping)(/books/st1)

In the project, I implemented a streamlined architecture based on the Controller-Service-Repository pattern. This design pattern enabled a clear separation of concerns and facilitated the maintainability and extensibility of the codebase.

The Controller layer served as the entry point for handling incoming requests and coordinating the overall flow of the application. It leveraged the Service layer to encapsulate the operational logic and orchestrate various operations. The Service layer acted as a mediator between the Controller and the Repository layer (a JpaRepository) which was responsible for CRUD operations at the DB. This approach allowed for modular development and facilitated the testing and reusability of the code.

There isn't any actual library data available at my hand, I used Mockio to create mock data for my app. Every time the app is started, LibraryDataLoader component (implements CommandLineRunner) loads the mock data into DB via RestTemplate. 

BookRequest/BookResponse DTOs are used besides the BookEntity model. Reason is to ensure seperation of DB entity from request-response objects clearly. Request/Response DTOs are also seperate. 

Project also has a RestControllerAdvice component for exception handling.

## What will the future bring?

Well, this project is - at this state - half cooked. Next, I will implement a log-in mechanism, then the API requests will all be made by a session-token. First, I had a ID/PW hashing then matching it with a ID/PW hash on a user DB, but currently I am looking for a way to do it via Spring Boot Security. Then, I will make a simple web interface to use the API. At the end, I plan to have it all containerized including DB.

## How to try the project?

First, `git clone` the repository. Then, run the LibraryProjectApplication.java class. Since the project is on Spring Boot, Tomcat will launch and then you can place requests at your 8080 port.
