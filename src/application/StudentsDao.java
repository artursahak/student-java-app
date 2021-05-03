package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentsDao {
    private ObservableList<Students> oblist = FXCollections.observableArrayList();
    private Statement statement;

    public ObservableList<Students> getStudentsList(){ return oblist;  }

    public ObservableList<Students> fillStudents() throws Exception{
        statement = Db.connector().createStatement();

        ResultSet resultSet = statement.executeQuery("select * from students");

        while(resultSet.next()){
            oblist.add(new Students(resultSet.getString(1),resultSet.getString(2),resultSet.getString(3),
            resultSet.getString(4)
            ));
        }
        return oblist;


    }

    public void insert(Students students) throws Exception{
        String name = students.getName();
        String password = students.getPassword();
        String location = students.getLocation();
        statement = Db.connector().createStatement();
        String query = "insert into students values( null,'"  + name + "','" + password + "','" + location + "')";
        System.out.println(query);
        statement.executeUpdate(query);
        statement.close();
    }

    public boolean check(String username,String password){
     //   oblist.stream().filter((x)->x.getName().equals(username)&&x.getPassword().equals(password)).forEach(System.out::println);
            boolean flag = false;
            for(Students students:oblist){
                if(students.getName().equals(username)&&students.getPassword().equals(password)){
                    flag=true;
                    break;
                }
            }
            return flag;
    }

}
