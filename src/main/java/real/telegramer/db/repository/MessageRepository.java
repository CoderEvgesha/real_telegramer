package real.telegramer.db.repository;

import org.springframework.data.repository.CrudRepository;
import real.telegramer.db.model.Message;

public interface MessageRepository extends CrudRepository<Message, Long> {
}
