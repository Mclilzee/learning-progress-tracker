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
- `add student` to add student, the input should be space separated as follow `firstName lastName email`
- `list` to output all the students currently enrolled in the courses 
-  `add points` will take the student ID and scores as input, separated by spaces, the points will be in order, `Java` `DSA` `Database` `Spring` more info in the examples bellow.
Note: adding score to a student will enroll him in the course, leaving points as 0 will not.
- `find` to search for specific student courses information
- `statistics` will show all courses overall data from popularity to hardest.
- `notify` will send messages to all users to notify them of their completed courses.
- `exit` to exit te program

# Examples