
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;

/**
 *
 * @author ischubert
 */
@Data
public class IDPlusLiveVerify {
    String adminUsername;
    String sessionExpiration;
    String userEmail;
    String userId;
    String verifyUrl;
    String verifyStatus="409";
    String status;
}
