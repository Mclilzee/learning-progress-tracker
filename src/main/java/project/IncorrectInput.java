package project;

public class IncorrectInput extends Exception {

    private IncorrectInput(String msg) {
        super(msg);
    }

    public static IncorrectInput incorrectPointsFormat() {
        return new IncorrectInput("Incorrect points format");
    }

    public static IncorrectInput incorrectStudentID(String id) {
        return new IncorrectInput("No student is found for id=" + id);
    }

    public static IncorrectInput emailTaken() {
        return new IncorrectInput("This email is already taken");
    }

    public static IncorrectInput incorrectCredentials() {
        return new IncorrectInput("Incorrect credentials");
    }

    public static IncorrectInput incorrectFirstName() {
        return new IncorrectInput("Incorrect first name");
    }

    public static IncorrectInput incorrectLastName() {
        return new IncorrectInput("Incorrect last name");
    }

    public static IncorrectInput incorrectEmail() {
        return new IncorrectInput("Incorrect email");
    }
}
