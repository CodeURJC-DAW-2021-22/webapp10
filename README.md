# Youdemy

## Programmers

| Name                           |  Email  |    Github  |
|--------------------------------| ------------- | ----- |
| Emiliano Sloth                 | e.sloth.2022@alumnos.urjc.es  | emslmar |
| Oscar Arranz                   | o.arranz.2019@alumnos.urjc.es | OscarArranz |
| Fiorella Hernandez             | fv.hernandez.2019@alumnos.urjc.es | fiorellaV |
| Jose Manuel De Pablo Cobo      | jm.depablo@alumnos.urjc.es | pablocjm |

## Description

Our web application will be a teaching  platform where students will be able to acquire courses from teachers around the world.

## Users

- Guest
- Student
- Teacher
- Administrator


### Guests

- Search Content

### Students

- Search Content
- Buy courses
- Review courses based on stars
- Leave comments

### Teachers

- Create courses
- Create different topics inside the course
- Upload videos
- Request video removal 

### Administrators

- Search Content
- Approve course
- Eliminate Course
- Ban users
- Visualize stastistics

## Tables

- User
- Order
- Course
- Video

## User
  - userID or mail
  - name
  - lastname
  - age
  - role (student, teacher)
  
## Order
  - orderID
  - userID (FK)
  - courseID (FK)
  - paymentMethod
  - price
  - orderDate
  
## Course
  - CourseID
  - courseName
  - Category (tech, finance, law)
  - price
  - enrolledStudents
  
## Video
  - videoId
  - videoName
  - videoDataa
  - courseID (FK)
 

## Additional Technology
- PDF generator
- Play Video

## Algorithm 
Search algorithm for courses (ajax)

## Graphics
Administrators will be able to check visual information about courses

# What every developer did

## Emiliano Sloth 
  - Restful Error Handling
  - User Authentication
  - CRF Handling
  - User Creation
  - HTTPS
  - Modify Course Information
  - Delete Course and Lessons within Course
  - Teacher allowed to Modify their own content

## Fiorella Hernández
  - Video View
  - Video Player

## José Manuel de Pablo 
  - Order Handling (Create, Delete).
  - Checkout step from course page to finish order.
  - Order info to PDF file.
  - Listing Orders to PDF file.
  - Users only can see his/her orders.
  - Admin dashboard listing entities.
  - Update database schema.

## Oscar Arranz Pato
  - Course creation
  - Lesson creation
  - AJAX course loading
  - Course search

# Diagrams
![Class Diagram](./Class%20Diagram.png)
![Database Diagram](./database%20schema%20updated.jpeg)


# Start coding

1- Clone the repo `git clone https://github.com/CodeURJC-DAW-2021-22/webapp10`.
2- Navigate to backend folder `cd ./webapp10/backend`.
3- Install the project dependencies `mvn clean install`.
4- Run the project `mvn exec:java -Dexec.mainClass=com.youdemy.YoudemyApplication`.
