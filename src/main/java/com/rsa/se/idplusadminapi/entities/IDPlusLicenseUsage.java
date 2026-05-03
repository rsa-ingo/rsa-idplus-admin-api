
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 * DTO for a ID Plus CAS Emergency Access Code
 * @author ischubert
 */
@Data
public class IDPlusLicenseUsage {
    Integer mfaLicensesUsed;
    Integer usersWithThirdPartyFidoAuthenticators;
    Integer smsAndVoiceTokencodesSent;
    Integer activeUsers;
    String lastUpdatedAt;
    String month;
}
