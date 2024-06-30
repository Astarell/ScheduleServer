package scheduleapp.scheduleserver.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scheduleapp.scheduleserver.models.SchoolClass;
import scheduleapp.scheduleserver.models.Student;
import scheduleapp.scheduleserver.projection_views.StudentView;
import scheduleapp.scheduleserver.repositories.SchoolClassRepository;
import scheduleapp.scheduleserver.repositories.StudentRepository;

import java.util.Optional;

@Service
@Getter
@Setter
@NoArgsConstructor
public class StudentService implements BasicServiceOptions<StudentView, Student>{
    private StudentRepository studentRepo;
    private SchoolClassRepository schoolClassRepo;

    @Autowired
    public StudentService(StudentRepository studentRepo,
                          SchoolClassRepository schoolClassRepo){
        this.studentRepo = studentRepo;
        this.schoolClassRepo = schoolClassRepo;
    }

    @Override
    public Iterable<StudentView> findAll() {
        return studentRepo.customFindAll();
    }

    @Override
    public Optional<StudentView> findById(Long id) {
        return studentRepo.findById(id, StudentView.class);
    }

    @Override
    public void add(Student entity) {
        SchoolClass currentSchClass = schoolClassRepo.findById(entity.getSchoolClass().getId(), SchoolClass.class).orElse(null);
        if(currentSchClass == null){
            return;
        }
        entity.setSchoolClass(currentSchClass);

        studentRepo.save(entity);
    }

    @Override
    public void update(Student patchEntity) {
        Student curStudent = studentRepo.findById(patchEntity.getId(), Student.class)
                .orElse(null);
        SchoolClass currentSchClass = schoolClassRepo.findById(patchEntity.getSchoolClass().getId(), SchoolClass.class)
                .orElse(null);

        if(curStudent == null || currentSchClass == null)
            return;

        if(!curStudent.getScore().equals(patchEntity.getScore()))
            curStudent.setScore(patchEntity.getScore());

        if(!curStudent.getPersonInfo().equals(patchEntity.getPersonInfo()))
            curStudent.setPersonInfo(patchEntity.getPersonInfo());

        if(!curStudent.getSchoolClass().equals(currentSchClass))
            curStudent.setSchoolClass(currentSchClass);

        studentRepo.save(curStudent);
    }

    @Override
    public void deleteById(Long id) {
        studentRepo.deleteById(id);
    }

    @Override
    public void delete(Student entity) {

        Student curStudent = studentRepo.findById(entity.getId(), Student.class).orElse(null);
        if(curStudent == null){
            return;
        }

        studentRepo.delete(curStudent);
    }
}
