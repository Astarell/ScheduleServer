package scheduleapp.scheduleserver.models;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import scheduleapp.scheduleserver.embeddable.PersonInfo;

import java.util.Collection;

@Entity
@Table(name = "teacher")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Teacher {

//    @JsonIgnore
    @Schema(name = "id", example = "1", description = "Field 'id' should be null for creating and have value for updating")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private PersonInfo personInfo;

    @JsonCreator
    public Teacher(@JsonProperty(value = "id") Long id,
                   @JsonProperty(value = "personInfo") PersonInfo personInfo){
        this.id = id;
        this.personInfo = personInfo;
    }

    @Hidden
//    @JsonBackReference
    @OneToMany(mappedBy = "teacher")
    private Collection<Schedule> teacherSchedules;
}
