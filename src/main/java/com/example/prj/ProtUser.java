package com.example.prj;

public class ProtUser {
    String username;
    String firstname;
    String lastname;
    String email;
    String id;
    String isAdmin;

    public ProtUser(String username, String firstname, String lastname, String email, String id, String isAdmin) {
        this.username = username;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.id = id;
        this.isAdmin = isAdmin;
    }

    public String getUsername() {
        return username;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public String getIsAdmin() {
        return isAdmin;
    }
}
