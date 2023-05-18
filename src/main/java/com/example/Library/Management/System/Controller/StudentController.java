package com.example.Library.Management.System.Controller;

import com.example.Library.Management.System.Entity.Student;
import com.example.Library.Management.System.RequestDto.StudentRequestDto;
import com.example.Library.Management.System.RequestDto.StudentUpdateEmailRequestDto;
import com.example.Library.Management.System.ResponseDto.StudentResponseDto;
import com.example.Library.Management.System.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto){
        studentService.addStudent(studentRequestDto);
        return "Student Added";
    }

    @PutMapping("/update_email")
    public StudentResponseDto updateEmail(@RequestBody StudentUpdateEmailRequestDto studentUpdateEmailRequestDto){
        return studentService.updateEmail(studentUpdateEmailRequestDto);
    }

    @GetMapping("/get_by_email")
    public StudentResponseDto getStudent(@RequestParam("email") String email) {
        StudentResponseDto studentResponseDto = studentService.getStudentByEmail(email);
        return studentResponseDto;
    }

}
