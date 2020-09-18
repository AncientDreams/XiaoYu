package com.xiaoyu.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ZhangXianYu
 * @since 2020-09-14
 */
@TableName("system_permission")
public class SystemPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 权限编号
     */
    @TableId(value = "permission_id", type = IdType.AUTO)
    private Integer permissionId;

    /**
     * 权限名
     */
    @TableField("permission_name")
    private String permissionName;

    /**
     * 请求地址
     */
    @TableField("url")
    private String url;

    /**
     * 显示菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 是否在菜单中显示
     */
    @TableField("exhibition")
    private Boolean exhibition;

    /**
     * 父级权限编号
     */
    @TableField("parentno")
    private Integer parentno;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }
    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    public Boolean getExhibition() {
        return exhibition;
    }

    public void setExhibition(Boolean exhibition) {
        this.exhibition = exhibition;
    }
    public Integer getParentno() {
        return parentno;
    }

    public void setParentno(Integer parentno) {
        this.parentno = parentno;
    }

    @Override
    public String toString() {
        return "SystemPermission{" +
            "permissionId=" + permissionId +
            ", permissionName=" + permissionName +
            ", url=" + url +
            ", icon=" + icon +
            ", exhibition=" + exhibition +
            ", parentno=" + parentno +
        "}";
    }
}
