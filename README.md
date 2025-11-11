# SerenityBDDFramework

## Overview
This is a sample automation framework built using [Serenity BDD](https://serenity-bdd.info/) with Java. It is designed to support Web UI testing (e.g., via Selenium WebDriver). The framework generates rich, detailed HTML reports using Serenity’s reporting capabilities.

## Why Serenity BDD?
- Provides readable, BDD-style reports that are understandable for both technical and non‐technical stakeholders.
- Integrates smoothly with Selenium WebDriver, REST Assured, JUnit/TestNG, etc.
- Includes built-in support for advanced reporting, screenshots, steps logging, and living documentation.

## Prerequisites
Before you begin, ensure you have met the following requirements:
- Java JDK 11 (or configured version)
- Maven 3.x (or Gradle, depending on project)
- Git installed on your machine

## Setup Instructions
```bash
# Clone the repo
git clone https://github.com/1hirachy/SerenityBDDFramework.git
cd SerenityBDDFramework

# Build and run tests (Maven example)
mvn clean verify
```

## Running Tests Locally

* Ensure your browser driver (e.g., ChromeDriver) version is compatible with your browser.
* Update serenity.properties or serenity.conf to set any environment-specific settings (e.g., webdriver.driver=chrome, serenity.browser=chrome).
* Use Maven (or Gradle) to run tests:

``
    mvn clean verify -Dserenity.issues.tracker=mytracker
``

After execution, view the Serenity report at: target/site/serenity/index.html.

## CI / GitHub Actions

See the .github/workflows/ci.yml file for full CI pipeline configuration. In summary:

* Triggered on push to main and on pull-requests.
* Runs mvn clean verify.
* Archives the Serenity HTML report as a workflow artifact.
* Fails the workflow if any tests fail.

## Project Structure

```
├── src/
│   ├── main/ (Test Data, Utils, etc.)
│   └── test/ (PageObjects,Test definitions, step definitions)
├── pom.xml
├── serenity.properties
├── .github/
│   └── workflows/
│       └── ci.yml
└── README.md
```

## Report Generation

The framework uses Serenity BDD’s automatic reporting features. As tests execute, Serenity captures detailed step logs, screenshots on failures, and generates a comprehensive HTML dashboard. After test execution (local or CI), open target/site/serenity/index.html to view results.

### Contributing

Contributions are welcome! Please fork the repository and submit a pull request.
Before submitting:

Follow the existing code style

Add or update tests/step definitions for new features

Ensure the CI pipeline passes without errors

### License
This project is licensed under the MIT License - see the LICENSE file for details.