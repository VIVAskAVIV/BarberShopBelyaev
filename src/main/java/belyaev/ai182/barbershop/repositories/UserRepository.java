package belyaev.ai182.barbershop.repositories;

import belyaev.ai182.barbershop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String userName);
}