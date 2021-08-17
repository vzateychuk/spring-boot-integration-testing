package vez.demo.controller;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import vez.demo.model.Student;
import vez.demo.srv.StudentService;

@WebMvcTest
public class StudentControllerTest {

    @Autowired private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getStudent_forSavedStudent_isReturned() throws Exception {

        // given
        given(studentService.getStudentById(anyLong())).willReturn(
            new Student(123L, "Mike", true, 10)
        );

        // when // then
        mockMvc.perform(get("/students/1"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("id").value(123L))
            .andExpect(jsonPath("name").value("Mike"))
            .andExpect(jsonPath("grade").value("10"));
    }
}
