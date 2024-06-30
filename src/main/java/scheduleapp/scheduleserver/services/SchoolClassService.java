package scheduleapp.scheduleserver.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scheduleapp.scheduleserver.models.SchoolClass;
import scheduleapp.scheduleserver.projection_views.SchoolClassView;
import scheduleapp.scheduleserver.repositories.SchoolClassRepository;

import java.util.Optional;

@Service
@Getter
@Setter
@NoArgsConstructor
public class SchoolClassService implements BasicServiceOptions<SchoolClassView, SchoolClass>{
    private SchoolClassRepository schoolClassRepo;

    @Autowired
    public SchoolClassService(SchoolClassRepository schoolClassRepo){
        this.schoolClassRepo = schoolClassRepo;
    }

    @Override
    public Iterable<SchoolClassView> findAll() {
        return schoolClassRepo.customFindAll();
    }

    @Override
    public Optional<SchoolClassView> findById(Long id) {
        return schoolClassRepo.findById(id, SchoolClassView.class);
    }

    @Override
    public void add(SchoolClass entity) {
        schoolClassRepo.save(entity);
    }

    @Override
    public void update(SchoolClass patchEntity) {
        SchoolClass curClass = schoolClassRepo.findById(patchEntity.getId(), SchoolClass.class).orElse(null);
        if(curClass == null)
            return;

        if(!curClass.getName().equals(patchEntity.getName()))
            curClass.setName(patchEntity.getName());

        schoolClassRepo.save(curClass);
    }

    @Override
    public void deleteById(Long id) {
        schoolClassRepo.deleteById(id);
    }

    @Override
    public void delete(SchoolClass entity) {
        schoolClassRepo.delete(entity);
    }
}
