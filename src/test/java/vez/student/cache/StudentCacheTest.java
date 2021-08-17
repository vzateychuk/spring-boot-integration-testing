package vez.student.cache;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import vez.student.model.Student;
import vez.student.repo.StudentRepo;
import vez.student.srv.StudentService;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class StudentCacheTest {

    @Autowired private StudentService studentService;

    @MockBean private StudentRepo studentRepo;

    @Test
    void getStudentById_forMultipleRequests_isRetrievedFromCache() {

        //given
        Long id = 123L;
        Student expected = new Student(id, "Mike", true, 10);
        given(studentRepo.findById(id)).willReturn(Optional.of(expected));

        //when
        studentService.getStudentById(id);
        studentService.getStudentById(id);
        studentService.getStudentById(id);

        //then
        then(studentRepo).should(times(1)).findById(id);
    }


}
