package com.jzx.net.netsetting;

import android.content.Context;

/**
 * Function:
 * Created by TianMing.Xiong on 18-4-9.
 */

public class RefEthernet {
    public static final String ETHER_MANAGER_NAME="android.net.EthernetManager";
    public static final String IPCONFIG__NAME="android.net.IpConfiguration";
    private  Class<?> ethernetManagerClass=null;
    private  Class<?> ipConfigurationClass=null;

    public RefEthernet() {
        try {
            ethernetManagerClass = Class.forName(ETHER_MANAGER_NAME);
            ipConfigurationClass = Class.forName(IPCONFIG__NAME);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

/**
 * 使能静态ip
 */
public void enable_static_ip(){
    try {
        ethernetManagerClass.getConstructor(Context.class,null);
    } catch (NoSuchMethodException e) {
        e.printStackTrace();
    }
}
}
