package kz.iitu.library.repo;

import kz.iitu.library.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findUserByNameIgnoreCase(String name);
    Long deleteUserByNameIgnoreCase(String title);

}
