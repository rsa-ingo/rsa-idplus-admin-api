
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 * DTO for a ID Plus CAS Authenticator. This includes all known authenticators: App, FIDO, DS100, SID700
 * @author ischubert
 */
@Data
public class IDPlusAuthenticator {
    String id;
    String name;
    String userId;
    String deviceType;
    String registeredDate;
    String capabilities;
    String browser;
    String tokenSerialNumber;
    String deviceSerialNumber;
    String updatedAt;
    String tokenState;
    String expiryDate;
    String tokenStatus;
    String assignedAt;
    String assignedBy;
    String pinSet;
    String tokenStatusChangedAt;
    String tokenStatusChangedBy;
    String status;
    String tokenStatusReason;
}
