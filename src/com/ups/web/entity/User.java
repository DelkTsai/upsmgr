package com.ups.web.entity;

import java.util.Date;

import org.nutz.dao.entity.annotation.*;

@Table("ups_user")
public class User {
	@Id
	@Comment("唯一标识，整型自增长")
	private int id;
	@Name
	@Comment("用户名（系统用户）")
	private String username;
	@Column
	@Comment("密码（系统用户）")
	private String password;
	@Column
	@Comment("昵称（系统用户）")
	private String nickname;
	@Column
	@Comment("用户生成时间")
	private Date createTime;
	@Column
	@Comment("用户更新时间")
	private Date updateTime;
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column
	@Comment("状态（0：禁用 1：启用 2：删除）")
	@Default("0")
	private int status;
	@Column
	@Comment("备注")
	private String comment;
	@Column
	@Comment("所属组织，外键")
	private int orgid;
	@One(target = Org.class, field = "orgid")
	private Org org;
	@Column
	@Comment("所属角色，外键")
	private int roleid;
	@One(target = Role.class, field = "roleid")
	private Role role;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
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

	public int getOrgid() {
		return orgid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	public Org getOrg() {
		return org;
	}

	public void setOrg(Org org) {
		this.org = org;
	}

	public int getRoleid() {
		return roleid;
	}

	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

}
