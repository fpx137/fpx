package com.ruoyi.cache.utils;

import com.ruoyi.cache.bean.DictData;
import com.ruoyi.cache.loader.CacheLoader;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CacheDictUtils {
    private static final Logger logger = LoggerFactory.getLogger(CacheDictUtils.class);

    public static String toDictLabel(String dictType,Long val){
        if(val == null){
            return null;
        }
        return toDictLabel(dictType,val.toString());
    }
    /**
     * 逗号隔开的val转成中文
     * @param vals
     * @return
     */
    public static String toDictLabel(String dictType,String vals){
        if(StringUtils.isBlank(vals)){
            return null;
        }

        List<DictData> dictData = CacheLoader.dictDataMap.get(dictType);
        if(dictData == null){
            logger.error("字典不存在，dictType="+dictType);
            return null;
        }

        String[] valsStr = vals.split(",");
        String labelRes = null;
        for (String val : valsStr) {
            String label = getDictLabel(dictData,val);
            if(StringUtils.isNotBlank(label)){
                if(labelRes == null){
                    labelRes = label;
                }else{
                    labelRes += "," + label;
                }
            }
        }
        return labelRes;
    }


    private static String getDictLabel(List<DictData> dictData,String value){
        if(dictData == null || dictData.size() == 0){
            return null;
        }
        for (DictData dictDatum : dictData) {
            if(dictDatum.getDictValue().equals(value)){
                return dictDatum.getDictLabel();
            }
        }
        return null;
    }
}
