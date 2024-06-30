package scheduleapp.scheduleserver.controllers;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @param <T> View for entity
 * @param <S> Entity
 */
@NoRepositoryBean
public interface BasicControllerOptions<T, S> {

    ResponseEntity<Iterable<T>> findAll();

    ResponseEntity<T> findById(@PathVariable Long id);

    void add(S entity);

    void update(S patchEntity);

    void deleteById(@PathVariable Long id);

    void delete(S entity);
}
