package com.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NumberDAOImplOracle implements NumberDAO {

	// 접속 코드
	// 접속 코드
	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			String dburl = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(dburl, "C##BITUSER", "bituser");
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			System.err.println("드라이버 로드 실패");
		}
		return conn;
	}

	@Override
	public List<NumberVO> getList() {
		List<NumberVO> list = new ArrayList<>();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			stmt = conn.createStatement();

			String sql = "SELECT phone_book.id, phone_book.name, phone_book.hp, phone_book.tel  "
					+ " FROM phone_book ORDER BY phone_book.id";
			rs = stmt.executeQuery(sql);
			// 루프: 객체화
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				NumberVO VO = new NumberVO(id, name, hp, tel);
				list.add(VO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return list;
	}

	@Override
	public List<NumberVO> search(String keyword) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<NumberVO> list = new ArrayList<>();

		try {
			conn = getConnection();
			String sql = "SELECT phone_book.id, phone_book.name, phone_book.hp, phone_book.tel  FROM phone_book "
					+ " WHERE phone_book.id LIKE ? OR phone_book.name LIKE ?" + " OR phone_book.hp LIKE ?"
					+ " OR phone_book.tel LIKE ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + keyword + "%");
			pstmt.setString(4, "%" + keyword + "%");

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Long id = rs.getLong(1);
				String name = rs.getString(2);
				String hp = rs.getString(3);
				String tel = rs.getString(4);
				NumberVO VO = new NumberVO(id, name, hp, tel);
				list.add(VO);
			}
		} catch (SQLException e) {
			// TODO: handle exception
		}
		return list;
	}

	@Override
	public boolean insert(NumberVO vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		try {
			conn = getConnection();
			String sql = "INSERT INTO phone_book" + " VALUES(seq_phone_book.NEXTVAL,?,?,?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getNumberName());
			pstmt.setString(2, vo.getNumberHp());
			pstmt.setString(3, vo.getNumberTel());

			insertedCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return 1 == insertedCount;
	}

	@Override
	public boolean insert(NumberVO vo, Long checkIndex) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int insertedCount = 0;

		try {
			conn = getConnection();
			String sql = "INSERT INTO phone_book" + " VALUES(?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, checkIndex);
			pstmt.setString(2, vo.getNumberName());
			pstmt.setString(3, vo.getNumberHp());
			pstmt.setString(4, vo.getNumberTel());

			insertedCount = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return 1 == insertedCount;
	}

	@Override
	public boolean delete(long index) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int deletedCount = 0;
		try {
			conn = getConnection();
			String sql = "DELETE FROM phone_book " + " WHERE phone_book.id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, index);
			deletedCount = pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
		return 1 == deletedCount;
	}
}
