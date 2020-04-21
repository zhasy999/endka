package kz.iitu.library.repo;

import kz.iitu.library.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByUsernameIgnoreCase(String name);
    Long deleteUserByUsernameIgnoreCase(String title);

}
