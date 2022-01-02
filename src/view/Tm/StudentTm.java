package view.Tm;

import java.awt.*;

public class StudentTm {
    private String id;
    private String name;
    private Button details;

    public StudentTm() {
    }

    public StudentTm(String id, String name, Button details) {
        this.id = id;
        this.name = name;
        this.details = details;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Button getDetails() {
        return details;
    }

    public void setDetails(Button details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "StudentTm{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", details=" + details +
                '}';
    }
}
