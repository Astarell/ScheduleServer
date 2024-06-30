package scheduleapp.scheduleserver.models;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Entity
@Table(name = "subject")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode
public class Subject {

//    @JsonIgnore
    @Schema(name = "id", example = "1", description = "Field 'id' should be null for creating and have value for updating")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Schema(name = "name", example = "Math", description = "Subject's name")
    @Column(name = "name")
    private String name;

    @JsonCreator
    public Subject(@JsonProperty(value = "id") Long id,
                   @JsonProperty(value = "name") String name){
        this.id = id;
        this.name = name;
    }

    @Hidden
//    @JsonBackReference
    @OneToMany(mappedBy = "subject")
    private Collection<Schedule> subjectSchedule;
}
