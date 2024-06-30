package scheduleapp.scheduleserver.DTOs;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleDTO {
    private Long id;
    private Date pairDate;
    private Integer classroom;
    private TimepairDTO timepair;
    private TeacherDTO teacher;
    private SubjectDTO subject;
    private SchoolClassDTO schoolClass;
}
