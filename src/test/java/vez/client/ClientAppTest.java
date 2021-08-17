package vez.client;

import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.okJson;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import vez.client.dto.StudentDto;

@SpringBootTest
@AutoConfigureWireMock(port = 8080)
public class ClientAppTest {

    @Autowired
    private StudentClient studentClient;

    @Test
    void getStudent_ForGivenStudent_IsReturned() {

        //given
        final Long id = 123L;
        // Stubbing WireMock
        stubFor( get(StudentClient.STUDENT_ENDPOINT + id).willReturn( okJson("{\"id\":123, \"name\": \"Mike\" }") ) );

        //when
        StudentDto dto = studentClient.getStudentById(id);

        //then
        then(dto.getId()).isEqualTo(id);
        then(dto.getName()).isNotBlank();
    }
}
