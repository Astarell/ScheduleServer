package scheduleapp.scheduleserver.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import scheduleapp.scheduleserver.models.Student;
import scheduleapp.scheduleserver.projection_views.StudentView;

import java.util.Optional;

public interface StudentRepository extends Repository<Student, Long> {
    void deleteById(Long id);
    @Query("FROM Student ")
    Iterable<StudentView> customFindAll();
    <T> Optional<T> findById(Long id, Class<T> type);
    void save(Student entity);
    void delete(Student entity);
}
