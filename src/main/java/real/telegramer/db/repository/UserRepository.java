package real.telegramer.db.repository;

import org.springframework.data.repository.CrudRepository;
import real.telegramer.db.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
