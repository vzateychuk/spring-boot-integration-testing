package vez.student.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import vez.student.exception.StudentNotFoundException;
import vez.student.model.Student;
import vez.student.srv.StudentService;

@RestController
@AllArgsConstructor
public class StudentController {

    public static final String STUDENT_API = "/students";

    @Autowired private final StudentService studentService;

    @GetMapping(STUDENT_API + "/{id}")
    public Student getStudent(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    // There is a first way how to return the 404 when the StudentNotFoundException will be thrown
    @ExceptionHandler(StudentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void studentNotFoundExceptionHandler(StudentNotFoundException exp) {
        System.out.println("StudentController.studentNotFoundExceptionHandler");
    }

}
