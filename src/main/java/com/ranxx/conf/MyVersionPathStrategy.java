package com.ranxx.conf;

import org.springframework.core.io.Resource;
import org.springframework.util.DigestUtils;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.resource.AbstractVersionStrategy;
import org.springframework.web.servlet.resource.VersionPathStrategy;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 自定义静态文件版本路径格式
 * 
 * File name-based {@code VersionPathStrategy}, e.g.
 * {@code "path/foo.css?version}"}.
 */
public class MyVersionPathStrategy extends AbstractVersionStrategy {

	public MyVersionPathStrategy() {
		super(new MyFileNameVersionPathStrategy());
	}

	public String getResourceVersion(Resource resource) {
		try {
			byte[] content = FileCopyUtils.copyToByteArray(resource
					.getInputStream());
			return DigestUtils.md5DigestAsHex(content);
		} catch (IOException ex) {
			throw new IllegalStateException("Failed to calculate hash for "
					+ resource, ex);
		}
	}

	/**
	 * File name-based {@code VersionPathStrategy}, e.g.
	 * {@code "path/foo.css? version}"}.
	 */
	protected static class MyFileNameVersionPathStrategy implements
			VersionPathStrategy {

		private static final Pattern pattern = Pattern.compile("\\?(\\S*)");

		public String extractVersion(String requestPath) {
			Matcher matcher = pattern.matcher(requestPath);
			if (matcher.find()) {
				String match = matcher.group(1);
				return (match.contains("?") ? match.substring(match
						.lastIndexOf("?") + 1) : match);
			} else {
				return null;
			}
		}

		public String removeVersion(String requestPath, String version) {
			return StringUtils.delete(requestPath, "?" + version);
		}

		public String addVersion(String requestPath, String version) {
			String baseFilename = StringUtils
					.stripFilenameExtension(requestPath);
			String extension = StringUtils.getFilenameExtension(requestPath);
			return (baseFilename + "." + extension + "?" + version);
		}
	}
}
