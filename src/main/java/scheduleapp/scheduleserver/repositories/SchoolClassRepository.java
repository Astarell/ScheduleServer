package scheduleapp.scheduleserver.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import scheduleapp.scheduleserver.models.SchoolClass;
import scheduleapp.scheduleserver.projection_views.SchoolClassView;

import java.util.Optional;

public interface SchoolClassRepository extends Repository<SchoolClass, Long> {
    void deleteById(Long id);
    @Query("FROM SchoolClass ")
    Iterable<SchoolClassView> customFindAll();
    <T> Optional<T> findById(Long id, Class<T> type);
    void save(SchoolClass entity);
    void delete(SchoolClass entity);
}
