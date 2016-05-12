package com.company.tax.role.dao.impl;

import org.hibernate.Query;
import com.company.core.dao.impl.BaseDaoImpl;
import com.company.tax.role.dao.RoleDao;
import com.company.tax.role.entity.Role;

/**
 * @author Dongfuming
 * @date 2016-5-11 下午3:23:13
 */
public class RoleDaoImpl extends BaseDaoImpl<Role> implements RoleDao {

	@Override
	public void deletePrivilegeByRoleId(String roleId) {
		String sql = "DELETE FROM RolePrivilege WHERE compositeRolePrivilege.role.id=?";
		Query query = this.getSession().createQuery(sql);
		query.setParameter(0, roleId);
		query.executeUpdate();
	}
}
