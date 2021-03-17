package com.tcGroup.trainingCenter.utility.logic;

import java.util.Date;
import java.util.List;

import com.tcGroup.trainingCenter.utility.context.UserContext;
import com.tcGroup.trainingCenter.utility.entity.AuditData;

public abstract class AbstractDAO {

    private AbstractRepository<AuditData, Long> repository;

    protected void validate() {

    }

    public List<AuditData> getItems() {
        return this.repository.findAll();
    }

    public AuditData getItem(Long id) {
        return this.repository.getOne(id);
    }

    public Long createItem(UserContext ctx, AuditData itemData) {
        validate();
        itemData.setAuditCD(new Date());
        itemData.setAuditCU(ctx.getUserId());

        itemData = this.repository.save(itemData);

        return itemData.getId();
    }

    public Long modifyItem(UserContext ctx, AuditData itemData) {
        validate();
        itemData.setAuditMD(new Date());
        itemData.setAuditMU(ctx.getUserId());

        itemData = this.repository.save(itemData);

        return itemData.getId();
    }

    public void removeItem(UserContext ctx, AuditData itemData) {
        itemData.setAuditRD(new Date());
        itemData.setAuditRU(ctx.getUserId());

        this.repository.save(itemData);
    }

    protected void setRepository(AbstractRepository<AuditData, Long> repository) {
        this.repository = repository;
    }

    protected AbstractRepository<AuditData, Long> getRepository() {
        return this.repository;
    }

}