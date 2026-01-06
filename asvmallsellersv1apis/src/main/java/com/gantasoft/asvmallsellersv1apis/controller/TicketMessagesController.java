package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.SupportTicket;
import com.gantasoft.asvmallsellersv1apis.entity.TicketMessage;
import com.gantasoft.asvmallsellersv1apis.repository.SupportTicketRepository;
import com.gantasoft.asvmallsellersv1apis.repository.TicketMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class TicketMessagesController {

    private final TicketMessageRepository ticketMessageRepository;
    private final SupportTicketRepository supportTicketRepository;

    @Autowired
    public TicketMessagesController(TicketMessageRepository ticketMessageRepository,
                                    SupportTicketRepository supportTicketRepository) {
        this.ticketMessageRepository = ticketMessageRepository;
        this.supportTicketRepository = supportTicketRepository;
    }

    
    @GetMapping
    public List<TicketMessage> getAllMessages() {
        return ticketMessageRepository.findAll();
    }

    
    @GetMapping("/{id}")
    public TicketMessage getMessageById(@PathVariable Long id) {
        return ticketMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id " + id));
    }

    
    @PostMapping
    public TicketMessage addMessage(@RequestBody TicketMessage ticketMessage) {
        SupportTicket managedTicket = supportTicketRepository.findById(ticketMessage.getTicket().getTicketId())
                .orElseThrow(() -> new RuntimeException("Ticket not found with id " + ticketMessage.getTicket().getTicketId()));

        ticketMessage.setTicket(managedTicket);
        return ticketMessageRepository.save(ticketMessage);
    }

  
    @PutMapping("/{id}")
    public TicketMessage updateMessage(@PathVariable Long id, @RequestBody TicketMessage updatedMessage) {
        TicketMessage existingMessage = ticketMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id " + id));

        // Update ticket if changed
        if (updatedMessage.getTicket() != null && updatedMessage.getTicket().getTicketId() != null) {
            SupportTicket managedTicket = supportTicketRepository.findById(updatedMessage.getTicket().getTicketId())
                    .orElseThrow(() -> new RuntimeException("Ticket not found with id " + updatedMessage.getTicket().getTicketId()));
            existingMessage.setTicket(managedTicket);
        }

        // Update other fields
        if (updatedMessage.getSenderType() != null) existingMessage.setSenderType(updatedMessage.getSenderType());
        if (updatedMessage.getSenderId() != null) existingMessage.setSenderId(updatedMessage.getSenderId());
        if (updatedMessage.getMessage() != null) existingMessage.setMessage(updatedMessage.getMessage());

        existingMessage.setUpdatedAt(java.time.LocalDateTime.now());

        return ticketMessageRepository.save(existingMessage);
    }


    @DeleteMapping("/{id}")
    public String deleteMessage(@PathVariable Long id) {
        TicketMessage existingMessage = ticketMessageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Message not found with id " + id));

        ticketMessageRepository.delete(existingMessage);
        return "Message with id " + id + " deleted successfully!";
    }
}
