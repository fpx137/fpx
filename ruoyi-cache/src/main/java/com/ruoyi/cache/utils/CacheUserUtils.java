package com.ruoyi.cache.utils;

import com.ruoyi.cache.bean.DictData;
import com.ruoyi.cache.bean.UserData;
import com.ruoyi.cache.loader.CacheLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CacheUserUtils {
    private static final Logger logger = LoggerFactory.getLogger(CacheUserUtils.class);

    /**
     *
     * @param userIds  多个用户ID 用逗号分隔
     * @return
     */
    public static String idToNickNames(String userIds){
        if(StringUtils.isBlank(userIds)){
            return null;
        }

        String res = null;
        String[] ids = userIds.split(",");
        for (String id : ids) {
            if(StringUtils.isBlank(id)){
                continue;
            }
            UserData userData = CacheLoader.userDataMapExcludeEnterprise.get(id);
            if(userData != null){
                if(StringUtils.isBlank(res)){
                    res = userData.getNickName();
                }else{
                    res = res + "," + userData.getNickName();
                }
            }
        }
        return res;
    }
}
