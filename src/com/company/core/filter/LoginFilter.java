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
 * 用户访问‘纳税服务’时的权限的判断
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
					// 获取spring容器
					WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(context);
					UserPrivilegeChecker checker = (UserPrivilegeChecker)applicationContext.getBean("userPrivilegeChecker");
					if(checker.isPrivilegeAccessible(user, Constant.PRIVILEGE_TAX)) {
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
