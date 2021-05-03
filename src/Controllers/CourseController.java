package Controllers;

import application.Courses;
import application.CoursesDao;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CourseController implements Initializable {

    @FXML
    private ImageView imageContainer;

    @FXML
    private TextArea descriptionContainer;

    @FXML
    private Text titleContainer;

    private CoursesDao coursesDao = new CoursesDao();

    private String courseTitle;

    private String descriptor;

    private String imagePath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            coursesDao.fillCourses();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            System.out.println("============Reading from file=========");
           // while (myReader.hasNextLine()) {
             //   String data = myReader.nextLine();
               // System.out.println(data);
            //}
            this.courseTitle = myReader.nextLine();
            this.descriptor = myReader.nextLine();
            this.imagePath = myReader.nextLine();
            myReader.close();
            File file = new File(imagePath);
            Image image = new Image(file.toURI().toString());
            imageContainer.setImage(image);
            descriptionContainer.setText(this.descriptor);
            titleContainer.setText(this.courseTitle);

            descriptionContainer.setStyle("-fx-background-insets: 0;-fx-background-color: transparent, white, transparent, white;");

            System.out.println(courseTitle + "\n" + descriptor + "\n" +  imagePath);

            // ImageView - javafx

            // Description - javafx

            //Title - javafx

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }




    }
}
