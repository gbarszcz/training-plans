package com.tcGroup.trainingCenter.account;

import com.tcGroup.trainingCenter.registration.RegistrationRequest;
import com.tcGroup.trainingCenter.role.Role;
import com.tcGroup.trainingCenter.utility.context.UserContext;
import com.tcGroup.trainingCenter.role.RoleRepository;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service(value = "accountService")
public class AccountServiceImpl extends AbstractService implements AccountService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserContext loadUserByUsername(String accountEmail) throws UsernameNotFoundException {
        Account account = accountDAO.findByAccountEmail(accountEmail);
        if (account == null) {
            throw new UsernameNotFoundException("Account with email address " + accountEmail + " not found.");
        }

        Set<Role> roles = account.getRoles();
        Set<GrantedAuthority> grantedAuthorities = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());

        return new UserContext(account, grantedAuthorities);
    }

    @Override
    public Long register(RegistrationRequest registrationRequest) {
        Account account = new Account();

        account.setAccountEmail(registrationRequest.getEmail());
        account.setAccountPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        account.setAccountStatus(Account.ACCOUNT_STATUSES.ACTIVE);
        account.addRole(roleRepository.findByRoleName(Role.Roles.USER));

        return accountDAO.createItem(getUserContext(), account);
    }

    @Override
    public Account getAccountByEmail(String email) {
        return accountDAO.findByAccountEmail(email);
    }
}
