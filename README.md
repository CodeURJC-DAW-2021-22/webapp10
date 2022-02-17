# Youdemy

## Programmers

| Name                           |  Email  |    Github  |
|--------------------------------| ------------- | ----- |
| Emiliano Sloth                 | e.sloth.2022@alumnos.urjc.es  | emslmar |
| Oscar Arranz                   | o.arranz.2019@alumnos.urjc.es | OscarArranz |
| Fiorella Hernandez             | fv.hernandez.2019@alumnos.urjc.es | fiorellaV |
| Jose Manuel De Pablo Cobo      | jm.depablo@alumnos.urjc.es | pablocjm |
| Cassiel Seth Mayorca Heirisman | cs.mayorca.2018@alumnos.urjc.es | cassiel_smh |

## Description

Our web application will be a teaching  platform where students will be able to acquire courses from teachers around the world.

## Users

-Guest
-Student
-Teacher
-Administrator


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

User
Order
Course
Video

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

Play Video

## Algorithm 
Search algorithm for courses (ajax)

## Graphics
Administrators will be able to check visual information about courses

