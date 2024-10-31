package com.dao;

import com.entity.JiaolianYuyueEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.JiaolianYuyueView;

/**
 * 教练预约申请 Dao 接口
 *
 * @author 
 */
public interface JiaolianYuyueDao extends BaseMapper<JiaolianYuyueEntity> {

   List<JiaolianYuyueView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}
