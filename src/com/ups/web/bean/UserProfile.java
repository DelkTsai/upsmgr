package com.ups.web.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import com.mysql.jdbc.Blob;

@Table("t_user_profile")
public class UserProfile extends BasePojo {
	@Id(auto = false)
	@Column("user_id")
	@Comment("关联的用户id")
	private int userId;
	
	@Column
	@Comment("用户昵称")
	private String nickname;
	
	@Column
	@Comment("用户邮箱")
	private String email;
	
	@Column("email_checked")
	@Comment("邮箱是否已经验证过")
	private boolean emailChecked;
	
	@Column
	@Comment("头像的byte数据")
	private Blob avatar;
	
	@Column
	@Comment("性别")
	private String gender;
	
	@Column
	@Comment("自我介绍")
	private String description;
	
	@Column
	@Comment("所在地")
	
	private String location;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isEmailChecked() {
		return emailChecked;
	}

	public void setEmailChecked(boolean emailChecked) {
		this.emailChecked = emailChecked;
	}

	public Blob getAvatar() {
		return avatar;
	}

	public void setAvatar(Blob avatar) {
		this.avatar = avatar;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
