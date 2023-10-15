# My Bank by Steve

## Architecture
- Use clean architecture and MVVM
- Use Jetpack Compose for UI
- Use Dagger Hilt for dependency injection
- Use Retrofit (better use Ktor for cross platform development KMM)

## Unit Test
I have tested mainly the business logic. 
- BanksViewModel
- GetBanksUseCase
- RetrofitAccountRepository
- Issue for running test with coverage on BankViewModelTest due to R resource. I got not the time to fix it
- Need more time to write UI tests

## Enhancement
- Create a dedicated view models for the detail account view
- Create a cache for storing request, either a memory cache or a persistent cache with Room DB
  and use it as a data source in the repository
- Security cipher persistent Data