package com.oscargreat.cloud.webhm.resource;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HostQueryResponse {
	private boolean succeeded;
	private int requestID;

	public HostQueryResponse() {
		succeeded = false;
		requestID = 0;
	}
	
	public HostQueryResponse(boolean succeeded, int requestID) {
		super();
		this.succeeded = succeeded;
		this.requestID = requestID;
	}

	public int getRequestID() {
		return requestID;
	}

	public void setRequestID(int requestID) {
		this.requestID = requestID;
	}

	public boolean isSucceeded() {
		return succeeded;
	}

	public void setSucceeded(boolean succeeded) {
		this.succeeded = succeeded;
	}
	
}
