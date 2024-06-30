package scheduleapp.scheduleserver.embeddable;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.sql.Date;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
public class PersonInfo {

    @Schema(name = "firstName", example = "Alex", description = "Entity's first name")
    @Column(name = "first_name")
    private String firstName;

    @Schema(name = "lastName", example = "Mitchell", description = "Entity's last name")
    @Column(name = "last_name")
    private String lastName;

    @Schema(name = "middleName", example = "Abramovich", description = "Entity's middle name")
    @Column(name = "middle_name")
    private String middleName;

    @Schema(name = "birthday", example = "1970-12-30", description = "Entity's birthday")
    @Column(name = "birthday")
    private Date birthday;
}
