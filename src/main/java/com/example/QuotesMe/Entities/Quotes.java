package com.example.QuotesMe.Entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.annotation.Collation;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "quotes_entries")
public class Quotes {
    @Id
    private ObjectId id;
    private String body;
    private String categories;
    private String author;
    private String date;
    public Quotes() {
    }


    public Quotes(ObjectId id, String body, String categories, String author, String date) {
        this.id = id;
        this.body = body;
        this.categories = categories;
        this.author = author;
        this.date = date;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Quotes{" +
                "id='" + id + '\'' +
                ", body='" + body + '\'' +
                ", categories='" + categories + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}
