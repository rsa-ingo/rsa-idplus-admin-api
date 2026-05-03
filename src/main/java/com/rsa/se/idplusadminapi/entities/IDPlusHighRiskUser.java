
package com.rsa.se.idplusadminapi.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO for a ID Plus CAS High Risk Users. Includes helpers to get anonymised IDs. 
 * @author ischubert
 */
@Data
@NoArgsConstructor
public class IDPlusHighRiskUser {
    String email;
    String primaryUsername;
    String alternateUsername;
    String id;
    String statusCode;
    String error;
    
    /**
     *
     * @param user
     */
    public IDPlusHighRiskUser(IDPlusUser user){
        this.email=user.getEmailAddress();
        this.id=user.getEmailAddress();
    }
    
    /**
     * Returns an anonymised primary username (e.g. email). This is to mask the user IDs for a demo application or for the helpdesk
     * @return the anonymised primary username
     */
    public String getAnonymisedPrimaryUsername() {
        return this.getAnonymisedString(primaryUsername);
    }
    
    /**
     * Returns an anonymised alternate username (e.g. samaccountname). This is to mask the user IDs for a demo application or for the helpdesk
     * @return the anonymised alternate usernmae
     */
    public String getAnonymisedAlternateUsername() {
        return this.getAnonymisedString(primaryUsername);
    }
    
    /**
     * Returns an anonymised primary username (e.g. email). This is to mask the user IDs for a demo application or for the helpdesk
     * @return the anonymised id
     */
    public String getAnonymisedId() {
        return this.getAnonymisedString(id);
    }
    
    /**
     * Returns an anonymised email. This is to mask the user IDs for a demo application or for the helpdesk
     * @return the anonymised email
     */
    public String getAnonymisedEmail() {
        return this.getAnonymisedString(email);
    }
    

    private String getAnonymisedString(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 3; i < sb.length() && sb.charAt(i) != '@'; ++i) {
            sb.setCharAt(i, '*');
        }
        
        if (sb.indexOf("@") != -1) {
            for (int i = sb.indexOf("@") + 3; i < sb.length() && sb.charAt(i) != '.'; ++i) {
                sb.setCharAt(i, '*');
            }
        }
        return sb.toString();
    }
    
    /**
     * returns Hashcode of the email 
     * @return the hash
     */
    @Override 
    public int hashCode() {
        return this.getEmail()!= null ? this.getEmail().hashCode() : 0;
    }
    
    /**
     * Equals implementation. Takes only email into account.
     * @param o the object to compare
     * @return true if equal, false if not
     */
    @Override
    public boolean equals(Object o) {

        IDPlusHighRiskUser hru = (IDPlusHighRiskUser) o;
        if (this.email.equals(hru.getEmail())) {
            return true;
        }

        return false;
    }
}
