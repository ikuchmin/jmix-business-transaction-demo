package com.company.businesstransactiondemo.entity;

import io.jmix.core.entity.annotation.JmixGeneratedValue;
import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@JmixEntity
@Table(name = "C_TABLE", indexes = {
        @Index(name = "IDX_C_TABLE_T004_KTOPL", columnList = "T004_KTOPL")
})
@Entity
public class CTable {
    @JmixGeneratedValue
    @Column(name = "ID", nullable = false)
    @Id
    private UUID id;

    @JoinColumn(name = "T004_KTOPL")
    @ManyToOne(fetch = FetchType.LAZY)
    private T004 t004;

    public T004 getT004() {
        return t004;
    }

    public void setT004(T004 t004) {
        this.t004 = t004;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}