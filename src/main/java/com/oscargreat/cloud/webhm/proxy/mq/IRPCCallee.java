package com.oscargreat.cloud.webhm.proxy.mq;

public interface IRPCCallee {
	void remotedCalled(MQClient client, CallbackContext context);
}
