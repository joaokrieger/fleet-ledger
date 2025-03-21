# Fleet Ledger System

This project was created to practice CRUD operations using Spring Boot, Docker, and other related technologies. It provides a basic structure for managing vehicles, drivers, maintenance records, deliveries, and routes for a logistics company.

## Features
- **Vehicle Management**: Registers and tracks the company’s fleet vehicles.
- **Driver Information**: Stores details of the drivers responsible for the vehicles.
- **Route Management**: Defines and manages delivery routes for the company’s operations.
- **Delivery Tracking**: Records each delivery made, including its status and timestamps.
- **Maintenance Records**: Tracks the maintenance activities and history for vehicles.

# Build the project

```bash
git clone https://github.com/joaokrieger/fleet-ledger.git
mvn clean install
docker build -t fleet-ledger .
docker-compose up --build
```
## Swagger Documentation

Once the application is running, visit the following URL in your browser to access the Swagger documentation:

```bash
http://localhost:8080/swagger-ui.html
```
## API Endpoints

### Vehicle Management
- **GET** `/api/v1/vehicle` – Retrieves all vehicles.
- **PUT** `/api/v1/vehicle` – Updates an existing vehicle.
- **POST** `/api/v1/vehicle` – Creates a new vehicle.
- **GET** `/api/v1/vehicle/{id}` – Retrieves a vehicle by ID.
- **DELETE** `/api/v1/vehicle/{id}` – Deletes a vehicle by ID.

### Driver Management
- **GET** `/api/v1/driver` – Retrieves all drivers.
- **PUT** `/api/v1/driver` – Updates an existing driver.
- **POST** `/api/v1/driver` – Creates a new driver.
- **GET** `/api/v1/driver/{id}` – Retrieves a driver by ID.
- **DELETE** `/api/v1/driver/{id}` – Deletes a driver by ID.

### Vehicle Maintenance Management
- **GET** `/api/v1/vehicle-maintenance` – Retrieves all vehicle maintenance records.
- **PUT** `/api/v1/vehicle-maintenance` – Updates an existing maintenance record.
- **POST** `/api/v1/vehicle-maintenance` – Creates a new vehicle maintenance record.
- **GET** `/api/v1/vehicle-maintenance/{id}` – Retrieves a maintenance record by ID.
- **DELETE** `/api/v1/vehicle-maintenance/{id}` – Deletes a vehicle maintenance record by ID.

### Delivery Management
- **GET** `/api/v1/delivery` – Retrieves all deliveries.
- **PUT** `/api/v1/delivery` – Updates an existing delivery.
- **POST** `/api/v1/delivery` – Creates a new delivery.
- **PUT** `/api/v1/delivery/{id}/mark-as-delivered` – Marks a delivery as delivered.
- **GET** `/api/v1/delivery/{id}` – Retrieves a delivery by ID.
- **DELETE** `/api/v1/delivery/{id}` – Deletes a delivery by ID.

### Route Management
- **GET** `/api/v1/route` – Retrieves all delivery routes.
- **PUT** `/api/v1/route` – Updates an existing route.
- **POST** `/api/v1/route` – Creates a new delivery route.
- **GET** `/api/v1/route/{id}` – Retrieves a route by ID.
- **DELETE** `/api/v1/route/{id}` – Deletes a delivery route by ID.
