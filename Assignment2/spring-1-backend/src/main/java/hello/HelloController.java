package hello;

import hello.StudentAdmission.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HelloController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }


    @RequestMapping(value = "/admission-status", method = RequestMethod.GET)
    public StudentAdmission getAdmissionStatus(
            @RequestParam(value = "name", defaultValue = "") String name) {
        Status status = name.contains("Baylor") ? Status.ACCEPTED :
                Status.DENIED;
        StudentAdmission sa = new StudentAdmission(name, status);
        return sa;
    }


    @RequestMapping(value = "/student-info", method = RequestMethod.GET)
    public StudentAdmission getStudentInfo(@RequestParam(value = "name", defaultValue = "") String name) {
        StudentAdmission student = getStatus(name);
        return student;
    }


    public StudentAdmission getStatus(String name) {
        String sql = "SELECT * FROM admissions WHERE name = ?";
        try {
            return (StudentAdmission) jdbcTemplate.queryForObject(sql, new Object[]{name}, new StudentAdmissionRowMapper());
        } catch (IncorrectResultSizeDataAccessException e) {
            return null;
        }
    }
}
