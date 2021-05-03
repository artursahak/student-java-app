package application;
import javafx.beans.property.SimpleStringProperty;
import sun.java2d.pipe.SpanShapeRenderer;


public class Courses {
    private SimpleStringProperty id;
    private SimpleStringProperty names;
    private SimpleStringProperty description;
    private SimpleStringProperty image;

    public String getId() {
        return id.get();
    }

    public SimpleStringProperty idProperty() {
        return id;
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public String getNames() {
        return names.get();
    }

    public SimpleStringProperty namesProperty() {
        return names;
    }

    public void setNames(String names) {
        this.names.set(names);
    }

    public String getDescription() {
        return description.get();
    }

    public SimpleStringProperty descriptionProperty() {
        return description;
    }

    public void setDescription(String description) {
        this.description.set(description);
    }

    public String getImage() {
        return image.get();
    }

    public SimpleStringProperty imageProperty() {
        return image;
    }

    public void setImage(String image) {
        this.image.set(image);
    }

    public Courses(String id, String names, String description, String image){
        this.id = new SimpleStringProperty(id);
        this.names = new SimpleStringProperty(names);
        this.description = new SimpleStringProperty(description);
        this.image = new SimpleStringProperty(image);

    }

    @Override
    public String toString(){
        return "Courses{" +
                "id=" + id +
                ", names=" + names +
                ", description=" + description +
                ", image=" + image +
                '}';
    }

}
