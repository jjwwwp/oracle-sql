package dao;

import java.sql.*;
import java.util.*;

import vo.Dept;

public class DeptDAO {
	//q003Case.jsp
	public static ArrayList<HashMap<String, String>> selectJobCaseList() throws Exception {
		ArrayList<HashMap<String, String>> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT ename, job, CASE"
				+ " WHEN job = 'PRESIDENT' THEN '빨강'"
				+ " WHEN job = 'MANAGER' THEN '주황'"
				+ " WHEN job = 'ANALYST' THEN '노랑'"
				+ " WHEN job = 'CLERK' THEN '초록'"
				+ " ELSE '파랑' END color"
				+ " FROM emp"
				+ " ORDER BY (CASE"
				+ " WHEN color = '빨강' THEN 1"
				+ " WHEN color = '주황' THEN 2"
				+ " WHEN color = '노랑' THEN 3"
				+ " WHEN color = '초록' THEN 4"
				+ " ELSE 5 END) ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, String> m = new HashMap<>();
			m.put("job", rs.getString("job"));
			m.put("color", rs.getString("color")); 
			list.add(m);
		}
									    
		conn.close();
		return list;
}
		
	
	// Map사용
	public static ArrayList<HashMap<String, Object>> selectDeptOnOfftList() throws Exception {
		ArrayList<HashMap<String, Object>> list = new ArrayList<>(); // Dept[] 배열과 같은 모습
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " deptno deptNo, dname, loc, 'ON' onOff"
				+ " FROM dept";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			HashMap<String, Object> m = new HashMap<>();
			m.put("deptNo", rs.getInt("deptNo"));
			m.put("dname", rs.getString("dname"));
			m.put("loc", rs.getString("loc"));
			m.put("onOff", rs.getString("onOff"));
			list.add(m);
		}
		return list;
	}
	
	// VO사용
	public static ArrayList<Dept> selectDeptList() throws Exception {
		ArrayList<Dept> list = new ArrayList<>(); // Dept[] 배열과 같은 모습
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT"
				+ " deptno deptNo, dname, loc"
				+ " FROM dept";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Dept d = new Dept();
			//d.deptNo = rs.getInt("deptNo");
			d.setDeptNo(rs.getInt("deptNo")); 
			//d.dname = rs.getString("dname");
			d.setDname(rs.getString("dname"));
			//d.loc = rs.getString("loc");
			d.setLoc(rs.getString("loc"));
			list.add(d);
		}
		return list;
	}
}
//Map은 변수명을 설정하면서 데이터의 키,값을 저장,실행 <-> VO는 class에 변수를 추가하고 자바에 객체를 생성한 다음 값을 불러와 실행
