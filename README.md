# Product Detail E-Commerce App

An Android application built with Jetpack Compose and MVVM Clean Architecture that displays a list of products and their details fetched from a remote API.

## Features

- **Product List Screen**: Displays a list of products with images, titles, prices, ratings, and categories
- **Product Detail Screen**: Shows detailed information about a selected product
- **MVVM Clean Architecture**: Separated into Data, Domain, and Presentation layers
- **Jetpack Compose**: Modern declarative UI framework
- **Dependency Injection**: Using Hilt for dependency management
- **Retrofit**: For API calls to fetch product data
- **Coil**: For image loading and caching

## Architecture

The app follows Clean Architecture principles with three main layers:

### Data Layer
- `ProductApiService`: Retrofit interface for API calls
- `ProductDto`: Data Transfer Objects for API responses
- `ProductRepositoryImpl`: Repository implementation

### Domain Layer
- `Product`: Domain model
- `ProductRepository`: Repository interface
- Use Cases: `GetProductsUseCase`, `GetProductByIdUseCase`

### Presentation Layer
- ViewModels: `ProductListViewModel`, `ProductDetailViewModel`
- UI Screens: `ProductListScreen`, `ProductDetailScreen`
- Navigation: `NavGraph` for screen navigation

## API

The app uses the [FakeStore API](https://fakestoreapi.com/) to fetch product data.

## Setup

1. Clone the repository
2. Open the project in Android Studio
3. Sync Gradle files
4. Run the app on an emulator or physical device

## Requirements

- Android Studio Hedgehog or later
- Minimum SDK: 24
- Target SDK: 34
- Kotlin 1.9.20

## Dependencies

- Jetpack Compose
- Hilt for Dependency Injection
- Retrofit for Networking
- Coil for Image Loading
- Navigation Compose
- Material 3


