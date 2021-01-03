package api.mailer.repository;

import api.mailer.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, String> {
    List<User> findAll();
    User findByEmail(String email);
}
