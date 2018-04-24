package com.feng.project.sales.quote.service;

import com.feng.project.sales.quote.dao.IQuoteBodyDao;
import com.feng.project.sales.quote.dao.IQuoteDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("quoteBodyService")
public class QuoteBodyServiceImpl implements  IQuoteBodyService{
    @Autowired
    private IQuoteBodyDao quoteBodyDao;

    @Autowired
    private IQuoteDao quoteDao;



    /**
     * delete quoteBody by Id
     *
     * @param quoteBodyId
     * @return
     */
    @Override
    public int deleteQuoteBodyById(Long quoteBodyId,Long quoteId) {

       int count = quoteBodyDao.deleteQuoteBodyById(quoteBodyId);

       if(count >0){
           /**
            * 如果QuoteBody中对应的quoteId 数量为0 ，删除相应的quote（ 将quote 的状态改为1）
            */
           int quoteCount = quoteBodyDao.checkQuoteUsed(quoteId);
            if(quoteCount <=0){
                quoteDao.deleteQuoteById(quoteId);
            }
       }
        return count;
    }
}
