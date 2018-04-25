package com.feng.project.system.dict.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feng.framework.aspectj.lang.annotation.Log;
import com.feng.framework.web.controller.BaseController;
import com.feng.framework.web.domain.JSON;
import com.feng.framework.web.page.TableDataInfo;
import com.feng.project.system.dict.domain.DictType;
import com.feng.project.system.dict.service.IDictTypeService;
import com.feng.project.system.dict.domain.DictData;
import com.feng.project.system.dict.service.IDictDataService;

/**
 * 数据字典信息
 * 
 * @author feng
 */
@Controller
@RequestMapping("/system/dict")
public class DictTypeController extends BaseController
{
    private String prefix = "system/dict";

    @Autowired
    private IDictTypeService dictTypeService;
    @Autowired
    private IDictDataService dictDataService;

    @RequiresPermissions("system:dict:view")
    @GetMapping()
    public String dict()
    {
        return prefix + "/dictType";
    }

    @GetMapping("/list")
    @RequiresPermissions("system:dict:list")
    @ResponseBody
    public TableDataInfo list(DictType dictType)
    {
        setPageInfo(dictType);
        List<DictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    /**
     * 修改字典类型
     */
    @Log(title = "System Management", action = "Dict Type - edit")
    @RequiresPermissions("system:dict:edit")
    @GetMapping("/edit/{dictId}")
    public String edit(@PathVariable("dictId") Long dictId, Model model)
    {
        DictType dict = dictTypeService.selectDictTypeById(dictId);
        List<DictData> dictdatas= dictDataService.selectDictDataByTypeId(dictId);
        model.addAttribute("dictdatas", dictdatas);
        model.addAttribute("dict", dict);
        return prefix + "/editDictType";
    }

    /**
     * 新增字典类型
     */
    @Log(title = "System Management", action = "Dict Type - add")
    @RequiresPermissions("system:dict:add")
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/addDictType";
    }

    /**
     * 保存字典类型
     */
    @Log(title = "System Management", action = "Dict Type - save")
    @RequiresPermissions("system:dict:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(DictType dict)
    {
        if (dictTypeService.saveDictType(dict) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 删除
     */
    @Log(title = "System Management", action = "Dict Type - delete")
    @RequiresPermissions("system:dict:remove")
    @RequestMapping("/remove/{dictId}")
    @ResponseBody
    public JSON remove(@PathVariable("dictId") Long dictId)
    {
        DictType dict = dictTypeService.selectDictTypeById(dictId);
        if (dict == null)
        {
            return JSON.error("DictType does not exist");
        }
        if (dictTypeService.deleteDictTypeById(dictId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @Log(title = "System Management", action = "Dict Type - batch remove")
    @RequiresPermissions("system:dict:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = dictTypeService.batchDeleteDictType(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 查询字典详细
     */
    @Log(title = "System Management", action = "Dict Type - get detail")
    @RequiresPermissions("system:dict:list")
    @GetMapping("/detail/{dictId}")
    public String detail(@PathVariable("dictId") Long dictId, Model model)
    {
        DictType dict = dictTypeService.selectDictTypeById(dictId);
        model.addAttribute("dict", dict);
        return prefix + "/dictData";
    }
    
    /**
     * 校验字典类型
     */
    @PostMapping("/checkDictTypeUnique")
    @ResponseBody
    public String checkDictTypeUnique(DictType dictType)
    {
        String uniqueFlag = "0";
        if (dictType != null)
        {
            uniqueFlag = dictTypeService.checkDictTypeUnique(dictType);
        }
        return uniqueFlag;
    }
}
