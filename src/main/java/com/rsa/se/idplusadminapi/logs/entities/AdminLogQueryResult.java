package com.rsa.se.idplusadminapi.logs.entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Data
public class AdminLogQueryResult implements LogQueryResult{

    @SerializedName("totalPages")
    @Expose
    private Integer totalPages;
    
    @SerializedName("totalAdminLogEntrys")
    @Expose
    private Integer totalAdminLogEntrys;
    
    @SerializedName("pageSize")
    @Expose
    private Integer pageSize;
    
    @SerializedName("currentPage")
    @Expose
    private Integer currentPage;
    @SerializedName("elements")
    @Expose
    private List<AdminLogEntry> elements = null;
    
    @SerializedName("totalElements")
    @Expose
    private String totalElements;
    /**
     * No args constructor for use in serialization
     *
     */
    public AdminLogQueryResult() {
    }

    /**
     *
     * @param totalAdminLogEntrys
     * @param pageSize
     * @param currentPage
     * @param elements
     * @param totalPages
     */
    public AdminLogQueryResult(Integer totalPages, Integer totalAdminLogEntrys, Integer pageSize, Integer currentPage, List<AdminLogEntry> elements) {
        super();
        this.totalPages = totalPages;
        this.totalAdminLogEntrys = totalAdminLogEntrys;
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.elements = elements;
    }

    @Override
    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public AdminLogQueryResult withTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public Integer getTotalAdminLogEntrys() {
        return totalAdminLogEntrys;
    }

    public void setTotalAdminLogEntrys(Integer totalAdminLogEntrys) {
        this.totalAdminLogEntrys = totalAdminLogEntrys;
    }

    public AdminLogQueryResult withTotalAdminLogEntrys(Integer totalAdminLogEntrys) {
        this.totalAdminLogEntrys = totalAdminLogEntrys;
        return this;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public AdminLogQueryResult withPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public AdminLogQueryResult withCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
        return this;
    }

    public List<AdminLogEntry> getAdminLogEntrys() {
        return getElements();
    }
    
    
  
    public void setAdminLogEntrys(List<AdminLogEntry> elements) {
        this.setElements(elements);
    }

    public AdminLogQueryResult withAdminLogEntrys(List<AdminLogEntry> elements) {
        this.setElements(elements);
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("totalPages", totalPages).append("totalAdminLogEntrys", totalAdminLogEntrys).append("pageSize", pageSize).append("currentPage", currentPage).append("elements", getElements()).toString();
    }

    /**
     * @return the totalElements
     */
    public String getTotalElements() {
        return totalElements;
    }

    /**
     * @param totalElements the totalElements to set
     */
    public void setTotalElements(String totalElements) {
        this.totalElements = totalElements;
    }

    /**
     * @return the elements
     */
    public List<AdminLogEntry> getElements() {
        return elements;
    }

    /**
     * @param elements the elements to set
     */
    public void setElements(List<AdminLogEntry> elements) {
        this.elements = elements;
    }

}
