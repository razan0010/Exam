package com.example.exam.Service;

import com.example.exam.Model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class StudentService {

    ArrayList<Student> students = new ArrayList<>();

    public ArrayList<Student> getStudents() {
        return students;
    }

//Check if student name is existing to make name unique because getstudentByName method will return one student
    public boolean addStudent(Student student){
        for (int i = 0; i < students.size(); i++) {
            if (student.getName().equalsIgnoreCase(students.get(i).getName())){
                return false;
            }
        }
        students.add(student);
        return true;
    }

    //Check if student name is existing to make name unique
    public boolean updateStudent(Integer id, Student student){
        for (int i = 0; i <students.size() ; i++) {
            if (student.getName().equalsIgnoreCase(students.get(i).getName())){
                return false;
            }
            if(students.get(i).getID().equals(id)){
                students.set(i, student);
                return true;
            }
        }
        return false;
    }

    public boolean deleteStudent(Integer id){
        for (int i = 0; i <students.size() ; i++) {
            if(students.get(i).getID().equals(id)){
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    public Student getstudentByName(String name){
        for (Student student : students) {
            if (student.getName().equalsIgnoreCase(name)) {
                return student;
            }
        }
        return null;
    }
}
