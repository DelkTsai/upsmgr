package com.ups.web.entity;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("ups_role")
public class Role {
	@Id
	@Comment("唯一标识，整型自增长")
	private int id;

	@Name
	@Comment("角色名")
	private String rolename;

	@Column
	@Comment("新增权限")
	private int authAdd;

	@Column
	@Comment("删除权限")
	private int authDel;

	@Column
	@Comment("编辑权限")
	private int authEdit;

	@Column
	@Comment("查看权限")
	private int authView;

	@Column
	@Comment("备注")
	private String comment;

	@Column
	@Comment("所属组织，外键")
	private int orgid;

	@One(target = Org.class, field = "orgid")
	private Org org;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public int getAuthAdd() {
		return authAdd;
	}

	public void setAuthAdd(int authAdd) {
		this.authAdd = authAdd;
	}

	public int getAuthDel() {
		return authDel;
	}

	public void setAuthDel(int authDel) {
		this.authDel = authDel;
	}

	public int getAuthEdit() {
		return authEdit;
	}

	public void setAuthEdit(int authEdit) {
		this.authEdit = authEdit;
	}

	public int getAuthView() {
		return authView;
	}

	public void setAuthView(int authView) {
		this.authView = authView;
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
}
