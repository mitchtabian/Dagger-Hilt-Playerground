# Dagger Hilt
Playground for learning how to use [Hilt](https://dagger.dev/hilt/). A new way to incorporate Dagger dependency injection into an Android application.

# Examples
Below is a list of examples.

## [Hilt Basics](https://github.com/mitchtabian/Dagger-Hilt-Playerground/tree/basics)
I will be making video lectures using the gists below as examples. 

1. Preparing to use Hilt
	- Follow the setup procedure:
		- https://developer.android.com/training/dependency-injection/hilt-android
	- https://gist.github.com/mitchtabian/e5e9b476764cdb7b139a14cb642325b0
2. Field injecting a class with no dependencies. You own that class.
	- https://gist.github.com/mitchtabian/130b29cf33696fc02710df3f7aba8152
3. Scopes and the "tier-like" system
	- https://gist.github.com/mitchtabian/7cdf070b5a0575aed4970fb3258f25e9
4. Dependencies that require dependencies (Concrete classes that you own)
	- https://gist.github.com/mitchtabian/fd5a5a4e17700ce23724245d500ebdbd
5. Some things can't be constructor-injected. What is the solution?
	- https://gist.github.com/mitchtabian/9d88bce861d1cfc21d590819781ff6e1
6. Hilt Modules, Binds and Provides
	- https://gist.github.com/mitchtabian/d06ee1d6445265adf00b087fc56708d8
	- Teach in this order:
		1. ComplexMethod_VALID.kt
		2. ComplexMethod_INVALID.kt
		3. SimpleMethod.kt
7. Multiple Bindings of the same type
	- https://gist.github.com/mitchtabian/2838e5497b2b2e5777d0f5c0e6ecc79e


## [Basic MVI Repository Pattern](https://github.com/mitchtabian/Dagger-Hilt-Playerground/tree/Basic-MVI-Repository-Pattern)

**NOTE** This is not how I would build out the architecture normally. I greatly simplified this for beginners. See [Simple Network & Cache Use-case](https://github.com/mitchtabian/Dagger-Hilt-Playerground/tree/Simple-Network-Cache-Use-Case) for a [Clean Architecture](https://codingwithmitch.com/courses/android-clean-architecture/) Implementation of the same thing.

1. Retrieve data from [open-api.xyz](https://open-api.xyz/placeholder/blogs) with [Retrofit](https://square.github.io/retrofit/)
2. Cache data with [Room](https://developer.android.com/topic/libraries/architecture/room)
3. Display cached data in UI



## [Simple Network & Cache Use-case](https://github.com/mitchtabian/Dagger-Hilt-Playerground/tree/Simple-Network-Cache-Use-Case)
1. Retrieve data from [open-api.xyz](https://open-api.xyz/placeholder/blogs) with [Retrofit](https://square.github.io/retrofit/)
2. Cache data with [Room](https://developer.android.com/topic/libraries/architecture/room)
3. Display cached data in UI


## [Instrumentation Testing](https://github.com/mitchtabian/Dagger-Hilt-Playerground/tree/hilt-testing)
#### Guide: 
https://developer.android.com/training/dependency-injection/hilt-testing

#### Important points:
1. Dependencies
    1. Hilt
        - `androidTestImplementation "com.google.dagger:hilt-android-testing:$hilt_version"`
        - `kaptAndroidTest "com.google.dagger:hilt-android-compiler:$hilt_version"`
    2. `launchFragmentInContainer`
        - `debugImplementation "androidx.fragment:fragment-testing:$fragment_version"`
2. Test classes
    - Annotate with `@HiltAndroidTest`. [Example](https://github.com/mitchtabian/Dagger-Hilt-Playerground/blob/hilt-testing/app/src/androidTest/java/com/codingwithmitch/daggerhiltplayground/MainActivityTest.kt#L25)
    - Include `HiltAndroidRule`. [Example](https://github.com/mitchtabian/Dagger-Hilt-Playerground/blob/hilt-testing/app/src/androidTest/java/com/codingwithmitch/daggerhiltplayground/MainActivityTest.kt#L29)
3. `HiltTestApplication `
    - Hilt automatically generates a `HiltTestAppliaction`. **But it requires a custom Test Runner (see next point)**
4. Custom Test Runner
    - You must create a [custom test runner](https://github.com/mitchtabian/Dagger-Hilt-Playerground/blob/hilt-testing/app/src/androidTest/java/com/codingwithmitch/daggerhiltplayground/MyTestRunner.kt) and specify in [build.gradle](https://github.com/mitchtabian/Dagger-Hilt-Playerground/blob/hilt-testing/app/build.gradle#L18)
5. Field Injection
    - Requres `hiltRule.inject()` in `@Before` function of test class. [Example](https://github.com/mitchtabian/Dagger-Hilt-Playerground/blob/hilt-testing/app/src/androidTest/java/com/codingwithmitch/daggerhiltplayground/MainActivityTest.kt#L39)
6. Test Fakes
    1. **Method 1**: Replacing a Module
        1. Uninstall the module in the test class
            - `@UninstallModules(AnalyticsModule::class)`. [Example](https://github.com/mitchtabian/Dagger-Hilt-Playerground/blob/hilt-testing/app/src/androidTest/java/com/codingwithmitch/daggerhiltplayground/MainActivityTest.kt#L24)
        2. Create a new module within the test class that replaces the old module. [Example](https://github.com/mitchtabian/Dagger-Hilt-Playerground/blob/hilt-testing/app/src/androidTest/java/com/codingwithmitch/daggerhiltplayground/MainActivityTest.kt#L56)
    2. **Method 2**: `@BindValue`
        - **I can't get this to work...**
            - `@BindValue var myString: String = "gggf"`
            - Might be a kotlin thing? Maybe can't change final params? Not sure.
        - https://developer.android.com/training/dependency-injection/hilt-testing#binding-new
7. Custom Application for testing
    - [Guide](https://developer.android.com/training/dependency-injection/hilt-testing#custom-application)
    - I guess you might have to do this if one of your dependencies requires your custom Application class. But I think you could probably work-around this.
8. Multiple Test rules
    - [Guide](https://developer.android.com/training/dependency-injection/hilt-testing#multiple-testrules)
    - I didn't know this was an issue? Never encountered this.
9. **`launchFragmentInContainer`**
    - `launchFragmentInContainer` does not work with Hilt because the activity it creates is not annotated with `@AndroidEntryPoint`. So there is a work-around:
    - [Guide](https://developer.android.com/training/dependency-injection/hilt-testing#launchfragment)
    - `launchFragmentInHiltContainer` work-around:
        1. Create [`HiltExt.kt`](https://github.com/android/architecture-samples/blob/dev-hilt/app/src/androidTest/java/com/example/android/architecture/blueprints/todoapp/HiltExt.kt)
            - **NOTE** One short-coming of this work-around is you cannot specify a `FragmentFactory` to the function. I made a slight modification so you can. That way you can do constructor injection. [Modified HiltExt.kt](https://gist.github.com/mitchtabian/b7bb933d2f1fb5262f9d6b24b247a0ab)
        2. Create [/debug/HiltTestActivity.kt](https://github.com/android/architecture-samples/blob/dev-hilt/app/src/debug/java/com/example/android/architecture/blueprints/todoapp/HiltTestActivity.kt)
        3. Create [/debug/AndroidManifest.xml](https://github.com/android/architecture-samples/blob/dev-hilt/app/src/debug/AndroidManifest.xml)
        4. Launch the fragment: `val scenario = launchFragmentInHiltContainer<MainFragment>(
                                             factory = fragmentFactory
                                         )`

# Issues
#### I got this weird error:
```
Could not determine the dependencies of task ':app:processDebugAndroidTestManifest'.
> Could not resolve all task dependencies for configuration ':app:debugAndroidTestRuntimeClasspath'.
   > Could not resolve com.google.code.findbugs:jsr305:{strictly 3.0.1}.
     Required by:
         project :app
      > Cannot find a version of 'com.google.code.findbugs:jsr305' that satisfies the version constraints: 
           Dependency path 'DaggerHiltPlayground:app:unspecified' --> 'androidx.test.espresso:espresso-core:3.2.0' --> 'com.google.code.findbugs:jsr305:2.0.1'
           Constraint path 'DaggerHiltPlayground:app:unspecified' --> 'com.google.code.findbugs:jsr305:{strictly 3.0.1}' because of the following reason: debugRuntimeClasspath uses version 3.0.1
           Dependency path 'DaggerHiltPlayground:app:unspecified' --> 'com.google.dagger:hilt-android-testing:2.28-alpha' --> 'com.google.code.findbugs:jsr305:3.0.1'
           Dependency path 'DaggerHiltPlayground:app:unspecified' --> 'com.google.dagger:hilt-android:2.28-alpha' --> 'com.google.code.findbugs:jsr305:3.0.1'
           Dependency path 'DaggerHiltPlayground:app:unspecified' --> 'com.google.dagger:hilt-android-testing:2.28-alpha' --> 'com.google.guava:guava:27.1-jre' --> 'com.google.code.findbugs:jsr305:3.0.2'
```

#### Solution
https://github.com/invertase/react-native-firebase/issues/1954

I just added this to `build.gradle` and everything worked fine:
```
android {
    ... 

    configurations.all {
        resolutionStrategy.force 'com.google.code.findbugs:jsr305:3.0.0'
    }
}
```




