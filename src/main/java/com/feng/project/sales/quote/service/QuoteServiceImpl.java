package com.feng.project.sales.quote.service;

import com.feng.common.constant.CustomerConstants;
import com.feng.common.utils.StringUtils;
import com.feng.common.utils.security.ShiroUtils;
import com.feng.project.product.production.dao.IProductionDao;
import com.feng.project.sales.quote.dao.IQuoteBodyDao;
import com.feng.project.sales.quote.dao.IQuoteDao;
import com.feng.project.sales.quote.domain.Quote;
import com.feng.project.sales.quote.domain.QuoteBody;
import com.feng.project.system.role.dao.IRoleDao;
import com.feng.project.system.user.dao.IUserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("quoteService")
public class QuoteServiceImpl implements  IQuoteService{
    @Autowired
    private IQuoteDao quoteDao;

    @Autowired
    private IQuoteBodyDao quoteBodyDao;

    @Autowired
    private IProductionDao productionDao;

    @Autowired
    private IUserRoleDao userRoleDao;

    @Autowired
    private IRoleDao roleDao;

    private static  String ROLE_KEY=null;

    @Override
    public List<Quote> selectQuoteList(Quote quote) {

        if(ROLE_KEY == null){
            initRole();
        }
        String loginName=ShiroUtils.getLoginName();
        String createBy = loginName;
        if (ROLE_KEY.equals(CustomerConstants.ADMINISTRATOR) || ROLE_KEY.equals(CustomerConstants.SALESMANAGER) ){
            createBy = "";
        }
        quote.setCreateBy(createBy);
        return quoteDao.selectQuoteList(quote);
    }

    @Override
    public String selectMinMaxPriceByItemCode(String itemCode){
        String createBy=ShiroUtils.getLoginName();
        return quoteDao.selectMinMaxPriceByItemCode(itemCode, createBy);
    }

    /**
     * 插入 quote
     *
     * @param quote
     * @return
     */
    @Override
    public int insertQuote(Quote quote) {

        //判断 Item Code 是否存在
        int ItemCodeNum=0;
        for (int i=0;i<quote.getBody().size();i++){
            ItemCodeNum= productionDao.checkItemCodeUnique(quote.getBody().get(i).getItemCode());
            if( ItemCodeNum<=0){

                return -1;
            }
        }

        Long quoteId = quote.getQuoteId();
        int count=0;
        if (StringUtils.isNotNull(quoteId))
        {
            //update
            quote.setUpdateBy(ShiroUtils.getLoginName());
            count =quoteDao.updateQuote(quote);
            //批量删除不在body里的 quoteBody
            Map map=new HashMap<String, Object>();
            map.put("quoteId",quoteId);
            List<String> list=new ArrayList<String>();

            for (int i=0;i<quote.getBody().size();i++){
                QuoteBody quoteBody = quote.getBody().get(i);
                Long quoteBodyId = quoteBody.getQuoteBodyId();
                if(StringUtils.isNotNull(quoteBodyId)){
                    list.add(""+quoteBodyId);
                }
            }
            int size=list.size();
            String[] array = (String[])list.toArray(new String[size]);
            map.put("ids", array);
            quoteBodyDao.batchDeleteQuoteBodyOnType(map);

            //更新quoteItem
            for (int i=0;i<quote.getBody().size();i++){
                QuoteBody quoteBody = quote.getBody().get(i);
                Long quoteBodyId = quoteBody.getQuoteBodyId();
                if(StringUtils.isNotNull(quoteBodyId)){
                    count = quoteBodyDao.updateQuoteBody(quoteBody);
                }
                else {
                    List<QuoteBody> list1 = new ArrayList<QuoteBody>();
                    quoteBody.setQuoteId(quoteId);
                    list1.add(quoteBody);
                    count = quoteBodyDao.batchQuoteBody(list1);
                }
            }
        }else {
            //insert
            String loginName=ShiroUtils.getLoginName();
            quote.setCreateBy(loginName);
             count = quoteDao.insertQuote(quote);

            /**
             *
             */
            if(count >0) {
                insertQuoteBody(quote);
            }
        }
        return count;
    }

    public  void insertQuoteBody(Quote quote){

        List<QuoteBody> list = new ArrayList<QuoteBody>();

        for (int i=0;i<quote.getBody().size();i++){

            quote.getBody().get(i).setQuoteId(quote.getQuoteId());
            list.add(quote.getBody().get(i));
        }

        if(list.size()>0){
            quoteBodyDao.batchQuoteBody(list);
        }
    }


    /**
     * delete quote by Id
     *
     * @param quoteId
     * @return
     */
    @Override
    public int deleteQuoteById(Long quoteId) {
        return quoteDao.deleteQuoteById(quoteId);
    }


    /**
     * get quote by id
     *
     * @param quoteId
     * @return
     */
    @Override
    public Quote selectQuoteById(Long quoteId) {
        return quoteDao.selectQuoteById(quoteId);
    }



    @Override
    public  String initRole(){
        //get current login name
//        String loginName=ShiroUtils.getLoginName();

        //get role id by user Id
        String userId = ShiroUtils.getUserId().toString();
        int roleId = userRoleDao.getRoleId(userId);
        Long lRoleId = new Long((long)roleId);
        ROLE_KEY  = (roleDao.selectRoleById(lRoleId)).getRoleKey();
        return ROLE_KEY;

    }
}
