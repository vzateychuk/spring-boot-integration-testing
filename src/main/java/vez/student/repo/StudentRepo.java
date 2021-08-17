package vez.student.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vez.student.model.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

    Student getStudentByName(String name);

    @Query("select avg(grade) from Student where active = true")
    double getAverageGrade();
}
