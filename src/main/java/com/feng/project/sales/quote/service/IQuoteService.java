package com.feng.project.sales.quote.service;

import com.feng.project.sales.quote.domain.Quote;

import java.util.List;

public interface IQuoteService {

    public List<Quote>  selectQuoteList(Quote quote);

    public String selectMinMaxPriceByItemCode(String itemCode);
    /**
            * 插入 quote
     * @param quote
     * @return
             */
    public int insertQuote(Quote quote);



    /**
     * delete quote by Id
     * @param quoteId
     * @return
     */
    public int deleteQuoteById(Long quoteId);




    /**
     * get quote by id
     * @param quoteId
     * @return
     */
    public Quote selectQuoteById(Long quoteId);

//         /** update quote
//             * @param quote
//             * @return
//             */
//    public int updateQuote(Quote quote);

    public String initRole();
}
