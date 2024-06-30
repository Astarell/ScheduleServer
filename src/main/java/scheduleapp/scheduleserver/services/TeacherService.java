package scheduleapp.scheduleserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scheduleapp.scheduleserver.models.Teacher;
import scheduleapp.scheduleserver.projection_views.TeacherView;
import scheduleapp.scheduleserver.repositories.TeacherRepository;

import java.util.Optional;

@Service
public class TeacherService implements BasicServiceOptions<TeacherView, Teacher>{

    private final TeacherRepository teacherRepo;

    @Autowired
    public TeacherService(TeacherRepository teacherRepo){
        this.teacherRepo = teacherRepo;
    }


    @Override
    public Iterable<TeacherView> findAll() {
        return teacherRepo.customFindAll();
    }

    @Override
    public Optional<TeacherView> findById(Long id) {
        return teacherRepo.findById(id, TeacherView.class);
    }

    @Override
    public void add(Teacher entity) {
        teacherRepo.save(entity);
    }

    @Override
    public void update(Teacher patchEntity) {
        Teacher curTeacher = teacherRepo.findById(patchEntity.getId(), Teacher.class).orElse(null);
        if(curTeacher == null)
            return;

        if(!curTeacher.getPersonInfo().equals(patchEntity.getPersonInfo()))
            curTeacher.setPersonInfo(patchEntity.getPersonInfo());

        teacherRepo.save(curTeacher);
    }

    @Override
    public void deleteById(Long id) {
        teacherRepo.deleteById(id);
    }

    @Override
    public void delete(Teacher entity) {
        teacherRepo.delete(entity);
    }
}
