package com.company.tax.user.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.company.core.constant.Constant;
import com.company.core.exception.ServiceException;
import com.company.core.service.imple.BaseServiceImpl;
import com.company.core.util.ExcelUtil;
import com.company.tax.role.entity.Role;
import com.company.tax.user.dao.UserDao;
import com.company.tax.user.entity.CompositeUserRole;
import com.company.tax.user.entity.User;
import com.company.tax.user.entity.UserRole;
import com.company.tax.user.service.UserService;

/**
 * @author Dongfuming
 * @date 2016-5-9 下午1:42:36
 */
@Service("userService")
public class UserSeviceImpl extends BaseServiceImpl<User> implements UserService {

	private UserDao userDao;
	
	@Resource
	public void setUserDao(UserDao userDao) {
		super.setBaseDao(userDao);
		this.userDao = userDao;
	}
	
	@Override
	public void save(User user) {
		userDao.save(user);
	}

	@Override
	public void update(User user) {
		userDao.update(user);
	}

	@Override
	public void delete(Serializable userId) {
		// 先删用户角色，再删用户
		userDao.deleteUserRolesByUserId((String)userId);
		userDao.delete(userId);
	}

	@Override
	public User findUserById(Serializable id) {
		return userDao.findObjectById(id);
	}

	@Override
	public List<User> findUsers() throws ServiceException {
		return userDao.findObjects();
	}

	@Override
	public void exportUserExcel(List<User> userList,
			ServletOutputStream outputStream) {
		ExcelUtil.exportUserExcel(userList, outputStream);
	}

	@Override
	public void importUserExcel(File userExcel, String userExcelFileName) {
		try {
			FileInputStream fileInputStream = new FileInputStream(userExcel);
			boolean is03Excel = userExcelFileName.matches("^.+\\.(?i)(xls)$");
			// 读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(fileInputStream):new XSSFWorkbook(fileInputStream);
			// 读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			// 读取行
			if(sheet.getPhysicalNumberOfRows() > 2) {
				for (int k = 2; k < sheet.getPhysicalNumberOfRows(); k++) {
					// 读取单元格
					Row row = sheet.getRow(k);
					User user = createUserWithRow(row);
					save(user);
				}
			}
			workbook.close();
			fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> findUserByAccountAndId(String id, String account) {
		return userDao.findUserByAccountAndId(id, account);
	}

	@Override
	public void saveUserAndRoles(User user, String... roleIdArray) {
		// 先保存用户，再保存用户角色
		save(user);
		saveUserRole(user, roleIdArray);
	}

	@Override
	public void updateUserAndRoles(User user, String... roleIdArray) {
		// 先删除之前的用户角色，然后更新用户，再保存现在的用户角色
		userDao.deleteUserRolesByUserId(user.getId());
		update(user);
		saveUserRole(user, roleIdArray);
	}

	@Override
	public List<UserRole> findUserRolesByUserId(String userId) {
		return userDao.findUserRolesByUserId(userId);
	}
	
	/*************** private ***************/
	private User createUserWithRow(Row row) {
		User user = new User();
		// 用户名
		Cell cell0 = row.getCell(0);
		user.setName(cell0.getStringCellValue());
		// 帐号
		Cell cell1 = row.getCell(1);
		user.setAccount(cell1.getStringCellValue());
		// 所属部门
		Cell cell2 = row.getCell(2);
		user.setDept(cell2.getStringCellValue());
		// 性别
		Cell cell3 = row.getCell(3);
		user.setGender(cell3.getStringCellValue().equals("男"));
		// 手机号
		String mobile = "";
		Cell cell4 = row.getCell(4);
		try {
			mobile = cell4.getStringCellValue();
		} catch (Exception e) {
			double mobileNum = cell4.getNumericCellValue();
			mobile = BigDecimal.valueOf(mobileNum).toString();
		}
		user.setMobile(mobile);
		//电子邮箱
		Cell cell5 = row.getCell(5);
		user.setEmail(cell5.getStringCellValue());
		// 生日
		Cell cell6 = row.getCell(6);
		if (cell6.getDateCellValue() != null) {
			user.setBirthday(cell6.getDateCellValue());
		}
		user.setPassword(Constant.DEFAULT_USER_PASSWORD);
		user.setState(Constant.DEFAULT_USER_STATE);
		
		return user;
	}
	
	private void saveUserRole(User user, String... roleIdArray) {
		if (roleIdArray != null) {
			for (String roleId : roleIdArray) {
				Role aRole = new Role(roleId);
				CompositeUserRole aCompositeUserRole = new CompositeUserRole(aRole, user.getId());
				UserRole aUserRole = new UserRole(aCompositeUserRole);
				userDao.saveUserRole(aUserRole);
			}
		}
	}

	@Override
	public List<User> findUsersByAccountAndPassword(String account,
			String password) {
		return userDao.findUsersByAccountAndPassword(account, password);
	}
}
