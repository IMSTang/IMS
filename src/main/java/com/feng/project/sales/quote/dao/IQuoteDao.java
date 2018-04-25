package com.feng.project.sales.quote.dao;

import com.feng.project.sales.quote.domain.Quote;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IQuoteDao {

    public List<Quote> selectQuoteList(Quote quote);

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


    /**
     * update quote
     * @param quote
     * @return
     */
    public int updateQuote(Quote quote);
}
