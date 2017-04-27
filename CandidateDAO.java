package com.wipro.candidate.dao;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.util.DBUtil;

public class CandidateDAO {
	Connection conn;
	public String addCandidate(CandidateBean CandidateBean) throws SQLException
	{
		int res = 0;
		String status;
		try
		{
			conn = DBUtil.getDBConn();
			
			String id = CandidateBean.getId();
			String name = CandidateBean.getName();
			int m1 = CandidateBean.getM1();
			int m2 = CandidateBean.getM2();
			int m3 = CandidateBean.getM3();
			String result = CandidateBean.getResult();
			String grade = CandidateBean.getGrade();
			
			PreparedStatement ps =conn.prepareStatement("insert into CANDIDATE_TBL(id,name,m1,m2,m3,result,grade) values(?,?,?,?,?,?,?)");
			
			ps.setString(1, id);
			ps.setString(2, name);
			ps.setInt(3, m1);
			ps.setInt(4, m2);
			ps.setInt(5, m3);
			ps.setString(6, result);
			ps.setString(7,grade);
			res = ps.executeUpdate();
			
		} 
		catch (ClassNotFoundException e)
		{
			status = "FAIL";
		} 
		catch (SQLException e)
		{
			status = "FAIL";
		}
	
		if(res == 1)
		{
			status = CandidateBean.getId()+":"+CandidateBean.getResult();
			conn.commit();
		}
			
		else
		{
			status ="Error";
		}
		return status;
	}

	public ArrayList<CandidateBean> getByResult(String criteria) throws ClassNotFoundException
	{
		ArrayList<CandidateBean>  a1 = new ArrayList<CandidateBean>();
		
		if(criteria.equals("PASS"))
		{ 
				try {
					 	conn = DBUtil.getDBConn();
						Statement stmt = conn.createStatement();
						ResultSet rs = stmt.executeQuery("select * from CANDIDATE_TBL where Result = 'PASS'");
					while(rs.next() )
					{
						CandidateBean e = new CandidateBean();
						e.setId(rs.getString(1));
						e.setName(rs.getString(2));
						e.setM1(rs.getInt(3));
						e.setM2(rs.getInt(4));
						e.setM2(rs.getInt(5));
						e.setResult(rs.getString(6));
						e.setGrade(rs.getString(7));
						a1.add(e);
					
					}
					return a1;
				} 
				catch (SQLException e) {
					
					return null ;
				}
			
			
		}
		
		else if(criteria.equals("FAIL"))
		{
			
			try {
				 conn = DBUtil.getDBConn();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from CANDIDATE_TBL where Result ='FAIL'");
				while(rs.next() )
				{
					CandidateBean e = new CandidateBean();
					e.setId(rs.getString(1));
					e.setName(rs.getString(2));
					e.setM1(rs.getInt(3));
					e.setM2(rs.getInt(4));
					e.setM2(rs.getInt(5));
					e.setResult(rs.getString(6));
					e.setGrade(rs.getString(7));
					a1.add(e);
				
				}
				return a1;
			} 
			catch (SQLException e) {
				
				return null ;
			}
			
			
		}
		
		else if(criteria.equals("ALL"))
		{
			
			try {
				 conn = DBUtil.getDBConn();
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery("select * from CANDIDATE_TBL where Result ='PASS' OR Result = 'FAIL'");
				while(rs.next() )
				{
					CandidateBean e = new CandidateBean();
					e.setId(rs.getString(1));
					e.setName(rs.getString(2));
					e.setM1(rs.getInt(3));
					e.setM2(rs.getInt(4));
					e.setM2(rs.getInt(5));
					e.setResult(rs.getString(6));
					e.setGrade(rs.getString(7));
					a1.add(e);
				
				}
				return a1;
			} 
			catch (SQLException e) {
				
				return null ;
			}
			
		}
		
		else
		{
			return null;
		}
		
	
	}
	
	public String generateCandidateId(String name)
	{
		String id = "";
		try {
			 conn = DBUtil.getDBConn();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("select CANDID_SEQ.NEXTVAL from dual ");
			if ( rs!=null && rs.next() )
			{
				 int seqNo = rs.getInt(1);
				 id = ""+Character.toUpperCase(name.charAt(0))+Character.toUpperCase(name.charAt(1))+""+seqNo;
			}
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return id;
		
	}
	
}
