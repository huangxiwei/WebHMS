package com.oscargreat.cloud.webhm.proxy.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CheckReply {
	public CheckReply(int progress, HostInfo hostinfo) {
		super();
		this.progress = progress;
		this.hostinfo = hostinfo;
	}
	private int progress;
	private HostInfo hostinfo;
	public int getProgress() {
		return progress;
	}
	public void setProgress(int progress) {
		this.progress = progress;
	}
	public HostInfo getHostinfo() {
		return hostinfo;
	}
	public void setHostinfo(HostInfo hostinfo) {
		this.hostinfo = hostinfo;
	}
}
