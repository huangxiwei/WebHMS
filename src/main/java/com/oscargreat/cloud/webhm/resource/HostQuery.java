package com.oscargreat.cloud.webhm.resource;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class HostQuery {
	private String format;
	private ArrayList<String> hosts;
	public HostQuery() {
		hosts = new ArrayList<String>();
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public ArrayList<String> getHosts() {
		return hosts;
	}
	public void setHosts(ArrayList<String> hosts) {
		this.hosts = hosts;
	}
	
}
