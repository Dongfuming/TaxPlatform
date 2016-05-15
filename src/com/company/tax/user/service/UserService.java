package com.company.tax.user.service;

import java.io.File;
import java.io.Serializable;
import java.util.List;
import javax.servlet.ServletOutputStream;
import com.company.core.exception.ServiceException;
import com.company.tax.user.entity.User;
import com.company.tax.user.entity.UserRole;

/**
 * @author Dongfuming
 * @date 2016-5-9 下午1:40:11
 */
public interface UserService {
	
	public void save(User user);

	public void update(User user);

	public void delete(Serializable userId);

	public User findUserById(Serializable id);

	public List<User> findUsers() throws ServiceException;
	
	public void exportUserExcel(List<User> userList, ServletOutputStream outputStream);
	
	public void importUserExcel(File userExcel, String userExcelFileName);
	
	public List<User> findUserByAccountAndId(String id, String account);

	public void saveUserAndRoles(User user, String... roleIdArray);

	public void updateUserAndRoles(User user, String... roleIdArray);

	public List<UserRole> findUserRolesByUserId(String userId);
	
	public List<User> findUsersByAccountAndPassword(String account, String password);
}
