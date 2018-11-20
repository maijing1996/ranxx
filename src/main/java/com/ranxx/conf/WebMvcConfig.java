package com.ranxx.conf;

import javax.servlet.MultipartConfigElement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
//import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.resource.VersionResourceResolver;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	Logger logger = LoggerFactory.getLogger(WebMvcConfig.class);
	
    /**
     * 文件上传配置 
     * 
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize("10240KB"); //10MB
        // 总上传数据大小
        factory.setMaxRequestSize("512000KB"); //50M
        return factory.createMultipartConfig();
    }

    /**
     *   静态资源
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/").setCachePeriod(600)
                .resourceChain(true)
                .addResolver(new VersionResourceResolver().addVersionStrategy(new MyVersionPathStrategy(), "/**"));
    }

    //拦截器实现逻辑
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    	//管理系统拦截器
    	/*registry.addInterceptor(new HandlerInterceptorAdapter() {
    		@Override
    		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    				throws Exception {
    			String key = mySiteSetting.getSessionName() + request.getSession().getId();
    			IdeaUser ideaUser = (IdeaUser) request.getSession().getAttribute(key);
    			if(ideaUser == null) {
    				response.sendRedirect("/page/manager/toLogin");
    				return false;
    			}
    			return true;
            }
    	}).addPathPatterns("/manager/*").excludePathPatterns("/page/manager", "/manager/login");*/
    	
    	//前段拦截器
    	/*registry.addInterceptor(new HandlerInterceptorAdapter() {
    		@Override
    		public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    				throws Exception {
    			return true;
            }
    	}).addPathPatterns("/wechat/to*",
        	"/wechat/shop/*");*/
    	//.excludePathPatterns("/");
    }
}