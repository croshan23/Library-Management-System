package edu.mum.cs.cs401.dao;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import edu.mum.cs.cs401.entity.Book;
import edu.mum.cs.cs401.entity.BookCopy;
import edu.mum.cs.cs401.entity.Person;
import edu.mum.cs.cs401.entity.Record;

public class DataAccess {
	
	static Gson gson = new Gson();
	
	public static void save(Object list, String desternation){
		try (FileWriter writer = new FileWriter(desternation)) {
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	
	
	public static List<Person> getList(String source){
		List<Person> list = new ArrayList<Person>();
		try {
			Reader reader = new FileReader(source);
			list = gson.fromJson(reader, new TypeToken<List<Person>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static List<Book> getBookList(String source){
		List<Book> list = new ArrayList<Book>();
		try {
			Reader reader = new FileReader(source);
			list = gson.fromJson(reader, new TypeToken<List<Book>>(){}.getType());
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<BookCopy> getBookCopyList(String source){
		List<BookCopy> list = new ArrayList<BookCopy>();
		try {
			Reader reader = new FileReader(source);
			list = gson.fromJson(reader, new TypeToken<List<BookCopy>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static List<Record> getRecordList(String source){
		List<Record> list = new ArrayList<Record>();
		try {
			Reader reader = new FileReader(source);
			list = gson.fromJson(reader, new TypeToken<List<Record>>(){}.getType());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
