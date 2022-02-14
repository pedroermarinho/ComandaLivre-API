package io.github.pedroermarinho.comandalivreapi.domain.Entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
@ToString
public abstract class Auditable{

    @Column(name = "created_date", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    protected Date creationDate;

    @Column(name = "lastMod_date")
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate;

    @CreatedBy
    @Column(name="created_by")
    protected User createdBy;

    @LastModifiedBy
    @Column(name="modified_by")
    protected User modifiedBy;

}
