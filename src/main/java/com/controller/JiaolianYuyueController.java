
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 教练预约申请
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/jiaolianYuyue")
public class JiaolianYuyueController {
    private static final Logger logger = LoggerFactory.getLogger(JiaolianYuyueController.class);

    private static final String TABLE_NAME = "jiaolianYuyue";

    @Autowired
    private JiaolianYuyueService jiaolianYuyueService;


    @Autowired
    private TokenService tokenService;

    @Autowired
    private DictionaryService dictionaryService;//字典
    @Autowired
    private ForumService forumService;//健身论坛
    @Autowired
    private JianshenkechengService jianshenkechengService;//健身课程
    @Autowired
    private JianshenkechengCollectionService jianshenkechengCollectionService;//课程收藏
    @Autowired
    private JianshenkechengLiuyanService jianshenkechengLiuyanService;//课程留言
    @Autowired
    private JiaolianService jiaolianService;//教练
    @Autowired
    private NewsService newsService;//健身资讯
    @Autowired
    private SingleSeachService singleSeachService;//单页数据
    @Autowired
    private YonghuService yonghuService;//用户
    @Autowired
    private UsersService usersService;//管理员


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永不会进入");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        else if("教练".equals(role))
            params.put("jiaolianId",request.getSession().getAttribute("userId"));
        CommonUtil.checkMap(params);
        PageUtils page = jiaolianYuyueService.queryPage(params);

        //字典表数据转换
        List<JiaolianYuyueView> list =(List<JiaolianYuyueView>)page.getList();
        for(JiaolianYuyueView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiaolianYuyueEntity jiaolianYuyue = jiaolianYuyueService.selectById(id);
        if(jiaolianYuyue !=null){
            //entity转view
            JiaolianYuyueView view = new JiaolianYuyueView();
            BeanUtils.copyProperties( jiaolianYuyue , view );//把实体数据重构到view中
            //级联表 用户
            //级联表
            YonghuEntity yonghu = yonghuService.selectById(jiaolianYuyue.getYonghuId());
            if(yonghu != null){
            BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段,当前表的级联注册表
            view.setYonghuId(yonghu.getId());
            }
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody JiaolianYuyueEntity jiaolianYuyue, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,jiaolianYuyue:{}",this.getClass().getName(),jiaolianYuyue.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(false)
            return R.error(511,"永远不会进入");
        else if("用户".equals(role))
            jiaolianYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        Wrapper<JiaolianYuyueEntity> queryWrapper = new EntityWrapper<JiaolianYuyueEntity>()
            .eq("yonghu_id", jiaolianYuyue.getYonghuId())
            .in("jiaolian_yuyue_yesno_types", new Integer[]{1,2})
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaolianYuyueEntity jiaolianYuyueEntity = jiaolianYuyueService.selectOne(queryWrapper);
        if(jiaolianYuyueEntity==null){
            jiaolianYuyue.setJiaolianYuyueYesnoTypes(1);
            jiaolianYuyue.setInsertTime(new Date());
            jiaolianYuyue.setCreateTime(new Date());
            jiaolianYuyueService.insert(jiaolianYuyue);
            return R.ok();
        }else {
            if(jiaolianYuyueEntity.getJiaolianYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(jiaolianYuyueEntity.getJiaolianYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody JiaolianYuyueEntity jiaolianYuyue, HttpServletRequest request) throws NoSuchFieldException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        logger.debug("update方法:,,Controller:{},,jiaolianYuyue:{}",this.getClass().getName(),jiaolianYuyue.toString());
        JiaolianYuyueEntity oldJiaolianYuyueEntity = jiaolianYuyueService.selectById(jiaolianYuyue.getId());//查询原先数据

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(false)
//            return R.error(511,"永远不会进入");
//        else if("用户".equals(role))
//            jiaolianYuyue.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        if("".equals(jiaolianYuyue.getJiaolianYuyueText()) || "null".equals(jiaolianYuyue.getJiaolianYuyueText())){
                jiaolianYuyue.setJiaolianYuyueText(null);
        }
        if("".equals(jiaolianYuyue.getJiaolianYuyueYesnoText()) || "null".equals(jiaolianYuyue.getJiaolianYuyueYesnoText())){
                jiaolianYuyue.setJiaolianYuyueYesnoText(null);
        }

            jiaolianYuyueService.updateById(jiaolianYuyue);//根据id更新
            return R.ok();
    }


    /**
    * 审核
    */
    @RequestMapping("/shenhe")
    public R shenhe(@RequestBody JiaolianYuyueEntity jiaolianYuyueEntity, HttpServletRequest request){
        logger.debug("shenhe方法:,,Controller:{},,jiaolianYuyueEntity:{}",this.getClass().getName(),jiaolianYuyueEntity.toString());

        JiaolianYuyueEntity oldJiaolianYuyue = jiaolianYuyueService.selectById(jiaolianYuyueEntity.getId());//查询原先数据

//        if(jiaolianYuyueEntity.getJiaolianYuyueYesnoTypes() == 2){//通过
//            jiaolianYuyueEntity.setJiaolianYuyueTypes();
//        }else if(jiaolianYuyueEntity.getJiaolianYuyueYesnoTypes() == 3){//拒绝
//            jiaolianYuyueEntity.setJiaolianYuyueTypes();
//        }
        jiaolianYuyueService.updateById(jiaolianYuyueEntity);//审核

        return R.ok();
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids, HttpServletRequest request){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        List<JiaolianYuyueEntity> oldJiaolianYuyueList =jiaolianYuyueService.selectBatchIds(Arrays.asList(ids));//要删除的数据
        jiaolianYuyueService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName, HttpServletRequest request){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        Integer yonghuId = Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId")));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //.eq("time", new SimpleDateFormat("yyyy-MM-dd").format(new Date()))
        try {
            List<JiaolianYuyueEntity> jiaolianYuyueList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            JiaolianYuyueEntity jiaolianYuyueEntity = new JiaolianYuyueEntity();
//                            jiaolianYuyueEntity.setJiaolianYuyueUuidNumber(data.get(0));                    //预约编号 要改的
//                            jiaolianYuyueEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            jiaolianYuyueEntity.setJiaolianYuyueText(data.get(0));                    //预约备注 要改的
//                            jiaolianYuyueEntity.setJiaolianYuyueTime(sdf.parse(data.get(0)));          //预约时间 要改的
//                            jiaolianYuyueEntity.setJiaolianYuyueYesnoTypes(Integer.valueOf(data.get(0)));   //预约状态 要改的
//                            jiaolianYuyueEntity.setJiaolianYuyueYesnoText(data.get(0));                    //审核回复 要改的
//                            jiaolianYuyueEntity.setInsertTime(date);//时间
//                            jiaolianYuyueEntity.setCreateTime(date);//时间
                            jiaolianYuyueList.add(jiaolianYuyueEntity);


                            //把要查询是否重复的字段放入map中
                                //预约编号
                                if(seachFields.containsKey("jiaolianYuyueUuidNumber")){
                                    List<String> jiaolianYuyueUuidNumber = seachFields.get("jiaolianYuyueUuidNumber");
                                    jiaolianYuyueUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> jiaolianYuyueUuidNumber = new ArrayList<>();
                                    jiaolianYuyueUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("jiaolianYuyueUuidNumber",jiaolianYuyueUuidNumber);
                                }
                        }

                        //查询是否重复
                         //预约编号
                        List<JiaolianYuyueEntity> jiaolianYuyueEntities_jiaolianYuyueUuidNumber = jiaolianYuyueService.selectList(new EntityWrapper<JiaolianYuyueEntity>().in("jiaolian_yuyue_uuid_number", seachFields.get("jiaolianYuyueUuidNumber")));
                        if(jiaolianYuyueEntities_jiaolianYuyueUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(JiaolianYuyueEntity s:jiaolianYuyueEntities_jiaolianYuyueUuidNumber){
                                repeatFields.add(s.getJiaolianYuyueUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [预约编号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        jiaolianYuyueService.insertBatch(jiaolianYuyueList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }




    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        CommonUtil.checkMap(params);
        PageUtils page = jiaolianYuyueService.queryPage(params);

        //字典表数据转换
        List<JiaolianYuyueView> list =(List<JiaolianYuyueView>)page.getList();
        for(JiaolianYuyueView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段

        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Integer id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        JiaolianYuyueEntity jiaolianYuyue = jiaolianYuyueService.selectById(id);
            if(jiaolianYuyue !=null){


                //entity转view
                JiaolianYuyueView view = new JiaolianYuyueView();
                BeanUtils.copyProperties( jiaolianYuyue , view );//把实体数据重构到view中

                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(jiaolianYuyue.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime", "username", "password", "newMoney", "yonghuId"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody JiaolianYuyueEntity jiaolianYuyue, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,jiaolianYuyue:{}",this.getClass().getName(),jiaolianYuyue.toString());
        Wrapper<JiaolianYuyueEntity> queryWrapper = new EntityWrapper<JiaolianYuyueEntity>()
            .eq("jiaolian_yuyue_uuid_number", jiaolianYuyue.getJiaolianYuyueUuidNumber())
            .eq("yonghu_id", jiaolianYuyue.getYonghuId())
            .eq("jiaolian_yuyue_text", jiaolianYuyue.getJiaolianYuyueText())
            .in("jiaolian_yuyue_yesno_types", new Integer[]{1,2})
            .eq("jiaolian_yuyue_yesno_text", jiaolianYuyue.getJiaolianYuyueYesnoText())
//            .notIn("jiaolian_yuyue_types", new Integer[]{102})
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        JiaolianYuyueEntity jiaolianYuyueEntity = jiaolianYuyueService.selectOne(queryWrapper);
        if(jiaolianYuyueEntity==null){
            jiaolianYuyue.setJiaolianYuyueYesnoTypes(1);
            jiaolianYuyue.setInsertTime(new Date());
            jiaolianYuyue.setCreateTime(new Date());
        jiaolianYuyueService.insert(jiaolianYuyue);

            return R.ok();
        }else {
            if(jiaolianYuyueEntity.getJiaolianYuyueYesnoTypes()==1)
                return R.error(511,"有相同的待审核的数据");
            else if(jiaolianYuyueEntity.getJiaolianYuyueYesnoTypes()==2)
                return R.error(511,"有相同的审核通过的数据");
            else
                return R.error(511,"表中有相同数据");
        }
    }

}

