package com.feng.project.sales.sample.dao;

import com.feng.project.sales.sample.domain.SampleBody;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ISampleBodyDao {
   public int  batchSampleBody(List<SampleBody> sampleBodieList);

   /**
    * delete sampleBody by Id
    * @param sampleBodyId
    * @return
    */
   public  int deleteSampleBodyById(Long sampleBodyId);


   /**
    *
    * @param sampleId
    * @return
    */
   public int checkSampleUsed(Long sampleId);

   public int batchDeleteSampleBody(Long[] sampleBodyIds);


   /**
    * get sampleBody by sampleId
    * @param sampleId
    * @return
    */
   public List<SampleBody> selectBodyBySampleId(Long sampleId);



   public  int batchDeleteSampleBodyOnType(Map<String, Object> param);

   public int updateSampleBody(SampleBody sampleBody);
}
