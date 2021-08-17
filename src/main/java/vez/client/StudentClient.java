package vez.client;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import vez.client.dto.StudentDto;

@Component
@AllArgsConstructor
public class StudentClient {

    public static final String STUDENT_ENDPOINT = "/students/";

    @Autowired private final RestTemplate restTemplate;

    public StudentDto getStudentById(final Long id) {

        StudentDto student = restTemplate.getForObject("http://localhost:8080" + STUDENT_ENDPOINT + id, StudentDto.class);
        return student;
    }
}
