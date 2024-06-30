package scheduleapp.scheduleserver.models;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import scheduleapp.scheduleserver.embeddable.PersonInfo;

import java.sql.Date;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Student {

    @Schema(name = "id", example = "1", description = "Field 'id' should be null for creating and have value for updating")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Embedded
    private PersonInfo personInfo;

    @Schema(name = "score", example = "10.0", description = "Student's average score")
    private Double score;

    @ManyToOne(cascade = CascadeType.MERGE)
    private SchoolClass schoolClass;

    @JsonCreator
    public Student(@JsonProperty(value = "id") Long id,
                   @JsonProperty(value = "personInfo") PersonInfo personInfo,
                   @JsonProperty(value = "score") Double score,
                   @JsonProperty(value = "schoolClass") SchoolClass schoolClass){
        this.id = id;
        this.personInfo = personInfo;
        this.score = score;
        this.schoolClass = schoolClass;
    }
}
