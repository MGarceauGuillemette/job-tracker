# Job Tracker

A web-based job application management system built with **Java, Spring Boot, PostgreSQL, and Thymeleaf**.

Job Tracker helps users organize their job search by tracking applications, managing their statuses, recording follow-up
dates, and viewing application statistics through a dashboard.

The project demonstrates backend development, database integration, layered application architecture, automated testing,
and continuous integration.

## Features

- **Application Management:** Create, view, edit, and delete job applications.
- **Status Tracking:** Track applications through Saved, Applied, Interview, Offer, Rejected, and Withdrawn stages.
- **Status Filtering:** Filter applications by their current status.
- **Dashboard Statistics:** View the total number of applications and counts for each status.
- **Application Details:** Store company names, job titles, job posting URLs, application dates, follow-up dates, and
  notes.
- **Form Validation:** Validate required fields and input lengths.
- **Responsive Interface:** User interface built with Thymeleaf, HTML, and CSS.
- **Automated Testing:** Unit tests using JUnit 5 and Mockito.
- **Continuous Integration:** GitHub Actions automatically builds and tests the application.

## Technology Stack

| Category            | Technologies                |
|---------------------|-----------------------------|
| Language            | Java 17                     |
| Framework           | Spring Boot                 |
| Web                 | Spring MVC, Thymeleaf       |
| Database            | PostgreSQL                  |
| Persistence         | Spring Data JPA / Hibernate |
| Database Migrations | Flyway                      |
| Validation          | Jakarta Bean Validation     |
| Testing             | JUnit 5, Mockito            |
| Build Tool          | Maven                       |
| CI                  | GitHub Actions              |
| Frontend            | HTML, CSS                   |

## Screenshots

Screenshots of the application dashboard, application form, and details page will be added here.

## Architecture

Job Tracker follows a layered architecture:

```text
Controller
    |
    v
Service
    |
    v
Repository
    |
    v
PostgreSQL Database
```

**Controller Layer:** Handles HTTP requests and communicates with the service layer.

**Service Layer:** Contains application operations and coordinates data access.

**Repository Layer:** Uses Spring Data JPA for database operations.

**Model Layer:** Defines the application entities and status enumeration.

**DTO Layer:** Handles form data and input validation.

**View Layer:** Uses Thymeleaf templates to render HTML pages.

## Getting Started

### Prerequisites

- Java 17
- PostgreSQL
- Git

The project includes the Maven Wrapper, so a separate Maven installation is not required.

### 1. Clone the repository

```bash
git clone https://github.com/MGarceauGuillemette/job-tracker.git
cd job-tracker
```

### 2. Configure PostgreSQL

Create a PostgreSQL database named:

```sql
CREATE DATABASE jobtracker;
```

Configure the database connection using the environment variables expected by `application.properties`.

For example, if your configuration uses `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD`, set these variables in your
terminal.

Do not commit your database password or other secrets to Git.

### 3. Run the application

On Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

On macOS or Linux:

```bash
./mvnw spring-boot:run
```

Once the application starts, open:

http://localhost:8080

Flyway manages the database schema migrations.

## Running Tests

To run the automated test suite:

**Windows:**

```powershell
.\mvnw.cmd clean test
```

**macOS / Linux:**

```bash
./mvnw clean test
```

The project includes unit tests using JUnit 5 and Mockito for service-layer operations, including application retrieval,
filtering, saving, deletion, and statistics.

The build also includes Spring Boot application-context testing, which requires a configured PostgreSQL connection.

## Continuous Integration

GitHub Actions automatically runs the Maven test suite when changes are pushed to the `main` branch or submitted through
a pull request.

The CI workflow provisions a temporary PostgreSQL database to run the tests.

Workflow configuration:

`.github/workflows/ci.yml`

## Future Improvements

- Improve application error handling.
- Add more integration and controller tests.
- Add searching and sorting.
- Add pagination for large application lists.
- Deploy the application to a hosting platform.

## Project Status

**Active development**

Core application management, filtering, dashboard statistics, automated tests, and continuous integration are
implemented.