package edu.citadel.csci370;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ArtistDbOpenHelper extends SQLiteOpenHelper  {
	private static final String DB_NAME = "artists.db";
	private static final int DB_VERSION = 1;
	String artistTable = "artists";
	
	public ArtistDbOpenHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String createSql =
			"create table artists"
			+"("
			+" 	_id 			integer primary key autoincrement,"
			+" 	name			text not null," 
			+" 	song_name 		text not null"
			+" 	date_info			text not null," 
			+" 	timeTaken_info 		text not null"
			+" 	status_info			text not null,"
			+")";
		db.execSQL(createSql);
		insertArtists(db);
	}
	
	public void insertArtists(SQLiteDatabase db) {
		String[] artistNames = { 
		"Kanye West","The Outline","New Found Glory","Sequoyah Prep School","Sum 41","Seether","Senses Fail","Yellowcard","Yellowcard","Yellowcard","Yellowcard","Yellowcard","Yellowcard"
		};
		String[] songNames =  {
		"Stronger","Shotgun","Oxygen","Little Slice of Heaven","The Hell Song","Ocean Avenue","Remedy","Buried A Lie","a", "b","c","d","d"
		};
		String[] dates=  {
		"March","May","April","june","december","november","january","february","october","september","january","july","january"
		};
		String[] timesTaken=  {
		"8:02 am","8:04 am","8:30 am","12:15 pm","8:30 am","12:02 pm","8:00 am","11:59 am","8:04 am","12:01 pm","8:30 am","11:59 am","12:15 pm"
		};
		String[] statuses=  {
		"OK","BAD","BAD","LATE","MISSED","OK","OK","OK","OK","OK","MISSED","OK","OK"
		};
		
		for(int i = 0; i < artistNames.length; ++i) {
			ContentValues artistValues = new ContentValues();
			artistValues.put("name", artistNames[i]);
			artistValues.put("song_name", songNames[i]);
			artistValues.put("date_info", dates[i]);
			artistValues.put("timeTaken_info", timesTaken[i]);
			artistValues.put("status_info", statuses[i]);
			db.insert(artistTable, null, artistValues);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// no need for upgrade
	}


}
