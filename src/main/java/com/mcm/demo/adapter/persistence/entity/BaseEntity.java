package com.mcm.demo.adapter.persistence.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Base Entity : Contains Audit column
 *
 * @author kchav
 */
@MappedSuperclass
public abstract class BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgen")
    @Column(name = "id")
    private Long id;

    /**
     * Audit DB column: created_at.
     */
    @Column(name = "created_at")
//    @Temporal(TemporalType.DATE)
    private LocalDateTime createdTimestamp;

    /**
     * Audit DB column: created_by.
     */
    @Column(name = "created_by")
    private String createdBy;

    /**
     * Audit DB column: last_updated_at.
     */
    @Column(name = "last_updated_at")
//    @Temporal(TemporalType.DATE)
    private LocalDateTime lastUpdatedTimestamp;

    /**
     * Audit DB column: last_updated_by.
     */
    @Column(name = "last_updated_by")
    private String lastUpdatedBy;

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(final LocalDateTime createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getLastUpdatedTimestamp() {
        return lastUpdatedTimestamp;
    }

    public void setLastUpdatedTimestamp(final LocalDateTime lastUpdatedTimestamp) {
        this.lastUpdatedTimestamp = lastUpdatedTimestamp;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(final String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
