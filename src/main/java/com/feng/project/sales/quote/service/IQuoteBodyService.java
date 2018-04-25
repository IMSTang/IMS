package com.feng.project.sales.quote.service;

import com.feng.project.sales.quote.domain.QuoteBody;

import java.util.List;

public interface IQuoteBodyService {
    /**
     * delete quoteBody by Id
     * @param quoteBodyId
     * @return
     */
    public  int deleteQuoteBodyById(Long quoteBodyId,Long quoteId);

    public  int  batchDeleteQuote(Long[] quoteBodyId,Long[] quoteId);



    /**
     * get quoteBody by quoteId
     * @param quoteId
     * @return
     */
    public List<QuoteBody> selectBodyByQuoteId(Long quoteId);
}
