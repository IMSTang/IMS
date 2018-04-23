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
import com.feng.project.system.dict.domain.DictData;
import com.feng.project.system.dict.service.IDictDataService;

/**
 * 数据字典信息
 * 
 * @author feng
 */
@Controller
@RequestMapping("/system/dictData")
public class DictDataController extends BaseController
{
    private String prefix = "system/dictData";

    @Autowired
    private IDictDataService dictDataService;

    @RequiresPermissions("system:dictdata:view")
    @GetMapping()
    public String dict()
    {
        System.out.println("-----------------dictdata");
        return prefix + "/dictData";
    }

    @GetMapping("/list")
    @RequiresPermissions("system:dictdata:list")
    @ResponseBody
    public TableDataInfo list(DictData dictData)
    {
        setPageInfo(dictData);
        List<DictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    /**
     * 修改字典类型
     */
    @Log(title = "System Management", action = "Dict Data - edit Dict Data")
    @RequiresPermissions("system:dictdata:edit")
    @GetMapping("/edit/{dictCode}")
    public String edit(@PathVariable("dictCode") Long dictCode, Model model)
    {
        DictData dict = dictDataService.selectDictDataById(dictCode);
        model.addAttribute("dict", dict);
        return prefix + "/editDictData";
    }

    /**
     * 新增字典类型
     */
    @Log(title = "System Management", action = "Dict Data - add Dict Data")
    @RequiresPermissions("system:dictdata:add")
    @GetMapping("/add/{dictType}")
    public String add(@PathVariable("dictType") String dictType, Model model)
    {
        model.addAttribute("dictType", dictType);
        return prefix + "/addDictData";
    }

    /**
     * 保存字典类型
     */
    @Log(title = "System Management", action = "Dict Data - save Dict Data")
    @RequiresPermissions("system:dictdata:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(DictData dict)
    {
        if (dictDataService.saveDictData(dict) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @Log(title = "System Management", action = "Dict Data - batch remove")
    @RequiresPermissions("system:dictdata:batchRemove")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = dictDataService.batchDeleteDictData(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
}
