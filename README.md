# CurrencyConverter

The application contains a list of the various currency rate conversion against top most base currency which is continuously updating the rates at every second of interval.
User can change the base currency after clicking on any of the items in a list and it will be automatically shifted to the topmost position in the list and will become the
base currency against the other currency values.

# Demo
![CurrencyConverter](screenshots/currency_converter.gif)

## Languages, libraries and tools used

* __[Kotlin](https://developer.android.com/kotlin)__
* __[RxJava](https://github.com/ReactiveX/RxJava)__
* __[Koin](https://github.com/InsertKoinIO/koin)__
* __[Coil](https://coil-kt.github.io/coil/getting_started/)__
* __[Android Material Design](https://material.io/components/)__
* __[Android Architecture Components](https://developer.android.com/topic/libraries/architecture/index.html)__
* __[Instrumented Unit Testing](https://developer.android.com/training/testing/unit-testing/instrumented-unit-tests)__

Above Features are used to make code simple, generic, understandable, clean and easily maintainable
for future development. Especially **Koin** for dependency injection and **RxJava** for
asynchronous API call continuously at every second of interval.

This application supports the screen rotation without losing the data and also use **Constraintlayout** to design layout which
gives better **UI support for both Mobile and Tablet**, and even when the screen rotates.


## Running and Building the application

You can run the app on a real device or an emulator.

* __[Run on a real device](https://developer.android.com/training/basics/firstapp/running-app#RealDevice)__
* __[Run on an emulator](https://developer.android.com/training/basics/firstapp/running-app#Emulator)__


# Prerequisites
* __Android Studio 3.5.3__
* __Gradle version 3.5.3__
* __Kotlin version 1.3.61__
* __Android Device with USB Debugging Enabled__

# Built With

* __[Android Studio](https://developer.android.com/studio/index.html)__ - The Official IDE for Android
* __[Kotlin](https://developer.android.com/kotlin)__ - Language used to build the application
* __[Gradle](https://gradle.org)__ - Build tool for Android Studio