package real.telegramer.db.repository;

import org.springframework.data.repository.CrudRepository;
import real.telegramer.db.model.Admin;

public interface AdminRepository extends CrudRepository<Admin, Integer> {
    Admin findTopByOrderByIdDesc();
}
