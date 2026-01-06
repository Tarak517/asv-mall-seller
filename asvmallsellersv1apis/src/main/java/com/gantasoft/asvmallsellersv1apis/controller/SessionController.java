package com.gantasoft.asvmallsellersv1apis.controller;

import com.gantasoft.asvmallsellersv1apis.entity.Session;
import com.gantasoft.asvmallsellersv1apis.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Session> getAllSessions() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Session getSession(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public Session createSession(@RequestBody Session session) {
        return service.save(session);
    }

    @PutMapping("/{id}")
    public Session updateSession(@PathVariable Long id, @RequestBody Session session) {
        Session existing = service.getById(id);
        if (existing != null) {
            existing.setUserType(session.getUserType());
            existing.setUserId(session.getUserId());
            existing.setJwtId(session.getJwtId());
            existing.setIpAddress(session.getIpAddress());
            existing.setUserAgent(session.getUserAgent());
            existing.setExpiresAt(session.getExpiresAt());
            existing.setUpdatedAt(session.getUpdatedAt());
            return service.save(existing);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Long id) {
        service.delete(id);
    }
}
