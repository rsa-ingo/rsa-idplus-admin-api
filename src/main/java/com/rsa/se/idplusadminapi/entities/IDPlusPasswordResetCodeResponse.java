
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 *
 * @author ischubert
 */
@Data
public class IDPlusPasswordResetCodeResponse {
    public int status;
    public String errorMessage;
    public IDPlusPasswordResetCodeDetails userDetailsRequestForVerifyCodeGeneration;
    public String verify_code;
    public String verify_code_validity_time;
    public String verify_code_generation_mode;
    public String verification_Link;
}
