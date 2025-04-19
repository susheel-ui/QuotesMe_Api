package com.example.QuotesMe.Entities;

import com.mongodb.lang.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "users")
public class User {
    @Id
    private ObjectId id;
    @Indexed(unique = true)
    @NonNull
    private String username;
    @NonNull
    private String password;
    @DBRef
    private ArrayList<Quotes> userQuotes = new ArrayList<>();
    private ArrayList<String> roles = new ArrayList<>();

    public User() {
    }

    public User(ObjectId id, @NonNull String username, @NonNull String password, ArrayList<Quotes> userQuotes,ArrayList<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userQuotes = userQuotes;
        this.roles = roles;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Quotes> getUserQuotes() {
        return userQuotes;
    }

    public void setUserQuotes(ArrayList<Quotes> userQuotes) {
        this.userQuotes = userQuotes;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ArrayList<String> getRoles() {
        return roles;
    }

    public void setRoles(ArrayList<String> roles) {
        this.roles = roles;
    }
}
