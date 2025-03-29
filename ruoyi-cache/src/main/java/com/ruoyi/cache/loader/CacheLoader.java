package com.ruoyi.cache.loader;

import com.ruoyi.cache.bean.DeptData;
import com.ruoyi.cache.bean.DictData;
import com.ruoyi.cache.bean.UserData;
import com.ruoyi.cache.mapper.CacheManageMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: zhaojf
 * @Description TODO
 * @date 2021/6/25
 */
@Component
@MapperScan("com.ruoyi.cache.mapper")
public class CacheLoader {
    private static final Logger logger = LoggerFactory.getLogger(CacheLoader.class);

    //字典
    public static final Map<String, List<DictData>> dictDataMap = new HashMap<>();


    //部门
    public static final List<DeptData> deptDataList = new ArrayList<>();
    public static final Map<Long, DeptData> deptDataMap = new HashMap<>();


    //人员字典（排除企业用户）
    public static final Map<Long, UserData> userDataMapExcludeEnterprise = new HashMap<>();




    @Autowired
    private CacheManageMapper cacheDictMapper;


//    @Scheduled(cron = "0 0/30 * * * ?")
    @PostConstruct

    public void init() {
        logger.info("-CacheLoader init--start--");
        loadAllDictData();
        loadAllDeptData();
        loadAllUserDataMapExcludeEnterprise();
        logger.info("-CacheLoader init--end--");
    }

    public void loadAllUserDataMapExcludeEnterprise(){
        List<UserData> list = cacheDictMapper.selectAllUserDataMapExcludeEnterprise();
        synchronized (userDataMapExcludeEnterprise) {
            userDataMapExcludeEnterprise.clear();
            for (UserData userData : list) {
                userDataMapExcludeEnterprise.put(userData.getUserId(), userData);
            }
        }
    }

    public void loadAllDictData() {
        List<DictData> list = cacheDictMapper.selectAllDictData();
        Map<String, List<DictData>> dictDataMapTmp = list.stream().collect(Collectors.groupingBy(DictData::getDictType));
        synchronized (dictDataMap) {
            dictDataMap.clear();
            dictDataMap.putAll(dictDataMapTmp);
        }
    }

    public void loadAllDeptData() {
        List<DeptData> list = cacheDictMapper.selectAllDeptData();
        Map<Long, DeptData> deptDataMapTmp = list.stream().collect(Collectors.toMap(DeptData::getDeptId, Function.identity()));
        synchronized (deptDataList) {
            deptDataList.clear();
            deptDataList.addAll(list);
            deptDataMap.clear();
            deptDataMap.putAll(deptDataMapTmp);
        }
    }


}
