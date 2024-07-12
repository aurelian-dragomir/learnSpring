package ing.hub.ingHub.repository.jdbc;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JdbcRepository<T, ID> {
    Optional<T> findById(ID id);

    List<T> findALl();

    T save(T t);

    boolean update(T t);

    boolean delete(ID id);
}
