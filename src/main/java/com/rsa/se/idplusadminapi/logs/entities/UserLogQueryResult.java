
package com.rsa.se.idplusadminapi.logs.entities;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserLogQueryResult implements LogQueryResult{

@SerializedName("totalPages")
@Expose
private Integer totalPages;
@SerializedName("totalElements")
@Expose
private Integer totalElements;
@SerializedName("pageSize")
@Expose
private Integer pageSize;
@SerializedName("currentPage")
@Expose
private Integer currentPage;
@SerializedName("userEventLogExportEntries")
@Expose
private List<UserLogEntry> userEventLogExportEntries = null;

/**
* No args constructor for use in serialization
* 
*/
public UserLogQueryResult() {
}

/**
* 
* @param userEventLogExportEntries
* @param totalElements
* @param pageSize
* @param currentPage
* @param totalPages
*/
public UserLogQueryResult(Integer totalPages, Integer totalElements, Integer pageSize, Integer currentPage, List<UserLogEntry> userEventLogExportEntries) {
super();
this.totalPages = totalPages;
this.totalElements = totalElements;
this.pageSize = pageSize;
this.currentPage = currentPage;
this.userEventLogExportEntries = userEventLogExportEntries;
}

@Override
public Integer getTotalPages() {
return totalPages;
}

public void setTotalPages(Integer totalPages) {
this.totalPages = totalPages;
}

public UserLogQueryResult withTotalPages(Integer totalPages) {
this.totalPages = totalPages;
return this;
}

public Integer getTotalElements() {
return totalElements;
}

public void setTotalElements(Integer totalElements) {
this.totalElements = totalElements;
}

public UserLogQueryResult withTotalElements(Integer totalElements) {
this.totalElements = totalElements;
return this;
}

public Integer getPageSize() {
return pageSize;
}

public void setPageSize(Integer pageSize) {
this.pageSize = pageSize;
}

public UserLogQueryResult withPageSize(Integer pageSize) {
this.pageSize = pageSize;
return this;
}

public Integer getCurrentPage() {
return currentPage;
}

public void setCurrentPage(Integer currentPage) {
this.currentPage = currentPage;
}

public UserLogQueryResult withCurrentPage(Integer currentPage) {
this.currentPage = currentPage;
return this;
}

public List<UserLogEntry> getUserEventLogExportEntries() {
return userEventLogExportEntries;
}

public void setUserEventLogExportEntries(List<UserLogEntry> userEventLogExportEntries) {
this.userEventLogExportEntries = userEventLogExportEntries;
}

public UserLogQueryResult withUserEventLogExportEntries(List<UserLogEntry> userEventLogExportEntries) {
this.userEventLogExportEntries = userEventLogExportEntries;
return this;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("totalPages", totalPages).append("totalElements", totalElements).append("pageSize", pageSize).append("currentPage", currentPage).append("userEventLogExportEntries", userEventLogExportEntries).toString();
}

}