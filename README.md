# Mix of API and web test

## Task description
Create an acceptance test for [BoardGameGeek ](https://boardgamegeek.com/) service covering the following scenario:
1.    WEB Open the game collection of a user.
2.    WEB Go to the page of one of the games (chosen at random).
3.    API Check the information about the game - look for poll results about Language Dependence.
4.    WEB Verify that the most voted Language Dependence level is presented on the game's page.

Note: Write test in human-friendly language (Cucumber/Spock) and simply runnable from command line (use Gradle as project build tool). Java/Groovy programming language should be used under the hood. Test is expected to run on both Windows and GNU/Linux OS.

Important: Please don't use any external libraries to handle BoardGameGeek API calls (like bgg4j). Your task is to test the API, not some BGG-specific library. However, you can (and are encouraged to) use a library to make REST calls/testing easier.

## Task implementation

### Main info about implementation
* Java 8 is used (not compatible with Java 7)
* Gradle wrapper is used (so you don't need installed gradle)
* Test is os-independent
* PageObjects and PageFactory patterns are used
* LoadableComponent is used (to verify browser on tight pages)
* No external libraries used (just Selenium, Cucumber and Log4J)
* For api used that reference [BGG XML API](https://boardgamegeek.com/wiki/page/BGG_XML_API)
* XML response is parsed by standard Java DOM and XPATH
* As it was no specific requirement for step 3,4 (specification discrepancy?) I implemented it in that way, that we get maximum voted language dependency level from API and check if it presented on the page

### Folder hierarchy and file/folders description
```
src
│   README.md - this file
|   gradle.build - gradle build file
|   gradlew - gradle wrapper for *nix
|   gradlew - gradle wrapper for Windows
|   settings.gradle - project settings
│
└───main
|     └───java
│     |   └───common - common libraries
│     |   └───pageobjects - page objects
│     |   └───helpers - helpers for different functions
|     └───resources
│         └───log4j.xml - log4j settings
│   
└───test
|   └───java
|   |   └───gradle
|   |       └───cucumber
|   |           └───RunCukesTest - cucumber tests runner
|   |           └───StepsDefinitons - steps definitions          
|    └───resources
|         └───cucumber.feature - test scenario in Gherkin format
```


### Test run
To run the test use:
```bash
gradlew cucumber
