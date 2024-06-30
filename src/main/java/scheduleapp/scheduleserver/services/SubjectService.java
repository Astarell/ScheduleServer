package scheduleapp.scheduleserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scheduleapp.scheduleserver.models.Subject;
import scheduleapp.scheduleserver.projection_views.SubjectView;
import scheduleapp.scheduleserver.repositories.SubjectRepository;

import java.util.Optional;

@Service
public class SubjectService implements BasicServiceOptions<SubjectView, Subject> {

    private final SubjectRepository subjectRepo;

    @Autowired
    public SubjectService(SubjectRepository subjectRepo){
        this.subjectRepo = subjectRepo;
    }


    @Override
    public Iterable<SubjectView> findAll() {
        return subjectRepo.customFindAll();
    }

    @Override
    public Optional<SubjectView> findById(Long id) {
        return subjectRepo.findById(id, SubjectView.class);
    }

    @Override
    public void add(Subject entity) {
        subjectRepo.save(entity);
    }

    @Override
    public void update(Subject patchEntity) {
        Subject curSubject = subjectRepo.findById(patchEntity.getId(), Subject.class).orElse(null);
        if(curSubject == null)
            return;

        if(!curSubject.getName().equals(patchEntity.getName()))
            curSubject.setName(patchEntity.getName());

        subjectRepo.save(curSubject);
    }

    @Override
    public void deleteById(Long id) {
        subjectRepo.deleteById(id);
    }

    @Override
    public void delete(Subject entity) {
        subjectRepo.delete(entity);
    }
}
