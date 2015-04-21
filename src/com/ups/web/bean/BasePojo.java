package com.ups.web.bean;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Comment;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;

public abstract class BasePojo {

    @Column
    @Comment("创建时间")
    protected Date createTime;
    @Column
    @Comment("更新时间")
    protected Date updateTime;

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

    public String toString() {
    	return String.format("/*%s*/%s", super.toString(), Json.toJson(this, JsonFormat.compact()));
    }
}
