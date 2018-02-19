package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Game {
	
	private String title;
	private String age;
	private Date releaseDate;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAge() {
		return age;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public Date getReleaseDate() {
		return releaseDate;
	}
	
	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}
	
	public void setReleaseDate2(String releaseDate2) {
		SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = textFormat.parse(releaseDate2);
		}catch(ParseException ex){
			ex.printStackTrace();
		}
		this.releaseDate = date;
	}

}
