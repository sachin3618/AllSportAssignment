# 🏀 All Sports Assignment App

A sample Android app demonstrating Clean Architecture, MVVM, Kotlin Coroutines, Room Database, and modern Jetpack practices. This was built as part of a recruiter assignment to showcase my architectural skills and ability to write testable, scalable code.

---

## 📱 Features

- ✅ Fetches a list of sports from a remote API
- ✅ Displays the list in a search-enabled UI
- ✅ Caches the data in a local Room database for offline access
- ✅ Follows MVVM + Clean Architecture principles
- ✅ Built with Kotlin Coroutines and Flows
- ✅ Fully unit tested ViewModel and use cases
- ✅ Proper UI state handling (Loading, Success, Error)

---

## 🧱 Architecture

Presentation (ViewModel)
↓
Domain (UseCases, Models)
↓
Data (Repository, Local DB, Remote API)


- **MVVM Pattern**
- **Clean Architecture**
- **StateFlow** for reactive UI updates
- **Coroutines** for async operations
- **Room** for local caching
- **JUnit & Mockito** for testing

---

## 🛠 Tech Stack

| Area            | Libraries / Tools                      |
|-----------------|----------------------------------------|
| Language        | Kotlin                                 |
| Architecture    | MVVM, Clean Architecture               |
| Networking      | Retrofit, OkHttp                       |
| Database        | Room                                   |
| Asynchronous    | Kotlin Coroutines, Flow                |
| Dependency DI   | Manual (can be swapped with Hilt)      |
| Testing         | JUnit, Mockito, Turbine, Coroutines Test |
| UI              | Jetpack Compose / XML (depending on setup) |

---

## 🧪 Testing

- ✅ Unit tests for ViewModel
- ✅ Mocking use cases with Mockito
- ✅ Coroutine dispatcher tested with `runTest` and `advanceUntilIdle`
- Test coverage includes:
  - UI state transitions
  - Error handling

---

## 🧰 Setup Instructions

1. Clone the repo:
   ```bash
   git clone https://github.com/sachin3618/AllSportAssignment.git


2. Open the project in Android Studio Arctic Fox or higher.
3. Run the app on an emulator or device.
4. Run tests:

./gradlew test



