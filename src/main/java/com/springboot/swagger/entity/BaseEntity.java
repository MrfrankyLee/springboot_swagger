package com.springboot.swagger.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * @author lixiaole
 * @date 2018/8/17 16:36
 */
@MappedSuperclass //使用该注解 使其类不映射到数据库表(不在数据库中创建表  但其属性字段都将在子类数据库字段中)
public abstract class BaseEntity implements Serializable {
    //设置序列化ID
    private static final long serialVersionUID = 6667979858215354999L;

    @Version
    @Column(columnDefinition = "float Default 0.1 ",nullable = false)
    private Long version;

    @CreatedBy
    @Column(name = "create_by" ,length = 50 ,updatable = false)
    private String CreateBy;

    @CreatedDate
    @Column(name = "create_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime createDate = ZonedDateTime.now();


    @LastModifiedBy
    @Column(name = "update_by", length = 50)
    private String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "update_date")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private ZonedDateTime lastModifiedDate = ZonedDateTime.now();

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreateBy() {
        return CreateBy;
    }

    public void setCreateBy(String createBy) {
        CreateBy = createBy;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public ZonedDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(ZonedDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
