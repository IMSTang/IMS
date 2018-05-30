package com.feng.project.sales.sample.service;

import com.feng.common.constant.CustomerConstants;
import com.feng.common.utils.StringUtils;
import com.feng.common.utils.security.ShiroUtils;
import com.feng.project.product.production.dao.IProductionDao;
import com.feng.project.sales.sample.dao.ISampleBodyDao;
import com.feng.project.sales.sample.dao.ISampleDao;
import com.feng.project.sales.sample.domain.Sample;
import com.feng.project.sales.sample.domain.SampleBody;
import com.feng.project.system.role.dao.IRoleDao;
import com.feng.project.system.user.dao.IUserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sampleService")
public class SampleServiceImpl implements  ISampleService{
    @Autowired
    private ISampleDao sampleDao;

    @Autowired
    private ISampleBodyDao sampleBodyDao;

    @Autowired
    private IProductionDao productionDao;

    @Autowired
    private IUserRoleDao userRoleDao;

    @Autowired
    private IRoleDao roleDao;

//    private static  String ROLE_KEY=null;
    @Override
    public List<Sample> selectSampleList(Sample sample) {
        if(ShiroUtils.ROLE_KEY == null){
            initRole();
        }
        String loginName=ShiroUtils.getLoginName();
        if (ShiroUtils.ROLE_KEY.equals(CustomerConstants.ADMINISTRATOR) || ShiroUtils.ROLE_KEY.equals(CustomerConstants.SALESMANAGER) ) {
            sample.setCreateBy("");
            return sampleDao.selectSampleList(sample);
        }
        sample.setCreateBy(loginName);
        return sampleDao.selectSampleList(sample);
    }

    /**
     * 插入 sample
     *
     * @param sample
     * @return
     */
    @Override
    public int insertSample(Sample sample) {

        //判断 Item Code 是否存在
        int ItemCodeNum=0;
        for (int i=0;i<sample.getBody().size();i++){
            ItemCodeNum= productionDao.checkItemCodeUnique(sample.getBody().get(i).getItemCode());
            if( ItemCodeNum<=0){

                return -1;
            }
        }

        Long sampleId = sample.getSampleId();
        int count=0;
        if (StringUtils.isNotNull(sampleId))
        {
            //update
            sample.setUpdateBy(ShiroUtils.getLoginName());
            count =sampleDao.updateSample(sample);
            //批量删除不在body里的 sampleBody
            Map map=new HashMap<String, Object>();
            map.put("sampleId",sampleId);
            List<String> list=new ArrayList<String>();

            for (int i=0;i<sample.getBody().size();i++){
                SampleBody sampleBody = sample.getBody().get(i);
                Long sampleBodyId = sampleBody.getSampleBodyId();
                if(StringUtils.isNotNull(sampleBodyId)){
                    list.add(""+sampleBodyId);
                }
            }
            int size=list.size();
            String[] array = (String[])list.toArray(new String[size]);
            map.put("ids", array);
            sampleBodyDao.batchDeleteSampleBodyOnType(map);

            //更新sampleItem
            for (int i=0;i<sample.getBody().size();i++){
                SampleBody sampleBody = sample.getBody().get(i);
                Long sampleBodyId = sampleBody.getSampleBodyId();
                if(StringUtils.isNotNull(sampleBodyId)){
                    count = sampleBodyDao.updateSampleBody(sampleBody);
                }
                else {
                    List<SampleBody> list1 = new ArrayList<SampleBody>();
                    sampleBody.setSampleId(sampleId);
                    list1.add(sampleBody);
                    count = sampleBodyDao.batchSampleBody(list1);
                }


            }
        }else {
            //insert
            String loginName=ShiroUtils.getLoginName();
            sample.setCreateBy(loginName);
             count = sampleDao.insertSample(sample);

            /**
             *
             */
            if(count >0) {
                insertSampleBody(sample);
            }
        }
        return count;
    }

    public  void insertSampleBody(Sample sample){

        List<SampleBody> list = new ArrayList<SampleBody>();

        for (int i=0;i<sample.getBody().size();i++){

            sample.getBody().get(i).setSampleId(sample.getSampleId());
            list.add(sample.getBody().get(i));
        }

        if(list.size()>0){
            sampleBodyDao.batchSampleBody(list);
        }
    }


    /**
     * delete sample by Id
     *
     * @param sampleId
     * @return
     */
    @Override
    public int deleteSampleById(Long sampleId) {
        return sampleDao.deleteSampleById(sampleId);
    }


    /**
     * get sample by id
     *
     * @param sampleId
     * @return
     */
    @Override
    public Sample selectSampleById(Long sampleId) {
        return sampleDao.selectSampleById(sampleId);
    }



    @Override
    public  String initRole(){

        String userId = ShiroUtils.getUserId().toString();
        int roleId = userRoleDao.getRoleId(userId);

        Long lRoleId = new Long((long)roleId);
        ShiroUtils.ROLE_KEY  = (roleDao.selectRoleById(lRoleId)).getRoleKey();
        System.out.println("ROLE_KEY:"+ShiroUtils.ROLE_KEY);
        return ShiroUtils.ROLE_KEY;

    }

}
