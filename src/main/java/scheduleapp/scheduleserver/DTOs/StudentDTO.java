package scheduleapp.scheduleserver.DTOs;

import io.swagger.v3.oas.annotations.Hidden;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import scheduleapp.scheduleserver.embeddable.PersonInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Long id;
    @Embedded
    private PersonInfo personInfo;
    private Double score;
    private SchoolClassDTO schoolClass;
}
