package fx.classes;

public class User {
    private String fullName;
    private String username;
    private String emailAddress;
    private String number;
    private String role;
    private String password;

    public User() {}

    public User(
            String fullName,
            String username,
            String emailAddress,
            String number,
            String password,
            String role
    ) {
        this.fullName = fullName;
        this.username = username;
        this.emailAddress = emailAddress;
        this.number = number;
        this.password = password;
        this.role = role;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFirstName(String firstName) {
        this.fullName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmailaddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRole() {
        return role;
    }


    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + fullName + '\'' +
                ", username='" + username + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", number='" + number + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
