# vaccinatedSystem

Please find the Link for Prometheus and Swagger Link Below.

1. Prometheus Link : http://localhost:8080/actuator/prometheus
2. Swagger Link: http://localhost:8080/swagger-ui/index.html

   Note - While registering the new center please take center JSON as the below format (in swagger with time format
    "openTime": {  "hour": 0,   "minute": 0,   "second": 0,   "nano": 0 } and  "closeTime": {  "hour": 0,   "minute": 0,   "second": 0,   "nano": 0 } it will not work
   
                
                        {
                          "name": "Vaccination Center 1",
                          "state": "State",
                          "city": "City",
                          "pinCode": "123456",
                          "maxCapacityPerHour": 2,
                        
                          "openTime": "01:00:00",
                          "closeTime": "11:00:00",
                          "appointments": [
                          ]
                        }


# Step to run the application
1. Create  Schema with the name vaccinationdata(I Used MySQL database for storing the Data)
2. run the application.
3. Hit the API with the Swagger Link provided above.
4. Monitor the Application with Prometheus Link provided above.

