package vez.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// There is a second way how to return the 404 when the StudentNotFoundException will be thrown
@ResponseStatus(HttpStatus.ACCEPTED)
public class StudentNotFoundException extends RuntimeException{ }
