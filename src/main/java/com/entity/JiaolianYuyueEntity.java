package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 教练预约申请
 *
 * @author 
 * @email
 */
@TableName("jiaolian_yuyue")
public class JiaolianYuyueEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public JiaolianYuyueEntity() {

	}

	public JiaolianYuyueEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 预约编号
     */
    @ColumnInfo(comment="预约编号",type="varchar(200)")
    @TableField(value = "jiaolian_yuyue_uuid_number")

    private String jiaolianYuyueUuidNumber;


    /**
     * 用户
     */
    @ColumnInfo(comment="用户",type="int(11)")
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 预约备注
     */
    @ColumnInfo(comment="预约备注",type="longtext")
    @TableField(value = "jiaolian_yuyue_text")

    private String jiaolianYuyueText;


    /**
     * 预约时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="预约时间",type="timestamp")
    @TableField(value = "jiaolian_yuyue_time")

    private Date jiaolianYuyueTime;


    /**
     * 预约状态
     */
    @ColumnInfo(comment="预约状态",type="int(11)")
    @TableField(value = "jiaolian_yuyue_yesno_types")

    private Integer jiaolianYuyueYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="longtext")
    @TableField(value = "jiaolian_yuyue_yesno_text")

    private String jiaolianYuyueYesnoText;


    /**
     * 申请时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="申请时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间  listShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

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
	 * 获取：创建时间  listShow
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间  listShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "JiaolianYuyue{" +
            ", id=" + id +
            ", jiaolianYuyueUuidNumber=" + jiaolianYuyueUuidNumber +
            ", yonghuId=" + yonghuId +
            ", jiaolianYuyueText=" + jiaolianYuyueText +
            ", jiaolianYuyueTime=" + DateUtil.convertString(jiaolianYuyueTime,"yyyy-MM-dd") +
            ", jiaolianYuyueYesnoTypes=" + jiaolianYuyueYesnoTypes +
            ", jiaolianYuyueYesnoText=" + jiaolianYuyueYesnoText +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
