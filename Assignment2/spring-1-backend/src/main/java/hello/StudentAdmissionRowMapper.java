package hello;

import hello.StudentAdmission.Level;
import hello.StudentAdmission.Status;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentAdmissionRowMapper implements RowMapper<StudentAdmission> {
    public StudentAdmission mapRow(ResultSet rs, int rowNum) throws SQLException {
        StudentAdmission a = new StudentAdmission();
        String name = rs.getString("name");
        System.out.println("Name: " + name);
        a.setName(name);
//Retrieve Status object from String saved in database
        String statusName = rs.getString("admissionStatus");
        System.out.println("Status: " + statusName);
        if (statusName == null) {
            return null;
        }
        for (Status s : Status.values()) {
            if (statusName.equals(s.name())) {
                a.setStatus(s);
            }
        }
//If status not matched, throw exception
        if (a.getStatus() == null) {
            throw new IllegalArgumentException("Status " + statusName + " was not recognized!");
        }

        String email = rs.getString("email");
        System.out.println("Email: " + email);
        a.setEmail(email);

        String address = rs.getString("address");
        System.out.println("Address: " + address);
        a.setAddress(address);

        String phone = rs.getString("phone");
        System.out.println("Phone: " + phone);
        a.setPhone(phone);


        String level = rs.getString("level");
        System.out.println("Level: " + level);
        if (level == null) {
            return null;
        }

        for (Level l : Level.values()) {
            if (level.equalsIgnoreCase(l.name())) {
                a.setLevel(l);
            }
        }

        return a;
    }
}