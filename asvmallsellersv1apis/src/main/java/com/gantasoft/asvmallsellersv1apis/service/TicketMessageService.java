package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.TicketMessage;
import com.gantasoft.asvmallsellersv1apis.repository.TicketMessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TicketMessageService {

    private final TicketMessageRepository repository;

    public TicketMessageService(TicketMessageRepository repository) {
        this.repository = repository;
    }

    public TicketMessage addMessage(TicketMessage message) {
        return repository.save(message);
    }

    public Optional<TicketMessage> getMessageById(Long id) {
        return repository.findById(id);
    }

    public List<TicketMessage> getMessagesByTicketId(Long ticketId) {
        return repository.findByTicket_TicketId(ticketId);
    }

    public void deleteMessage(Long id) {
        repository.deleteById(id);
    }
}
