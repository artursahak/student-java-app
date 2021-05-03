package application;
import javafx.beans.property.SimpleStringProperty;
import sun.java2d.pipe.SpanShapeRenderer;

import java.sql.Date;


public class Students {
    private SimpleStringProperty id;
    private SimpleStringProperty name;
    private SimpleStringProperty password;
    private SimpleStringProperty location;

    public Students(String id, String name, String password, String location){
        this.id = new SimpleStringProperty(id);
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleStringProperty(password);
        this.location = new SimpleStringProperty(location);
    }

    @Override
    public String toString() {
        return "Students{" +
                "id=" + id +
                ", name=" + name +
                ", password=" + password +
                ", location=" + location +
                '}';
    }

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPassword() {
        return password.get();
    }

    public SimpleStringProperty passwordProperty() {
        return password;
    }

    public void setPassword(String password) {
        this.password.set(password);
    }

    public String getLocation() {
        return location.get();
    }

    public SimpleStringProperty locationProperty() {
        return location;
    }

    public void setLocation(String location) {
        this.location.set(location);
    }
}
