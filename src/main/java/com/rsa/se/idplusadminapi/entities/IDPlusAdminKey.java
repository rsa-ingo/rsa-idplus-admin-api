
package com.rsa.se.idplusadminapi.entities;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * DTO for a ID Plus CAS Admin Key
 * @author ischubert
 */
@Data
@NoArgsConstructor
public class IDPlusAdminKey {
    private String customerName;
    private String accessKey;
    private String adminRestApiUrl;
    private String description;
    private String accessID;
    
}
