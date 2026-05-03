
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 * DTO for a ID Plus CAS Verify (aka Enroll and later Password reset) codes.
 * @author ischubert
 */
@Data
public class IDPlusVerifyCodeResult {
    Integer status;
    String errorMessage;
    IDPlusDetailsRequestVerifyCodeGeneration userDetailsRequestForVerifyCodeGeneration;
    String verify_code;
    String verify_code_validity_time;
    String verify_code_generation_mode;
    String verification_Link; 
}
