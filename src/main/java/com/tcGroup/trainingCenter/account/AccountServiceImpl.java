package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.role.Role;
import com.tcGroup.trainingCenter.utility.context.UserContext;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

public class AccountServiceImpl extends AbstractService implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserContext loadUserByUsername(String accountEmail) throws UsernameNotFoundException {
        Account account = accountRepository.findByAccountEmail(accountEmail);
        if (account == null) {
            throw new UsernameNotFoundException("Account with email address " + accountEmail + " not found.");
        }

        Set<Role> roles = account.getRoles();
        Set<GrantedAuthority> grantedAuthorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());

        return new UserContext(account, grantedAuthorities);
    }
}
