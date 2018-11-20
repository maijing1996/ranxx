package com.ranxx.conf;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

@Component
public class MyResourceUrlProvider extends ResourceUrlProvider {

	private String host;

	public String getHost() {
		if (host == null) {
			return "";
		}
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public final String getPath(String lookupPath) {
		return this.getHost() + this.getForLookupPath(lookupPath);
	}
}
