# Rock - Paper - Scissors

This is a simple implementation of the rock - paper - scissors game
implemented in Kotlin. You can find an explanation about the game
on [wikipedia](https://en.wikipedia.org/wiki/Rock_paper_scissors).

There are the following game play options available:
- Rock
- Paper
- Scissors

The game is playing statically with two different players. Each player
can be configured by the play options it chooses of in every round.

- Random Player: 
This player is randomly choosing one of the available options. In this game version all game
play options are used.
- Static Player:
This player is always choosing Rock.

The game is running 100 rounds and finally prints the result for each of the player. The 
result contains the amount of the wins, draws and losses.

## Run Application

`./gradlew run`

## Autoformat Code
This will automatically format the code according to the default rules configured
in ktlint (see [ktlint](https://pinterest.github.io/ktlint/0.50.0/))

`./gradlew ktlintFormat`

## Static Code Analysis
This will execute the static code analysis using the detekt rules configured
in [config](./config/detekt/detekt.yml)

`./gradlew detekt`

The reports in different formats can be found in the [report](build/reports/detekt)
directory.

## Code Coverage
This executes all tests and created the coverage report.

`./gradlew jacocoTestReport`

The report can be found in the [report](build/jacocoHtml)
directory.

## Mutation Tests
The mutation tests verify the quality of the tests. For additional information
regarding mutation testing see [pitest](https://pitest.org/)

This executes mutation tests for all productive code.

`./gradlew pitest`

The report can be found in the [report](build/reports/pitest) directory.

## Architecture
There are [ArchUnit](https://www.archunit.org/) tests to verify that
the architecture inside the single module is respected. There is a test
class for this: [EnforceArchitectureTest](src/test/kotlin/com/poisonedyouth/rps/architecture/EnforceArchitectureTest.kt)

There are 3 root packages that have the following access rules:
- domain should only access failure.
- failure should not access any other root package.
- application can access any other package.

## Updating Versions
The current versions of the 3rd party dependencies and plugins can 
be updated to the newest version by the version catalog update plugin.

`./gradlew versionCatalogUpdate --interactive`

This is creating a libs.versions.updates.toml besides the [libs.versions.toml](gradle/libs.versions.toml)
which contains all versions that can be updated. 

By calling

`./gradlew versionCatalogApplyUpdates`

the updates are automatically applied and the libs.versions.updates.toml file is deleted.

More information can be found in the Github repository of the [project](https://github.com/littlerobots/version-catalog-update-plugin)

## Tech Stack

- Kotlin
- Arrow (Either)
- Junit5
- Kotest (Assertions)
- ktlint
- detekt
- jacoco
- pitest (Mutation testing)
- ArchUnit (Enforcing Architecture)