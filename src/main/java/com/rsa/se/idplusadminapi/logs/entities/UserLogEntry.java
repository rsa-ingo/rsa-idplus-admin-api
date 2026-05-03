
package com.rsa.se.idplusadminapi.logs.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

@JsonIgnoreProperties(ignoreUnknown = true)

public class UserLogEntry extends LogEntry{
    


@SerializedName("eventId")
@Expose
private String eventId;
@SerializedName("eventLogDate")
@Expose
private String eventLogDate;
@SerializedName("eventType")
@Expose
private String eventType;
@SerializedName("eventLevel")
@Expose
private String eventLevel;
@SerializedName("eventCategory")
@Expose
private String eventCategory;
@SerializedName("serverIPAddress")
@Expose
private String serverIPAddress;
@SerializedName("tenantId")
@Expose
private String tenantId;
@SerializedName("customerName")
@Expose
private String customerName;
@SerializedName("userId")
@Expose
private String userId;
@SerializedName("sourceIPAddress")
@Expose
private Object sourceIPAddress;
@SerializedName("eventCode")
@Expose
private String eventCode;
@SerializedName("eventDescription")
@Expose
private String eventDescription;
@SerializedName("application")
@Expose
private Object application;
@SerializedName("method")
@Expose
private String method;
@SerializedName("deviceName")
@Expose
private Object deviceName;
@SerializedName("deviceId")
@Expose
private Object deviceId;
@SerializedName("policyId")
@Expose
private String policyId;
@SerializedName("policyName")
@Expose
private Object policyName;
@SerializedName("authenticationDetails")
@Expose
private Object authenticationDetails;
@SerializedName("assuranceLevel")
@Expose
private Object assuranceLevel;

@SerializedName("verboseFlag")
@Expose
private String verboseFlag;

@SerializedName("userActivityId")
@Expose
private String userActivityId;
@SerializedName("transactionId")
@Expose
private String transactionId;



/**
* No args constructor for use in serialization
* 
*/
public UserLogEntry() {
}

/**
* 
* @param tenantId
* @param customerName
* @param application
* @param eventType
* @param serverIPAddress
* @param authenticationDetails
* @param policyName
* @param eventLogDate
* @param eventDescription
* @param deviceName
* @param eventId
* @param assuranceLevel
* @param policyId
* @param sourceIPAddress
* @param userId
* @param eventCode
* @param eventLevel
* @param method
* @param deviceId
* @param eventCategory
* @param verboseFlag
*/
public UserLogEntry(String eventId, String eventLogDate, String eventType, String eventLevel, String eventCategory, String serverIPAddress, String tenantId, String customerName, String userId, Object sourceIPAddress, String eventCode, String eventDescription, Object application, String method, Object deviceName, Object deviceId, String policyId, Object policyName, Object authenticationDetails, Object assuranceLevel) {
super();
this.eventId = eventId;
this.eventLogDate = eventLogDate;
this.eventType = eventType;
this.eventLevel = eventLevel;
this.eventCategory = eventCategory;
this.serverIPAddress = serverIPAddress;
this.tenantId = tenantId;
this.customerName = customerName;
this.userId = userId;
this.sourceIPAddress = sourceIPAddress;
this.eventCode = eventCode;
this.eventDescription = eventDescription;
this.application = application;
this.method = method;
this.deviceName = deviceName;
this.deviceId = deviceId;
this.policyId = policyId;
this.policyName = policyName;
this.authenticationDetails = authenticationDetails;
this.assuranceLevel = assuranceLevel;
}

public String getEventId() {
return eventId;
}

public void setEventId(String eventId) {
this.eventId = eventId;
}

public UserLogEntry withEventId(String eventId) {
this.eventId = eventId;
return this;
}

public String getEventLogDate() {
return eventLogDate;
}

public void setEventLogDate(String eventLogDate) {
this.eventLogDate = eventLogDate;
}

public UserLogEntry withEventLogDate(String eventLogDate) {
this.eventLogDate = eventLogDate;
return this;
}

public String getEventType() {
return eventType;
}

public void setEventType(String eventType) {
this.eventType = eventType;
}

public UserLogEntry withEventType(String eventType) {
this.eventType = eventType;
return this;
}

public String getEventLevel() {
return eventLevel;
}

public void setEventLevel(String eventLevel) {
this.eventLevel = eventLevel;
}

public UserLogEntry withEventLevel(String eventLevel) {
this.eventLevel = eventLevel;
return this;
}

public String getEventCategory() {
return eventCategory;
}

public void setEventCategory(String eventCategory) {
this.eventCategory = eventCategory;
}

public UserLogEntry withEventCategory(String eventCategory) {
this.eventCategory = eventCategory;
return this;
}

public String getServerIPAddress() {
return serverIPAddress;
}

public void setServerIPAddress(String serverIPAddress) {
this.serverIPAddress = serverIPAddress;
}

public UserLogEntry withServerIPAddress(String serverIPAddress) {
this.serverIPAddress = serverIPAddress;
return this;
}

public String getTenantId() {
return tenantId;
}

public void setTenantId(String tenantId) {
this.tenantId = tenantId;
}

public UserLogEntry withTenantId(String tenantId) {
this.tenantId = tenantId;
return this;
}

public String getCustomerName() {
return customerName;
}

public void setCustomerName(String customerName) {
this.customerName = customerName;
}

public UserLogEntry withCustomerName(String customerName) {
this.customerName = customerName;
return this;
}

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public UserLogEntry withUserId(String userId) {
this.userId = userId;
return this;
}

public Object getSourceIPAddress() {
return sourceIPAddress;
}

public void setSourceIPAddress(Object sourceIPAddress) {
this.sourceIPAddress = sourceIPAddress;
}

public UserLogEntry withSourceIPAddress(Object sourceIPAddress) {
this.sourceIPAddress = sourceIPAddress;
return this;
}

public String getEventCode() {
return eventCode;
}

public void setEventCode(String eventCode) {
this.eventCode = eventCode;
}

public UserLogEntry withEventCode(String eventCode) {
this.eventCode = eventCode;
return this;
}

public String getEventDescription() {
return eventDescription;
}

public void setEventDescription(String eventDescription) {
this.eventDescription = eventDescription;
}

public UserLogEntry withEventDescription(String eventDescription) {
this.eventDescription = eventDescription;
return this;
}

public Object getApplication() {
return application;
}

public void setApplication(Object application) {
this.application = application;
}

public UserLogEntry withApplication(Object application) {
this.application = application;
return this;
}

public String getMethod() {
return method;
}

public void setMethod(String method) {
this.method = method;
}

public UserLogEntry withMethod(String method) {
this.method = method;
return this;
}

public Object getDeviceName() {
return deviceName;
}

public void setDeviceName(Object deviceName) {
this.deviceName = deviceName;
}

public UserLogEntry withDeviceName(Object deviceName) {
this.deviceName = deviceName;
return this;
}

public Object getDeviceId() {
return deviceId;
}

public void setDeviceId(Object deviceId) {
this.deviceId = deviceId;
}

public UserLogEntry withDeviceId(Object deviceId) {
this.deviceId = deviceId;
return this;
}

public String getPolicyId() {
return policyId;
}

public void setPolicyId(String policyId) {
this.policyId = policyId;
}

public UserLogEntry withPolicyId(String policyId) {
this.policyId = policyId;
return this;
}

public Object getPolicyName() {
return policyName;
}

public void setPolicyName(Object policyName) {
this.policyName = policyName;
}

public UserLogEntry withPolicyName(Object policyName) {
this.policyName = policyName;
return this;
}

public Object getAuthenticationDetails() {
return authenticationDetails;
}

public void setAuthenticationDetails(Object authenticationDetails) {
this.authenticationDetails = authenticationDetails;
}

public UserLogEntry withAuthenticationDetails(Object authenticationDetails) {
this.authenticationDetails = authenticationDetails;
return this;
}

public Object getAssuranceLevel() {
return assuranceLevel;
}

public void setAssuranceLevel(Object assuranceLevel) {
this.assuranceLevel = assuranceLevel;
}

public UserLogEntry withAssuranceLevel(Object assuranceLevel) {
this.assuranceLevel = assuranceLevel;
return this;
}

@Override
public String toString() {
return new ToStringBuilder(this).append("eventId", eventId).append("eventLogDate", eventLogDate).append("eventType", eventType).append("eventLevel", eventLevel).append("eventCategory", eventCategory).append("serverIPAddress", serverIPAddress).append("tenantId", tenantId).append("customerName", customerName).append("userId", userId).append("sourceIPAddress", sourceIPAddress).append("eventCode", eventCode).append("eventDescription", eventDescription).append("application", application).append("method", method).append("deviceName", deviceName).append("deviceId", deviceId).append("policyId", policyId).append("policyName", policyName).append("authenticationDetails", authenticationDetails).append("assuranceLevel", assuranceLevel).toString();
}

public String toCSVString() {
return eventLogDate+","+
        customerName+","+
        eventType+","+
        eventCategory+","+
        userId+","+
        eventCode+","+
        eventDescription+","+
        application+","+
        method+","+
        policyId+","+
        policyName+","+
        authenticationDetails+","+
        assuranceLevel+"\n";
}

}