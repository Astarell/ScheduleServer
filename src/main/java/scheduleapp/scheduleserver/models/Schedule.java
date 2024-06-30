package scheduleapp.scheduleserver.models;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;

@Entity
@Table(name = "schedule")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Schedule{

    @Schema(name = "id", example = "1", description = "Field 'id' should be null for creating and have value for updating")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Schema(name = "pairDate", type = "Date-time", example = "12:00:00", description = "The pair date can be written in '12:00:00' format")
    @Column(name = "pair_date")
    private Date pairDate;

    @Schema(name = "classroom", example = "111", description = "The classroom number can be written in '12:00:00' format")
    @Column(name = "classroom")
    private int classroom;

    @JsonCreator
    public Schedule(@JsonProperty(value = "id") Long id,
                    @JsonProperty(value = "pairDate") Date pairDate,
                    @JsonProperty(value = "classroom") Integer classroom){
        this.id = id;
        this.pairDate = pairDate;
        this.classroom = classroom;
    }


    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Timepair timepair;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Teacher teacher;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private Subject subject;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    private SchoolClass schoolClass;
}

