# Autohub Microservices (Spring Boot)

This monorepo contains 8 Spring Boot services.

## Services
- product-service – Catalog
- inventory-service – Stock & reservations
- user-service – Customers
- payment-service – Payments
- notification-service – Email/SMS
- shipping-service – Delivery
- auth-service – JWT/OAuth2
- search-service – Search

## Local run (per service)
```bash
mvn -q -DskipTests spring-boot:run
