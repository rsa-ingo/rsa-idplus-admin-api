
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 * DTO for a ID Plus CAS Authenticator Registration Code
 * @author ischubert
 */
@Data
public class IDPlusAuthenticatorRegistrationCode {
    String companyId;
    String deviceRegistrationCode;
    String email;
    String expirationDate;
    
    Integer timestamp;
    Integer status;
    String error;
    String message;
    String path;
}
