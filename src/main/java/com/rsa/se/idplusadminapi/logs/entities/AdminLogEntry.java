
package com.rsa.se.idplusadminapi.logs.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class AdminLogEntry extends LogEntry{

    @SerializedName("eventId")
    @Expose
    private String eventId;
    @SerializedName("eventLogDate")
    @Expose
    private String eventLogDate;
    @SerializedName("eventType")
    @Expose
    private String eventType;
    @SerializedName("serverURL")
    @Expose
    private String serverURL;
    @SerializedName("serverIPAddress")
    @Expose
    private String serverIPAddress;
    @SerializedName("application")
    @Expose
    private String application;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("sourceIPAddress")
    @Expose
    private String sourceIPAddress;
    @SerializedName("adminUserName")
    @Expose
    private String adminUserName;
    @SerializedName("adminUserRole")
    @Expose
    private String adminUserRole;
    @SerializedName("activityKey")
    @Expose
    private String activityKey;
    @SerializedName("activityCode")
    @Expose
    private Integer activityCode;
    @SerializedName("result")
    @Expose
    private String result;
    @SerializedName("reasonKey")
    @Expose
    private String reasonKey;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("requiresPublish")
    @Expose
    private Boolean requiresPublish;
    @SerializedName("targetObject1Id")
    @Expose
    private Object targetObject1Id;
    @SerializedName("targetObject1Name")
    @Expose
    private Object targetObject1Name;
    @SerializedName("targetObject1Type")
    @Expose
    private Object targetObject1Type;
    @SerializedName("targetObject2Id")
    @Expose
    private Object targetObject2Id;
    @SerializedName("targetObject2Name")
    @Expose
    private Object targetObject2Name;
    @SerializedName("targetObject2Type")
    @Expose
    private Object targetObject2Type;
    
    private String tenantId="unknown";
    

    /**
     * No args constructor for use in serialization
     *
     */
    public AdminLogEntry() {
    }

    /**
     *
     * @param requiresPublish
     * @param activityCode
     * @param customerName
     * @param result
     * @param application
     * @param adminUserName
     * @param targetObject2Id
     * @param targetObject1Type
     * @param serverURL
     * @param adminUserRole
     * @param eventType
     * @param targetObject1Name
     * @param serverIPAddress
     * @param targetObject1Id
     * @param targetObject2Type
     * @param message
     * @param eventLogDate
     * @param customerId
     * @param eventId
     * @param sourceIPAddress
     * @param activityKey
     * @param targetObject2Name
     * @param reasonKey
     */
    public AdminLogEntry(String eventId, String eventLogDate, String eventType, String serverURL, String serverIPAddress, String application, Integer customerId, String customerName, String sourceIPAddress, String adminUserName, String adminUserRole, String activityKey, Integer activityCode, String result, String reasonKey, String message, Boolean requiresPublish, Object targetObject1Id, Object targetObject1Name, Object targetObject1Type, Object targetObject2Id, Object targetObject2Name, Object targetObject2Type) {
        super();
        this.eventId = eventId;
        this.eventLogDate = eventLogDate;
        this.eventType = eventType;
        this.serverURL = serverURL;
        this.serverIPAddress = serverIPAddress;
        this.application = application;
        this.customerId = customerId;
        this.customerName = customerName;
        this.sourceIPAddress = sourceIPAddress;
        this.adminUserName = adminUserName;
        this.adminUserRole = adminUserRole;
        this.activityKey = activityKey;
        this.activityCode = activityCode;
        this.result = result;
        this.reasonKey = reasonKey;
        this.message = message;
        this.requiresPublish = requiresPublish;
        this.targetObject1Id = targetObject1Id;
        this.targetObject1Name = targetObject1Name;
        this.targetObject1Type = targetObject1Type;
        this.targetObject2Id = targetObject2Id;
        this.targetObject2Name = targetObject2Name;
        this.targetObject2Type = targetObject2Type;
    }
    
    
    

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public AdminLogEntry withEventId(String eventId) {
        this.eventId = eventId;
        return this;
    }

    public String getEventLogDate() {
        return eventLogDate;
    }

    public void setEventLogDate(String eventLogDate) {
        this.eventLogDate = eventLogDate;
    }

    public AdminLogEntry withEventLogDate(String eventLogDate) {
        this.eventLogDate = eventLogDate;
        return this;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public AdminLogEntry withEventType(String eventType) {
        this.eventType = eventType;
        return this;
    }

    public String getServerURL() {
        return serverURL;
    }

    public void setServerURL(String serverURL) {
        this.serverURL = serverURL;
    }

    public AdminLogEntry withServerURL(String serverURL) {
        this.serverURL = serverURL;
        return this;
    }

    public String getServerIPAddress() {
        return serverIPAddress;
    }

    public void setServerIPAddress(String serverIPAddress) {
        this.serverIPAddress = serverIPAddress;
    }

    public AdminLogEntry withServerIPAddress(String serverIPAddress) {
        this.serverIPAddress = serverIPAddress;
        return this;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public AdminLogEntry withApplication(String application) {
        this.application = application;
        return this;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public AdminLogEntry withCustomerId(Integer customerId) {
        this.customerId = customerId;
        return this;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public AdminLogEntry withCustomerName(String customerName) {
        this.customerName = customerName;
        return this;
    }

    public String getSourceIPAddress() {
        return sourceIPAddress;
    }

    public void setSourceIPAddress(String sourceIPAddress) {
        this.sourceIPAddress = sourceIPAddress;
    }

    public AdminLogEntry withSourceIPAddress(String sourceIPAddress) {
        this.sourceIPAddress = sourceIPAddress;
        return this;
    }

    public String getAdminUserName() {
        return adminUserName;
    }

    public void setAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
    }

    public AdminLogEntry withAdminUserName(String adminUserName) {
        this.adminUserName = adminUserName;
        return this;
    }

    public String getAdminUserRole() {
        return adminUserRole;
    }

    public void setAdminUserRole(String adminUserRole) {
        this.adminUserRole = adminUserRole;
    }

    public AdminLogEntry withAdminUserRole(String adminUserRole) {
        this.adminUserRole = adminUserRole;
        return this;
    }

    public String getActivityKey() {
        return activityKey;
    }

    public void setActivityKey(String activityKey) {
        this.activityKey = activityKey;
    }

    public AdminLogEntry withActivityKey(String activityKey) {
        this.activityKey = activityKey;
        return this;
    }

    public Integer getActivityCode() {
        return activityCode;
    }

    public void setActivityCode(Integer activityCode) {
        this.activityCode = activityCode;
    }

    public AdminLogEntry withActivityCode(Integer activityCode) {
        this.activityCode = activityCode;
        return this;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public AdminLogEntry withResult(String result) {
        this.result = result;
        return this;
    }

    public String getReasonKey() {
        return reasonKey;
    }

    public void setReasonKey(String reasonKey) {
        this.reasonKey = reasonKey;
    }

    public AdminLogEntry withReasonKey(String reasonKey) {
        this.reasonKey = reasonKey;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AdminLogEntry withMessage(String message) {
        this.message = message;
        return this;
    }

    public Boolean getRequiresPublish() {
        return requiresPublish;
    }

    public void setRequiresPublish(Boolean requiresPublish) {
        this.requiresPublish = requiresPublish;
    }

    public AdminLogEntry withRequiresPublish(Boolean requiresPublish) {
        this.requiresPublish = requiresPublish;
        return this;
    }

    public Object getTargetObject1Id() {
        return targetObject1Id;
    }

    public void setTargetObject1Id(Object targetObject1Id) {
        this.targetObject1Id = targetObject1Id;
    }

    public AdminLogEntry withTargetObject1Id(Object targetObject1Id) {
        this.targetObject1Id = targetObject1Id;
        return this;
    }

    public Object getTargetObject1Name() {
        return targetObject1Name;
    }

    public void setTargetObject1Name(Object targetObject1Name) {
        this.targetObject1Name = targetObject1Name;
    }

    public AdminLogEntry withTargetObject1Name(Object targetObject1Name) {
        this.targetObject1Name = targetObject1Name;
        return this;
    }

    public Object getTargetObject1Type() {
        return targetObject1Type;
    }

    public void setTargetObject1Type(Object targetObject1Type) {
        this.targetObject1Type = targetObject1Type;
    }

    public AdminLogEntry withTargetObject1Type(Object targetObject1Type) {
        this.targetObject1Type = targetObject1Type;
        return this;
    }

    public Object getTargetObject2Id() {
        return targetObject2Id;
    }

    public void setTargetObject2Id(Object targetObject2Id) {
        this.targetObject2Id = targetObject2Id;
    }

    public AdminLogEntry withTargetObject2Id(Object targetObject2Id) {
        this.targetObject2Id = targetObject2Id;
        return this;
    }

    public Object getTargetObject2Name() {
        return targetObject2Name;
    }

    public void setTargetObject2Name(Object targetObject2Name) {
        this.targetObject2Name = targetObject2Name;
    }

    public AdminLogEntry withTargetObject2Name(Object targetObject2Name) {
        this.targetObject2Name = targetObject2Name;
        return this;
    }

    public Object getTargetObject2Type() {
        return targetObject2Type;
    }

    public void setTargetObject2Type(Object targetObject2Type) {
        this.targetObject2Type = targetObject2Type;
    }

    public AdminLogEntry withTargetObject2Type(Object targetObject2Type) {
        this.targetObject2Type = targetObject2Type;
        return this;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("eventId", eventId).append("eventLogDate", eventLogDate).append("eventType", eventType).append("serverURL", serverURL).append("serverIPAddress", serverIPAddress).append("application", application).append("customerId", customerId).append("customerName", customerName).append("sourceIPAddress", sourceIPAddress).append("adminUserName", adminUserName).append("adminUserRole", adminUserRole).append("activityKey", activityKey).append("activityCode", activityCode).append("result", result).append("reasonKey", reasonKey).append("message", message).append("requiresPublish", requiresPublish).append("targetObject1Id", targetObject1Id).append("targetObject1Name", targetObject1Name).append("targetObject1Type", targetObject1Type).append("targetObject2Id", targetObject2Id).append("targetObject2Name", targetObject2Name).append("targetObject2Type", targetObject2Type).toString();
    }
    
    public String toCSVString() {
        return eventLogDate+","+
                customerName+","+
                eventType+","+
                application+","+
                adminUserName+","+
                adminUserRole+","+
                activityKey+","+
                activityCode+","+
                result+","+
                reasonKey+","+
                message+","+
                targetObject1Name+","+
                targetObject1Type+","+
                targetObject2Id+","+
                targetObject2Name+"\n";
    }

    /**
     * @return the tenantId
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * @param tenantId the tenantId to set
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

}
