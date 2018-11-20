package com.ranxx.conf;

import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.net.URISyntaxException;

@Component
@ConfigurationProperties
public class MySiteSetting {

	@Value("${com.yjh.site.host}")
	private String host;
	@Value("${com.yjh.site.staticHost}")
	private String staticHost;
	@Value("${com.yjh.site.whiteDomainList}")
	private String whiteDomainList;
	@Value("${com.yjh.site.siteDirPath}")
	private String siteDirPath;
	@Value("${com.yjh.site.uploadDir}")
	private String uploadDir;
	@Value("${com.yjh.site.session}")
	private String sessionName;
	@Value("${com.yjh.site.prefix}")
	private String prefix;
	@Value("${com.yjh.site.fractionA}")
	private Integer fractionA;
	@Value("${com.yjh.site.coverCharge}")
	private Double coverCharge;
	@Value("${com.yjh.site.fractionB}")
	private int fractionB;
	@Value("${com.yjh.site.fractionC}")
	private int fractionC;
	
	@Value("${com.yjh.wx.mchId}")
	private String mchId;
	@Value("${com.yjh.wx.mchKey}")
	private String mchKey;
	@Value("${com.yjh.wx.appId}")
	private String myAppId;
	@Value("${com.yjh.wx.appSecret}")
	private String appSecret;
	@Value("${com.yjh.wx.notifyUrl}")
	private String notifyUrl;
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

	public String getMyAppId() {
		return myAppId;
	}

	public void setMyAppId(String myAppId) {
		this.myAppId = myAppId;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public String getUploadDir() {

		return uploadDir;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getStaticHost() {
		return staticHost;
	}

	public void setStaticHost(String staticHost) {
		this.staticHost = staticHost;
	}

	public String getWhiteDomainList() {
		return whiteDomainList;
	}

	public void setWhiteDomainList(String whiteDomainList) {
		this.whiteDomainList = whiteDomainList;
	}

	public String getSiteDirPath() throws URISyntaxException {

		if (StringUtils.isNullOrEmpty(siteDirPath)) {
			siteDirPath = Thread.currentThread().getContextClassLoader().getResource("").toURI().getPath();
		}

		return siteDirPath;
	}

	public void setSiteDirPath(String siteDirPath) {
		this.siteDirPath = siteDirPath;
	}

	/**
	 * 返回上传文件夹路径
	 * 
	 * @param absolute
	 *            是否绝对路径
	 * @return
	 * @throws URISyntaxException
	 */
	public String getUploadDir(boolean absolute) throws URISyntaxException {
		if (StringUtils.isNullOrEmpty(uploadDir)) {
			return uploadDir;
		}

		if (absolute) {
			return this.getSiteDirPath() + uploadDir;
		} else {
			return uploadDir;
		}
	}

	public void setUploadDir(String uploadDir) {
		this.uploadDir = uploadDir;
	}

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public Integer getFractionA() {
		return fractionA;
	}

	public void setFractionA(Integer fractionA) {
		this.fractionA = fractionA;
	}

	public Double getCoverCharge() {
		return coverCharge;
	}

	public void setCoverCharge(Double coverCharge) {
		this.coverCharge = coverCharge;
	}

	public int getFractionB() {
		return fractionB;
	}

	public void setFractionB(int fractionB) {
		this.fractionB = fractionB;
	}

	public int getFractionC() {
		return fractionC;
	}

	public void setFractionC(int fractionC) {
		this.fractionC = fractionC;
	}
}
