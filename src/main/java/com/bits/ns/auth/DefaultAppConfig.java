/*
 * package com.bits.ns.auth;
 * 
 * import org.apache.catalina.Context; import
 * org.apache.catalina.connector.Connector; import
 * org.apache.tomcat.util.descriptor.web.SecurityCollection; import
 * org.apache.tomcat.util.descriptor.web.SecurityConstraint; import
 * org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
 * import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
 * import org.springframework.context.annotation.Bean; import
 * org.springframework.context.annotation.Configuration;
 * 
 * @Configuration public class DefaultAppConfig {
 * 
 * Logger logger = LoggerFactory.getLogger(getClass());
 * 
 * @Bean public ServletWebServerFactory servletContainer() {
 * logger.info("Creating bean for https"); TomcatServletWebServerFactory tomcat
 * = new TomcatServletWebServerFactory() {
 * 
 * @Override protected void postProcessContext(Context context) {
 * SecurityConstraint securityConstraint = new SecurityConstraint();
 * securityConstraint.setUserConstraint("CONFIDENTIAL"); SecurityCollection
 * collection = new SecurityCollection(); collection.addPattern("/*");
 * securityConstraint.addCollection(collection);
 * context.addConstraint(securityConstraint); } };
 * logger.info("Creating bean for https");
 * tomcat.addAdditionalTomcatConnectors(redirectConnector()); return tomcat; }
 * 
 * private Connector redirectConnector() {
 * logger.info("Redirecting traffic to 443"); Connector connector = new
 * Connector("org.apache.coyote.http11.Http11NioProtocol");
 * connector.setScheme("http"); connector.setPort(80);
 * connector.setSecure(false); connector.setRedirectPort(443); return connector;
 * }
 * 
 * }
 */