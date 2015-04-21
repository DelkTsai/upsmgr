package com.ups.web.bean;

import java.util.List;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;

@Table("t_role")
public class Role extends BasePojo{
	@Id
	@Comment("唯一标识，整型自增长")
	private int id;

	@Name
	@Comment("角色名")
	private String name;
	
	@Column
	@Comment("角色别名")
	private String alias;
	
	@Column
	@Comment("备注")
	private String comment;

	@ManyMany(from="role_id", relation="t_role_permission", target=Permission.class, to="permission_id")
	private List<Permission> permissions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
	
}
