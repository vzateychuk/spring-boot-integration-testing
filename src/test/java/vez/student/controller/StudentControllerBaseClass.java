package vez.student.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import vez.student.model.Student;
import vez.student.srv.StudentService;

@WebMvcTest
public class StudentControllerBaseClass {

    @MockBean private StudentService studentService;

    @Autowired private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {

        RestAssuredMockMvc.mockMvc(mockMvc);

        // setup service mock bean
        given(studentService.getStudentById(anyLong())).willReturn(
            new Student(123L, "Mike", true, 10)
        );
    }

}
