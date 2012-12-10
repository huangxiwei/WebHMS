package com.oscargreat.cloud.webhm.proxy;

/***
 * This class create the proxy. Of course this hard coding is not the right way
 * however we take it here for simplicity. 
 * TODO: Use something such as Spring instead  
 * @author Oscar
 *
 */
public class HMSProxyFactory {
	static public HostMonitoringSystemProxy getProxy(){
		return new StormHMSImpl();
	}
}
