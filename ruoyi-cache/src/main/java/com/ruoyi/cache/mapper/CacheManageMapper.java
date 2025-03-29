package com.ruoyi.cache.mapper;

import com.ruoyi.cache.bean.DeptData;
import com.ruoyi.cache.bean.DictData;
import com.ruoyi.cache.bean.UserData;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * 字典表 数据层
 *
 * @author ruoyi
 */

public interface CacheManageMapper
{
    public List<DictData> selectAllDictData();
    public List<DeptData> selectAllDeptData();
    public List<UserData> selectAllUserDataMapExcludeEnterprise();


}
