# My Bank by Steve
This README outlines the primary features of the "Ma Banque" interview exercise, which displays a list of banks along with their accounts and provides a detailed view of their transactions.

<img src="./docs/assets/ma-banque.gif" alt="drawing" width="200"/>

The repo contains a generated apk called *ma-banque.apk* (Note: it's only to facilitate the review, binary must not be put inside git repository)

## Architecture
- Use clean architecture and MVVM
- Domain layer has no external dependencies and Data layer contains the implementation of the repository 
of the domain layer. Data, Domain and Ui can be encapsulated in their own module.
- Use Jetpack Compose for UI
- Use Dagger Hilt for dependency injection (NetworkModule, BanksModule)
- Use Retrofit (better use Ktor for cross platform development KMM)
- Use a cache for Retrofit to not make to many request to the restricted API

## Unit Test
I have tested mainly the business logic. 
- BanksViewModel
- GetBanksUseCase
- RetrofitAccountRepository
- Issue for running test with coverage on BankViewModelTest due to R resource. I don't get the time to fix it


## Enhancement
- Create a dedicated view models for the detail account view
- Create a cache for storing request, either a memory cache or a persistent cache with Room DB
  and use it as a data source in the repository
- Security cipher persistent Data
- Need more time to write UI tests
- Add Integration test with mock of OKHttpClient