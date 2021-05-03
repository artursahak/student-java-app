package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class CoursesDao {
    private ObservableList<Courses> courseList = FXCollections.observableArrayList();
    private Statement statement;

    public ObservableList<Courses> getCoursesList(){
        return courseList;
    }

    public ObservableList<Courses> fillCourses() throws Exception{
        statement = Db.connector().createStatement();

        ResultSet resultSet = statement.executeQuery("select * from courses");

        while(resultSet.next()){
            courseList.add(new Courses(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
                    resultSet.getString(4)));
        }
        return courseList;

    }

    public void insert(Courses courses) throws Exception {
        String names = courses.getNames();
        String description = courses.getDescription();
        String image = courses.getImage();

        statement = Db.connector().createStatement();
        String query = "insert into students values(null,)" + names + "','" + description + "','" + image + "','" + "')'";

        System.out.println(query);
        statement.executeUpdate(query);
        statement.close();
    }


}
