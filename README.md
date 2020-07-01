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