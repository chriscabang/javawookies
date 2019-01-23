# *Time Tracking System* by *JavaWookies*
This ***README*** file contains all the information about our project: a time tracking system that uses **Java SE** *(Standard Edition)* technology. In *this repository*, you will understand what this system is all about and you will also find the *instructions on how to get this application up and running on your local machine* for further development and/or testing.

# Table of Contents
- **Project Overview**
- **Prerequisites**
- **System Requirements**
- **Setup Guide**
- **Development and Release Dates**
- **Notes/Updates**
- **About JavaWookies**

# Project Overview

Our system is called the ***JavaWookies Time Tracking System***.

The **Javawookies Time Tracking System** is a desktop/laptop application targeted at small- to medium-sized businesses. The system is simply a *Login/Logout* application that allows two (2) types of users to log in. The users are classified into *Employee* and *Admin*, and both can sign in to their specific accounts and view the corresponding details. The user *Employee* has the ability to check its login/logout details on that day, while the user *Admin* has the ability to add, delete, and edit information of recent and former employees. The system records the time of a specific user who logs in or out based on the local machine's timer.

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
1. Log on to the Javawookies Web Repository by holding the [Shift] key and left-clicking [here](https://www.github.com/chriscabang/javawookies).

![2](https://github.com/chriscabang/javawookies/blob/master/doc/img/GitHub_Javawookies_RepoWeb_CloneOrDownload.png "2")

2. On the right-hand side of the landing page, click the green **Clone or download** drop-down.

3. Click **Download ZIP** below, and choose the directory where you want to save the *javawookies-master.zip* file.

4. Open *javawookies-master.zip*, and extract its contents to your desired application directory. Refer to the examples below:

    `C:\Users\Username\Desktop\Javawookies\master`

![4](https://github.com/chriscabang/javawookies/blob/master/doc/img/ExtractToLocalRepo.png "4")

5. Download and install [**Java SE Development Kit 8**](https://download.oracle.com/otn-pub/java/jdk/8u201-b09/42970487e3af4f5aa5bca3f542482c60/jdk-8u201-windows-x64.exe), and follow the on-screen instructions. It is highly recommended that you leave the default values as they are during installation, and apply this same procedure to future installations.

6. Download and install [**Java SE Runtime Environment 8**](https://download.oracle.com/otn-pub/java/jdk/8u201-b09/42970487e3af4f5aa5bca3f542482c60/jre-8u201-windows-x64.exe).

7. Press together the **[WindowsLogo]**+**[PauseBreak]** keys to show the ***System*** **Information** window.

![7](https://github.com/chriscabang/javawookies/blob/master/doc/img/Windows_Key.png "7")

![8](https://github.com/chriscabang/javawookies/blob/master/doc/img/PathVariableValue_01.png "8")

8. On the left-hand side, click **Advanced system settings** to show the **System Properties** sub-window.

9. Within the **Advanced** tab, click the **Environment Variables...** button, which is located below *Startup and Recovery* > *Settings*.

10. Within **System variables**, look for **Path**, click on that item, and click the **Edit** button. If no **Path** variable exists, create one by clicking **New**. The **Variable name:** should be: *Path*

11. On the **Variable value:** text field, type in or copy-and-paste the directory/path to the *bin* folder of your Java program, and click **OK**. Refer to the example below:

    `C:\Program Files\Java\jdk1.8.0_91\bin`

12. On your Windows computer, click **Start** > **Run**.

13. Type in *cmd.exe* and press **Enter** or click **OK** to open the Windows Command-Line Interface.

14. Type in *java -version* and press **Enter**.

![14](https://github.com/chriscabang/javawookies/blob/master/doc/img/JavaVersion_01.png "14")

15. After doing so, the system should display the most recent versions of both Java Development Kit and Java Runtime Environment. Once confirmed, type in *exit* and press **Enter** to close the Windows Command-Line Interface.

![15](https://github.com/chriscabang/javawookies/blob/master/doc/img/JavaVersion_02.png "15")

16. Download and install the [**MySQL Connector (.JAR file)**](https://github.com/chriscabang/javawookies/raw/master/lib/mysql-connector-java-8.0.13.jar).

17. Refer to **Steps 7 through 9** to re-open **System Information** > **Advanced system settings** > **Environment Variables...**.

![18](https://github.com/chriscabang/javawookies/blob/master/doc/img/CLASSPATH_01.png "18")

18. Within **System variables**, look for **CLASSPATH**, click on that item, and click the **Edit** button. If no **CLASSPATH** variable exists, create one by clicking **New**. The **Variable name:** should be: *CLASSPATH*

19. On the **Variable value:** text field, type in or copy-and-paste the directory/path to the folder where your *mysql-connector-java-8.0.13.jar* file is located, and click **OK**. Refer to the example below:

    `C:\Users\Username\Desktop\Javawookies\master\lib\mysql-connector-java-8.0.13.jar`

![19](https://github.com/chriscabang/javawookies/blob/master/doc/img/CLASSPATH_02.png "19") ![19](https://github.com/chriscabang/javawookies/blob/master/doc/img/CLASSPATH_03.png "19")

20. Download and install [**XAMPP (32-Bit) with MySQL Server**](https://www.apachefriends.org/xampp-files/7.1.25/xampp-win32-7.1.25-0-VC14-installer.exe).

21. After installing XAMPP, open this program from your local computer. Refer to the screenshot below on how to access **PhpMyAdmin**:

![21](https://github.com/chriscabang/javawookies/blob/master/doc/img/XAMPP_PhpMyAdmin.png "21")

22. On the XAMPP Control Panel window, click the **Apache** server's **Start** button, and wait for it to update the *Status change detected:* to *running*.

23. Click the **MySQL** server's **Start** button, and wait for it to update the *Status change detected:* to *running*.

24. Click the **MySQL** server's **Admin** button, and your default browser will open in a new window to access **PhyMyAdmin**.

![25](https://github.com/chriscabang/javawookies/blob/master/doc/img/PhpMyAdmin_01.png "25")

25. On the PhpMyAdmin landing page, click the **Databases** tab.

26. On the **Database name** text field, type in the name of your database, and click the **Create** button. For this example, we are using the database name *timetracker* to be consistent with *timetracker.sql*.

![26](https://github.com/chriscabang/javawookies/blob/master/doc/img/PhpMyAdmin_02.png "26")

27. After creating the database, click the **Import** tab to import the tables inside the *timetracker.sql* file that we downloaded and saved earlier **during Step 4**.

![27](https://github.com/chriscabang/javawookies/blob/master/doc/img/PhpMyAdmin_03.png "27")

28. On the next page, click the **Choose File** button, locate where you extracted/saved *timetracker.sql*, click on that file, and click the **Open** button. Scroll down to the bottom of that same page, and click **Go**.

![28](https://github.com/chriscabang/javawookies/blob/master/doc/img/PhpMyAdmin_04.png "28")

![28](https://github.com/chriscabang/javawookies/blob/master/doc/img/PhpMyAdmin_05.png "28")

29. On the next page, **you should see a number of successful confirmations highlighted in green**, one of which states that the *Import has been successfully finished*.

![29](https://github.com/chriscabang/javawookies/blob/master/doc/img/PhpMyAdmin_06.png "29")

# Development and Release Dates
**JavaWookies** started developing this *Time Tracking System* last *December 15, 2018*. However, at the time of this writing, we are still continuing its development, thus you may experience several bugs during testing and some features are not working. The application will be pre-released *on or before January 26, 2019*.

# Notes/Updates
- "Login.java" can be found under "src" folder.
- "Admin.java" is not yet linked to "Login.java".

# About JavaWookies
Our team consists of:

**Member** | **Username**
:--- | :---
Benjie Fuentes | [benjiefuentes](https://github.com/benjiefuentes)
JM Hortillosa | [jmhort](https://github.com/jmhort)
Loebel Busis | [bellabelle](https://github.com/bellabelle)
Edneil Quitara | [edneil224](https://github.com/edneil224)
Juneil Gamallo | [gamalloneil](https://github.com/gamalloneil)
