package belyaev.ai182.barbershop.repositories;

import belyaev.ai182.barbershop.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    Worker findByWorkerId(Integer id);
}
