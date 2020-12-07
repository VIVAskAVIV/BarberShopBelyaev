package belyaev.ai182.barbershop.services;

import belyaev.ai182.barbershop.entities.Worker;
import belyaev.ai182.barbershop.repositories.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {
    private WorkerRepository repository;

    @Autowired
    public void setRepository(WorkerRepository repository) {
        this.repository = repository;
    }

    public Worker addWorker(Worker worker) {
        return repository.save(worker);
    }

    public void deleteWorker(Worker worker) {
        repository.delete(worker);
    }

    public List<Worker> findAll() {
        return repository.findAll();
    }

    public Worker findById(Integer id) { return repository.findByWorkerId(id);}

}
