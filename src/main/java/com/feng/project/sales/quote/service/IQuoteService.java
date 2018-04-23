package com.feng.project.sales.quote.service;

import com.feng.project.sales.quote.domain.Quote;

import java.util.List;

public interface IQuoteService {

    public List<Quote>  selectQuoteList(Quote quote);

    /**
            * 插入 quote
     * @param quote
     * @return
             */
    public int insertQuote(Quote quote);
}
