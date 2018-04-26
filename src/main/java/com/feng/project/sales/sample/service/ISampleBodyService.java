package com.feng.project.sales.sample.service;

import com.feng.project.sales.sample.domain.SampleBody;

import java.util.List;

public interface ISampleBodyService {
    /**
     * delete sampleBody by Id
     * @param sampleBodyId
     * @return
     */
    public  int deleteSampleBodyById(Long sampleBodyId,Long sampleId);

    public  int  batchDeleteSample(Long[] sampleBodyId,Long[] sampleId);



    /**
     * get sampleBody by sampleId
     * @param sampleId
     * @return
     */
    public List<SampleBody> selectBodyBySampleId(Long sampleId);
}
