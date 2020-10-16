Requirements:
Java 8+
Maven
POSTMAN or any rest client

How to run:
1. Open cmd, then go to project directory
2. Once in the directory, input 'mvn clean spring-boot:run' in command line and press enter
3. After the spring boot loads, we can now call the rest endpoints as provided in the assignment specification:
	​http://localhost:8080/assignment/transaction​/{transaction_id}
	http://localhost:8080/assignment/transactionSummaryByProducts​/{last_n_days}
	http://localhost:8080/assignment/transactionSummaryByManufacturingCity​/{last_n_days}