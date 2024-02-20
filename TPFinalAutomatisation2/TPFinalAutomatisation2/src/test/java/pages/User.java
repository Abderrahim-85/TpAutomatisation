package pages;

public class User {
    private String username;
    private String password;
    private boolean isValid;
    public User() {
        this.username = "standard_user";
        this.password = "secret_sauce";
        this.isValid = true;
    }

    public User(String username, String password, boolean isValid) {
        this.username = username;
        this.password = password;
        this.isValid = isValid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String email) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }


    @Override
    public String toString() {
        return "User : " +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isValid='" + isValid;

    }
}

