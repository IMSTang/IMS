package com.feng.project.sales.sample.service;

import com.feng.project.sales.sample.dao.ISampleBodyDao;
import com.feng.project.sales.sample.dao.ISampleDao;
import com.feng.project.sales.sample.domain.SampleBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sampleBodyService")
public class SampleBodyServiceImpl implements  ISampleBodyService{
    @Autowired
    private ISampleBodyDao sampleBodyDao;

    @Autowired
    private ISampleDao sampleDao;



    /**
     * delete sampleBody by Id
     *
     * @param sampleBodyId
     * @return
     */
    @Override
    public int deleteSampleBodyById(Long sampleBodyId,Long sampleId) {

       int count = sampleBodyDao.deleteSampleBodyById(sampleBodyId);

       if(count >0){
           /**
            * 如果SampleBody中对应的sampleId 数量为0 ，删除相应的sample（ 将sample 的状态改为1）
            */
           int sampleCount = sampleBodyDao.checkSampleUsed(sampleId);
            if(sampleCount <=0){
                sampleDao.deleteSampleById(sampleId);
            }
       }
        return count;
    }

    @Override
    public int batchDeleteSample(Long[] sampleBodyId, Long[] sampleId) {
        int result= sampleBodyDao.batchDeleteSampleBody(sampleBodyId);

//        if(result == (sampleBodyId.length-1)){
            for (int i=0;i< sampleId.length;i++){
                int sampleCount = sampleBodyDao.checkSampleUsed(sampleId[i]);
                if(sampleCount <=0){
                    sampleDao.deleteSampleById(sampleId[i]);
                }
            }
//        }
        return result;
    }

    /**
     * get sampleBody by sampleId
     *
     * @param sampleId
     * @return
     */
    @Override
    public List<SampleBody> selectBodyBySampleId(Long sampleId) {
        return sampleBodyDao.selectBodyBySampleId(sampleId);
    }
}
