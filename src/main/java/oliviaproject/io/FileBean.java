package oliviaproject.io;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class FileBean {
	public static final String SEPARATOR = ",";
	String author;

	String file;
	Date date;
	String boxvalue;
	String reference;
	String title;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getBoxvalue() {
		return boxvalue;
	}
	public void setBoxvalue(String boxvalue) {
		this.boxvalue = boxvalue;
	}
	public FileBean(String line) {
		String[] fields= line.split(SEPARATOR);
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy", new Locale("us"));
		try {
			date=sdf.parse(fields[0]);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		file=fields[1];
		author=fields[2];
		boxvalue=fields[3];
		reference=fields[4];
		title=fields[5];
		
	}
	public FileBean() {
		// TODO Auto-generated constructor stub
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}

}