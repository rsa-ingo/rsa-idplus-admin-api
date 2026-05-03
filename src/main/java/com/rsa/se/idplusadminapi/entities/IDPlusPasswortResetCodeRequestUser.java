
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 *
 * @author ischubert
 */
@Data
public class IDPlusPasswortResetCodeRequestUser {
    public String email;
    public String custom_email;
    public String code_validity;
    public String validity_time_duration_unit;
    public String code_send_to;
}
