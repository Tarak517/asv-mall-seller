package com.gantasoft.asvmallsellersv1apis.service;

import com.gantasoft.asvmallsellersv1apis.entity.Notification;
import com.gantasoft.asvmallsellersv1apis.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {

    private final NotificationRepository repo;

    public NotificationService(NotificationRepository repo) {
        this.repo = repo;
    }

    public Notification save(Notification n) {
        return repo.save(n);
    }

    public List<Notification> getAll() {
        return repo.findAll();
    }

    public Notification getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public List<Notification> getByUser(Long userId) {
        return repo.findByUserId(userId);
    }

    public Notification markAsRead(Long id) {
        Notification n = repo.findById(id).orElse(null);
        if (n != null) {
            n.setIsRead(true);
            repo.save(n);
        }
        return n;
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }
}
