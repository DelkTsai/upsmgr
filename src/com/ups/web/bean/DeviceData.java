package com.ups.web.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_device_data")
public class DeviceData extends BasePojo{
	@Id
	@Comment("唯一标识，整型自增长")
	private int id;
	@Column
	@Comment("设备编号")
	private String deviceId;
	@Column
	@Comment("输出电压")
	private double outputVoltage;
	@Column
	@Comment("电池电压")
	private double batteryVoltage;
	@Column
	@Comment("通讯方式")
	private int communicateMethod;
	@Column
	@Comment("负载")
	private double batteryLoad;
	@Column
	@Comment("数据采集时间")
	private Date dataTime;
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
	public double getOutputVoltage() {
		return outputVoltage;
	}
	public void setOutputVoltage(double outputVoltage) {
		this.outputVoltage = outputVoltage;
	}
	public double getBatteryVoltage() {
		return batteryVoltage;
	}
	public void setBatteryVoltage(double batteryVoltage) {
		this.batteryVoltage = batteryVoltage;
	}
	public int getCommunicateMethod() {
		return communicateMethod;
	}
	public void setCommunicateMethod(int communicateMethod) {
		this.communicateMethod = communicateMethod;
	}
	public double getBatteryLoad() {
		return batteryLoad;
	}
	public void setBatteryLoad(double batteryLoad) {
		this.batteryLoad = batteryLoad;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getDataTime() {
		return dataTime;
	}
	public void setDataTime(Date dataTime) {
		this.dataTime = dataTime;
	}
	
}
