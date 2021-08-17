package vez.demo.repo;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Random;
import java.util.stream.Stream;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import vez.demo.model.Student;

@DataJpaTest
public class StudentRepositoryTest {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void testGetStudentByName_returnsStudentDetails() {

        // given
        Student saved = entityManager.persistFlushFind( new Student(null, "Mike", true, 1) );

        // when
        Student found = studentRepo.getStudentByName("Mike");

        // then
        then(found.getId()).isNotNull();
        then(found.getName()).isSameAs(saved.getName());
        then(found.isActive()).isEqualTo(saved.isActive());
    }

    @Test
    void testAvgGradeStudent_isCorrect() {

        // given
        Random rnd = new Random();
        Student mike = entityManager.persistFlushFind( new Student(null, "Mike", true, rnd.nextInt(100)) );
        Student john = entityManager.persistFlushFind( new Student(null, "John", true, rnd.nextInt(100)) );
        Student susan = entityManager.persistFlushFind( new Student(null, "Susan", false, rnd.nextInt(100)) );
        double expected = Stream.of(mike, john)
            .mapToInt(Student::getGrade)
            .average().orElse(0.0);

        // when
        double got = studentRepo.getAverageGrade();

        // then
        then(got).isCloseTo(expected, Offset.offset(0.01));
    }

}
