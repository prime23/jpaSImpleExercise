package com.self.exercise.jpa.model;

import javax.persistence.*;

/**
 * Created by prime23 on 2/14/17.
 */
@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;
    private String title;
    private String body;
    private long entered;
    @ManyToOne
    private User user;

    public Entry() {
        // JPA use
    }

    public Entry(String title, String body, long entered, User user) {
        this.title = title;
        this.body = body;
        this.entered = entered;
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getEntered() {
        return entered;
    }

    public void setEntered(long entered) {
        this.entered = entered;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Entry entry = (Entry) o;

        if (entered != entry.entered) return false;
        if (id != null ? !id.equals(entry.id) : entry.id != null) return false;
        if (title != null ? !title.equals(entry.title) : entry.title != null) return false;
        if (body != null ? !body.equals(entry.body) : entry.body != null) return false;
        return user != null ? user.equals(entry.user) : entry.user == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (body != null ? body.hashCode() : 0);
        result = 31 * result + (int) (entered ^ (entered >>> 32));
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Entry{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", entered=" + entered +
                ", user=" + user +
                '}';
    }
}
