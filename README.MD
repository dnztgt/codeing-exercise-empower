# Beneficiary Android App

## Used technologies and libraries

- Architecture: MVVM with Repository, Single Activity
- Coding Language: Kotlin
- Deserialization: Json
- Observable Pattern: LiveData
- Asynchronous: Coroutines
- List: RecyclerView with DiffUtil

## Main Flow

The app contains of two screens: Main and Details.

In the main screen, user will see all beneficiaries in the list (RecyclerView). MainViewModel gets
the data from repository and MainFragment observes this data.

In the details screen, user can see additional details of the beneficiary. When navigating to
details screen, we pass SSN as a parameter and get the according data from repository.
DetailsFragment observes the data in DetailsViewModel.

Repository acts as a in-memory cache. It loads data from assets file on app launch and saves it.

## Run instructions

Unzip the archive and open in Android Studio. After build and run the app on emulator/device (minSdk
is 23).
