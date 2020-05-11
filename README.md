# Prerequisites

* Maven installed
* MAVEN_HOME specified
* To check is it installed type `mvn --version` in command line

# Setup

* Clone the repo
* Install dependencies `mvn compile`

## API tests

* Go to https://github.com/settings/tokens
and generate a personal access token.

* Paste the results into token field inside src/test/resources/conf/config.conf.json file 
* Specify the username in the file mentioned above

* Go to GistTests and click Run. ( Ctrl + Shift + F10 in IntelliJ ).

## UI Tests

* Proceed to src/test/java/UITest/MainPageTest and click Run (Ctrl + Shift + F10 in InteliJ).
* Right click on challenge/config/mainpage_tests.testng.xml and then click Run.
* Type `mvn test -P mainpage_tests` in terminal.



