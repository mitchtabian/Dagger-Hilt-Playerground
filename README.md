# [Basic MVI Repository Pattern](https://github.com/mitchtabian/Dagger-Hilt-Playerground/tree/Basic-MVI-Repository-Pattern)

**NOTE** This is not how I would build out the architecture normally. I greatly simplified this for beginners. See [Simple Network & Cache Use-case](https://github.com/mitchtabian/Dagger-Hilt-Playerground/tree/Simple-Network-Cache-Use-Case) for a [Clean Architecture](https://codingwithmitch.com/courses/android-clean-architecture/) Implementation of the same thing.

1. Retrieve data from [open-api.xyz](https://open-api.xyz/placeholder/blogs) with [Retrofit](https://square.github.io/retrofit/)
2. Cache data with [Room](https://developer.android.com/topic/libraries/architecture/room)
3. Display cached data in UI