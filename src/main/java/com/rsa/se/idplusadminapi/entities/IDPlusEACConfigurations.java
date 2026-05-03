
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 * DTO for a ID Plus CAS Emergency Access Code Configurations
 * @author ischubert
 */
@Data
public class IDPlusEACConfigurations {
    String expiryValue;
    String expiryUnit;
    Boolean oneTimeUse;
}
