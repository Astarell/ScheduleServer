package scheduleapp.scheduleserver.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scheduleapp.scheduleserver.models.Timepair;
import scheduleapp.scheduleserver.projection_views.TimepairView;
import scheduleapp.scheduleserver.repositories.TimepairRepository;

import java.util.Optional;

@Service
public class TimepairService implements BasicServiceOptions<TimepairView, Timepair>{
    private final TimepairRepository timepairRepo;

    @Autowired
    public TimepairService(TimepairRepository timepairRepo){
        this.timepairRepo = timepairRepo;
    }

    @Override
    public Iterable<TimepairView> findAll() {
        return timepairRepo.customFindAll();
    }

    @Override
    public Optional<TimepairView> findById(Long id) {
        return timepairRepo.findById(id, TimepairView.class);
    }

    @Override
    public void add(Timepair entity) {
        timepairRepo.save(entity);
    }

    @Override
    public void update(Timepair patchEntity) {
        Timepair curTimepair = timepairRepo.findById(patchEntity.getId(), Timepair.class).orElse(null);

        if(curTimepair == null)
            return;

        if(curTimepair.getStartPair() != patchEntity.getStartPair())
            curTimepair.setStartPair(patchEntity.getStartPair());

        if(curTimepair.getEndPair() != patchEntity.getEndPair())
            curTimepair.setEndPair(patchEntity.getEndPair());

        timepairRepo.save(curTimepair);
    }

    @Override
    public void deleteById(Long id) {
        timepairRepo.deleteById(id);
    }

    @Override
    public void delete(Timepair entity) {
        timepairRepo.delete(entity);
    }
}
