/**   
 * 
 * 简介
 * 项目名称:  [MGR]
 * 包:        [com.loyal.entity]    
 * 类名称:    [Page]  
 * 类描述:    [页面信息及数据]
 * 创建人:    [Loyal]   
 * 创建时间:  [2015-2-26 10:49:10]   
 * 修改人:    [Loyal]   
 * 修改时间:  [2015-2-26 10:49:10]   
 * 修改备注:  [说明本次修改内容]  
 * 版本:      [v1.0]   
 *    
 */
package com.ups.web.entity;

import org.nutz.dao.pager.Pager;

public class Page {
	private Pager pager;
	private Object data;
	public Pager getPager() {
		return pager;
	}
	public void setPager(Pager pager) {
		this.pager = pager;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
