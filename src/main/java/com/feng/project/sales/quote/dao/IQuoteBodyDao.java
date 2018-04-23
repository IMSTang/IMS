package com.feng.project.sales.quote.dao;

import com.feng.project.sales.quote.domain.QuoteBody;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IQuoteBodyDao {
   public int  batchQuoteBody(List<QuoteBody> quoteBodieList);
}
