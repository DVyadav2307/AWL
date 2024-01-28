# AWL: Attendence Watch Log

![AWL Logo](https://github.com/DVyadav2307/AWL/blob/main/app/src/main/resources/io/github/dvyadav/awl/images/interface/NerdyOwl.gif)

AWL is a Java application that manages attendance for students and teachers. It allows teachers to create classes, add students, and mark their attendance. It also allows students to view their attendance records and request changes if needed. AWL stands for Attendance Watch Log.

## Features

- Remote Database connection using JDBC
- JavaFX GUI with CSS styling
- Attendance marking and viewing with date
- Attendance change and approval system

## Tech-Stack

- [Java](https://java.com/en/download/)
- [JavaFX](https://gluonhq.com/products/javafx/)
- [MySQL](https://www.mysql.com/)
- CSS
- Gradle
- Web-api Calls

## Installation

To install AWL, you have two options:

- Clone or download this repository from [GitHub](https://github.com/DVyadav2307/AWL) and run the application with gradle. You need to set up a MySQL database and configure the connection details in the `FromDatabase.java` file.
- Download the executable files from the [installer](https://github.com/DVyadav2307/AWL/tree/main/installers) directory and run the application with the appropriate script for your operating system. The executable files include a built-in database and do not require any configuration.

## Usage

To run AWL, you can use one of the following methods:

- Use the gradle wrapper scripts (`gradlew` or `gradlew.bat`) that are included in the repository. You can run the application from the command line with the following command:

  ```bash
  ./gradlew run
  ```

- Use the executable files from the installer directory. You can run the application from the command line with the appropriate script for your operating system. For example, on Windows:

  ```bash
  AWL.bat/.exe/.msi
  ```

Once the application is running, you can create an account by registering yourself in the application, then log in with the same registration credentials. You can choose to register as a teacher or a student. Note that the student part is still developing, and only teachers can mark and view attendance.

If you register as a teacher, you can create classes, add students, and mark their attendance. You can also view the attendance records of your classes and approve or reject the change requests from the students.

If you register as a student, you can view your attendance records and request changes if needed.

## Documentation

This block contains a brief documentation of each file that you gave me. For more details, you can refer to the comments and javadocs in the source code.

- `FromDatabase.java`: This file contains the class that handles the database connection and queries. It uses the MySQL Connector/J library to establish a connection with the MySQL server and execute SQL statements. It also defines some constants and methods for the database operations.
 
- `Launcher.java`: This file contains the class that launches the application. It extends the `Application` class from the JavaFX library and overrides the `start` method. It also sets the title and icon of the primary stage and loads the login page as the initial scene.
 
- `LoginPageController.java`: This file contains the class that controls the login page. It implements the `Initializable` interface from the JavaFX library and overrides the `initialize` method. It also defines some fields and methods for the login logic and the navigation to the teacher or student page.

- `Main.java`: This file contains the main class that runs the application. It calls the `launch` method from the `Application` class and passes the `Launcher` class as the argument. It also catches and prints any exceptions that may occur during the execution.

- `StudentModel.java`: This file contains the class that represents a student. It defines some fields and methods for the student's id, name, email, password, and attendance. It also implements the `Serializable` interface from the Java library to enable the data export and import functionality.

- `TeacherPageController.java`: This file contains the class that controls the teacher page. It implements the `Initializable` interface from the JavaFX library and overrides the `initialize` method. It also defines some fields and methods for the teacher's id, name, email, password, classes, students, and attendance. It also handles the user interface and the interaction with the database and the files.

- `All-In-one.css`: This file contains the cascading style sheet that defines the appearance and layout of the user interface. It uses the CSS syntax and properties to style the JavaFX components and elements.

- `Sigin-Login-Page.fxml`: This file contains the FXML document that defines the structure and content of the login page. It uses the FXML syntax and tags to create the JavaFX components and elements. It also specifies the controller class and the event handlers for the login page.

- `Teacher-Page.fxml`: This file contains the FXML document that defines the structure and content of the teacher page. It uses the FXML syntax and tags to create the JavaFX components and elements. It also specifies the controller class and the event handlers for the teacher page.

## Contributing

AWL is an open source project and contributions are welcome. If you want to contribute, please follow these steps:

- Fork this repository and create a new branch
- Raise an issue with complete description
- Make your changes and commit them with a descriptive message
- Push your branch to your forked repository
- Create a pull request and explain your changes

## Contact

If you have any questions, suggestions, or feedback, you can contact me at:

- Email: [divyanshuy858@gmail.com](mailto:divyanshuy858@gmail.com)
- Twitter: [DVyadav2307](https://twitter.com/DVyadav2307)

## Acknowledgements

I would like to thank the following sources for providing the resources and inspiration for this project:

- [JavaFX](https://gluonhq.com/products/javafx/) for the GUI framework
- [FreeDB](https://freedb.tech/) for the free remote database system
- [Gradle](https://gradle.org/) as a build tool
- [httpClient](https://mvnrepository.com/artifact/org.apache.httpcomponents.client5/httpclient5) for api calls
- [json.org](https://mvnrepository.com/artifact/org.json/json) for json handling libs
- [APOTD](https://apod.nasa.gov): Atronpmical Picture of the Day API
- [Introduction to Java Programming](https://amzn.eu/d/9td00zC) by Y. Daniel Liang for the Java concepts and examples.
