package scheduleapp.scheduleserver.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import scheduleapp.scheduleserver.models.Subject;
import scheduleapp.scheduleserver.projection_views.SubjectView;

import java.util.Optional;

public interface SubjectRepository extends Repository<Subject, Long> {
    void deleteById(Long id);
    @Query("FROM Subject ")
    Iterable<SubjectView> customFindAll();
    <T> Optional<T> findById(Long id, Class<T> type);
    void save(Subject entity);
    void delete(Subject entity);
}
