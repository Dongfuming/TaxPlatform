package com.company.core.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.company.core.checker.UserPrivilegeChecker;
import com.company.core.constant.Constant;
import com.company.tax.user.entity.User;

/**
 * 登录及权限过滤器：
 * 在用HTTP请求后台时，需要判断用户有没有登录，且登录后有没有‘纳税管理’的访问权限，
 * 1. 若是登录请求，则直接放行，进入到登录页面；
 * 2. 若非登录请求，判断用户是否已经登录：
 * 2.1 若未登录，重定向到登录页面；
 * 2.2 若已登录，判断是否是‘纳税服务’的访问请求：
 * 2.2.1 若非‘纳税服务’的请求，则直接放行；
 * 2.2.2 若是‘纳税服务’的请求，判断是否有访问权限：
 * 2.2.2.1 若有访问权限，则直接放行
 * 2.2.2.2 若无访问权限，提示无权限
 * @author Dongfuming
 * @date 2016-5-13 下午5:56:44
 */
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)servletRequest;
		HttpServletResponse response = (HttpServletResponse)servletResponse;
		String uri = request.getRequestURI();
		System.out.println("doFilter, uri = " + uri);

		if (!uri.contains("/system/login")) { // 非登录请求
			if (request.getSession().getAttribute(Constant.USER) != null) { // 已登录
				if (uri.contains("/tax")) { // 访问纳税服务
					User user = (User)request.getSession().getAttribute(Constant.USER);
					ServletContext context = request.getSession().getServletContext();
					WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
					UserPrivilegeChecker checker = (UserPrivilegeChecker)applicationContext.getBean("userPrivilegeChecker");
					
					if(checker.isPrivilegeAccessible(user, Constant.PRIVILEGE_TAX)) { // 有权限
						chain.doFilter(request, response);
					} else { // 无权限 
						response.sendRedirect(request.getContextPath() + "/system/login/toNoPrivilegePage.action");
					}
				} else {
					// 非访问纳税服务
					chain.doFilter(request, response);
				}
			} else { // 未登录
				response.sendRedirect(request.getContextPath() + "/system/login/toLoginPage.action");
			}
		} else { // 登录请求
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {

	}

}
