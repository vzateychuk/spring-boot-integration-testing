package vez.demo.srv;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import vez.demo.exception.StudentNotFoundException;
import vez.demo.model.Student;
import vez.demo.repo.StudentRepo;

@Service
@RequiredArgsConstructor
public class StudentService {

    @Autowired private StudentRepo studentRepo;

    @Cacheable("students")
    public Student getStudentById(final Long id) {
        return studentRepo.findById(id).orElseThrow(StudentNotFoundException::new);
    }

}
