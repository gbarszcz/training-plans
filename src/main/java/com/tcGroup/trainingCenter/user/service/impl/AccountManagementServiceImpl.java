package com.tcGroup.trainingCenter.user.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.tcGroup.trainingCenter.user.dao.AccountDAO;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.user.entity.RoleData;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;
import com.tcGroup.trainingCenter.utility.context.UserContext;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "accountManagementService")
public class AccountManagementServiceImpl extends AbstractService implements AccountManagementService {

    @Autowired
    private AccountDAO accountDAO;

    // -------------------------- ACCOUNTS -------------------------------------

    @Override
    @Transactional
    public AccountData getAccountByEmail(String email) {
        return accountDAO.findAccountByEmail(email);
    }

    @Override
    @Transactional(readOnly = true)
    public UserContext loadUserByUsername(String accountEmail) throws UsernameNotFoundException {
        AccountData account = accountDAO.findAccountByEmail(accountEmail);
        if (account == null) {
            throw new UsernameNotFoundException("Account with email address " + accountEmail + " not found.");
        }

        Set<RoleData> roles = account.getRoles();
        Set<GrantedAuthority> grantedAuthorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().toString())).collect(Collectors.toSet());

        return new UserContext(account, grantedAuthorities);
    }
}
