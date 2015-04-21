package com.ups.web.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_device")
public class Device extends BasePojo{
	@Id
	@Comment("唯一标识，整型自增长")
	private int id;
	@Name
	@Comment("设备编号")
	private String deviceId;
	@Column
	@Comment("安装时间")
	private Date installTime;
	@Column
	@Comment("通讯方式")
	private int communicateMethod;
	@Column
	@Comment("工作状态")
	private int status;
	@Column
	@Comment("备注")
	private String comment;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getInstallTime() {
		return installTime;
	}

	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
	}

	public int getCommunicateMethod() {
		return communicateMethod;
	}

	public void setCommunicateMethod(int communicateMethod) {
		this.communicateMethod = communicateMethod;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
