package com.rsa.se.idplusadminapi;

import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import org.bouncycastle.util.io.pem.PemObject;
import org.bouncycastle.util.io.pem.PemReader;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.AsymmetricJWK;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.KeyType;
import com.nimbusds.oauth2.sdk.ClientCredentialsGrant;
import com.nimbusds.oauth2.sdk.Scope;
import com.nimbusds.oauth2.sdk.TokenRequest;
import com.nimbusds.oauth2.sdk.TokenResponse;
import com.nimbusds.oauth2.sdk.auth.ClientAuthentication;
import com.nimbusds.oauth2.sdk.auth.PrivateKeyJWT;
import com.nimbusds.oauth2.sdk.id.ClientID;
import com.rsa.se.idplusadminapi.entities.*;
import com.rsa.se.idplusadminapi.logs.entities.AdminLogEntry;
import com.rsa.se.idplusadminapi.logs.entities.AdminLogQueryResult;
import com.rsa.se.idplusadminapi.logs.entities.UserLogEntry;
import com.rsa.se.idplusadminapi.logs.entities.UserLogQueryResult;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Java Library for the RSA ID Plus Admin REST API Instantiate an object with
 * the Admin Key (or set admin key separately) and simply call the appropriate
 * methods.
 *
 * @author Ingo Schubert
 */
public class IDPlusAdminAPI {

    private IDPlusAdminKey adminKey;
    private RSAPrivateKey privateKey;

    private ClientID clientId;
    private String issuerUrl;
    private JWK keyPair;
    private String adminAPIUrl;
    
    private String accessToken=null;
    
    private String[] permissionsOLD={"rsa.user.risky.read","rsa.user.risky.manage","rsa.user.read","rsa.authenticator.mobile.read", 
        "rsa.authenticator.mobile.manage","rsa.authenticator.device.delete","rsa.authenticator.device.manage",
        "rsa.user.factor.manage","rsa.authenticator.emergency.manage","rsa.authenticator.emergency.manage",
        "rsa.authenticator.emergency.manage","rsa.user.sync","rsa.audit.admin"};
    
    
    public static  String[] permissions={"￼rsa.audit.admin","rsa.audit.system","rsa.audit.user",
                                    "rsa.authenticator.ds100.delete","rsa.authenticator.ds100.manage","rsa.authenticator.ds100.read",
                                    "rsa.authenticator.emergency.manage",
                                    "rsa.authenticator.fido.delete","rsa.authenticator.fido.manage","rsa.authenticator.fido.read",
                                    "rsa.authenticator.mobile.delete","rsa.authenticator.mobile.manage","rsa.authenticator.mobile.read",
                                    "rsa.authenticator.sidtoken.delete","rsa.authenticator.sidtoken.manage","rsa.authenticator.sidtoken.read",
                                    "rsa.group.manage","rsa.group.read","rsa.group.users.manage","rsa.group.users.read",
                                    "rsa.report.health","rsa.report.license.usage","rsa.report.read","rsa.report.user.risky",
                                    "rsa.user.delete","rsa.user.delete.soft","rsa.user.factor.manage","rsa.user.local.manage",
                                    "rsa.user.manage","rsa.user.read","rsa.user.risky.manage","rsa.user.risky.read","rsa.user.sync",
                                    "rsa.user.verify"  };
                                    

    public static final String REPORT_USERS="users";
    public static final String REPORT_HARDWARE_TOKENS="hardware_tokens";
    public static final String REPORT_MFA_CLIENTS="mfa_clients";
    
    public static final String EXPIRATION_HOURS="hours";
    public static final String EXPIRATION_MINUTES="minutes";
    public static final String EXPIRATION_DAYS="days";      
         
    /**
     * Constructor with an ID Plus Admin Key JSON string as init parameter
     *
     * This is the "old" JWT integration type
     *
     * @param adminKey the ID Plus CAS Admin Key
     */
    public IDPlusAdminAPI(IDPlusAdminKey adminKey) {
        this.adminKey = adminKey;
        prepareKey();
    }

    /**
     * Constructor with an ID Plus Admin Key JSON string as init parameter
     *
     * This is the new Oauth integration type
     *
     * @param adminKey the ID Plus CAS Admin Key JSON string ("{...}")
     */
    public IDPlusAdminAPI(String clientId, String issuerUrl, String adminKey, String adminAPIUrl) {
        this.clientId = new ClientID(clientId);

        try {
            this.keyPair = JWK.parse(adminKey);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        this.issuerUrl = issuerUrl;
        this.adminAPIUrl=adminAPIUrl;
    }
    
    /**
     * Constructor with an ID Plus Admin Key JSON string as init parameter
     *
     * This is the new Oauth integration type, ability to set custom permissions
     *
     * @param adminKey the ID Plus CAS Admin Key JSON string ("{...}")
     */
    public IDPlusAdminAPI(String clientId, String issuerUrl, String adminKey, String adminAPIUrl, String[] permissions) {
        this.clientId = new ClientID(clientId);

        try {
            this.keyPair = JWK.parse(adminKey);
        } catch (Exception e) {
            System.out.println(e.toString());
        }

        this.issuerUrl = issuerUrl;
        this.adminAPIUrl=adminAPIUrl;
        this.permissions=permissions;
    }

    /**
     *
     * /**
     * NoArgs Constructor
     *
     * Do not forget to call setAdminKey() to set the admin key...
     */
    public IDPlusAdminAPI() {

    }

    /**
     * Helper to set the ID Plus Admin Key if the default constructor has been
     * called instead of the one with JSON string as init parameter
     *
     * @param adminKey the ID Plus CAS Admin Key JSON string ("{...}")
     * @return boolean true if key loaded successfully, false if something went
     * wrong
     */
    public boolean setAdminKey(IDPlusAdminKey adminKey) {
        this.adminKey = adminKey;
        return prepareKey();
    }

    private boolean prepareKey() {
        //takes the raw key we have and turns it into something bouncycastle can use.
        try {
            //Read the PEM encoded PKCS1 private key
            //Need to use Bouncy Castle as native Java can't do that
            PemObject privateKeyObject;
            PemReader pemReader = new PemReader(
                    new InputStreamReader(
                            new ByteArrayInputStream(adminKey.getAccessKey().getBytes())));
            privateKeyObject = pemReader.readPemObject();
            org.bouncycastle.asn1.pkcs.RSAPrivateKey rsa = org.bouncycastle.asn1.pkcs.RSAPrivateKey.getInstance(privateKeyObject.getContent());
            RSAPrivateKeySpec spec = new RSAPrivateKeySpec(rsa.getModulus(), rsa.getPrivateExponent());
            KeyFactory kf = KeyFactory.getInstance("RSA");

            //Finally.... we get the key! In a format that plain java (and the JWT lib) understands
            privateKey = (RSAPrivateKey) kf.generatePrivate(spec);

            return true;

        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    private String createAccessToken() {
        return createAccessToken(null);
    }

    private String createAccessToken(String[] permissions) {

        if (this.accessToken!=null){
            return accessToken;
        }
        
        //JWT Variant
        if (adminKey != null) {
            //Now for the JWT stuff... init the algorithm
            Algorithm algorithm = Algorithm.RSA256((RSAKey) privateKey);

            //Create the calendar(s) to determine start/end
            GregorianCalendar cal = new GregorianCalendar();
            long iat = cal.getTimeInMillis() / 1000;
            cal.add(Calendar.MINUTE, 58);
            long exp = cal.getTimeInMillis() / 1000;

            GregorianCalendar cal2 = new GregorianCalendar();

            cal2.add(Calendar.MINUTE, 58);

            //Create the JWT
            String token = JWT.create()
                    .withClaim("sub", adminKey.getAccessID())
                    .withClaim("aud", adminKey.getAdminRestApiUrl())
                    .withClaim("exp", cal2.getTime())
                    .withClaim("iat", cal.getTime())
                    .sign(algorithm);
            this.accessToken=token;
            return token;
        }

        //Oauth variant
        if (this.keyPair != null) {

            try {
                URI issuerUri = new URI(issuerUrl + "/token");

                ClientAuthentication clientAuthentication = null;

                if (!keyPair.isPrivate() /*|| key.getAlgorithm() == null*/ || keyPair.getKeyID() == null) {

                    throw new JOSEException("Unsupported signing private key: kty=" + keyPair.getKeyType() + " alg=" + keyPair.getAlgorithm() + " kid=" + keyPair.getKeyID() + " private=" + keyPair.isPrivate());

                }

                RSAPrivateKey privateKey;

                privateKey = (RSAPrivateKey) ((AsymmetricJWK) keyPair).toPrivateKey();

                JWSAlgorithm jwsAlgorithm;

                if (keyPair.getKeyType() == KeyType.RSA) {

                    jwsAlgorithm = JWSAlgorithm.RS256;

                } else if (keyPair.getKeyType() == KeyType.EC) {

                    jwsAlgorithm = JWSAlgorithm.ES256;

                } else {

                    throw new JOSEException("Unsupported signing key: kty=" + keyPair.getKeyType() + " alg=" + keyPair.getAlgorithm() + " kid=" + keyPair.getKeyID());

                }

                clientAuthentication = new PrivateKeyJWT(clientId, issuerUri, jwsAlgorithm, privateKey, keyPair.getKeyID(), null);

                TokenRequest tokenRequest;
                if (permissions != null) {
                    tokenRequest = new TokenRequest(issuerUri, clientAuthentication, new ClientCredentialsGrant(), new Scope(permissions));
                } else {
                    tokenRequest = new TokenRequest(issuerUri, clientAuthentication, new ClientCredentialsGrant());
                }

                TokenResponse tokenResponse = TokenResponse.parse(tokenRequest.toHTTPRequest().send());

                if (tokenResponse.indicatesSuccess()) {
                    this.accessToken=tokenResponse.toSuccessResponse().getTokens().getAccessToken().toString();
                    return this.accessToken;

                } else {

                    throw new RuntimeException("Failed to generate access token " + tokenResponse.toErrorResponse().getErrorObject().getHTTPStatusCode() + ", " + tokenResponse.toErrorResponse().getErrorObject().toString());

                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }

        }

        return "";
    }
    
    
    /**
     * Retrieve Details about a particular user
     *
     * @param email the email address of the user to be retrieved
     * @return IDPlusUser with all the details. IDPlusUser will be empty JSON if
     * something went wrong
     */
    public IDPlusUser userDetails(String email) {
        IDPlusUser user = new IDPlusUser();

        JSONObject bodyJson = new JSONObject();
        bodyJson.put("email", email);
        
         try {
        Client client = ClientBuilder.newClient();
        
        WebTarget userDetailsTarget = client.target(this.adminAPIUrl+"v1/users/lookup");
        
        user=userDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .post(Entity.json(bodyJson), IDPlusUser.class);
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return user;
    }
    


    /**
     * Search users
     *
     * @param emailLike the email address of the user to be retrieved
     * @return IDPlusUserSearchResult with all the found user objects plus page info. IDPlusUserSearchResult will be empty if
     * something went wrong
     */
    public IDPlusUserSearchResult userSearch(String emailLike, int pageSize, int pageNumber) {
        IDPlusUserSearchResult userSearchResult = new IDPlusUserSearchResult();

        JSONObject bodyJson = new JSONObject();
        bodyJson.put("emailLike", emailLike);
        
         try {
        Client client = ClientBuilder.newClient();
        
        WebTarget usersSearchTarget = client.target(this.adminAPIUrl+"v2/users/search?pageSize="+pageSize+"&pageNumber="+pageNumber);
        
        userSearchResult=usersSearchTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .post(Entity.json(bodyJson), IDPlusUserSearchResult.class);

        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userSearchResult;
    }
    
    

    /**
     * Retrieves Details about all authenticators for a particular user.
     *
     * @param userId is the ID of the IDPlusUser (not the eMail address!)
     * @return IDPlusUserDevicesHelper
     */
    public IDPlusDevicesHelper authenticatorDetails(String userId) {

        IDPlusDevicesHelper authenticators = new IDPlusDevicesHelper();
        
        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget authenticatorDetailsTarget = client.target(this.adminAPIUrl+"v2/users/" + userId + "/devices");
        
        authenticators=authenticatorDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .get(IDPlusDevicesHelper.class);
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return authenticators;
    }

    /**
     * Retrieves Details about all authenticators for a particular user.
     *
     * @param user the IDPlusUser this is about
     * @return IDPlusUserDevicesHelper
     */
    public IDPlusDevicesHelper authenticatorDetails(IDPlusUser user) {
        return this.authenticatorDetails(user.getId());
    }

    /**
     * Deletes a particular authenticator device from a user.
     *
     * @param userId the userID from an IDPlusUser.
     * @param deviceId the deviceId from a IDPlusAuthenticator
     * @return true if deletion successful. Other errors see ID Plus Admin API
     * documentation
     */
    public Boolean deleteUserDevice(String userId, String deviceId) {

        try {
            Client client = ClientBuilder.newClient();

            WebTarget deleteUserDeviceTarget = client.target(this.adminAPIUrl + "v1/users/" + userId + "/devices/" + deviceId);

            var res = deleteUserDeviceTarget.request()
                    .header("Authorization", "Bearer " + createAccessToken(permissions))
                    .header("Accept",MediaType.APPLICATION_JSON)
                .header("Content-type",MediaType.APPLICATION_JSON)
                    .delete();
            if (res.getStatus() == 200) {
                return true;
            } else{
                return false;
            }
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    /**
     * Deletes a particular authenticator device from a user.
     *
     * @param user the IDPlusUser.
     * @param device the IDPlusAuthenticator
     * @return true if deletion successful. Other errors see ID Plus Admin API
     * documentation
     */
    public Boolean deleteUserDevice(IDPlusUser user, IDPlusAuthenticator device) {
        return this.deleteUserDevice(user.getId(), device.getId());
    }

    /**
     * Retrieves an registration code for a particular user/Email to register
     * the RSA Authenticator app. The code can also be used to create a QR Code.
     *
     * @param email the user email address. The registration code returned will
     * be for this user.
     * @return IDPlusAuthenticatorRegistrationCode with all relevant information
     * or empty object.
     */
    public IDPlusAuthenticatorRegistrationCode retrieveRegistrationCode(String email) {
        
        IDPlusAuthenticatorRegistrationCode regCode = new IDPlusAuthenticatorRegistrationCode();

        JSONObject bodyJson = new JSONObject();
        bodyJson.put("email", email);
        bodyJson.put("appId", "1f00c62b-a5c0-49d3-9ffb-92314d717187");
        
         try {
        Client client = ClientBuilder.newClient();
        
        WebTarget userDetailsTarget = client.target(this.adminAPIUrl+"v1/users/deviceRegistrationCode");
        
        regCode=userDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .post(Entity.json(bodyJson), IDPlusAuthenticatorRegistrationCode.class);
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return regCode;
       
    }

    /**
     * Retrieves an enrollment code for a particular user.
     *
     * @param email the email address of the user the enrollment code should be
     * fore
     * @param customEmail a custom email address that the code will be emailed
     * to. This does not have to be the same email address. Could be e.g. a
     * private email address or the email address of a colleague.
     * @param validity the validity period. Unit depends on the timeUnit
     * parameter. Max 24h, Min 10 Minutes.
     * @param timeUnit the time unit for the validity. Either MIN for minutes or
     * HOUR for... hours.
     * @param delivery the type of delivery. EMAIL to send the code to
     * customEmail, DISPLAY to simply return it
     * @return IDPlusVerifyCodeResult with all relevant information or empty
     * object
     */
    public IDPlusVerifyCodeResult getVerifyCodeEnroll(String email, String customEmail, String validity, String timeUnit, String delivery) {
        IDPlusVerifyCodeResult verifyCodeResult = new IDPlusVerifyCodeResult();

        List<IDPlusDetailsRequestVerifyCodeGeneration> requestDetails = new ArrayList<>();
        IDPlusDetailsRequestVerifyCodeGeneration requestDetail = new IDPlusDetailsRequestVerifyCodeGeneration();

        requestDetail.setEmail(email);
        requestDetail.setCustom_email(customEmail);
        requestDetail.setCode_validity(validity);
        requestDetail.setValidity_time_duration_unit(timeUnit);
        requestDetail.setCode_send_to(delivery);

        requestDetails.add(requestDetail);

        
        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget userDetailsTarget = client.target(this.adminAPIUrl+"v1/users/generateVerifyCode/enroll");
        
        var res=userDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .post(Entity.json(requestDetails));
        

        IDPlusVerifyCodeResult[] resultsArray =res.readEntity(IDPlusVerifyCodeResult[].class);
        if (resultsArray.length>0){
        verifyCodeResult = resultsArray[0];
        }
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return verifyCodeResult;
    }

    //Defaults to 10 minutes validity
    /**
     * Retrieves an enrollment code for a particular user. Defaults to 20
     * minutes validity.
     *
     * @param email the email address of the user the enrollment code should be
     * fore
     * @param customEmail a custom email address that the code will be emailed
     * to. This does not have to be the same email address. Could be e.g. a
     * private email address or the email address of a colleague.
     * @param delivery the type of delivery. EMAIL to send the code to
     * customEmail, DISPLAY to simply return it
     * @return IDPlusVerifyCodeResult with all relevant info or empty object.
     */
    public IDPlusVerifyCodeResult getVerifyCodeEnroll(String email, String customEmail, String delivery) {
        return this.getVerifyCodeEnroll(email, customEmail, "20", "MIN", delivery);
    }

    /**
     * Retrieve an Emergency Access code for a particular user.
     *
     * @param userId is the ID of the IDPlusUser (not the eMail address!)
     * @param expirationUnit unit one of days, hours, minutes
     * @param expirationValue value of expiration units
     * @param oneTime if true, EAC is valid for one time only. Otherwise until expired
     * @return IDPlusEAC with all relevant information or null if error.
     */
    public IDPlusEAC getEAC(String userId, String expirationUnit, Integer expirationValue, Boolean oneTime) {
        IDPlusEAC eac = new IDPlusEAC();
        JSONObject bodyJson = new JSONObject();

        if (expirationUnit!=null){
           bodyJson.put("expiryUnit", expirationUnit);
           bodyJson.put("expiryValue", expirationValue);
        }
        
        if (oneTime!=null){
            bodyJson.put("oneTimeUse", oneTime);
        }

        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget authenticatorDetailsTarget = client.target(this.adminAPIUrl+"v2/users/" + userId + "/emergencyTokencode");
        
        eac=authenticatorDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .put(Entity.json(bodyJson),IDPlusEAC.class);
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
        return eac;
    }

    /**
     * Retrieve an Emergency Access code for a particular user.
     *
     * EAC will be one time use!
     * 
     * @param user the IDPlusUser
     * @param expiration number of days validity. Min. 1, max. 7
     * @return IDPlusEAC with all relevant information or empty object.
     */
    public IDPlusEAC getEAC(IDPlusUser user, Integer expiration) {
        return this.getEAC(user.getId(), EXPIRATION_DAYS,expiration,true);
    }
    
    /**
     * Retrieve an Emergency Access code for a particular user.
     *
     * EAC will be one time use!
     * 
     * @param userId the RSA IDPlus user ID 
     * @param expiration number of days validity. Min. 1, max. 7
     * @return IDPlusEAC with all relevant information or empty object.
     */
    public IDPlusEAC getEAC(String userId, Integer expiration) {
        return this.getEAC(userId, EXPIRATION_DAYS,expiration,true);
    }

    /**
     * Removes the Emergency Access Code from a user.
     *
     * @param userId is the ID of the IDPlusUser (not the eMail address!)
     * @param eacId the eacId that got returned from a getVerifyCodeEnroll() or
     * a userDetails() call.
     * @return true if successful deletion. Other errors see see ID Plus Admin
     * API documentation
     */
    public Boolean deleteEAC(String userId, String eacId) {

        
        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget deleteUserDeviceTarget = client.target(this.adminAPIUrl+"v1/users/" + userId + "/emergencyTokencode/" + eacId);
        
        var resp=deleteUserDeviceTarget.request()
                .header("Accept",MediaType.APPLICATION_JSON)
                .header("Content-type",MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .delete();
        
              
        return true;
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    /**
     * Removes the Emergency Access Code from a user.
     *
     * @param user is IDPlusUser
     * @param eacId the eacId that got returned from a getVerifyCodeEnroll() or
     * a userDetails() call.
     * @return true if successful deletion. Other errors see see ID Plus Admin
     * API documentation
     */
    public Boolean deleteEAC(IDPlusUser user, String eacId) {

        return this.deleteEAC(user.getId(), eacId);
    }

    /**
     * Removes the Emergency Access Code from a user.
     *
     * @param user is IDPlusUser
     * @param eac the IDPlusEAC
     * @return true if successful deletion. Other errors see see ID Plus Admin
     * API documentation
     */
    public Boolean deleteEAC(IDPlusUser user, IDPlusEAC eac) {

        return this.deleteEAC(user, eac);
    }

    /**
     * Returns a list of IDPlusHighRiskUser which are on the High Risk User List
     *
     * @return List of IDPlusHighRiskUser objects.
     */
    public List<IDPlusHighRiskUser> retrieveHighRiskUsers() {

        IDPlusHighRiskUserHelper highRiskUsers = new IDPlusHighRiskUserHelper();
        
        
         try {
        Client client = ClientBuilder.newClient();
        
        WebTarget authenticatorDetailsTarget = client.target(this.adminAPIUrl+"v2/users/highrisk");
        
        highRiskUsers=authenticatorDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .get(IDPlusHighRiskUserHelper.class);
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return highRiskUsers.getUsers();
    }

    /**
     * Adds a particular user to the High Risk User List
     *
     * @param userId is the ID of the IDPlusUser (not the eMail address!)
     * @return true if deletion successful
     * objects
     */
    public Boolean addUserHighRisk(String userId) {
        return modifyUserHighRisk(userId, "add");
    }

    /**
     * Adds a particular user to the High Risk User List
     *
     * @param user is the IDPlusUser
     * @return true if deletion successful
     * objects
     */
    public Boolean addUserHighRisk(IDPlusUser user) {
        return this.modifyUserHighRisk(user.getId(), "add");
    }

    /**
     * Removes a user from the High Risk User List
     *
     * @param userId is the ID of the IDPlusUser (not the eMail address!)
     * @return true if deletion successful
     * objects
     */
    public Boolean removeUserHighRisk(String userId) {
        return modifyUserHighRisk(userId, "remove");
    }

    /**
     * Removes a user from the High Risk User List
     *
     * @param user is the IDPlusUser
     * @return true if deletion successful
     * objects
     */
    public Boolean removeUserHighRisk(IDPlusUser user) {
        return modifyUserHighRisk(user.getId(), "remove");
    }

    /**
     * Adds or removes the user from the High Risk User List
     *
     * @param userId is the ID of the IDPlusUser (not the eMail address!)
     * @param requestedAction "add" or "remove"
     * @return IDPlusHighRiskUserHelper which is a list of IDPlusHighRiskUser
     * objects
     */
    public Boolean modifyUserHighRisk(String userId, String requestedAction) {
        IDPlusHighRiskUserHelper userActionResult = new IDPlusHighRiskUserHelper();
        String action = "add";
        switch (requestedAction.toLowerCase().strip()) {
            case "remove":
                action = "remove";
                break;
            default:
                action = "add";
        }

        JSONObject requestBody = new JSONObject();

        requestBody.put("action", action);

        JSONArray users = new JSONArray();
        users.add(userId);

        requestBody.put("users", users);

        
        
          try {
        Client client = ClientBuilder.newClient();
        
        WebTarget authenticatorDetailsTarget = client.target(this.adminAPIUrl+"v1/users/highrisk");
        
//        userActionResult=authenticatorDetailsTarget.request()
//                .header("Authorization", "Bearer "+ createAccessToken(permissions))
//                .header("Accept",MediaType.APPLICATION_JSON)
//                .header("Content-type",MediaType.APPLICATION_JSON)
//                .put(Entity.json(requestBody),IDPlusHighRiskUserHelper.class);
        
         var res=authenticatorDetailsTarget.request()
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .header("Accept",MediaType.APPLICATION_JSON)
                .header("Content-type",MediaType.APPLICATION_JSON)
                .put(Entity.json(requestBody));
         
         if (res.getStatus()==200){
             return true;
         }
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }

    /**
     * Adds or removes the user from the High Risk User List
     *
     * @param user is the IDPlusUser
     * @param requestedAction "add" or "remove"
     * @return IDPlusHighRiskUserHelper which is a list of IDPlusHighRiskUser
     * objects
     */
    public Boolean modifyUserHighRisk(IDPlusUser user, String requestedAction) {
        return this.modifyUserHighRisk(user.getId(), requestedAction);
    }

    /**
     * Triggers a synchronisation of the user details from a identity
     * repository. Note: this is for this user only, not for the entire user
     * population.
     *
     * @param user IDPlusUser this synchronise should be for.
     * @return IDPlusUser with the information about the newly updated
     * IDPlusUser
     */
    public IDPlusUser synchronizeUser(IDPlusUser user) {

        IDPlusUser userUpdated = new IDPlusUser();
        
        
        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget userDetailsTarget = client.target(this.adminAPIUrl+"v1/users/" + user.getId() + "/sync");
        
        userUpdated=userDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .post(Entity.json(""), IDPlusUser.class);
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return userUpdated;
    }

    /**
     * Triggers a synchronisation of the user details from a identity
     * repository. Note: this is for this user only, not for the entire user
     * population.
     *
     * @param userId is the ID of the IDPlusUser (not the eMail address!)
     * @return IDPlusUser with the information about the newly updated
     * IDPlusUser
     */
    public IDPlusUser synchronizeUser(String userId) {
        IDPlusUser user = new IDPlusUser();
        user.setId(userId);
        return this.synchronizeUser(user);
    }
    
    
    /**
     * Creates password reset code
     *
     * @param user IDPlusUser this code should be for.
     * @return IDPlusUser with the information about the newly updated
     * IDPlusUser
     */
    public IDPlusPasswordResetCodeResponse getPasswordResetCode(IDPlusUser user) {

        IDPlusPasswordResetCodeResponse passwordResetCode = new IDPlusPasswordResetCodeResponse();
        IDPlusPasswortResetCodeRequestUser requestUser=new IDPlusPasswortResetCodeRequestUser();
        
        requestUser.setCode_validity("15");
        requestUser.setValidity_time_duration_unit("MIN");
        requestUser.setCode_send_to("DISPLAY");
        requestUser.setEmail(user.getEmailAddress());
        
        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget userDetailsTarget = client.target(this.adminAPIUrl+"v1/users/generateVerifyCode/resetPassword");
        
        passwordResetCode=userDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .post(Entity.json(requestUser), IDPlusPasswordResetCodeResponse.class);
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return passwordResetCode;
    }
    

    /**
     * Retrieves Admin Logs from CAS
     *
     * @param startTimeAfter Start time of log events
     * @param endTimeOnOrBefore End time of log events
     * @param pageNumber Zero-based index of the page to return
     * @param pageSize Number of records to return in a page (or batch). 1-100
     * @return AdminLogQueryResult which contains log entries
     */
    public List<AdminLogEntry> retrieveAdminLogs(LocalDateTime startTimeAfter, LocalDateTime endTimeOnOrBefore, Integer pageNumber, Integer pageSize) {
        Gson gson = new Gson();

        //Make sure there are reasonable defaults and within limits
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = 0;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = 100;
        }
        if (pageSize > 100) {
            pageSize = 100;
        }

        if (startTimeAfter == null) {
            startTimeAfter = LocalDateTime.now().minusDays(1);
        }
        if (endTimeOnOrBefore == null) {
            endTimeOnOrBefore = LocalDateTime.now();
        }

        ArrayList<AdminLogEntry> adminLogs = new ArrayList<>();

        try {
            AdminLogQueryResult rawLogs = this.getAdminLogs(startTimeAfter, endTimeOnOrBefore, pageNumber, pageSize);

            int pages = rawLogs.getTotalPages();

            //Go thru the first page
            for (AdminLogEntry aLogEntry : rawLogs.getAdminLogEntrys()) {
                adminLogs.add(aLogEntry);
            }

            //Retrieve the remaining pages and add them to the list.
            for (int i = 1; i < pages; i++) {
                rawLogs = this.getAdminLogs(startTimeAfter, endTimeOnOrBefore, i, pageSize);
                for (AdminLogEntry aLogEntry : rawLogs.getAdminLogEntrys()) {
                    adminLogs.add(aLogEntry);
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return adminLogs;
    }

    /**
     * Retrieves Admin Logs from CAS Last 24h max 100 logs returned
     *
     * @return AdminLogQueryResult which contains log entries
     */
    public List<AdminLogEntry> retrieveAdminLogs() {
        return this.retrieveAdminLogs(null, null, null, null);
    }

    private AdminLogQueryResult getAdminLogs(LocalDateTime startTimeAfter, LocalDateTime endTimeOnOrBefore, Integer pageNumber, Integer pageSize) throws IOException {

        AdminLogQueryResult result=new AdminLogQueryResult();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        
        
         try {
        Client client = ClientBuilder.newClient();
        
        WebTarget authenticatorDetailsTarget = client.target(this.adminAPIUrl+"v1/adminlog/exportlogs?pageSize="
                + pageSize
                + "&pageNumber="
                + pageNumber
                + "&startTimeAfter=" + URLEncoder.encode(startTimeAfter.atZone(ZoneId.systemDefault()).format(dtf), "UTF-8")
                + "&endTimeOnOrBefore=" + URLEncoder.encode(endTimeOnOrBefore.atZone(ZoneId.systemDefault()).format(dtf), "UTF-8"));
        
        result=authenticatorDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .get(AdminLogQueryResult.class);

        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        return result;

    }
    
    
     /**
     * Retrieve license usage.
     *
     * @return IDPlusLicenseUsage with all relevant information or empty object.
     */
    public IDPlusLicenseUsage[] getLicenseUsage() {
        IDPlusLicenseUsage[] licenseUsage={};

        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget licenseTarget = client.target(this.adminAPIUrl+"v2/licenseusage");
        
        licenseUsage=licenseTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .get(IDPlusLicenseUsage[].class);
        
        } catch (Exception ex) {
           Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return licenseUsage;
    }
    
    /**
     * Retrieve authentication logs usage for particular user.
     *
     * @return UserLogEntry[] with all relevant information or empty object.
     */
    public UserLogEntry[] getAuthenticationLogs(String userId) {
        UserLogEntry[] userAuthenticationEntries={};

        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget licenseTarget = client.target(this.adminAPIUrl+"v1/users/"+userId+"/authlogs/");
        
        userAuthenticationEntries=licenseTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .get(UserLogEntry[].class);
        
        } catch (Exception ex) {
           Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userAuthenticationEntries;
    }
    
    
    /**
     * Retrieve user event logs.
     *
     * @return UserLogEntry[] with all relevant information or empty object.
     */
    public List<UserLogEntry> getUserEventLogs() {
        List<UserLogEntry> userAuthenticationEntries=new ArrayList<>();
        UserLogQueryResult logResult;

        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget licenseTarget = client.target(this.adminAPIUrl+"v1/usereventlog/exportlogs");
        
        logResult=licenseTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .get(UserLogQueryResult.class);
        
        List<UserLogEntry> eventLogs=logResult.getUserEventLogExportEntries();
        
        return eventLogs;
        
        } catch (Exception ex) {
           Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return userAuthenticationEntries;
    }
    
     /**
     * Generate Report .
     * @param reportType one of "users", "hardware_tokens", "mfa_clients"
     *
     * @return Integer of response code.
     */
    public Integer generateReport(String reportType) {
        
        Integer returnCode=404;
        
        if (!(reportType.equals("users") || reportType.equals("hardware_tokens") || reportType.equals("mfa_clients"))){
            return returnCode;
        }
        
        

        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget licenseTarget = client.target(this.adminAPIUrl+"v1/report/"+reportType+"/generate");
        
        Response responseGenReport=licenseTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .post(Entity.json(""));
        returnCode=responseGenReport.getStatus();
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return returnCode;
    }
    
    /**
     * Download Report .
     * @param reportType one of "users", "hardware_tokens", "mfa_clients"
     *
     * @return String containing the CSV.
     */
    public String downloadReport(String reportType) {
        
        Integer returnCode=404;
        String csvContent="";
        
        if (!(reportType.equals("users") || reportType.equals("hardware_tokens") || reportType.equals("mfa_clients"))){
            return "";
        }
        
        //TODO: Download into CSV
        
        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget licenseTarget = client.target(this.adminAPIUrl+"v1/report/"+reportType+"/download");
        
        Response responseGenReport=licenseTarget.request(MediaType.APPLICATION_OCTET_STREAM)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .get();
        returnCode=responseGenReport.getStatus();
        
        if (returnCode==200){
            csvContent=responseGenReport.readEntity(String.class);
        }
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return csvContent;
    }
    
    /**
     * Creates Live Verify Session
     *
     * @param user IDPlusUser this LV session should be for.
     * @return IDPlusLiveVerify with the information about the new LV session. verifyUrl will be 409 if another session already in progress
     * 
     */
    public IDPlusLiveVerify liveVerifyInit(String userId) {

        IDPlusLiveVerify lvInit=new IDPlusLiveVerify();
        
        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget userDetailsTarget = client.target(this.adminAPIUrl+"v1/users/"+userId+"/verify/start");
        
        lvInit=userDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .post(Entity.json(""), IDPlusLiveVerify.class);
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lvInit;
    }
    
    
     /**
     * Retrieve Live Verify Status for a user.
     *
     * @param user IDPlusUser
     * @return IDPlusLicenseUsage with all relevant information or empty object.
     */
    public IDPlusLiveVerify liveVerifyStatus(String userId) {
        IDPlusLiveVerify lvStatus=new IDPlusLiveVerify();

        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget licenseTarget = client.target(this.adminAPIUrl+"v1/users/"+userId+"/verify/status");
        
        lvStatus=licenseTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .get(IDPlusLiveVerify.class);
        
        } catch (Exception ex) {
           Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return lvStatus;
    }
    
    /**
     * Verifies a Live Verify Code
     *
     * @param user IDPlusUser this LV session should be for.
     * @param verifyCode the code to verify
     * @return IDPlusLiveVerify with the information about the new LV session. verifyUrl will be 409 if another session already in progress
     * 
     */
    public IDPlusLiveVerify liveVerifyCode(String userId, String verifyCode) {

        IDPlusLiveVerify lvVerify=new IDPlusLiveVerify();
        JSONObject verifyCodeJSON = new JSONObject();
        
        verifyCodeJSON.put("verifyCode", verifyCode);
        
        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget userDetailsTarget = client.target(this.adminAPIUrl+"v1/users/"+userId+"/verify/code");
        
        lvVerify=userDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .post(Entity.json(verifyCodeJSON), IDPlusLiveVerify.class);
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lvVerify;
    }
    
    
    /**
     * Creates Live Verify Session
     *
     * @param user IDPlusUser this LV session should cancelled be for.
     * @return Integer with the information about the result of the REST Call 
     * 
     */
    public Integer liveVerifyCancel(String userId) {

        IDPlusLiveVerify lvInit=new IDPlusLiveVerify();
        
        try {
        Client client = ClientBuilder.newClient();
        
        WebTarget userDetailsTarget = client.target(this.adminAPIUrl+"v1/users/"+userId+"/verify/cancel");
        
        Response response=userDetailsTarget.request(MediaType.APPLICATION_JSON)
                .header("Authorization", "Bearer "+ createAccessToken(permissions))
                .post(Entity.json(""));
        return response.getStatus();
        
        
        } catch (Exception ex) {
            Logger.getLogger(IDPlusAdminAPI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 500;
    }

}
