##Online Job Portal:Java web based project

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

job-portal/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/jobportal/
│   │   │       ├── dao/
│   │   │       ├── exceptions/
│   │   │       ├── model/
│   │   │       └── servlets/
│   │   └── webapp/
│   │       ├
│   │       │  
│   │       │   
│   │       │   
│   │       ├── jsp/
│   │       │   └── login.jsp, dashboard.jsp, etc.
│   │       ├── WEB-INF/
│   │       │   └── web.xml
│   │       └
└── pom.xml

The main user workflow is: **Registration -> Login -> Dashboard.**
