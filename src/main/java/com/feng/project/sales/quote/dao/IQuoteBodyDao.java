package com.feng.project.sales.quote.dao;

import com.feng.project.sales.quote.domain.QuoteBody;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface IQuoteBodyDao {
   public int  batchQuoteBody(List<QuoteBody> quoteBodieList);

   /**
    * delete quoteBody by Id
    * @param quoteBodyId
    * @return
    */
   public  int deleteQuoteBodyById(Long quoteBodyId);


   /**
    *
    * @param quoteId
    * @return
    */
   public int checkQuoteUsed(Long quoteId);

   public int batchDeleteQuoteBody(Long[] quoteBodyIds);


   /**
    * get quoteBody by quoteId
    * @param quoteId
    * @return
    */
   public List<QuoteBody> selectBodyByQuoteId(Long quoteId);



   public  int batchDeleteQuoteBodyOnType(Map<String, Object> param);

   public int updateQuoteBody(QuoteBody quoteBody);
}
