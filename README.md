# RandomUser Fetch App

This Android project fetches data from the RandomUser API and presents the users in a paginated list using Jetpack Compose. The project follows the MVVM pattern with Clean Architecture and employs various modern technologies to ensure clean and maintainable code.

## Technologies Used

- **Kotlin**: Main programming language.
- **Jetpack Compose**: Framework for building native UIs declaratively.
- **MVVM (Model-View-ViewModel)**: Architectural pattern for separating concerns.
- **Clean Architecture**: Architecture promoting separation of concerns and ease of testing.
- **Koin**: Dependency injection framework.
- **Retrofit**: HTTP client for making network requests.
- **Mockk**: Library for unit testing.
- **Pagination**: Implementation for continuous loading of users.

## Features

- Fetch data from the RandomUser API.
- Display users in a paginated list.
- Automatic data loading as the user scrolls.
- Clean and maintainable structure using Clean Architecture.
- Unit tests to ensure code quality.

## Project Structure

- **data**: Contains definitions for data sources, such as repositories and network services.
- **domain**: Contains business entities and use cases.
- **presentation**: Contains UI and ViewModel definitions.
- **di**: Contains dependency injection setup and modules.
