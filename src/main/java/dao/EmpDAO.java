package dao;

import java.sql.*;
import java.util.*;

import vo.Emp;

public class EmpDAO {
	// 조인으로 Map을 사용하는 경우
	public static ArrayList<HashMap<String, Object>> selectEmpAndDeptList() throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " emp.empno empNo, emp.ename ename, emp.deptno deptNo,"
				+ " dept.dname dname"
				+ " FROM emp INNER JOIN dept"
				+ " ON emp.deptno = dept.deptno";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("empNo", rs.getInt("empNo"));
			m.put("ename", rs.getString("ename"));
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("dname", rs.getString("dname"));
			list.add(m);
		}
		return list;
	}
	// VO사용
	public static ArrayList<Emp> selectEmptList() throws Exception {
		ArrayList<Emp> list = new ArrayList<>(); // Emp[] 배열과 같은 모습
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " empno empNo, ename, sal"
				+ " FROM emp";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			//e.empNo = rs.getInt("empNo");
			e.setEmpNo(rs.getInt("empno"));
			//e.ename = rs.getString("ename");
			e.setEname(rs.getString("ename"));
			//e.sal = rs.getDouble("sal");
			e.setSal(rs.getDouble("sal"));
			list.add(e);
		}
		return list;
	}
}
