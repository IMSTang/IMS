package com.feng.project.sales.quote.service;

public interface IQuoteBodyService {
    /**
     * delete quoteBody by Id
     * @param quoteBodyId
     * @return
     */
    public  int deleteQuoteBodyById(Long quoteBodyId,Long quoteId);
}
