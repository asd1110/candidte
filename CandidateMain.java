package com.wipro.candidate.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.wipro.candidate.bean.CandidateBean;
import com.wipro.candidate.dao.CandidateDAO;
import com.wipro.candidate.util.WrongDataException;

public class CandidateMain {
	CandidateDAO cd;
	public String addCandidate(CandidateBean candBean) throws SQLException 
	{
		String status;
		if(candBean == null || candBean.getName().isEmpty()|| candBean.getName().length()<2 || candBean.getM1()<0 || candBean.getM1()>100 || candBean.getM2()<0 || candBean.getM2()>100 || candBean.getM3()<0 || candBean.getM3()>100)
		{
			try
			{
				throw new WrongDataException();
			}
			catch(WrongDataException e)
			{
				status = "Data Incorrect";
			
			}
			
		}
		
		else
		{
			cd = new CandidateDAO();
		   String id = cd.generateCandidateId(candBean.getName());
			candBean.setId(id);
			int M1 = candBean.getM1();
			int M2 = candBean.getM2();
			int M3 = candBean.getM3();
			int totalmark = M1+M2+M3;
			if(totalmark>=240)
			{
				candBean.setResult("PASS");
				candBean.setGrade("Distinction");
		
			}
			
			if(totalmark>=180 && totalmark<240)
			{
				candBean.setResult("PASS");
				candBean.setGrade("First Class");
		
			}
			

			if(totalmark>=150 && totalmark<180)
			{
				candBean.setResult("PASS");
				candBean.setGrade("Second Class");
		
			}
			

			if(totalmark>=105 && totalmark<150)
			{
				candBean.setResult("PASS");
				candBean.setGrade("Third Class");
		
			}

			if(totalmark<105)
			{
				candBean.setResult("FAIL");
				candBean.setGrade("No Grade");
		
			}
			
		 status = cd.addCandidate(candBean);
		}
		return status;
	}
	
	public ArrayList<CandidateBean> displayAll(String criteria) throws ClassNotFoundException
	{
		if(criteria.equals("PASS") ||  criteria.equals("FAIL") || criteria.equals("ALL"))
		{
			cd = new CandidateDAO();
			
			ArrayList<CandidateBean> cand = new ArrayList<CandidateBean>();
			
			cand = cd.getByResult(criteria);
			
			return cand;
		}
		else
		{
			try
			{
				throw new WrongDataException();
			}
			catch(WrongDataException e)
			{
				return null;
			
			}
			
		}
		
		
	}
	
	
	public static void main(String[] args) throws SQLException {
		
		CandidateMain candidateMain = new CandidateMain();
		
		
		String result = candidateMain.addCandidate(null);

		System.out.println(result);
	}

}
