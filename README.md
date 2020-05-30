# JStripe [![](https://jitpack.io/v/Thibstars/JStripe.svg)](https://jitpack.io/#Thibstars/JStripe) [![](https://jitci.com/gh/Thibstars/jstripe/svg)](https://jitci.com/gh/Thibstars/jstripe) [![Dependabot Status](https://api.dependabot.com/badges/status?host=github&repo=Thibstars/JStripe)](https://dependabot.com)
#

Stripe, the leader of Gremlins is responsible for unleashing hordes.

Jstripe is a library that enables use of [gremlins.js](https://github.com/marmelab/gremlins.js) for Java and Selenium.
Monkey test web pages and components to check their robustness.

## Installation
Import this library into your project via maven using the following snippets in your `pom.xml`:

````
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
````

````
	<dependency>
	    <groupId>com.github.thibstars</groupId>
	    <artifactId>jstripe</artifactId>
	    <version>currentVersion</version>
	</dependency>
````

## Usage ##

Example usage:

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");

        ChromeDriver chromeDriver = new ChromeDriver(options);

        BattleField battleField = BattleField.builder()
            .webDriver(chromeDriver)
            .url(new URL("http://www.google.com"))
            .build();

        controller.prepareBattle(battleField);

        simpleHorde.unleash(chromeDriver);

        Thread.sleep(10000);

        simpleHorde.retreat(chromeDriver);

        chromeDriver.quit();