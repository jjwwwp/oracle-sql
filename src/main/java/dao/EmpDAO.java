package dao;

import java.sql.*;
import java.util.*;

import vo.Emp;

public class EmpDAO {
	// q005OrderBy.jsp
	public static ArrayList<Emp> selectEmpListSort(String col, String sort) throws Exception {
		// 매개값 디버깅
		System.out.println(col+"<--EmpDAO.selectEmpListSort param col");
		System.out.println(sort+"<--EmpDAO.selectEmpListSort param sort");
		
		ArrayList<Emp> list = new ArrayList<>();
		Connection conn = DBHelper.getConnection();
		
		/*
		 * 동적쿼리(쿼리문자열이 매개값에 따라 분기되어 차이가 나는 경우)
		 * 없다
		 * empno ASC
		 * empno DESC
		 * ename ASC
		 * ename DESC
		 */
		String sql = "SELECT empno, ename"
				+ " FROM emp";
		
		if(col != null && sort != null) {
			sql = sql + " ORDER BY "+ col+" "+sort; //띄어쓰기 주의할것
		}
		PreparedStatement stmt = conn.prepareStatement(sql);
		//디버깅
		System.out.println(stmt);
		
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			e.setEmpNo(rs.getInt("EmpNo"));
			e.setEname(rs.getString("Ename"));
			list.add(e);
		}
		conn.close();
		return list;
	}
	
	// q004WhereIn.jsp
	//emp목록을 불러온 다음 Integer로 변환후 등급목록을 출력
	public static ArrayList<Emp> selectEmpListByGrade(ArrayList<Integer> ckList) throws Exception {
		ArrayList<Emp> list = new ArrayList<>();
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT ename, grade"
				+ " FROM emp"
				+ " WHERE grade IN ";
		PreparedStatement stmt = null;
		//등급을 1개 고를때
		if(ckList.size() == 1) {
			sql = sql + "(?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
		//등급을 2개 고를때
		} else if(ckList.size() == 2) {
			sql = sql + "(?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
		//등급을 3개 고를때	
		}	else if(ckList.size() == 3) {
			sql = sql + "(?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
		//등급을 4개 고를때	
		}	else if(ckList.size() == 4) {
			sql = sql + "(?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
			stmt.setInt(4, ckList.get(3));
		//등급을 5개 고를때	
		}	else if(ckList.size() == 5) {
			sql = sql + "(?, ?, ?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ckList.get(0));
			stmt.setInt(2, ckList.get(1));
			stmt.setInt(3, ckList.get(2));
			stmt.setInt(4, ckList.get(3));
			stmt.setInt(5, ckList.get(4));
		}
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Emp e = new Emp();
			e.setEname(rs.getString("ename"));
			e.setGrade(rs.getInt("grade"));
			list.add(e);
		}
		return list;
	}
	
	//DEPTNO 뒤에 부서별 인원 같이 조회하는 메서드
	public static ArrayList<HashMap<String, Integer>> selectDeptNoCntList() throws Exception {
		ArrayList<HashMap<String, Integer>> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		// COUNT(*)의 *은 모든열이 아니고 rowid를 가르킨다
				String sql = "SELECT deptno deptNo, COUNT(*) cnt" 
						+ " FROM emp"
						+ " WHERE deptno IS NOT NULL"
						+ " GROUP BY deptno"
						+ " ORDER BY deptno ASC";
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();
				while(rs.next()) {
					HashMap<String, Integer> m = new HashMap<>();
					m.put("cnt", rs.getInt("cnt"));
					m.put("deptNo", rs.getInt("deptNo")); 
					list.add(m);
				}
				
				conn.close();
				return list; // 구현 후 수정
			}
	//중복을 제외한 DEPTNO 목록을 출력하는 메서드
	public static ArrayList<Integer> selectDeptNoList() throws Exception{
		ArrayList<Integer> list = new ArrayList<>();
		
		Connection conn = DBHelper.getConnection();
		String sql = "SELECT DISTINCT deptno deptNo"
				+ " FROM emp"
				+ " WHERE deptno IS NOT NULL"
				+ " ORDER BY deptno ASC";
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Integer i = rs.getInt("deptNo"); // 랩퍼타입과 기본타입간에 Auto Boxing
			list.add(i);
		}
		conn.close();
		
		return list;
	}
	
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
