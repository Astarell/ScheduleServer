package scheduleapp.scheduleserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scheduleapp.scheduleserver.models.*;
import scheduleapp.scheduleserver.projection_views.ScheduleView;
import scheduleapp.scheduleserver.repositories.*;

import java.util.Optional;

@Service
public class ScheduleService implements BasicServiceOptions<ScheduleView, Schedule> {

    private final ScheduleRepository scheduleRepo;
    private final TeacherRepository teacherRepo;
    private final SubjectRepository subjectRepo;
    private final TimepairRepository timepairRepo;
    private final SchoolClassRepository schoolClassRepo;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepo,
                           TeacherRepository teacherRepo,
                           SubjectRepository subjectRepo,
                           TimepairRepository timepairRepo,
                           SchoolClassRepository schoolClassRepo){
        this.scheduleRepo = scheduleRepo;
        this.teacherRepo = teacherRepo;
        this.subjectRepo = subjectRepo;
        this.timepairRepo = timepairRepo;
        this.schoolClassRepo = schoolClassRepo;
    }

    @Override
    public Iterable<ScheduleView> findAll(){
        return scheduleRepo.customFindAll();
    }

    @Override
    public Optional<ScheduleView> findById(Long scheduleId){
        return scheduleRepo.findById(scheduleId, ScheduleView.class);
    }

    @Override
    public void add(Schedule schedule){
        Timepair currentTimepair = timepairRepo.findById(schedule.getTimepair().getId(), Timepair.class)
                .orElse(null);
        Teacher currentTeacher = teacherRepo.findById(schedule.getTeacher().getId(), Teacher.class)
                .orElse(null);
        Subject currentSubject = subjectRepo.findById(schedule.getSubject().getId(), Subject.class)
                .orElse(null);
        SchoolClass currentSchoolClass = schoolClassRepo.findById(schedule.getSchoolClass().getId(), SchoolClass.class)
                .orElse(null);

        if(currentTimepair == null || currentTeacher == null ||
                currentSubject == null || currentSchoolClass == null){
            return;
        }

        schedule.setTimepair(currentTimepair);
        schedule.setTeacher(currentTeacher);
        schedule.setSubject(currentSubject);
        schedule.setSchoolClass(currentSchoolClass);

        scheduleRepo.save(schedule);
    }

    @Override
    public void update(Schedule patchSchedule){
        Schedule curSchedule = scheduleRepo.findById(patchSchedule.getId(), Schedule.class)
                .orElse(null);
        Timepair currentTimepair = timepairRepo.findById(patchSchedule.getTimepair().getId(), Timepair.class)
                .orElse(null);
        Teacher currentTeacher = teacherRepo.findById(patchSchedule.getTeacher().getId(), Teacher.class)
                .orElse(null);
        Subject currentSubject = subjectRepo.findById(patchSchedule.getSubject().getId(), Subject.class)
                .orElse(null);
        SchoolClass currentSchoolClass = schoolClassRepo.findById(patchSchedule.getSchoolClass().getId(), SchoolClass.class)
                .orElse(null);

        if(curSchedule == null || currentTimepair == null ||
                currentTeacher == null || currentSubject == null || currentSchoolClass == null){
            return;
        }

        if(curSchedule.getClassroom() != patchSchedule.getClassroom())
            curSchedule.setClassroom(patchSchedule.getClassroom());

        if(!curSchedule.getSubject().equals(currentSubject))
            curSchedule.setSubject(currentSubject);

        if(!curSchedule.getSchoolClass().equals(currentSchoolClass))
            curSchedule.setSchoolClass(currentSchoolClass);

        if(!curSchedule.getTeacher().equals(currentTeacher))
            curSchedule.setTeacher(currentTeacher);

        if(!curSchedule.getTimepair().equals(currentTimepair))
            curSchedule.setTimepair(currentTimepair);

        if(!curSchedule.getPairDate().equals(patchSchedule.getPairDate()))
            curSchedule.setPairDate(patchSchedule.getPairDate());

        scheduleRepo.save(curSchedule);
    }

    @Override
    public void deleteById(Long scheduleId){
        scheduleRepo.deleteById(scheduleId);
    }

    @Override
    public void delete(Schedule entity) {

        Schedule currentSchedule = scheduleRepo.findById(entity.getId(), Schedule.class)
                .orElse(null);
        if(currentSchedule == null){
            return;
        }

        scheduleRepo.delete(currentSchedule);
    }
}
