package com.tcGroup.trainingCenter.user.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.tcGroup.trainingCenter.user.dao.AccountDAO;
import com.tcGroup.trainingCenter.user.dao.RoleDAO;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.user.entity.RoleData;
import com.tcGroup.trainingCenter.user.registration.RegistrationRequest;
import com.tcGroup.trainingCenter.user.service.AccountManagementService;
import com.tcGroup.trainingCenter.utility.context.UserContext;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "accountManagementService")
public class AccountManagementServiceImpl extends AbstractService implements AccountManagementService {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    // -------------------------- ACCOUNTS -------------------------------------

    @Override
    @Transactional
    public Long register(RegistrationRequest registrationRequest) {
        AccountData account = new AccountData();

        account.setAccountEmail(registrationRequest.getEmail());
        account.setAccountPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        account.setAccountStatus(AccountData.ACCOUNT_STATUSES.ACTIVE);
        account.addRole(roleDAO.findRoleByName(RoleData.Roles.USER));

        return accountDAO.createItem(getUserContext(), account);
    }

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
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());

        return new UserContext(account, grantedAuthorities);
    }
}
