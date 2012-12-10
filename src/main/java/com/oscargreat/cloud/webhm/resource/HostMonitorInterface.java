package com.oscargreat.cloud.webhm.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.oscargreat.cloud.webhm.proxy.HMSProxyFactory;
import com.oscargreat.cloud.webhm.proxy.HostMonitoringSystemProxy;
import com.oscargreat.cloud.webhm.proxy.model.CheckReply;

/***
 * Restful API Interface for end user
 * 
 * @author Oscar
 * 
 */

@Path("/hostMonitorAPI")
public class HostMonitorInterface {


	@POST
	@Path("startQuery")
	@Consumes({ MediaType.APPLICATION_JSON, "application/json" })
	@Produces({ MediaType.APPLICATION_JSON, "application/json" })
	public HostQueryResponse postStartQuery(HostQuery data) {
		String format = data.getFormat();
		List<String> hosts = data.getHosts();
		HostMonitoringSystemProxy proxy = getProxy();
		return startQueryViaProxy(proxy, format, hosts);
	}

	@GET
	@Path("startQuery")
	@Produces({ MediaType.APPLICATION_JSON, "application/json" })
	public HostQueryResponse getStartQuery(@QueryParam("format") String format,
			@QueryParam("hosts") String hostsInLine) {
		if (hostsInLine == null)
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		HostMonitoringSystemProxy proxy = getProxy();
		List<String> hosts = Arrays.asList(hostsInLine.split(";"));
		return startQueryViaProxy(proxy, format, hosts);
	}

	/***
	 * Here we put a proxy singleton in session to reduce overhead, which means the call from same single browser
	 * will be synchronized 
	 */
	private HostMonitoringSystemProxy getProxy() {
			
		return HMSProxyFactory.getProxy();
	}

	private HostQueryResponse startQueryViaProxy(
			HostMonitoringSystemProxy proxy, String format, List<String> hosts)
			throws WebApplicationException {
		try {
			List<String> hostURLs = new ArrayList<String>(hosts.size());
			for (String host : hosts) {
				hostURLs.add(format.replace("{node}", host));
			}

			int ret = proxy.startQuery(hostURLs);
			return new HostQueryResponse(ret != 0, ret);
		} catch (Exception e) {
			throw new WebApplicationException(Response.Status.BAD_REQUEST);
		}
	}

	@GET
	@Path("checkProgress/{queryID}")
	@Produces({ MediaType.APPLICATION_JSON, "application/json" })
	public CheckReply checkProgress(@PathParam("queryID") int queryID) {
		HostMonitoringSystemProxy hmsp = HMSProxyFactory.getProxy();
		return hmsp.checkProgress(queryID);
	}
	
	@GET
	@Path("metric/{id}")
	@Produces("text/plain")
	public String metricMocker(@PathParam("id") int id){
		return String.format("{\"cpu\": {\"core1\": \"80%%\",\"core2\": \"33%%\"},\"mem\":"
				+"{\"used\": \"1234M\",\"free\": \"%sM\"}}",id);
	}
}