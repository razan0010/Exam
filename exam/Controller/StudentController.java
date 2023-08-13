package com.example.exam.Controller;

import com.example.exam.ApiResponse.ApiResponse;
import com.example.exam.Model.Student;
import com.example.exam.Service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student/")
@AllArgsConstructor
public class StudentController {

    private final StudentService studentService;

   @GetMapping("get")
    public ResponseEntity getStudents(){
        return ResponseEntity.status(200).body(studentService.getStudents());
    }

   @PostMapping("add")
    public ResponseEntity addStudent(@RequestBody @Valid Student student, Errors errors){
       if(errors.hasErrors()){
           return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
       }
      boolean isAvalible = studentService.addStudent(student);
       if (isAvalible) {
           return ResponseEntity.status(200).body(new ApiResponse("Student has been added!"));
       }
       return ResponseEntity.status(400).body(new ApiResponse("Name "+ student.getName() +" is already existing!"));

   }

   @PutMapping("update/{id}")
    public ResponseEntity updateStudent(@PathVariable Integer id, @RequestBody @Valid Student student, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        boolean isFounded = studentService.updateStudent(id, student);
        if (isFounded){
            return ResponseEntity.status(200).body( new ApiResponse("Student data has been updated!"));
        }
       return ResponseEntity.status(400).body(new ApiResponse("ID "+ id + " Not found or name is already existing!"));
   }

   @DeleteMapping("delete/{id}")
   public ResponseEntity deleteStudent(@PathVariable Integer id){
       boolean isFounded = studentService.deleteStudent(id);
       if (isFounded){
           return ResponseEntity.status(200).body( new ApiResponse("Student has been deleted!"));
       }
        return ResponseEntity.status(400).body(new ApiResponse("ID "+ id + " Not found"));
    }

   @GetMapping("getstudentByName/{name}")
    public ResponseEntity getstudentByName(@PathVariable String name){
       if(studentService.getstudentByName(name)==null){
           return ResponseEntity.status(200).body(new ApiResponse("Name "+ name + " Not found"));

       }
           return ResponseEntity.status(200).body(studentService.getstudentByName(name));
   }
}
