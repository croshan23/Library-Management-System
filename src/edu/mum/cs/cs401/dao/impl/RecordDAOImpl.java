package edu.mum.cs.cs401.dao.impl;

import java.util.ArrayList;
import java.util.List;

import edu.mum.cs.cs401.dao.DataAccess;
import edu.mum.cs.cs401.dao.RecordDAO;
import edu.mum.cs.cs401.entity.Record;

public class RecordDAOImpl implements RecordDAO {
	
	private String cdr = System.getProperty("user.dir");
	private String recordJson =cdr + "/src/edu/mum/cs/cs401/dao/record.json";
	private static List<Record> list;
	
	private RecordDAOImpl(){
		loadList();
	}
	
	private static RecordDAOImpl recordDAO = new RecordDAOImpl();
	
	public static RecordDAOImpl getInstance() {
		return recordDAO;
	}


	@Override
	public List<Record> getAll() {
		return list;
	}

	@Override
	public void addRecords(List<Record> bookcopy) {
		if (list == null) {
			list = new ArrayList<Record>();
		} 
		list.addAll(bookcopy);
		DataAccess.save(list, recordJson);
		
	}

	@Override
	public List<Record> searchRecordsByCopyNumber(String copyNumber) {
		List<Record> listRe = new ArrayList<Record>();
		for (Record re : list) {
			if (re.getCopyNumber().equals(copyNumber)) {
				listRe.add(re);
			}
		}
		return listRe;
	}

	@Override
	public List<Record> searchRecordsByMember(int id) {
		List<Record> listRe = new ArrayList<Record>();
		for (Record re : list) {
			if (re.getPersonId() == id) {
				listRe.add(re);
			}
		}
		return listRe;
	}

	public void loadList(){
		list = DataAccess.getRecordList(recordJson);
	}
}
