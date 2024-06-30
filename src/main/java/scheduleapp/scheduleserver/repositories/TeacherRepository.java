package scheduleapp.scheduleserver.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import scheduleapp.scheduleserver.models.Teacher;
import scheduleapp.scheduleserver.projection_views.TeacherView;

import java.util.Optional;

public interface TeacherRepository extends Repository<Teacher, Long> {
    void deleteById(Long id);
    @Query("FROM Teacher ")
    Iterable<TeacherView> customFindAll();
    <T> Optional<T> findById(Long id, Class<T> type);
    void save(Teacher entity);
    void delete(Teacher entity);
}
