package com.ruoyi.cache.utils;

import com.ruoyi.cache.bean.DeptData;
import com.ruoyi.cache.loader.CacheLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class CacheDeptUtils {
    private static final Logger logger = LoggerFactory.getLogger(CacheDeptUtils.class);

    public static String getDeptName(Long deptId){
        if(deptId == null){
            return null;
        }
        DeptData deptData = CacheLoader.deptDataMap.get(deptId);
        return deptData == null ? null : deptData.getDeptName();
    }

    public static DeptData getDept(Long deptId){
        if(deptId == null){
            return null;
        }
        return CacheLoader.deptDataMap.get(deptId);
    }


    public static String getDeptNamesUp(Long deptId){
        List<DeptData> resList = new ArrayList<>();
        List<DeptData> allList = CacheLoader.deptDataList;
        recursionDeptUp(resList,allList,deptId);
        String res = null;
        for(DeptData data : resList){
            if(res == null){
                res = data.getDeptName();
            }else{
                res = data.getDeptName() + "/" + res;
            }
        }
        return res;
    }

    /**
     *  向上递归
     * @param resList   存放结果
     * @param allList   所有的部门
     * @param deptId    部门ID
     */
    public static void recursionDeptUp(List<DeptData> resList,List<DeptData> allList,Long deptId){
        DeptData data = getDeptDataById(allList,deptId);
        if(data == null){
            return;
        }
        resList.add(data);
        Long parentId = data.getParentId();
        if(parentId == null){
            return;
        }
        recursionDeptUp(resList,allList,parentId);
    }


    private static DeptData getDeptDataById(List<DeptData> list,Long deptId){
        for (DeptData deptData : list) {
            if(deptId.longValue() == deptData.getDeptId().longValue()){
                return deptData;
            }
        }
        return null;
    }

}
