package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.SupportTicket;
import com.gantasoft.asvmallsellersv1apis.service.SupportTicketService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tickets")
public class SupportTicketController {

    private final SupportTicketService service;

    public SupportTicketController(SupportTicketService service) {
        this.service = service;
    }

    @GetMapping
    public List<SupportTicket> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public SupportTicket getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public SupportTicket create(@RequestBody SupportTicket ticket) {
        return service.save(ticket);
    }

    @PutMapping("/{id}")
    public SupportTicket update(@PathVariable Long id, @RequestBody SupportTicket ticket) {
        SupportTicket existing = service.getById(id);
        if (existing != null) {
            existing.setCustomer(ticket.getCustomer());
            existing.setOrder(ticket.getOrder());
            existing.setSubject(ticket.getSubject());
            existing.setStatus(ticket.getStatus());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
