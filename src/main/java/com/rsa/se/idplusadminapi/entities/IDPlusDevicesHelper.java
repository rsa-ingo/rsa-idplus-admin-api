
package com.rsa.se.idplusadminapi.entities;

import java.util.List;
import lombok.Data;

/**
 * DTO for a ID Plus CAS Authenticators. Three lists are members: for app(s), for FIDO Token(s) and for SID Token(s)
 * @author ischubert
 */
@Data
public class IDPlusDevicesHelper {
    List<IDPlusAuthenticator> devices;
    List<IDPlusAuthenticator> fidoTokens;
    List<IDPlusAuthenticator> sidTokens;
}
