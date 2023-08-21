/*
package com.couponproject.CouponManagmentSystem.Login;

import com.couponproject.CouponManagmentSystem.service.ClientServices;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class LoginManager {

    private ClientServices adminService;
    private ClientServices companyService;
    private ClientServices customerServices;

    */
/*private LoginManager() {
    dailyJob = new CouponExpirationDailyJob();
        thread = new Thread(dailyJob);
        thread.start();

    }*//*

    public ClientServices login(String email, String password, ClientType clientType){
        ClientServices returnedClient= null;
        switch (clientType)   {
            case Administrator:
                returnedClient = adminService;
                break;
            case Company:
                returnedClient=companyService;
                break;
            case Customer:
                returnedClient=customerServices;
                break;
            default:
                return null;
        }
        if(returnedClient.login(email, password)) {
            return returnedClient;
        }
        return null;
    }

}
*/
