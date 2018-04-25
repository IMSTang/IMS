package com.feng.project.sales.quote.service;

import com.feng.common.utils.security.ShiroUtils;
import com.feng.project.product.production.dao.IProductionDao;
import com.feng.project.sales.quote.dao.IQuoteBodyDao;
import com.feng.project.sales.quote.dao.IQuoteDao;
import com.feng.project.sales.quote.domain.Quote;
import com.feng.project.sales.quote.domain.QuoteBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("quoteService")
public class QuoteServiceImpl implements  IQuoteService{
    @Autowired
    private IQuoteDao quoteDao;

    @Autowired
    private IQuoteBodyDao quoteBodyDao;

    @Autowired
    private IProductionDao productionDao;

    @Override
    public List<Quote> selectQuoteList(Quote quote) {
        return quoteDao.selectQuoteList(quote);
    }

    /**
     * 插入 quote
     *
     * @param quote
     * @return
     */
    @Override
    public int insertQuote(Quote quote) {
        int ItemCodeNum=0;
        for (int i=0;i<quote.getBody().size();i++){
            System.out.println();
            ItemCodeNum= productionDao.checkItemCodeUnique(quote.getBody().get(i).getItemCode());
            if( ItemCodeNum<=0){

                return -1;
            }
        }
        String loginName=ShiroUtils.getLoginName();
        quote.setCreateBy(loginName);
        int count = quoteDao.insertQuote(quote);

        /**
         *
         */
        if(count >0) {
            insertQuoteBody(quote);
        }
        return count;
    }

    public  void insertQuoteBody(Quote quote){

        List<QuoteBody> list = new ArrayList<QuoteBody>();

        for (int i=0;i<quote.getBody().size();i++){

            quote.getBody().get(i).setQuoteId(quote.getQuoteId());
            list.add(quote.getBody().get(i));
        }

        if(list.size()>0){
            quoteBodyDao.batchQuoteBody(list);
        }
    }


    /**
     * delete quote by Id
     *
     * @param quoteId
     * @return
     */
    @Override
    public int deleteQuoteById(Long quoteId) {
        return quoteDao.deleteQuoteById(quoteId);
    }
}
