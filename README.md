# *Time Tracking System* by *JavaWookies*
This ***README*** file contains all the information about our project: a time tracking system that uses **Java SE** *(Standard Edition)* technology. In *this repository*, you will understand what this system is all about and you will also find the *instructions on how to get this application up and running on your local machine* for further development and/or testing.

# Table of Contents
- **About JavaWookies**
- **Project Overview**
- **Prerequisites**
- **System Requirements**
- **Setup Guide**
- **Development and Release Dates**
- **Notes/Updates**

# About JavaWookies
Our team consists of:

**Member** | **Username**
:--- | :---
Benjie Fuentes | [benjiefuentes](https://github.com/benjiefuentes)
JM Hortillosa | [jmhort](https://github.com/jmhort)
Loebel Busis | [bellabelle](https://github.com/bellabelle)
Edneil Quitara | [edneil224](https://github.com/edneil224)
Juneil Gamallo | [gamalloneil](https://github.com/gamalloneil)

# Project Overview

Our system is called the ***JavaWookies Time Tracking System***.

The **Javawookies Time Tracking System** is a desktop/laptop application targeted at small- to medium-sized businesses. The system is simply a *Login/Logout* application that allows two (2) types of users to log in. The users are classified into *Employee* and *Admin*, and both can sign in to their specific accounts and view the corresponding details. The user *Employee* has the ability to check its login/logout details or history and at the same can time can delete time stamps, while the user *Admin* has the ability to add and delete time stamps aS well as informations of recent and former employees. The system records the time of a specific user who logs in or out based on the local machine's timer.

Using this system, employers can keep track of the *Time-In/Time-Out (TITO)* of their employees, view their time logs, and register newly hired employees to the database. In addition, they can also incorporate images of their company logo into this system's design. This application runs in real time.

# Prerequisites
- Java SE Development Kit 8 for compiling .java files
- Java SE Runtime Environment 8 for executing .class files
- The value of *Path* Environment System Variable for Java
- MySQL Connector (.JAR file)
- The value of *CLASSPATH* Environment System Variable for MySQL Connector
- XAMPP (32-Bit) with MySQL Server
- The timetracker.sql database to be imported via `http://localhost/phpmyadmin`
- Button/label images (may be optional)

# System Requirements
- Windows 7 (32-Bit or 64-Bit) or higher
- A dual-core processor
- Less than 500 MB storage space
- At least 1 GB of memory

# Setup Guide
\*NOTE: This section requires an Internet connection.
1. Log on to the Javawookies Remote/Web Repository in a new browser window without closing this specific guide/window. Hold the [Shift] key and left-click [here](https://www.github.com/chriscabang/javawookies).

![2](https://github.com/chriscabang/javawookies/blob/master/doc/img/GitHub_Javawookies_RepoWeb_CloneOrDownload.png "2")

2. On the right-hand side of the landing page, click the green **Clone or download** drop-down.

3. Click **Download ZIP** below, and choose the directory where you want to save the *javawookies-master.zip* file.

4. Open *javawookies-master.zip*, and extract its contents to your desired application directory. Refer to the examples below:

    `C:\Users\Username\Desktop\Javawookies\master`

![4](https://github.com/chriscabang/javawookies/blob/master/doc/img/ExtractToLocalRepo.png "4")

5. Download and install the [**Java SE Development Kit (JDK) 8**](https://download.oracle.com/otn-pub/java/jdk/8u201-b09/42970487e3af4f5aa5bca3f542482c60/jdk-8u201-windows-x64.exe), and follow the on-screen instructions. It is highly recommended that you leave the default values as they are during installation, and apply this same procedure to future installations.

6. Download and install the [**Java SE Runtime Environment (JRE) 8**](https://download.oracle.com/otn-pub/java/jdk/8u201-b09/42970487e3af4f5aa5bca3f542482c60/jre-8u201-windows-x64.exe).

7. Press together the **[WindowsLogo]**+**[PauseBreak]** keys to show the ***System*** **Information** window.

![7](https://github.com/chriscabang/javawookies/blob/master/doc/img/Windows_Key.png "7")

![8](https://github.com/chriscabang/javawookies/blob/master/doc/img/PathVariableValue_01.png "8")

8. On the left-hand side, click **Advanced system settings** to show the **System Properties** sub-window.

9. Within the **Advanced** tab, click the **Environment Variables...** button, which is located below *Startup and Recovery* > *Settings*.

10. Within **System variables**, look for **Path**, click on that item, and click the **Edit** button. If no **Path** variable exists, create one by clicking **New**. The **Variable name:** should be: *Path*

11. On the **Variable value:** text field, type in or copy-and-paste the directory/path to the *bin* folder of your Java program, and click **OK**. Refer to the example below:

    `C:\Program Files\Java\jdk1.8.0_91\bin`

12. On your Windows computer, click **Start** > **Run**.

13. Type in *cmd.exe* and press **Enter** or click **OK** to open the **Windows Command-Line Interface (CMD)**.

14. Type in *java -version* and press **Enter**.

![14](https://github.com/chriscabang/javawookies/blob/master/doc/img/JavaVersion_01.png "14")

15. After doing so, the system should display the most recent versions of both Java JDK and JRE. Once confirmed, type in *exit* and press **Enter** to close the Windows Command-Line Interface.

![15](https://github.com/chriscabang/javawookies/blob/master/doc/img/JavaVersion_02.png "15")

16. Download the [**MySQL Connector (.JAR file)**](https://github.com/chriscabang/javawookies/raw/master/lib/mysql-connector-java-8.0.13.jar), and place this file in the same directory as your .java and .class files.

17. Refer to **Steps 7 through 9** to re-open **System Information** > **Advanced system settings** > **Environment Variables...**.

![18](https://github.com/chriscabang/javawookies/blob/master/doc/img/CLASSPATH_01.png "18")

18. Within **System variables**, look for **CLASSPATH**, click on that item, and click the **Edit** button. If no **CLASSPATH** variable exists, create one by clicking **New**. The **Variable name:** should be: *CLASSPATH*

19. On the **Variable value:** text field, type in or copy-and-paste the directory/path to the folder where your *mysql-connector-java-8.0.13.jar* file is located, and click **OK**. Refer to the example below:

    `C:\Users\Username\Desktop\Javawookies\master\lib\mysql-connector-java-8.0.13.jar`

![19](https://github.com/chriscabang/javawookies/blob/master/doc/img/CLASSPATH_02.png "19") ![19](https://github.com/chriscabang/javawookies/blob/master/doc/img/CLASSPATH_03.png "19")

20. Download and install [**XAMPP (32-Bit) with MySQL Server**](https://www.apachefriends.org/xampp-files/7.1.25/xampp-win32-7.1.25-0-VC14-installer.exe).

21. After installing XAMPP, open this program from your local computer. Refer to the screenshot below on how to access **PhpMyAdmin**:

![21](https://github.com/chriscabang/javawookies/blob/master/doc/img/XAMPP_PhpMyAdmin.png "21")

22. On the ***XAMPP*** **Control Panel** window, click the **Apache** server's **Start** button, and wait for it to update the *Status change detected:* to *running*.

23. Click the **MySQL** server's **Start** button, and wait for it to update the *Status change detected:* to *running*.

24. Click the **MySQL** server's **Admin** button, and your default browser will open in a new window to access **PhyMyAdmin**.

![25](https://github.com/chriscabang/javawookies/blob/master/doc/img/PhpMyAdminHomeLandingPage.png "25")

25. On the ***PhpMyAdmin*** landing page, click the **Import** tab.

![25](https://github.com/chriscabang/javawookies/blob/master/doc/img/ImportTimetrackerSQLtoPhpMyAdmin_01.png "25")

26. On the next page, click the **Choose File** button, locate the directory/folder where you extracted/saved *timetracker.sql*, click on that file, and click the **Open** button. Scroll down to the bottom of that same page, and click **Go**.

![26](https://github.com/chriscabang/javawookies/blob/master/doc/img/ImportTimetrackerSQLtoPhpMyAdmin_02.png "26")

![26](https://github.com/chriscabang/javawookies/blob/master/doc/img/ImportTimetrackerSQLtoPhpMyAdmin_03.png "26")

![26](https://github.com/chriscabang/javawookies/blob/master/doc/img/ImportTimetrackerSQLtoPhpMyAdmin_04.png "26")

27. On the next page, **you should see a number of successful confirmations highlighted in green**, one of which states that the *Import has been successfully finished*. In addition, the user account associated with *timetracker.sql* and its corresponding password have been created for you.

![27](https://github.com/chriscabang/javawookies/blob/master/doc/img/ImportTimetrackerSQLtoPhpMyAdmin_10.png "27")

28. On the left-hand side where a list of databases is visible, make sure that *timetracker* is on the list. This confirms that the *timetracker* database is created successfully. You may also click *timetracker* to expand this database and view its tables.

![28](https://github.com/chriscabang/javawookies/blob/master/doc/img/ImportTimetrackerSQLtoPhpMyAdmin_07.png "28")

29. To check if the user account *admin* was created successfully, click the **User accounts** tab.

![29](https://github.com/chriscabang/javawookies/blob/master/doc/img/ImportTimetrackerSQLtoPhpMyAdmin_11.png "29")

30. On the **User accounts overview** page, *admin* and its associated host *localhost* should be on the list. Refer to the screenshot below.

![30](https://github.com/chriscabang/javawookies/blob/master/doc/img/ImportTimetrackerSQLtoPhpMyAdmin_12.png "30")

31. Refer to **Steps 12 through 14** to make sure that Java is set up on your computer. Once confirmed, still within the **Windows CMD** window, type in `CD`, press the **Space** key once, type in **the path to the folder/directory where your *\*.java* files are saved**, enclose the directory/path in double quotes ( **\"** ) as much as possible, and press **Enter**. If your *\*.java* files are saved in a different storage drive, follow this sample format `CD "anotherdrive:\path\to\dotjava\files\"`. See the example screenshots below for more details as well.

![31](https://github.com/chriscabang/javawookies/blob/master/doc/img/CompileJavaFiles_01.png "31")

![31](https://github.com/chriscabang/javawookies/blob/master/doc/img/CompileJavaFiles_02.png "31")

![31](https://github.com/chriscabang/javawookies/blob/master/doc/img/CompileJavaFiles_03.png "31")

![31](https://github.com/chriscabang/javawookies/blob/master/doc/img/CompileJavaFiles_04.png "31")

32. Type in `JAVAC`, press **Space**, type in **the full name of your Java source code file complete with the .java extension/filetype**, and press **Enter** to build/compile your Java source code into a system-generated **.class** file. Apply this same procedure to the rest of the accompanying .java files. Refer to the example format below.

    `C:\Users\yourUsername\Desktop\Javawookies\master\src>JAVAC Employee.java`

33. If the build/compilation is successful or there are no errors, type in `JAVA`, press **Space**, type in **the file name of your Java source code MINUS the .java part**, and press **Enter** to run the program.

If you do not encounter any error(s), Congratulations!

# Development and Release Dates
**JavaWookies** started developing this *Time Tracking System* last *December 15, 2018*. However, at the time of this writing, we are still continuing its development, thus you may experience several bugs during testing and some features are not working. The application will be pre-released *on or before January 26, 2019*.

# Notes/Updates
- "Login.java" can be found under "src" folder.
- "Admin.java" is not yet linked to "Login.java".
