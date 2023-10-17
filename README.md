# MyHeroAPI

Welcome to MyHeroAPI, a challenge project for W2M. This API is designed to manage heroes.

## Version
- Version: 1.0

## Server
- URL: [http://localhost:8080](http://localhost:8080)
- Swagger: [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

## API Endpoints

### Get a Hero by ID
- **URL**: `/hero/{id}`
- **Description**: Retrieve a hero by their ID.
- **Parameters**:
  - `id` (path) - The ID of the hero (integer)
- **Responses**:
  - `200`: Hero retrieved successfully
  - `404`: Hero not found

### Modify a Hero by ID
- **URL**: `/hero/{id}`
- **Description**: Modify a hero based on their ID.
- **Parameters**:
  - `id` (path) - The ID of the hero (integer)
- **Request Body**: JSON object representing the hero
- **Responses**:
  - `200`: Hero modified successfully
  - `400`: Bad request
  - `404`: Hero not found

### Delete a Hero by ID
- **URL**: `/hero/{id}`
- **Description**: Delete a hero by their ID.
- **Parameters**:
  - `id` (path) - The ID of the hero (integer)
- **Responses**:
  - `204`: Hero deleted successfully
  - `404`: Hero not found
  - `500`: Internal server error

### Get All Heroes
- **URL**: `/hero`
- **Description**: Retrieve a list of all heroes.
- **Responses**:
  - `200`: List of heroes retrieved successfully

### Create a New Hero
- **URL**: `/hero`
- **Description**: Create a new hero and return the created hero.
- **Request Body**: JSON object representing the hero
- **Responses**:
  - `201`: Hero created successfully

### Search Heroes by Name
- **URL**: `/hero/search`
- **Description**: Find heroes whose names contain the provided search parameter.
- **Parameters**:
  - `searchParameter` (query) - Search parameter (string)
- **Responses**:
  - `200`: Heroes found successfully

## Data Model

### Hero
- **Properties**:
  - `id` (integer, int64) - The ID of the hero
  - `name` (string) - The name of the hero
  - `description` (string) - The description of the hero
  - `dob` (string, date) - Date of birth of the hero
  - `age` (integer, int32) - The age of the hero

## Additional Information
- **Purpose**: MyHeroAPI is a challenge project for W2M, designed for managing heroes.
- **Contact**: For more information, please contact Carlos Uriel at [carluitosgfx@gmail.com](mailto:carluitosgfx@gmail.com).
- **Documentation**: For in-depth details, please refer to the Swagger documentation.
