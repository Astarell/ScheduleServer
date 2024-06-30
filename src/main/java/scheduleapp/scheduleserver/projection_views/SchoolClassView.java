package scheduleapp.scheduleserver.projection_views;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.Collection;

@JsonPropertyOrder({"id", "name", "students", "classSchedule"})
public interface SchoolClassView {
    Long getId();
    String getName();
    Collection<StudentView> getStudents();
    Collection<ScheduleView> getClassSchedule();
}
