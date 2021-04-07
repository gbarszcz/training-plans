package com.tcGroup.trainingCenter.utility.logic;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tcGroup.trainingCenter.utility.context.UserContext;
import com.tcGroup.trainingCenter.utility.entity.AuditData;

public abstract class AbstractDAO<T extends AuditData, ID extends Serializable> {

    private AbstractRepository<T, ID> repository;

    protected void validate() {

    }

    public List<T> getItems() {
        return this.repository.findAll();
    }

    public T getItem(ID id) {
        return this.repository.findById(id).orElse(null);
    }

    public Long createItem(UserContext ctx, T itemData) {
        validate();
        itemData.setAuditCD(new Date());
        itemData.setAuditCU(ctx.getUserId());

        itemData = this.repository.save(itemData);

        return itemData.getId();
    }

    public Long modifyItem(UserContext ctx, T itemData) {
        validate();
        itemData.setAuditMD(new Date());
        itemData.setAuditMU(ctx.getUserId());

        itemData = this.repository.save(itemData);

        return itemData.getId();
    }

    public void removeItem(UserContext ctx, T itemData) {
        itemData.setAuditRD(new Date());
        itemData.setAuditRU(ctx.getUserId());

        this.repository.save(itemData);
    }

    protected void setRepository(AbstractRepository<T, ID> repository) {
        this.repository = repository;
    }

    protected AbstractRepository<T, ID> getRepository() {
        return this.repository;
    }

}