package com.example.Library.Management.System.Service;

import com.example.Library.Management.System.Entity.LibraryCard;
import com.example.Library.Management.System.Entity.Student;
import com.example.Library.Management.System.Enum.CardStatus;
import com.example.Library.Management.System.Repository.StudentRepository;
import com.example.Library.Management.System.RequestDto.StudentRequestDto;
import com.example.Library.Management.System.RequestDto.StudentUpdateEmailRequestDto;
import com.example.Library.Management.System.ResponseDto.StudentResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    public void addStudent(StudentRequestDto studentRequestDto){
        // create student object;
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setEmail(studentRequestDto.getEmail());

        // create a card object;
        LibraryCard libraryCard = new LibraryCard();
        libraryCard.setStatus(CardStatus.ACTIVATED);
        libraryCard.setStudent(student) ;
        student.setCard(libraryCard);

        studentRepository.save(student);
    }

    public StudentResponseDto updateEmail(StudentUpdateEmailRequestDto studentUpdateEmailRequestDto){

        Student student = studentRepository.findById(studentUpdateEmailRequestDto.getId()).get();
        student.setEmail(studentUpdateEmailRequestDto.getEmail());

        Student updatestudent = studentRepository.save(student);

        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setName(updatestudent.getName());
        studentResponseDto.setEmail(updatestudent.getEmail());

        return studentResponseDto;
    }

    public StudentResponseDto getStudentByEmail(String email) {
        Student student = studentRepository.findByEmail(email);
        StudentResponseDto studentResponseDto = new StudentResponseDto();
        studentResponseDto.setName(student.getName());
        studentResponseDto.setEmail(student.getEmail());
        return studentResponseDto;
    }

}
