package scheduleapp.scheduleserver.projection_views;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Value;

@JsonPropertyOrder({"id", "schoolClass", "pairDate", "timepair", "classroom", "subject", "teacher"})
public interface ScheduleView {
    Long getId();
    String getPairDate();
    Integer getClassroom();

    @Value("#{target.timepair.startPair + ' – ' + target.timepair.endPair}")
    String getTimepair();

    @Value("#{target.teacher.personInfo.firstName + ' ' + target.teacher.personInfo.middleName + ' ' + target.teacher.personInfo.lastName}")
    String getTeacher();

    @Value("#{target.subject.name}")
    String getSubject();

    @Value("#{target.schoolClass.name}")
    String getSchoolClass();
}
