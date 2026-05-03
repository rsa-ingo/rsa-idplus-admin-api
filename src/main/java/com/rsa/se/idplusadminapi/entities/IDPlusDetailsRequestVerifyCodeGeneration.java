
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 * DTO for a ID Plus CAS Enrollment code
 * @author ischubert
 */
@Data
public class IDPlusDetailsRequestVerifyCodeGeneration {
    String email;
    String custom_email;
    String code_validity;
    String validity_time_duration_unit;
    String code_send_to;      
    
    
}
