package edu.citadel.csci370;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Artist_Songs extends ListActivity{
	
	private ArtistDbOpenHelper events;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {   	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.artist_songs);
        
        // intent stuff
     
        Intent intent = getIntent();
        String nameInput = intent.getStringExtra("name");
        
		String message = ",\nArtist: " + nameInput;
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
       
        events = new ArtistDbOpenHelper(this);
        int[] views = new int[]
                             //{R.id.artistName, R.id.songName};
        					{R.id.songName};
        					//{R.id.artistName};
        String[] columns = new String[]
                                      //{"name", "song_name"};
        					{"song_name"};
        					//{"name"};
        Cursor cursor = null;
        try {
        	cursor = getCursor(nameInput);
        
        	SimpleCursorAdapter adapter = new SimpleCursorAdapter
        		(this, R.layout.rows, cursor, columns, views);
        	setListAdapter(adapter);
        	ListView lv = getListView();
        
        	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        		public void onItemClick(AdapterView<?> parent, 
        				View view, int position, long id)
        		{
//        			ViewGroup vwGroup = (ViewGroup) view;
//        			TextView childArtist = (TextView) vwGroup.getChildAt(0);
//        			TextView childSong = (TextView) vwGroup.getChildAt(1);
//        			String name = childArtist.getText().toString();
//        			String song_name = childSong.getText().toString();
//        			setTable(id, name, song_name, position);
        			finish();
        		}
        	});
        }
    	finally {
        	return;
        }
    }
    
//private void setTable(long id, String artist, String song, int position)  {
//		String message =
//			"ID: " + id + ", Position: " + position
//			+ ",\nArtist: " + artist + ", Song: " + song;
//		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
//	}
//    
    private Cursor getCursor(String inputArtist) {
    	String sqlQuery = "select distinct song_name as _id, song_name from artists where name = '"+inputArtist+"'";// where name = Yellowcard";
    	SQLiteDatabase db = events.getReadableDatabase();
    	Cursor cursor = db.rawQuery(sqlQuery, null);
    	startManagingCursor(cursor);
    	
    	return cursor;
    }
    
   


}
