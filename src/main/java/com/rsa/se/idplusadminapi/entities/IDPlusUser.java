
package com.rsa.se.idplusadminapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * DTO for a ID Plus CAS for User information. 
 * @author ischubert
 */
@Data 
@JsonIgnoreProperties(ignoreUnknown = true)
public class IDPlusUser {
    String id;
    String emailAddress;
    String firstName;
    String lastName;
    String creationDate;
    String identitySource;
    String userStatus;
    String markDeleted;
    String highRiskUser;
    String markDeletedAt;
    String markDeletedBy;
    String smsNumber;
    String voiceNumber;
    String isTokenLocked;
    String isFingerprintLocked;
    String isApproveLocked;
    String emergencyTokencodeOneTimeUse;
    String principalUsername;
    String alternateUsername;
    String primaryUniqueIdentifier;
    String secondaryUniqueIdentifier;
    String isSmsLocked;
    String isVoiceLocked;
    String lastSyncTime;
    String monthLastAuthenticated;
    String emergencyAccessStatus;
    String emergencyTokencodeId;
    String emergencyTokencodeExpiration;
    String emergencyTokencodeLastUse;
    String offlineEmergencyTokencodeExpiration;
    String offlineEmergencyAccessStatus;
    String lastSuccessfulAuthenticationMethod;
    String lastSuccessfulAuthenticationDate;
    
    String []identitySourceSpecificGroups;
    String []globalGroups;
}
