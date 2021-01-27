# microservicesDemo

These is Demo application for demonstrating microservices. In this case I have created application about cart management of an online store.
This application could be very huge hence I am depicting only for very small part of the application 

This demo consists of 5 main micro serviecs developed using spring boot: 

	-	CART-SERVICE  ( this microservice talks to other microservie to complete its functionality) 
	
	-	LOCATION-SERVICE
	
	-	ORDER-SERVICE
	
	-	PAYMENT-SERVICE
	
	-	USER-SERVICE
	
Apart from these applications there are 2 more applications
	
	-	Service-Discovery (for registring and discovering services) 
	
	-	API-Gateway (acts as API gateway by mapping URLS to serice-regisrty ) 
	
# Steps to run the applications: 
	
	-	download/clone the application 
	
	-	clean and build all the applications
	
	-	run service registry
	
	-	run all other applications
	
	-	open http://localhost:8761/ - here you should see all the microservices registered
	
	-	Now open Postman or any other client 
	
	-	Call the post endpoint from postman:  http://localhost:9001/cart/checkout/ with following data: 
			{
				"userId":2,
				"paymentCardInfo": {
					"cardNumber": "4005-5087-6743-8700-6547",
					"cardSequence": 123	
				},
				"amount": 100, 
				"pickupOrder" : true,
				"itemsList": [{"itemId":1234 , "itemAmount": 40},{"itemId":2354 , "itemAmount": 60}],
				"locationId": 3
			}

	-	This should call other microservices based on service-registry and give the results. 
	
	-	This is loadbalanced already and hence we can add more nodes easily and scale application 
	
	-	You can also query using other rest endpoints from other application using rest endpoint to test the application
	

#	Things not covered in this application

	-	authentication and authorization
	
	-	token management 
	
	-	logging can be improved 
	
	- 	unit test and integration tests 
	
	-	Other microservice in the chain 
	
	-	Other technologies like queues, caches, reports, reconcilation engine and other offline jobs 
	
	
	
