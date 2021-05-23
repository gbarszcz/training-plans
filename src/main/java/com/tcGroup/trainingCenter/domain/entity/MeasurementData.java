package com.tcGroup.trainingCenter.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "MEASUREMENTS")
@AttributeOverrides({
        @AttributeOverride(name="auditCD", column=@Column(name="MSR_AUDIT_CD")),
        @AttributeOverride(name="auditCU", column=@Column(name="MSR_AUDIT_CU")),
        @AttributeOverride(name="auditMD", column=@Column(name="MSR_AUDIT_MD")),
        @AttributeOverride(name="auditMU", column=@Column(name="MSR_AUDIT_MU")),
        @AttributeOverride(name="auditRD", column=@Column(name="MSR_AUDIT_RD")),
        @AttributeOverride(name="auditRU", column=@Column(name="MSR_AUDIT_RU"))
})
@Data
@Where(clause = "MSR_AUDIT_RD is null or MSR_AUDIT_RU is null")
public class MeasurementData extends AuditData {
    @Id
    @Column(name = "MSR_ID")
    @GeneratedValue
    private Long id;

    @Column(name = "MSR_DATE")
    @NotBlank
    private Date measurementDate;

    @Column(name = "MSR_HEIGHT")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double height;

    @Column(name = "MSR_WEIGHT")
    @DecimalMin(value = "0")
    @DecimalMax(value = "500")
    private Double weight;

    @Column(name = "MSR_CALF")
    @DecimalMin(value = "10")
    @DecimalMax(value = "100")
    private Double calf;

    @Column(name = "MSR_THIGH")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double thigh;

    @Column(name = "MSR_LOINS")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double loins;

    @Column(name = "MSR_HIPS")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double hips;

    @Column(name = "MSR_WAIST")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double waist;

    @Column(name = "MSR_ABDOMEN")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double abdomen;

    @Column(name = "MSR_CHEST")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double chest;

    @Column(name = "MSR_SHOULDERS")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double shoulders;

    @Column(name = "MSR_TRICEPS")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double triceps;

    @Column(name = "MSR_BICEPS")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double biceps;

    @Column(name = "MSR_FOREARM")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double forearm;

    @Column(name = "MSR_WRIST")
    @DecimalMin(value = "50")
    @DecimalMax(value = "250")
    private Double wrist;

    @ManyToOne(targetEntity = AccountData.class)
    @JoinColumn(name = "ACC_ID")
    @JsonIgnore
    private AccountData account;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MeasurementData that = (MeasurementData) o;
        return id.equals(that.id) && measurementDate.equals(that.measurementDate)
                && Objects.equals(weight, that.weight)
                && Objects.equals(calf, that.calf)
                && Objects.equals(account, that.account);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, measurementDate, weight, calf, account);
    }
}
