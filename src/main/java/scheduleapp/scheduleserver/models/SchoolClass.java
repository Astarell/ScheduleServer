package scheduleapp.scheduleserver.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class SchoolClass {

    @Schema(name = "id", example = "1", description = "Field 'id' should be null for creating and have value for updating")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Schema(name = "name", example = "10A", description = "The school class name")
    private String name;

    @Hidden
    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.MERGE)
    private Collection<Student> students;

    @Hidden
    @OneToMany(mappedBy = "schoolClass", cascade = CascadeType.MERGE)
    private Collection<Schedule> classSchedule;
}
