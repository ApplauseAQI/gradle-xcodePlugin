gradle-xcodePlugin
==================

gradle-xcodePlugin makes it easier to build Mac and iOS projects by specifying the build settings in a single configuration file. The goal is to keep the build file as simple as possible, but also enable a great flexibility for the build.

The gradle-xcodePlugin uses the Apple command line tools (like xcodebuild) to perform the build.

Here a brief overview of the features:

* Build iOS and Mac projects
* Override sign settings for iOS builds
* Perform unit tests
* Support for multiple Xcodes (on one machine)
* [Cocoapods](Cocoapods) support
* [Appledoc](http://gentlebytes.com/appledoc/) support
* Code coverage support (using [gcovr](http://gcovr.com) )
* [Hockeykit](http://hockeykit.net/), [HockeyApp](http://hockeyapp.net), [DeployGate](https://deploygate.com/) , [Apple TestFlight](https://developer.apple.com/testflight/)
* OCLint (Works not with Xcode 7-beta)


## Note

**With version 0.11 the identifier of the plugin has changed to org.openbakery.xcode-plugin see: https://plugins.gradle.org/plugin/org.openbakery.xcode-plugin**

## Requirements

* Xcode 5 or greater
* [Gradle](http://gradle.org) 2.0 or greater
* Java 1.6 or greater


### Current stable version is 0.11.6

0.9.x up to 0.11.x supports Xcode 5, Xcode 6.* and Xcode 7

**Note:**

When you use gradle 1.x you you can use version 0.10.3-gradle1

## Documentation

* [Parameters Documentation](Documentation/Parameters.md)


## Usage

Create a build.gradle file and place it in the same directory where xcodeproj file lies.

Here the minimal content you need in your build.gradle file:

```
plugins {
  id "org.openbakery.xcode-plugin" version "0.11.6"
}

xcodebuild {
  scheme = 'MY-SCHEMA'
  target = 'MY-TARGET'
}

```

## Example

You find example projects in [example/](example/) with a working build.gradle file.
After you have fetched the example go to the `example/iOS/Example` directory and you build the project different targets:

* Build with `gradle xcodebuild`
* Run the unit tests with `gradle test`
* Perform a device build and upload it to hockeyapp with `gradle integration`. Here you need to specify your sign settings first (see [Signing](Documentation/Parameters.md#sign-settings) ). Open the build.gradle file an follow the instructions.
* Perform an appstore build with `gradle appstore`. (Also the sign settings are needed).

# Collaborate

I'm always happy to receive pull requests with new features and if you send a pull request please consider the following things:

* Use the _develop_ branch for pull requests, because all the new stuff is implemented in the _develop_ branch and also pull requests are always merge into _develop_
* Use Tabs instead of spaces.
* Make sure that all unit tests are working before you send the pull request. Just run 'gradle test' 
* I urge you to write unit tests.
* For unit test please use the [spock framework](http://spockframework.org) for mocking. I want to remove the old gmock framework and port the old tests to use spock.
* If a pull request does not contain any unit tests, I always think twice if I should merge it at all.

