# MovieBookingModules
Movie Booking Service Modules

MovieBookingService (http://<IP>/8081) - Takes care for
 - Adding/Updating/Deleting Theatre
 - Adding/Updating/Deleting Movie
 - Adding/Updating/Deleting Movie Show
 - Creating a reservation and booking
 - Completing a booking (not actual :) )
 - Authorization using JWT token generated from User Service

User Service (http://<IP>/8082)
 - Takes care for authentication and generating JWT token

Service Registory
 - Is a Eureka Service registery for User and Movie Service.

Note: 
  - This is not a fully working movie booking service, but a small POC for same.
  - Both of service User and Movie booking is having Swagger documentation (Open API) for easy idea of APIs available and it's request and response.

