package hello;

import hello.StudentAdmission.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StudentAdmissionController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @RequestMapping(value = "/api/admission-statuses", method = RequestMethod.GET)
    public Status[] getStatuses() {
        return Status.values();
    }

    @RequestMapping(
            value = "/api/add-student",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces = "application/json"
    )
    public StudentAdmissionForm updateAdmission(@RequestBody StudentAdmissionForm studentAdmissionForm) {
        // Some Business Logic
        if (studentAdmissionForm.getEmail().contains("baylor")) {
            studentAdmissionForm.setStatus(Status.ACCEPTED.toString());
        } else {
            studentAdmissionForm.setStatus(Status.DENIED.toString());
        }
        Status status = getStatus(studentAdmissionForm.getName());
        if (status != null) {
            String deleteSql = "DELETE FROM admissions WHERE name = ?";
            jdbcTemplate.update(deleteSql, studentAdmissionForm.getName());
        }

        System.out.println(
                "Saving admission name: " + studentAdmissionForm.getName() + ",status: " + studentAdmissionForm.getStatus()
        );
        List<Object[]> parameters = new ArrayList<Object[]>();
        parameters.add(new Object[]{studentAdmissionForm.getName(),
                studentAdmissionForm.getStatus(), studentAdmissionForm.getAddress(),
                studentAdmissionForm.getEmail(), studentAdmissionForm.getPhone(),
                studentAdmissionForm.getLevel()});
        jdbcTemplate.batchUpdate(
                "INSERT INTO admissions (name, admissionStatus, address, email, phone, level) VALUES(?, ?,?,?,?,?)",
                parameters
        );
        return studentAdmissionForm;
    }

    public Status getStatus(String name) {
        String sql = "SELECT * FROM admissions WHERE name = ?";
        try {
            StudentAdmission a = (StudentAdmission) jdbcTemplate.queryForObject(
                    sql, new Object[]{name}, new StudentAdmissionRowMapper());
            return a.getStatus();
        } catch (IncorrectResultSizeDataAccessException | NullPointerException e) {
            return null;
        }
    }
}