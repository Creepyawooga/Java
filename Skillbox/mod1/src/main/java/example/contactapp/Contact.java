package example.contactapp;

public class Contact {
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private String email;

    public Contact(String firstName, String lastName, String middleName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return lastName + " " + firstName + " " + middleName + " - Phone: " + phoneNumber + ", Email: " + email;
    }
}
