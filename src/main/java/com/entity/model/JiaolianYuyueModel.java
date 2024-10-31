package com.entity.model;

import com.entity.JiaolianYuyueEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 教练预约申请
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class JiaolianYuyueModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 预约编号
     */
    private String jiaolianYuyueUuidNumber;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 预约备注
     */
    private String jiaolianYuyueText;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date jiaolianYuyueTime;


    /**
     * 预约状态
     */
    private Integer jiaolianYuyueYesnoTypes;


    /**
     * 审核回复
     */
    private String jiaolianYuyueYesnoText;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：预约编号
	 */
    public String getJiaolianYuyueUuidNumber() {
        return jiaolianYuyueUuidNumber;
    }


    /**
	 * 设置：预约编号
	 */
    public void setJiaolianYuyueUuidNumber(String jiaolianYuyueUuidNumber) {
        this.jiaolianYuyueUuidNumber = jiaolianYuyueUuidNumber;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：预约备注
	 */
    public String getJiaolianYuyueText() {
        return jiaolianYuyueText;
    }


    /**
	 * 设置：预约备注
	 */
    public void setJiaolianYuyueText(String jiaolianYuyueText) {
        this.jiaolianYuyueText = jiaolianYuyueText;
    }
    /**
	 * 获取：预约时间
	 */
    public Date getJiaolianYuyueTime() {
        return jiaolianYuyueTime;
    }


    /**
	 * 设置：预约时间
	 */
    public void setJiaolianYuyueTime(Date jiaolianYuyueTime) {
        this.jiaolianYuyueTime = jiaolianYuyueTime;
    }
    /**
	 * 获取：预约状态
	 */
    public Integer getJiaolianYuyueYesnoTypes() {
        return jiaolianYuyueYesnoTypes;
    }


    /**
	 * 设置：预约状态
	 */
    public void setJiaolianYuyueYesnoTypes(Integer jiaolianYuyueYesnoTypes) {
        this.jiaolianYuyueYesnoTypes = jiaolianYuyueYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getJiaolianYuyueYesnoText() {
        return jiaolianYuyueYesnoText;
    }


    /**
	 * 设置：审核回复
	 */
    public void setJiaolianYuyueYesnoText(String jiaolianYuyueYesnoText) {
        this.jiaolianYuyueYesnoText = jiaolianYuyueYesnoText;
    }
    /**
	 * 获取：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：申请时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3 listShow
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }
