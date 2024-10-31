package main.java.text;

public class LoginText {

    public String getSignIn() {
        return "Sign In";
    }

    public String getSignUp() {
        return "Sign Up";
    }

    public String getUsernameLabel() {
        return "Username: ";
    }

    public String getPasswordLabel() {
        return "Password: ";
    }

    public String getWelcome() {
        return "Welcome to this recipe sharing platform!";
    }

    public String getInvalidUsername() {
        return "Your input is an invalid username.";
    }

    public String getInvalidPassword() {
        return "Your input is an invalid password.";
    }

    public String getSignUpFailure() {
        return "Sorry. Fail to sign up.";
    }

    public String getSignInFailure() {
        return "Sorry. Fail to sign in.";
    }

    public String getConfirmLogOutQuestion() {
        return "Are you sure that you want to log out?";
    }

    public String getConfirmExitQuestion() {
        return "Are you sure that you want to terminate the program?";
    }

    public String getConfirmLoginQuestionTemplate() {
        return "Successfully signed up!\nDo you want to log in as %s?";
    }

    public String getUserNameInstruction(int minUsernameLength,
                                         int maxUsernameLength) {
        CommonText commonText = new CommonText();
        return String.format(
                "Username should satisfy the following:\n"
                        + commonText.getOptionPrefix() + "%d to %d characters long\n"
                        + commonText.getOptionPrefix() + "each character is either alphabetic or numeric",
                minUsernameLength, maxUsernameLength);
    }

    public String getPasswordInstruction(int minPasswordLength,
                                         int maxPasswordLength) {
        CommonText commonText = new CommonText();
        return String.format("Password should satisfy the following:\n"
                        + commonText.getOptionPrefix() + "%d to %d characters long\n"
                        + commonText.getOptionPrefix() + "contain at least one uppercase letter\n"
                        + commonText.getOptionPrefix() + "contain at least one lowercase letter\n"
                        + commonText.getOptionPrefix() + "contain at least one digit",
                minPasswordLength, maxPasswordLength);
    }
}
