package com.ups.web.bean;

import java.util.List;

import org.nutz.dao.entity.annotation.ColDefine;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.ManyMany;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.One;
import org.nutz.dao.entity.annotation.Table;

@Table("t_user")
public class User extends BasePojo {
	@Id
	@Comment("唯一标识，整型自增长")
	private int id;

	@Name
	@Comment("用户名（系统用户）")
	private String username;

	@Column
	@ColDefine(width=128)
	@Comment("密码（系统用户）")
	private String password;

	@Column
	@Comment("密码加盐")
	private String salt;
	
	@Column
	@Comment("是否锁定")
	private boolean locked;

	@Column
	@Comment("备注")
	private String comment;

	@ManyMany(from = "user_id", relation = "t_user_role", target = Role.class, to = "role_id")
	private List<Role> roles;

	@ManyMany(from = "user_id", relation = "t_user_permission", target = Permission.class, to = "permission_id")
	protected List<Permission> permissions;
	
	@One(target = UserProfile.class, field = "id", key = "userId")
	protected UserProfile profile;
	
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

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public boolean isLocked() {
		return locked;
	}

	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}

	public UserProfile getProfile() {
		return profile;
	}

	public void setProfile(UserProfile profile) {
		this.profile = profile;
	}
}
