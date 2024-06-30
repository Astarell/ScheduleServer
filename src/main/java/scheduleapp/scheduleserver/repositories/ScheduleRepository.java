package scheduleapp.scheduleserver.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import scheduleapp.scheduleserver.models.Schedule;
import scheduleapp.scheduleserver.projection_views.ScheduleView;

import java.util.Optional;

public interface ScheduleRepository extends Repository<Schedule, Long> {
    void deleteById(Long id);
    @Query("FROM Schedule")
    Iterable<ScheduleView> customFindAll();
    <T> Optional<T> findById(Long id, Class<T> type);
    void save(Schedule entity);
    void delete(Schedule entity);
}
