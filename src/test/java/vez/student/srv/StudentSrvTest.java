package vez.student.srv;

import static org.assertj.core.api.Assertions.catchThrowable;
import static org.assertj.core.api.BDDAssertions.then;

import javax.transaction.Transactional;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import vez.student.exception.StudentNotFoundException;
import vez.student.model.Student;
import vez.student.repo.StudentRepo;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Transactional
public class StudentSrvTest {

    @Autowired private StudentRepo studentRepo;
    @Autowired private StudentService studentService;

    @DisplayName("Returning saved student from service layer")
    @Test
    void getStudentByID_forSavedStudent_isReturned() {
        // given
        Student saved = studentRepo.save( new Student(null, "Mike", true, 10) );

        // when
        Student got = studentService.getStudentById(saved.getId());

        // then
        then(got.getId()).isNotNull();
        then(got.getName()).isSameAs(saved.getName());
    }

    @DisplayName("Given getStudentById when Student missed Throw StudentNotFoundException")
    @Test
    void getStudentById_whenMissed_throwException() {

        // given
        final Long ID = 123L;

        // when
        Throwable throwable = catchThrowable( ()-> studentService.getStudentById(ID));

        // then
        BDDAssertions.then(throwable).isInstanceOf(StudentNotFoundException.class);
    }
}
