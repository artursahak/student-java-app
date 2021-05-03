package Controllers;
import application.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Observable;
import java.util.ResourceBundle;

public class MainPageController implements Initializable {

    @FXML
    private Text userName;

    @FXML
    private HBox superContainer;


    @FXML
    private ScrollPane scrollCont;

    //final ScrollPane sp = new ScrollPane();
    final Image[] images = new Image[5];
    final ImageView[] pics = new ImageView[5];
    //final Label fileName = new Label();
    final String [] imageNames = new String [] {"Images/fw1.jpg","Images/fw2.jpg","Images/fw3.jpg","Images/fw4.jpg","Images/fw5.jpg"
          };

    @FXML
    private Label fileName;

    @FXML
    private Button signOut;

    @FXML
    private ScrollPane sp;

    @FXML
    private ImageView logoContainer;

    @FXML
    private Label myLabel;

    @FXML
    private TitledPane infoSections;


    private CoursesDao coursesDao = new CoursesDao();
    private StudentsDao studentsDao = new StudentsDao();

     static int id;

     private String descriptor;
     private String imagePath;
     private String courseTitle;

     //This method is called whenever the observed object is changed. Observer Java



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

       // System.out.println(MainPageController.getUserData());
      // System.out.println(stage.getUserData());
        sp.setHmax(500);
        sp.setVmax(500);
        File file = new File("Images/moodle-logo-2019.png");
        Image image = new Image(file.toURI().toString());
        logoContainer.setImage(image);

        try {
            coursesDao.fillCourses();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try{
            studentsDao.fillStudents();
        }catch(Exception e){
            e.printStackTrace();
        }

        //System.out.println(myLabel.getScene().getWindow());
        //superContainer.setVgrow(sp, Priority.ALWAYS);
        superContainer.setHgrow(sp, Priority.NEVER);
        //fileName.setLayoutX(30);
        //fileName.setLayoutY(160);

        ObservableList<Students> tmpStudents = studentsDao.getStudentsList();
        GridPane grid = new GridPane();
        grid.setVgap(4);
        grid.setPadding(new Insets(5, 5, 5, 5));
        /*
        grid.add(new Label("First Name: "), 0, 0);
        grid.add(new TextField(), 1, 0);
        grid.add(new Label("Last Name: "), 0, 1);
        grid.add(new TextField(), 1, 1);
        grid.add(new Label("Email: "), 0, 2);
        grid.add(new TextField(), 1, 2);

         */
        //inner class Stud


        infoSections.setText("Registered");

        //1 loop create elements
        for(int i = 0;i<tmpStudents.size();i++){
            grid.add(new TextField(), 1, i);
            TextField tmpText =  (TextField)grid.getChildren().get(i);
            tmpText.setText(tmpStudents.get(i).getName());
            infoSections.setContent(grid);
        }
        // 2 loop, fill those elements

        GridPane greatContainer = new GridPane();


        WriteToFile FileTools = new WriteToFile();
        ObservableList<Courses> tmpCourses = coursesDao.getCoursesList();

        for (int i=0;i<tmpCourses.size();i++){
            GridPane myGrid = new GridPane();
            StackPane myStack = new StackPane();


            images[i] = new Image(tmpCourses.get(i).getImage());
            pics[i] = new ImageView(images[i]);
            pics[i].setFitWidth(150);
            pics[i].setFitHeight(150);
            //pics[i].setX((int)10*i);
            //superContainer.getChildren().add(pics[i]);
            //sp.setContent(pics[]);



            Button btnNumber = new Button();
            //btnNumber.setText(String.valueOf(i+1) + ".View");
            btnNumber.setText(String.valueOf(i+1) + "." + tmpCourses.get(i).getNames());
            btnNumber.setStyle("-fx-background-color:orange;-fx-text-fill:white;");

            btnNumber.setMaxWidth(100);
            btnNumber.setMinHeight(30);
            btnNumber.setId(i+1+"");
            this.id = Integer.parseInt(btnNumber.getId());

            // for lambda expressions

            int tmpI = i;

            btnNumber.setOnAction((ActionEvent)->{
               // System.out.println(btnNumber.getText());
               // OpenScene();


                this.descriptor = tmpCourses.get(tmpI).getDescription();
                this.imagePath = tmpCourses.get(tmpI).getImage();
                this.courseTitle = tmpCourses.get(tmpI).getNames();
                FileTools.writer(courseTitle,descriptor,imagePath);

                //System.out.println(btnNumber.getId());
                Stage CoursePage = new Stage();
                Parent root3 = null;
                try {
                    root3 = FXMLLoader.load(getClass().getResource("/FXML/Course.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene scene3 = new Scene(root3);

                CoursePage.setScene(scene3);
                CoursePage.show();
            });

            myStack.getChildren().addAll(pics[i],btnNumber);
            myStack.setMaxSize(230,230);
            myStack.setAlignment(btnNumber,Pos.BOTTOM_CENTER);
            myGrid.add(myStack,0,0);
           // myGrid.setAlignment();
            greatContainer.setHgap(10);
            greatContainer.setVgap(10);
            int tempoRow = 1;
            int tempoCol = i;
            int k = 2*(i+1);
            if(i>2){
                tempoRow++;
                tempoCol-=3;
            }
            greatContainer.add(myGrid,tempoCol,tempoRow);




           // greatContainer.add(myGrid,1,i);
            //superContainer.getChildren().add(btnNumber);
            //superContainer.getChildren().add(myStack);

        }
        superContainer.getChildren().add(greatContainer);
        superContainer.setSpacing(10);
        //superContainer.setStyle('');
        //scrollCont.setVmax(440);

        sp.setContent(superContainer);
        sp.vvalueProperty().addListener((ObservableValue<? extends Number> ov,
                                         Number old_val, Number new_val) -> {
            fileName.setText(imageNames[(new_val.intValue() - 1)/100]);
        });

    }

    @FXML
    public void getUserText(){
        System.out.println(myLabel.getScene().getWindow());
    }

    @FXML
    public void signOutAction() throws IOException {
        signOut.getScene().getWindow().hide();
        Stage login = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/Login.fxml"));
        Scene scene = new Scene(root);
        login.setScene(scene);
        login.show();
    }
}

