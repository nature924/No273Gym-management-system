package com.entity.vo;

import com.entity.JiaolianYuyueEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 教练预约申请
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("jiaolian_yuyue")
public class JiaolianYuyueVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 预约编号
     */

    @TableField(value = "jiaolian_yuyue_uuid_number")
    private String jiaolianYuyueUuidNumber;


    /**
     * 用户
     */

    @TableField(value = "yonghu_id")
    private Integer yonghuId;


    /**
     * 预约备注
     */

    @TableField(value = "jiaolian_yuyue_text")
    private String jiaolianYuyueText;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "jiaolian_yuyue_time")
    private Date jiaolianYuyueTime;


    /**
     * 预约状态
     */

    @TableField(value = "jiaolian_yuyue_yesno_types")
    private Integer jiaolianYuyueYesnoTypes;


    /**
     * 审核回复
     */

    @TableField(value = "jiaolian_yuyue_yesno_text")
    private String jiaolianYuyueYesnoText;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "insert_time")
    private Date insertTime;


    /**
     * 创建时间 show3 listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：预约编号
	 */
    public String getJiaolianYuyueUuidNumber() {
        return jiaolianYuyueUuidNumber;
    }


    /**
	 * 获取：预约编号
	 */

    public void setJiaolianYuyueUuidNumber(String jiaolianYuyueUuidNumber) {
        this.jiaolianYuyueUuidNumber = jiaolianYuyueUuidNumber;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：预约备注
	 */
    public String getJiaolianYuyueText() {
        return jiaolianYuyueText;
    }


    /**
	 * 获取：预约备注
	 */

    public void setJiaolianYuyueText(String jiaolianYuyueText) {
        this.jiaolianYuyueText = jiaolianYuyueText;
    }
    /**
	 * 设置：预约时间
	 */
    public Date getJiaolianYuyueTime() {
        return jiaolianYuyueTime;
    }


    /**
	 * 获取：预约时间
	 */

    public void setJiaolianYuyueTime(Date jiaolianYuyueTime) {
        this.jiaolianYuyueTime = jiaolianYuyueTime;
    }
    /**
	 * 设置：预约状态
	 */
    public Integer getJiaolianYuyueYesnoTypes() {
        return jiaolianYuyueYesnoTypes;
    }


    /**
	 * 获取：预约状态
	 */

    public void setJiaolianYuyueYesnoTypes(Integer jiaolianYuyueYesnoTypes) {
        this.jiaolianYuyueYesnoTypes = jiaolianYuyueYesnoTypes;
    }
    /**
	 * 设置：审核回复
	 */
    public String getJiaolianYuyueYesnoText() {
        return jiaolianYuyueYesnoText;
    }


    /**
	 * 获取：审核回复
	 */

    public void setJiaolianYuyueYesnoText(String jiaolianYuyueYesnoText) {
        this.jiaolianYuyueYesnoText = jiaolianYuyueYesnoText;
    }
    /**
	 * 设置：申请时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：申请时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间 show3 listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间 show3 listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
