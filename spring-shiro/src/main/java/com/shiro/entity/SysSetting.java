package com.shiro.entity;

public class SysSetting {
    private String id;

    private String syskey;

    private String sysname;

    private String sysvalue;

    private Integer sort;

    private String sysdesc;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getSyskey() {
        return syskey;
    }

    public void setSyskey(String syskey) {
        this.syskey = syskey == null ? null : syskey.trim();
    }

    public String getSysname() {
        return sysname;
    }

    public void setSysname(String sysname) {
        this.sysname = sysname == null ? null : sysname.trim();
    }

    public String getSysvalue() {
        return sysvalue;
    }

    public void setSysvalue(String sysvalue) {
        this.sysvalue = sysvalue == null ? null : sysvalue.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getSysdesc() {
        return sysdesc;
    }

    public void setSysdesc(String sysdesc) {
        this.sysdesc = sysdesc == null ? null : sysdesc.trim();
    }
}