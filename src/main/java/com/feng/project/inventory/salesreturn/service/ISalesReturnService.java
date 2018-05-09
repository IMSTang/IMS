package com.feng.project.inventory.salesreturn.service;
import java.util.List;

import com.feng.project.inventory.salesreturn.domain.SalesReturn;

public interface ISalesReturnService {


    public List<SalesReturn> selectSalesReturnList(SalesReturn salesreturn);

    public SalesReturn selectSalesReturnById(Long sn);

    public void spSalesReturn(SalesReturn salesreturn);

    public void spSalesReturnRemove(int sn);

}
