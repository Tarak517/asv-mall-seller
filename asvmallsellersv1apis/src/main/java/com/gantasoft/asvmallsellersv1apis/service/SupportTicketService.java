package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.SupportTicket;
import com.gantasoft.asvmallsellersv1apis.repository.SupportTicketRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupportTicketService {

    private final SupportTicketRepository repository;

    public SupportTicketService(SupportTicketRepository repository) {
        this.repository = repository;
    }

    public SupportTicket save(SupportTicket ticket) {
        return repository.save(ticket);
    }

    public List<SupportTicket> getAll() {
        return repository.findAll();
    }

    public SupportTicket getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
