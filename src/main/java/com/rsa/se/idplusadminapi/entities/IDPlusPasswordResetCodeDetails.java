/*
 * Testware
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 *
 * @author ischubert
 */
@Data
public class IDPlusPasswordResetCodeDetails {
        public String email;
    public String code_validity;
    public String validity_time_duration_unit;
    public String code_send_to;
}
