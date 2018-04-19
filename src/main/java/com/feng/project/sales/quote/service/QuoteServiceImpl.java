package com.feng.project.sales.quote.service;

import com.feng.project.sales.quote.dao.IQuoteDao;
import com.feng.project.sales.quote.domain.Quote;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("quoteService")
public class QuoteServiceImpl implements  IQuoteService{
    @Autowired
    private IQuoteDao quoteDao;


    @Override
    public List<Quote> selectQuoteList(Quote quote) {
        return quoteDao.selectQuoteList(quote);
    }
}
