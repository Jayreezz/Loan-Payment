Build and Run Instructions:
Requirements: Java 17+, Maven
Build: mvn clean install
Run: mvn spring-boot:run
API Endpoints:
POST /loans - Create a new loan
Request body: { "loanAmount": 1000, "term": 12 }
GET /loans/{loanId} - Get loan details
POST /payments - Record a payment
Request body: { "loanId": 1, "paymentAmount": 200 }
