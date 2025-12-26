## Online Job Portal:Java web based project

This is a proof-of-concept for a multi-role job portal application developed using Java Servlets,JSP,and JDBC, following a basic Model-View-Controller (MVC) architectural pattern. 
The project demonstrates core Java EE concepts for user authentication, data management, and web-based application developmen

### 🔑 Key Java & Web Features

This project utilizes the following technologies and features to manage data and user flow:

* **Java Database Connectivity (JDBC):** Used extensively in the **DAO (Data Access Object) Layer** to handle all persistent data operations (CRUD: Create, Read, Update, Delete). The `DBUtil.java` class manages the connection pool and database credentials.
* **Java Servlets:** Servlets act as the **Controller** layer. They handle all incoming HTTP requests (POST for registration/login, GET for loading pages) and business logic, then delegate data operations to the DAO layer.
* **JSP (JavaServer Pages):** JSP files (e.g., `register.jsp`, `login.jsp`, `dashboard.jsp`) act as the **View** layer, responsible only for generating the HTML interface seen by the user.
* **Apache Tomcat:** The application is packaged as a **WAR file** and deployed on the Tomcat server, which provides the runtime environment for the Servlets and JSPs.

---

### 🌐 Website Structure and Flow

JDBC project/ (Root Folder)
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── jobportal/
│       │               ├── dao/
│       │               │   ├── UserDAO.java
│       │               │   └── util/
│       │               │       └── DBUtil.java
│       │               ├── exceptions/
│       │               │   └── UserNotFoundException.java
│       │               ├── model/
│       │               │   ├── Admin.java
│       │               │   ├── Employer.java
│       │               │   ├── JobSeeker.java
│       │               │   └── User.java
│       │               └── servlets/
│       │                   ├── LoginServlet.java
│       │                   ├── LogoutServlet.java
│       │                   └── RegisterServlet.java
│       └── webapp/
│           ├── index.jsp
│           ├── jsp/
│           │   ├── dashboard.jsp
│           │   ├── login.jsp
│           │   └── register.jsp
│           └── WEB-INF/
│               └── web.xml
├── pom.xml
├── README.md
└── database
The main user workflow is: **Registration -> Login -> Dashboard.**

#### Review 2
This project has been upgraded to a robust, Jakarta EE 11-compliant job portal. It implements a clean Model-View-Controller (MVC) architecture, ensuring a strict separation between the user interface, business logic, and database operations.
#### Updated Technical Features
-->Advanced JDBC Integration: The DAO Layer (Data Access Object) has been optimized for efficiency. DBUtil.java now handles secure connections to the MySQL database using the latest com.mysql.cj.jdbc.Driver.

-->Standardized Maven Structure: The project now follows the official Maven Directory Layout, improving build reliability and dependency management through pom.xml.

--->Jakarta Expression Language (EL) & JSTL: Integrated Jakarta-compliant tag libraries to replace raw Java code inside JSP files, leading to more maintainable and readable View templates.

--->Automated Build Lifecycle: Utilized Maven to package the application into a .war file, automating the compilation and dependency resolution process.
