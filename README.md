# TODOLIST APP

Mid-term app made in **Kotlin** for the Android App Developer course (*Vianova/Experis*)

  

**Mandatory requests:**

1. The app provides for the insertion of tasks and the possibility of marking them as completed.

2. Create an isolated CRUD (*Create, Read, Update, Delete*) system. Tasks must be integrated with all CRUD functions running on them.

3. The system must close after confirmation.

  

**Additional  requests chosen:**

A. Functioning login system which provides for the possibility of recovering the password, the presence of a unique username and a request for an email which is also subject to uniqueness.<br/>

B. Make sure you can export the list of tasks in .txt format.

  

>**IDE**: *IntelliJ* - **TECNOLOGIA**: *Kotlin* - **DEVELOPERS**: *Filippo Bellucci, Alessandro Monti*

  
## App operation

Entering the app you access the first menu which provides two possible choices:

1) Access

2) Signing up



**Access**: Access is possible if there are registered users. For previously registered users, the app requires an username and password. There is the possibility to recover the password through the username and email.

  

**Signing up**: Registration involves entering various fields, such as username, email and password. Username and email must be different from the username and email already present in the system (*the app will automatically notify the user in case of username or email already used*).

Once logged in, we will be faced with a second menu:

1) Create Tasks

2) Edit Tasks

3) Delete Tasks

4) Exit


**Create Tasks**: The app will ask for the name  and the preference of the tasks (*bookmark the tasks*)

  

**Edit Tasks**: The system will automatically print the user's tasks on the screen and ask the user which one he wants to modify. After entering the new data, the system confirms the changes upon request to the user.

  

**Delete Tasks**: The system will automatically print the user's tasks on the screen and ask the user which one he wants to delete. After that the system prints the new updated list of tasks.

  

**Exit**: Upon exiting, the system will ask the user for the name of the .txt file which will be saved automatically. The .txt file will contain the complete list of tasks of the logged in user.


## Database and Classes

The app database consists of two arrayLists. The first arrayList contains the list of registered users. The second arrayList contains the list of inserted tasks.

The two Classes included in the app are:

- ToDo

- User


**ToDo**: Class relating to the individual tasks that are entered by the user who registers or accesses the app. The Class expects an ID, the name of the task and the preference.

  

**User**: Class relating to the single user who registers with the app. The Class includes ID, username, email and password.

  

