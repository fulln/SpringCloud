package com.fulln.pips.Entity.baseEntity;


import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;

public class BaseEntity implements Serializable {

        private String createBy;
        private Long createDate;
        private String updateBy;
        private Long updateDate;
        @Transient
        private Integer pageSize;
        @Transient
        private Integer pageNo;
        @Transient
        private Date startTime;
        @Transient
        private Date endTime;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getPageSize() {
            return pageSize;
        }

        public void setPageSize(Integer pageSize) {
            this.pageSize = pageSize;
        }

        public Integer getPageNo() {
            return pageNo;
        }

        public void setPageNo(Integer pageNo) {
            this.pageNo = pageNo;
        }

        public String getCreateBy() {
            return createBy;
        }
        public void setCreateBy(String createBy) {
            this.createBy = createBy;
        }
        public String getUpdateBy() {
            return updateBy;
        }
        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }
        public Long getCreateDate() {
        return createDate;
    }
        public void setCreateDate(Long createDate) {
        this.createDate = createDate;
    }
        public Long getUpdateDate() {
        return updateDate;
    }
        public void setUpdateDate(Long updateDate) {
        this.updateDate = updateDate;
    }
}
