# SoftUni-Java-Web-Basics-September-2019

SoftUni Java Web Basics September 2019

## Instructions how to run projects from console

Make sure you have `git` and `maven` installed.

Used versions:

- OpenJDK 13
- Apache Maven 3.6.2
- git version 2.23.0
- MySQL Community Server 8.0.17

Clone the repository.

```bash
git clone https://github.com/VasAtanasov/SoftUni-Java-Web-Basics-September-2019.git
```

Navigate to the main project directory (`the folder with pom.xml`) and run the following command:

```bash
mvn clean package tomee:run
```

This may take a while for maven to download all the dependencies.

A copy of Tomee version 8.0.0-M3 will be downloaded and managed by tomee maven plugin.

Open the browser and navigate to http://localhost:8007