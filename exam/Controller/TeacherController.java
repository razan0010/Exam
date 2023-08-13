package com.example.exam.Controller;

import com.example.exam.ApiResponse.ApiResponse;
import com.example.exam.Model.Teacher;
import com.example.exam.Service.TeacherService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/va/teacher/")
public class TeacherController {

    private final TeacherService teacherService;

    @GetMapping("get")
    public ResponseEntity getTeachers(){
        return ResponseEntity.status(200).body(teacherService.getTeachers());
    }

    @PostMapping("add")
    public ResponseEntity addTeacher(@RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isAvalible = teacherService.addTeacher(teacher);
        if (isAvalible) {
            return ResponseEntity.status(200).body(new ApiResponse("Teacher has been added!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID "+ teacher.getID() +" is already existing!"));

    }

    @PutMapping("update/{id}")
    public ResponseEntity updateTeacher(@PathVariable Integer id, @RequestBody @Valid Teacher teacher, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isFounded = teacherService.updateTeacher(id, teacher);
        if (isFounded){
            return ResponseEntity.status(200).body( new ApiResponse("Teacher data has been updated!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID "+ id + " Not found or new ID is already existing!"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteTeacher(@PathVariable Integer id){
        boolean isFounded = teacherService.deleteTeacher(id);
        if (isFounded){
            return ResponseEntity.status(200).body( new ApiResponse("Teacher has been deleted!"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("ID "+ id + " Not found"));
    }

    @GetMapping("getStudentByID/{id}")
    public ResponseEntity getstudentByName(@PathVariable Integer id){
        if(teacherService.getStudentByID(id)==null){
            return ResponseEntity.status(200).body(new ApiResponse("ID "+ id + " Not found"));

        }
        return ResponseEntity.status(200).body(teacherService.getStudentByID(id));
    }

}
