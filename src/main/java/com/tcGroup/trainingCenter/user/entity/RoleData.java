package com.tcGroup.trainingCenter.user.entity;

import com.tcGroup.trainingCenter.user.enumeration.RoleName;
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
public class RoleData extends AuditData {

        @Id
        @Column(name = "ROL_ID")
        @GeneratedValue
        protected long id;

        @Column(name = "ROL_NAME")
        @Enumerated(EnumType.STRING)
        private RoleName roleName;

        @ManyToMany(cascade = CascadeType.PERSIST, mappedBy = "roles")
        Set<AccountData> accounts;

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
                RoleData role = (RoleData) o;
                return id == role.id && roleName.equals(role.roleName) && Objects.equals(accounts, role.accounts);
        }

        @Override
        public int hashCode() {
                return Objects.hash(super.hashCode(), id, roleName);
        }
}
