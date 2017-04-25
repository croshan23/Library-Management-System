package edu.mum.cs.cs401.dao;

import java.util.List;

import edu.mum.cs.cs401.entity.Record;

public interface RecordDAO {
	
	public List<Record> getAll();
	
	public void addRecords(List<Record> bookcopy);
	
	public List<Record> searchRecordsByCopyNumber(String copyNumber);
	
	public List<Record> searchRecordsByMember(int id);
	
}
