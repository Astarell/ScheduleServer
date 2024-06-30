package scheduleapp.scheduleserver.projection_views;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Value;
import scheduleapp.scheduleserver.embeddable.PersonInfo;

@JsonPropertyOrder({"id", "score", "schoolClass", "personInfo"})
public interface StudentView {
    @Value("#{target.schoolClass.name}")
    String getSchoolClass();
    Long getId();
    Double getScore();
    PersonInfo getPersonInfo();
}
