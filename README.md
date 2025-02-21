# WallSplashCompose

## Overview

Wallpaper application made with Jetpack compose using Clean architecture.
Used [Unsplash api](https://unsplash.com/documentation) for image data.

### User stories

* Can view varieties of images from the Unsplash api.
* Can mark images as favorite and view later from favorite section. Which will load the data from
  local database.
* Endless scrolling by using pagination using paging-3 with offline cashing using remote mediator
  and RoomDb.
* Can have a sneak peek by holding the image with a mind pleasing background blur
  using [Cloudy](https://github.com/skydoves/Cloudy) library.
* Search your favorite image by going to search page.
* Download any images in 3 different formats(Low, Medium and High) Quality pictures.

### The final app screens:
<table>
<tr>
<td><img src="images/image1_light.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
<td><img src="images/image1_dark.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
</tr>
<tr>
<td><img src="images/image2_light.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
<td><img src="images/image2_dark.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
</tr>
<tr>
<td><img src="images/image3_light.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
<td><img src="images/image3_dark.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
</tr>
<tr>
<td><img src="images/image4_light.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
<td><img src="images/image4_dark.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
</tr>
<tr>
<td><img src="images/image5_light.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
<td><img src="images/image5_dark.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
</tr>
<tr>
<td><img src="images/image6_light.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
<td><img src="images/image6_dark.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
</tr>
<tr>
<td><img src="images/image7_light.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
<td><img src="images/image7_dark.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
</tr>
<tr>
<td><img src="images/image8_light.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
<td><img src="images/image8_dark.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
</tr>
<tr>
<td><img src="images/image9_light.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
<td><img src="images/image9_dark.jpg" class="img-fluid rounded work-image" alt="screenshot"/></td>
</tr>
</table>

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) - a cross-platform, statically typed, general-purpose
      programming language with type inference.
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform
      background operations.
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - handle the stream of data
      asynchronously that executes sequentially.
    * [Jetpack](https://developer.android.com/jetpack)
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) -
          perform action when lifecycle state changes.
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store
          and manage UI-related data in a lifecycle conscious way.
        * [Compose](https://developer.android.com/jetpack/compose?gclsrc=ds&gclsrc=ds) - build clean
          modern android UIs.
        * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) -
          dependency injection library for Android that reduces the boilerplate of doing manual
          dependency
        * [Navigation component](https://developer.android.com/guide/navigation) - perform complex
          navigation.
    * [Retrofit](https://square.github.io/retrofit/) - A type safe HTTP client for Android.
    * [SplashScreen](https://developer.android.com/develop/ui/views/launch/splash-screen) - Native
      api for splash screen for above Android 12.
    * [RoomDB](https://developer.android.com/training/data-storage/room) - Room library enables you
      to persistently store and manage your app's data with an abstraction layer over SQLite.
    * [Coil](https://coil-kt.github.io/coil/compose/) - An image loading library for Android backed
      by Kotlin Coroutines.
    * [Paging-3](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) -
      The Paging library helps you load and display pages of data from a larger dataset from local
      storage or over a network.
    * [Cloudy](https://github.com/skydoves/Cloudy) - Jetpack Compose blur effect library.
    * [ComposeImageBlurHash](https://github.com/orlandev/compose-image-blurhash) -
      ComposeImageBlurHash is a Jetpack Compose component with the necessary implementation to
      display a blurred image while the real image is loaded from the internet.


* Architecture
    * Clean architecture with MVVM

### Demo:
https://github.com/user-attachments/assets/14c81885-26eb-4064-a395-0711bb4200ef



### Features under development

- [ ] Setting the downloaded image as device wallpaper.
