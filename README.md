# Learning Progress Tracker

A Program to keep track of progress, play with points and student information.
You will be able to add students, list students, add points, find student information, add statistics, send notification for students "Not really but as a practice, lets pretend it does".

Courses are currently: `Java` `DSA` `Spring` `Databases`.
Each course will have different completion score.
When a student reaches that score, he will be put on the notification list, each student will only be notified once to avoid spam.

### Course scores
- Java - 600
- DSA - 400
- Spring - 550
- Database - 480

# Controls Menu
- `add students` to add student, the input should be space separated as follows `firstName lastName email` the student will be able to have as many middle names as they wish to before he enters the email at the end. 
more in the examples bellow
- `list` to output all the students currently enrolled in the courses 
-  `add points` will take the student ID and scores as input, separated by spaces, the points will be in order, `Java` `DSA` `Database` `Spring` more info in the examples bellow.
Note: adding score to a student will enroll him in the course, leaving points as 0 will not.
- `find` to search for specific student courses information by providing the student's id
- `statistics` will show all courses overall data from popularity to hardest.
- `notify` will send messages to all users to notify them of their completed courses.
- `exit` to exit te program

# Examples

### Adding Students
```console
$ ./gradlew run
> Task :run
Learning Progress Tracker
Refer to README.md for the controls input
$ add students
Enter student credentials or 'back' to return
$ john doe johndoe@hotmail.com
The student has been added
$ mark khalil azmar mark@gmail.com
The student has been added
$ omar ali omar@yahoo.com 
The student has been added
$ back
Total 3 students have been added.
```

### Listing Students
```console
$ list
Students:
1148414523
318941027
2034683521
```

### Adding Points
```console
add points
Enter an id and points or 'back' to return:
1148414523 0 0 300 100
Points updated.
318941027 700 100 50 50
Points updated.
2034683521 0 200 0 100
Points updated.
back
```

### Checking Statistics
```console
$ statistics
Type the name of a course to see details or 'back to quit:
Most popular: Spring
Least popular:: Java
Highest activity: Spring
Lowest activity: Java
Easiest course: Java
Hardest course: Spring
$ Java
Java
id              points          completed
318941027               700             116.7%
$ Spring
Spring
id              points          completed
1148414523              100             18.2%
2034683521              100             18.2%
318941027               50              9.1%
```
### Find student course details
```console
$ find
Enter an id or 'back' to return:
$ 1148414523
1148414523 points: Java=0; DSA=0; Databases=300; Spring=100
```
### Notifying Students
```console
$ notify
To: mark@gmail.com
Re: Your Learning Progress
Hello, mark khalil azmar! You have accomplished our Java course!
Total 1 student have been notified.
$ notify
Total 0 students have been notified.
```