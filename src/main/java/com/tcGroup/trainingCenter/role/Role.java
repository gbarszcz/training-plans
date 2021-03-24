package com.tcGroup.trainingCenter.role;

import com.tcGroup.trainingCenter.account.Account;
import com.tcGroup.trainingCenter.utility.entity.AuditData;
import lombok.Data;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ROLES")
@AttributeOverrides({ @AttributeOverride(name = "auditCD", column = @Column(name = "ROL_AUDIT_CD")),
                @AttributeOverride(name = "auditCU", column = @Column(name = "ROL_AUDIT_CU")),
                @AttributeOverride(name = "auditMD", column = @Column(name = "ROL_AUDIT_MD")),
                @AttributeOverride(name = "auditMU", column = @Column(name = "ROL_AUDIT_MU")),
                @AttributeOverride(name = "auditRD", column = @Column(name = "ROL_AUDIT_RD")),
                @AttributeOverride(name = "auditRU", column = @Column(name = "ROL_AUDIT_RU")) })
@Data
public class Role extends AuditData {

        public interface Roles {
                public static final String ADMIN = "ADMIN";
                public static final String USER = "USER";
        }

        @Id
        @Column(name = "ROL_ID")
        @GeneratedValue
        protected long id;

        @Column(name = "ROL_NAME")
        private String roleName;

        @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "roles")
        Set<Account> accounts;

        @Override
        public Long getId() {
                return this.id;
        }

        @Override
        public boolean equals(Object o) {
                if (this == o)
                        return true;
                if (o == null || getClass() != o.getClass())
                        return false;
                if (!super.equals(o))
                        return false;
                Role role = (Role) o;
                return id == role.id && roleName.equals(role.roleName) && Objects.equals(accounts, role.accounts);
        }

        @Override
        public int hashCode() {
                return Objects.hash(super.hashCode(), id, roleName);
        }
}
