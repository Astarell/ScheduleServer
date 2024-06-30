package scheduleapp.scheduleserver.projection_views;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.OrderColumn;
import scheduleapp.scheduleserver.embeddable.PersonInfo;
import scheduleapp.scheduleserver.models.Schedule;

import java.util.Collection;

@JsonPropertyOrder({"id", "personInfo", "teacherSchedules"})
public interface TeacherView {
    Long getId();
    PersonInfo getPersonInfo();
    Collection<ScheduleView> getTeacherSchedules();
}
