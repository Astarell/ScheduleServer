package scheduleapp.scheduleserver.repositories;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import scheduleapp.scheduleserver.models.Timepair;
import scheduleapp.scheduleserver.projection_views.TimepairView;

import java.util.Optional;

public interface TimepairRepository extends Repository<Timepair, Long> {
    void deleteById(Long id);
    @Query("FROM Timepair ")
    Iterable<TimepairView> customFindAll();
    <T> Optional<T> findById(Long id, Class<T> type);
    void save(Timepair entity);
    void delete(Timepair entity);
}
