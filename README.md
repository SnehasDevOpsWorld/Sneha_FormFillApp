# Sneha_FormFillApp

This is simple FormFill Java Maven Web Application (Servlet + JSP).



FULL PROJECT STRUCTURE :

─ 📁FormFillApp/                                         ← your Java web application


    ├── 📄pom.xml                                        ← Maven config — tells Jenkins how to build
    
    ├── 📁db/
    
    │   └── 📄create_tables.sql                         ← to create users table
    
    └── 📁src/
    
        ├──📁main/
        
        │   ├── 📁java/com/sneha/registration/         ← all Java source code
        
        │   │   ├── 📁model/
        
        │   │   │   └── ☕User.java                      ← represents one user (name, email, aadhar etc.)
        
        │   │   ├── 📁util/
        
        │   │   │   └── ☕DBConnection.java               ← DB on/off switch — set DB_ENABLED=true for v2.1 RDS
        
        │   │   ├── 📁dao/
        
        │   │   │   └── ☕UserDAO.java                     ← all DB queries live here (save user, check email)
        
        │   │   └── 📁servlet/
        
        │   │       └── ☕RegistrationServlet.java         ← reads form, validates, calls DAO, shows result
        
        │   └── 📁webapp/                                  ← everything the browser sees
        
        │       ├── 📄index.jsp                            ← registration form page (what user sees)
        
        │       ├── 📄success.jsp                          ← shown after successful registration
        
        │       ├── 📁css/
        
        │       │   └── 📄style.css                        ← all page styling — fonts, colors, layout
        
        │       ├── 📁js/
        
        │       │   └── 📄form-validation.js               ← checks form before submit (client-side, no page reload)
        
        │       └── 📁WEB-INF/
        
        │           └── 📄web.xml                          ← tells Tomcat about the app (welcome page, error pages)
        
        └── 📁test/         
        
            └── ☕UserRegistrationTest.java                 ← Unit tests for Jenkins who runs these automatically on every build


          
