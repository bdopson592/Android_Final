package edu.citadel.csci370;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;



public class LastActivity extends ListActivity {
	
	private ArtistDbOpenHelper events;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_last);
        // intent stuff
	     
        Intent intent = getIntent();
        String[] paramArray = intent.getStringArrayExtra("paramArray");
      
        
		String message = "Name: "+ paramArray[0] +",\nSong or Medicine: " + paramArray[1];
			Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
       
        events = new ArtistDbOpenHelper(this);
        int[] views = new int[]
                             //{R.id.artistName, R.id.songName};
        					//{R.id.songName};
        					//{R.id.artistName};
        	{R.id.dateTxtView,R.id.timeTxtView, R.id.statusTxtView};
        String[] columns = new String[]
                                      //{"name", "song_name"};
        					//{"song_name"};
        					//{"name"};
        		{"date_info","timeTaken_info","status_info"};
        Cursor cursor = null;
        try {
        	cursor = getCursor(paramArray[0], paramArray[1]);
        
        	SimpleCursorAdapter adapter = new SimpleCursorAdapter
        		(this, R.layout.rows2, cursor, columns, views);
        	setListAdapter(adapter);
        	ListView lv = getListView();
        
        	lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        		public void onItemClick(AdapterView<?> parent, 
        				View view, int position, long id)
        		{
        			ViewGroup vwGroup = (ViewGroup) view;
        			TextView childArtist = (TextView) vwGroup.getChildAt(0);
        			TextView childSong = (TextView) vwGroup.getChildAt(1);
        			TextView childDate = (TextView) vwGroup.getChildAt(2);
        			TextView childTime = (TextView) vwGroup.getChildAt(3);
        			TextView childStatus = (TextView) vwGroup.getChildAt(4);
        			String name = childArtist.getText().toString();
        			String song_name = childSong.getText().toString();
        			String date_info = childDate.getText().toString();
        			String timeTaken_info = childTime.getText().toString();
        			String status_info = childStatus.getText().toString();

        			//setTable(id, name, song_name, position);
        			finish();
//        			Intent intent = new Intent(Artists_Songs.this, LastActivity.class);
//        			intent.putExtra("song_name", song_name);
//        			startActivity(intent);
        		}
        	});
        }
    	finally {
        	return;
        }
		
	}
	  private Cursor getCursor(String inputArtist, String inputSong) {
	    	//String sqlQuery = "select distinct date_info as _id, name from artists";// where name = Yellowcard";
	    	String sqlQuery = "select distinct date_info as _id, date_info from artists where name = '"+inputArtist+"' and song_name = '"+inputSong+ "'";
	    	//String sqlQuery = "select * from artists where name = '"+inputArtist+"' and song_name = '"+inputSong+ "'";// where name = Yellowcard";
	    	
	    	SQLiteDatabase db = events.getReadableDatabase();
	    	Cursor cursor = db.rawQuery(sqlQuery, null);
	    	startManagingCursor(cursor);
	    	
	    	return cursor;
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

		return true;
	}



}
