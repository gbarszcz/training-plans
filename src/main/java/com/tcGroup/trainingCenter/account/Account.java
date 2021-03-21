package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.role.Role;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ACCOUNTS",
        uniqueConstraints=
        @UniqueConstraint(columnNames={"ACC_EMAIL"})
)
@AttributeOverrides({
        @AttributeOverride(name="auditCD",
                column=@Column(name="ACC_AUDIT_CD")),
        @AttributeOverride(name="auditCU",
                column=@Column(name="ACC_AUDIT_CU")),
        @AttributeOverride(name="auditMD",
                column=@Column(name="ACC_AUDIT_MD")),
        @AttributeOverride(name="auditMU",
                column=@Column(name="ACC_AUDIT_MU")),
        @AttributeOverride(name="auditRD",
                column=@Column(name="ACC_AUDIT_RD")),
        @AttributeOverride(name="auditRU",
                column=@Column(name="ACC_AUDIT_RU"))
})
@Data
public class Account extends AuditData {
    @Id
    @Column(name = "ACC_ID")
    @GeneratedValue
    protected long id;

    @Column(name = "ACC_EMAIL")
    private String accountEmail;

    @Column(name = "ACC_PASSWORD")
    private String accountPassword;

    @Column(name = "ACC_STATUS")
    private String accountStatus;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "ACCOUNT_ROLE",
            joinColumns = @JoinColumn(name = "ACC_ID"),
            inverseJoinColumns = @JoinColumn(name = "ROL_ID"))
    private Set<Role> roles;

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Account account = (Account) o;
        return id == account.id
                && accountEmail.equals(account.accountEmail)
                && accountPassword.equals(account.accountPassword)
                && accountStatus.equals(account.accountStatus)
                && Objects.equals(roles, account.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, accountEmail, accountPassword, accountStatus);
    }
}
