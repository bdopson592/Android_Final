package edu.citadel.csci370;

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

public class MainActivity extends ListActivity {

	private ArtistDbOpenHelper events;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {   	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        events = new ArtistDbOpenHelper(this);
        int[] views = new int[]
                             //{R.id.artistName, R.id.songName};
        					//{R.id.songName};
        					{R.id.artistName};
        String[] columns = new String[]
                                      //{"patientName", "medicationName"};
        						//{"name", "song_name"};
        					//{"medicationName"};
        					{"name"};
        Cursor cursor = null;
        try {
        	cursor = getCursor();
        
        	SimpleCursorAdapter adapter = new SimpleCursorAdapter
        		(this, R.layout.rows, cursor, columns, views);
        	setListAdapter(adapter);
        	ListView lv = getListView();
        
        	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        		@Override
        		public void onItemClick(AdapterView<?> parent, 
        				View view, int position, long id)
        		{
        			ViewGroup vwGroup = (ViewGroup) view;
        			int count =  vwGroup.getChildCount();

        			TextView childArtist = (TextView) vwGroup.getChildAt(0);
        			TextView childSong = (TextView) vwGroup.getChildAt(1);
        			String name = childArtist.getText().toString();
        			String song_name = childSong.getText().toString();
        			setTable(id, name, song_name, count, position);
        		
        		}
        	});
        }
    	finally {
    		
        	return;
        }
    }
    
private void setTable(long id, String artist, String song, int count, int position)  {
		String message =
			"ID: " + id + ", Position: " + position
			+ ",\nArtist: " + artist + ", Song: " + song + "View Group Count: " + count;
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
		Intent intent = new Intent(MainActivity.this, Artists_Songs.class);
		intent.putExtra("name", artist);
		//startActivity(intent);
	}
    
    private Cursor getCursor() {
    	//String sqlQuery = "select * from artists";// where name = Yellowcard";
    	String sqlQuery = "select distinct name as _id, name from artists";// where name = Yellowcard";
    	//String sqlQuery = "select distinct song_name as _id, song_name from artists where name = 'Yellowcard'";// where name = Yellowcard";
    	SQLiteDatabase db = events.getReadableDatabase();
    	Cursor cursor = db.rawQuery(sqlQuery, null);
    	startManagingCursor(cursor);
    	
    	return cursor;
    }
    
    @Override
    protected void onDestroy(){
    	events.close();
    	super.onDestroy();
    }


}


