package com.test;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

@Entity
@NamedQuery(name = "SubDomain.allocateDomain", query = "SELECT sd from SubDomain sd WHERE sd.bucket = :bucket ORDER BY 'counter' ASC")
public class SubDomain {

    @Id
    @GeneratedValue(generator = "idgen_sd")
    @TableGenerator(name = "idgen_sd", table = "ID_GEN", pkColumnName = "ID_NAME", valueColumnName = "ID_VAL", pkColumnValue = "AGN_SD_SEQ", allocationSize = 1, initialValue = 10)
    protected Long   id;

    @Basic
    protected long   counter;

    @Basic
    protected String assubdomain;

    public long getCounter() {
        return counter;
    }

    public void setCounter(long counter) {
        this.counter = counter;
    }

    @Basic
    protected String tssubdomain;

    @Enumerated(EnumType.STRING)
    protected Bucket bucket;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAssubdomain() {
        return assubdomain;
    }

    public void setAssubdomain(String assubdomain) {
        this.assubdomain = assubdomain;
    }

    public String getTssubdomain() {
        return tssubdomain;
    }

    public void setTssubdomain(String tssubdomain) {
        this.tssubdomain = tssubdomain;
    }

    public Bucket getBucket() {
        return bucket;
    }

    public void setBucket(Bucket bucket) {
        this.bucket = bucket;
    }

}
