package com.tcGroup.trainingCenter.user.service.impl;

import com.tcGroup.trainingCenter.user.dao.AccountDAO;
import com.tcGroup.trainingCenter.user.dao.RoleDAO;
import com.tcGroup.trainingCenter.user.entity.AccountData;
import com.tcGroup.trainingCenter.user.enumeration.AccountStatus;
import com.tcGroup.trainingCenter.user.enumeration.RoleName;
import com.tcGroup.trainingCenter.user.request.RegistrationRequest;
import com.tcGroup.trainingCenter.user.service.RegistrationService;
import com.tcGroup.trainingCenter.utility.logic.AbstractService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "registrationService")
public class RegistrationServiceImpl extends AbstractService implements RegistrationService {

    @Autowired
    private RoleDAO roleDAO;

    @Autowired
    private AccountDAO accountDAO;

    @Autowired
    @Qualifier("bCryptPasswordEncoder")
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    @Transactional
    public Long register(RegistrationRequest registrationRequest) {
        AccountData account = new AccountData();

        account.setAccountEmail(registrationRequest.getEmail());
        account.setAccountPassword(bCryptPasswordEncoder.encode(registrationRequest.getPassword()));
        account.setAccountStatus(AccountStatus.ACTIVE);
        account.addRole(roleDAO.findRoleByName(RoleName.USER));

        return accountDAO.createItem(getUserContext(), account);
    }
}