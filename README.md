# Learning Progress Tracker

A Program to keep track of progress, play with points and student information. You will be able to add students, list students, add points, find student information, add statistics, send notification for studetns "Not really but as a practice, lets pretend it does".
                case "":
                    System.out.println("No input");
                    break;
                case "exit":
                    System.out.println("Bye!");
                    quit = true;
                    break;
                case "back":
                    System.out.println("Enter 'exit' to exit the program.");
                    break;
                case "add students":
                    handleStudentsAdding();
                    break;
                case "list":
                    handleStudentsIDPrinting();
                    break;
                case "add points":
                    addPointsToStudent();
                    break;
                case "find":
                    printStudentPointsInformation();
                    break;
                case "statistics":
                    printStatistics();
                    break;
                case "notify":
                    statistics.notifyStudents();
                    break;
                default:
                    System.out.println("unknown command!");
                    break;
            }
        
