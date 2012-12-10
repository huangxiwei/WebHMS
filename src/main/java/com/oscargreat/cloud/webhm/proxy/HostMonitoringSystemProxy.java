package com.oscargreat.cloud.webhm.proxy;

import java.io.IOException;
import java.util.List;

import com.oscargreat.cloud.webhm.proxy.model.CheckReply;

/***
 * This interface represents the proxy to backend monitoring system 
 * @author Oscar
 *
 */
public interface HostMonitoringSystemProxy {
	public void init() throws IOException;
	public void close();
	/***
	 * start a new host information query
	 * @param hosts the URL list for info monitoring
	 * @return the id of this query, 0 if failed
	 */
	public int startQuery(List<String> hostURLs);
	/***
	 * Check the progress of given query
	 * @param queryID the queryID return by startQuery
	 * @param results the result of query if query is done
	 * @return progress of the given query, 0 - 100
	 */
	public CheckReply checkProgress(int queryID);
}
