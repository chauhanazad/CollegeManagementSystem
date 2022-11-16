package com.example.college.faculty;

public class FacultyItem {
    String type;
    String firstName;
    String middleName;
    String lastName;
    String email;
    String imagePath;

    public FacultyItem(String type, String firstName, String middleName, String lastName, String email, String imagePath) {
        this.type = type;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.imagePath = imagePath;
    }

    public String getType() {
        return type;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getImagePath() {
        return imagePath;
    }

}
