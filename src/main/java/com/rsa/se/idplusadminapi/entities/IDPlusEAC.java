
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 * DTO for a ID Plus CAS Emergency Access Code
 * @author ischubert
 */
@Data
public class IDPlusEAC {
    String emergencyTokencodeId;
    String emergencyTokencode;
    String expirationDate;
    String offlineExpirationDate;
    IDPlusEACConfigurations configurationsUsed;
    String oneTimeUse;
    String configurationsLocked;
}
