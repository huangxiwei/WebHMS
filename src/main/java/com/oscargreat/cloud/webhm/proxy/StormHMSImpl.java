package com.oscargreat.cloud.webhm.proxy;

import java.io.IOException;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

import com.oscargreat.cloud.webhm.proxy.model.CheckReply;
import com.oscargreat.cloud.webhm.proxy.mq.MQClient;
/***
 * Proxy to Monitoring System implemented on Twitter Storm
 * @author Oscar
 *
 */
public class StormHMSImpl implements HostMonitoringSystemProxy {
	private static final String HOSTQUERY_QUEUE = "query";
	private static final String CHECK_QUEUE = "check";
	private MQClient queryclient, checkclient; 
	
	
	
	public StormHMSImpl() {
		queryclient = new MQClient();
		
		checkclient = new MQClient();
		
	}
	
	@Override
	public void init() throws IOException {
		queryclient.connect(HOSTQUERY_QUEUE);
		checkclient.connect(CHECK_QUEUE);
	}
	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public int startQuery(List<String> hostURLs) {
		StringBuilder sb = new StringBuilder();
		for(String s: hostURLs)
			sb.append(s);
			sb.append(";");
		try {
			String ret = queryclient.syncCall(sb.toString());
			return Integer.parseInt(ret);
		} catch (Exception e) {//silent fail
			return 0;
		}
		
	}

	@Override
	public CheckReply checkProgress(int queryID) {
		try {
			String ret = checkclient.syncCall(String.valueOf(queryID));
			ObjectMapper map = new ObjectMapper();
			CheckReply remote_result = map.readValue(ret, CheckReply.class);
			return remote_result;
		} catch (Exception e) {//silent fail
			return null;
		}
	}


}
