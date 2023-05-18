package com.company.businesstransactiondemo.entity;

import io.jmix.core.metamodel.annotation.JmixEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@JmixEntity
@Table(name = "T004")
@Entity
public class T004 {
    @Column(name = "ID", nullable = false)
    @Id
    private String ktopl;

    public String getKtopl() {
        return ktopl;
    }

    public void setKtopl(String id) {
        this.ktopl = id;
    }
}