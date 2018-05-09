package com.feng.project.inventory.salesreturn.dao;

import com.feng.project.inventory.salesreturn.domain.SalesReturn;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface ISalesReturnDao {


    public List<SalesReturn> selectSalesReturnList(SalesReturn salesReturn);


    public int spSalesReturn(Map<String, Object> paramsMap);

    public int spSalesReturnRemove(Map<String, Object> paramsMap);


    /**
     * 查询出库单详细
     *
     * @param sn
     * @return 出库单对象
     */
    public SalesReturn selectSalesReturnById(Long sn);



}
