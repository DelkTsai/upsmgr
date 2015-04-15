package com.ups.web.entity;

import java.util.List;

import org.nutz.dao.entity.annotation.*;

@Table("ups_menu")
public class Menu {
	@Id
	@Comment("唯一标识，整型自增长")
	private int id;
	@Name
	@Comment("菜单名称")
	private String menuName;
	@Column
	@Comment("菜单显示文本")
	private String menuText;
	@Column
	@Comment("菜单图标")
	private String menuIcon;
	@Column
	@Comment("菜单连接")
	private String menuLink;
	@Column
	@Comment("菜单状态")
	private int status;
	@Column
	@Comment("父菜单")
	private int pmenu;
	@Many(target = Menu.class, field = "pmenu")
	private List<Menu>  subMenus;
	
	private boolean active;
	private boolean expand;
	private boolean hasChild;
	
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public boolean isExpand() {
		return expand;
	}
	public void setExpand(boolean expand) {
		this.expand = expand;
	}
	public boolean isHasChild() {
		return hasChild;
	}
	public void setHasChild(boolean hasChild) {
		this.hasChild = hasChild;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuText() {
		return menuText;
	}
	public void setMenuText(String menuText) {
		this.menuText = menuText;
	}
	public String getMenuIcon() {
		return menuIcon;
	}
	public void setMenuIcon(String menuIcon) {
		this.menuIcon = menuIcon;
	}
	public String getMenuLink() {
		return menuLink;
	}
	public void setMenuLink(String menuLink) {
		this.menuLink = menuLink;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getPmenu() {
		return pmenu;
	}
	public void setPmenu(int pmenu) {
		this.pmenu = pmenu;
	}
	public List<Menu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}
}
