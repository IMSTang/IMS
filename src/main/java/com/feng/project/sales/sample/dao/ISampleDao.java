package com.feng.project.sales.sample.dao;

import com.feng.project.sales.sample.domain.Sample;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ISampleDao {

    public List<Sample> selectSampleList(Sample sample);

    /**
     * 插入 sample
     * @param sample
     * @return
     */
    public int insertSample(Sample sample);


    /**
     * delete sample by Id
     * @param sampleId
     * @return
     */
    public int deleteSampleById(Long sampleId);


    /**
     * get sample by id
     * @param sampleId
     * @return
     */
    public Sample selectSampleById(Long sampleId);


    /**
     * update sample
     * @param sample
     * @return
     */
    public int updateSample(Sample sample);
}
