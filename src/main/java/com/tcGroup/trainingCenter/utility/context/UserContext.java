package com.tcGroup.trainingCenter.utility.context;

import java.util.Collection;
import java.util.Set;

import com.tcGroup.trainingCenter.account.Account;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserContext implements UserDetails {

    private static final long serialVersionUID = -8763266680941529428L;

    private Account account;
    private Set<GrantedAuthority> grantedAuthorities;

    public UserContext(Account account, Set<GrantedAuthority> grantedAuthorities) {
        this.account = account;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.account.getAccountPassword();
    }

    @Override
    public String getUsername() {
        return this.account.getAccountEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.account.getAccountStatus() == Account.ACCOUNT_STATUSES.ACTIVE;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.account.getAccountStatus() == Account.ACCOUNT_STATUSES.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.account.getAccountStatus() == Account.ACCOUNT_STATUSES.ACTIVE;
    }

    @Override
    public boolean isEnabled() {
        return this.account.getAccountStatus() == Account.ACCOUNT_STATUSES.ACTIVE;
    }

    public Long getUserId() {
        return this.account.getId();
    }

}