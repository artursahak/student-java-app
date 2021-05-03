package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteToFile {
    /*
    public static void main(String[] args) {
        writer();
        reader();
    }

     */
      //   FileTools.writer(courseTitle,descriptor,imagePath);
    public static void writer(String courseTitle,String descriptor, String imagePath) {
        try {
            FileWriter myWriter = new FileWriter("filename.txt");
            myWriter.write(courseTitle + "\n" + descriptor + "\n" + imagePath );
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void reader(){
        try {
            File myObj = new File("filename.txt");
            Scanner myReader = new Scanner(myObj);
            System.out.println("============Reading from file=========");
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                System.out.println(data);
            }

            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

}
