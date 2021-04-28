# spacexsample #
Sample Kotlin Android App using SpaceX api

Architecture used is:
* MVVM
* Dagger for Dependency Injection
* Coroutines to keep calls scoped to ViewModels
* Retrofit to call API 
* Navigation Components with safeargs to navigate between fragments and parse data between them
* Timber for logging
* LiveData and Binding to update the UI once data is loaded
