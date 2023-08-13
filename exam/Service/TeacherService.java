package com.example.exam.Service;

import com.example.exam.Model.Teacher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TeacherService {
    ArrayList<Teacher> teachers = new ArrayList<>();

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    //Check if teacher id is existing to make id unique
    public boolean addTeacher(Teacher teacher){
        for (int i = 0; i < teachers.size(); i++) {
            if (teacher.getID().equals(teachers.get(i).getID())){
                return false;
            }
        }
        teachers.add(teacher);
        return true;
    }

    //Check if teacher id is existing to make id unique
    public boolean updateTeacher(Integer id, Teacher teacher){
        for (int i = 0; i <teachers.size() ; i++) {
            if (teacher.getID().equals(teachers.get(i).getID())){
                return false;
            }
            if(teachers.get(i).getID().equals(id)){
                teachers.set(i, teacher);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTeacher(Integer id){
        for (int i = 0; i <teachers.size() ; i++) {
            if(teachers.get(i).getID().equals(id)){
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

    public Teacher getStudentByID(Integer id){
        for (Teacher teacher : teachers) {
            if (teacher.getID().equals(id)) {
                return teacher;
            }
        }
        return null;
    }
}
