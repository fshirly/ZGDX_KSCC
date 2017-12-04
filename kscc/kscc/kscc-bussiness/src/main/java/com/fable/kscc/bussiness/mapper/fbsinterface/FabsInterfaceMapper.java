package com.fable.kscc.bussiness.mapper.fbsinterface;

import com.fable.kscc.api.model.fbsInterface.FbsInterface;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/21 0021.
 */
public interface FabsInterfaceMapper {
    /**
     * 全查接口信息
     * @param params
     * @return
     */
    List<FbsInterface> selectFbsInterfaceList(@Param("params") Map<String,Object> params);
}
