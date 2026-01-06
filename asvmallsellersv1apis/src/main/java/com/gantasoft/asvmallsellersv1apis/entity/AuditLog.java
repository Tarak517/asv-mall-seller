package com.gantasoft.asvmallsellersv1apis.entity;
import com.gantasoft.asvmallsellersv1apis.enums.ActorType;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "audit_log")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long logId;

    @Enumerated(EnumType.STRING)
    private ActorType actorType;

    private Long actorId;
    private String action;
    private String entity;
    private Long entityId;

    @Column(columnDefinition = "TEXT")
    private String meta;

    private LocalDateTime loggedAt;

    // Getters and Setters
    public Long getLogId() { return logId; }
    public void setLogId(Long logId) { this.logId = logId; }

    public ActorType getActorType() { return actorType; }
    public void setActorType(ActorType actorType) { this.actorType = actorType; }

    public Long getActorId() { return actorId; }
    public void setActorId(Long actorId) { this.actorId = actorId; }

    public String getAction() { return action; }
    public void setAction(String action) { this.action = action; }

    public String getEntity() { return entity; }
    public void setEntity(String entity) { this.entity = entity; }

    public Long getEntityId() { return entityId; }
    public void setEntityId(Long entityId) { this.entityId = entityId; }

    public String getMeta() { return meta; }
    public void setMeta(String meta) { this.meta = meta; }

    public LocalDateTime getLoggedAt() { return loggedAt; }
    public void setLoggedAt(LocalDateTime loggedAt) { this.loggedAt = loggedAt; }

    @PrePersist
    protected void onCreate() {
        loggedAt = LocalDateTime.now();
    }
}
