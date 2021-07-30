# metricReportingService
This project is implemented using Spring boot framework
Steps to run this project

1) Clone this project
2) Open Eclipse/Spring tool suite/ Intellige 
3) Import Maven project and select this project
4) Run the Application using Run as Spring boot application
5) This project currently running at localhost:8080. Now open the Postman
6) Select Post , and add URL "localhost:8080/metric/{Key}" , lets say key is "Indigo" . 
   Here in the request body add { "value": 10}
7) Send a same request again

8) Now send a Get request for the URL "localhost:8080/metric/{Key}/sum", lets say key is "Indigo". You will receive output as 20.
9) There is scheduler method , which runs every 1 hour and delete the old entries from the storage.

