
package com.rsa.se.idplusadminapi.entities;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;

/**
 * DTO for a ID Plus CAS. Helper that contains a list of IDPlusHighRiskUser Objects.
 * @author ischubert
 */
@Data
public class IDPlusHighRiskUserHelper {
    List<IDPlusHighRiskUser> users=new ArrayList<>();
}
