package scheduleapp.scheduleserver.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.util.Collection;

@Entity
@Table(name = "timepair")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Timepair {

    @Schema(name = "id", example = "1", description = "Field 'id' should be null for creating and have value for updating")
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Schema(name = "startPair", type = "Date-time", example = "12:00:00", description = "The start pair time can be written in '12:00:00' format")
    @Column(name = "start_pair")
    private Time startPair;

    @Schema(name = "endPair", type = "Date-time", example = "12:00:00", description = "The end pair time can be written in '12:00:00' format")
    @Column(name = "end_pair")
    private Time endPair;

    @Hidden
    @OneToMany(mappedBy = "timepair")
    private Collection<Schedule> timepairSchedules;
}