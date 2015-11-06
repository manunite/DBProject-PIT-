/**
 * @author Ben Pitts
 * Timeline Calendar project from CS495 (Android app development)
 * Won't do much in emulator, as it needs calendar data and multitouch.
 * email me if you do anything cool with this idea: methodermis@gmail.com
 */
package org.drgn.timeline.TimeLine;

import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.net.Uri;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import android.util.Log;

import org.drgn.timeline.Manager.DB;
import org.drgn.timeline.Manager.ListItem;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.ExecutionException;

public class CalStuff
{
	private Context context;
	static String aaa = new String();
	public Map<Long, Clndr> CalendarsMap;
	public ArrayList<Clndr> ourCalendars;
	public Map<Long, Evnt> EventsMap;
	public ArrayList<Evnt> ourEvents;
	public ArrayList<Inst> ourInstances;
	static String S_year = new String();
	static String S_month = new String();
	static String S_day = new String();
	static String E_year = new String();
	static String E_month = new String();
	static String E_day = new String();

	//


	static public ArrayList<ListItem> listitem = new ArrayList<ListItem>();
	//
	
	public class Clndr
	{
		long _id;
		String account_name;
		String display_name;
		String name;
		int color;
		String timezone; // should be parsed

		Paint paint; // bad to have view code in model
	}
	
	public class Evnt
	{
		long _id;
		long dtstart;
		long dtend;
		int all_day;
		String title;
		String desc;
		int color;
		long calendar_id;
	}
	
	public class Inst
	{
		long _id;
		long event_id;
		
		/** The beginning time of the instance, in UTC milliseconds. millis since epoch */
		long begin_ms;
		
		/** The ending time of the instance, in UTC milliseconds. millis since epoch */
		long end_ms;
		
		/** The Julian start day of the instance, relative to the local time zone. */
		int startday_julian;
		
		/** The Julian end day of the instance, relative to the local time zone. */ 
		int endday_julian;

		/** The start minute of the instance measured from midnight in the local time zone. */
		int startminute;
		
		/** The end minute of the instance measured from midnight in the local time zone. */
		int endminute;
	}

	public CalStuff(Context context)
	{
		this.context = context;

		CalendarsMap = new HashMap<Long, Clndr>();
		EventsMap = new HashMap<Long, Evnt>();

		ourCalendars = new ArrayList<Clndr>();
		ourEvents = new ArrayList<Evnt>();
	}
	
	public void LoadEvents()
	{
		Uri uri = CalendarContract.Events.CONTENT_URI;
		//Uri uri = Uri.parse("content://com.android.calendar/events");
		String[] projection = new String[] {
			Events._ID,
			Events.DTSTART,
			Events.DTEND,
			Events.ALL_DAY,
			Events.TITLE,
			Events.EVENT_COLOR,
			Events.CALENDAR_ID,
			Events.DESCRIPTION
		};

		DB db = new DB();
		String result = new String();
		int events = 0;
		//String aaa = "117.16.198.54:8080/DBProject/project_list?Email = 222";

        //String Email = "222";
        //String CateName = "윤순모";
        //aaa="http://192.168.0.37:8080/DBProject/project_list?Email="+Email+"&CateName="+CateName;
		aaa = "http://192.168.0.37:8080/DBProject/project_list?Email=222&CateName=허규진";

		//aaa = "http://192.168.0.37:8080/DBProject/category_list?Email=222";
		Log.i("LogTag ",aaa);
		try {
			result = db.execute(aaa).get();
			Log.i("LogTag : test", result);
		} catch (InterruptedException e) {
			Log.i("LogTag : test", "QQ");
			e.printStackTrace();
		} catch (ExecutionException e) {
			Log.i("LogTag : test", "EE");
			e.printStackTrace();
		}
		//Cursor c = context.getContentResolver().query(uri, projection, null, null, Events.DTSTART);
		//Cursor c = context.getContentResolver().query(aaa,bbb,null,null,null);
		setExecute(result);
		//종료후 listitem에 프로젝트 리스트 정보다 담겨서 넘어오게 된다.
		//if (c.moveToFirst())
		{
			//do
			for(int i =0;i<listitem.size();i++)
			{
				Evnt e = new Evnt();


				String S_D = listitem.get(i).getData(1);
				String E_D = listitem.get(i).getData(2);

				StringTokenizer Stz_1 = new StringTokenizer(S_D,"-");
				StringTokenizer Stz_2 = new StringTokenizer(E_D,"-");


				while(Stz_1.hasMoreTokens())
				{
					S_year = Stz_1.nextToken();
					S_month = Stz_1.nextToken();
					S_day = Stz_1.nextToken();
				}

				while(Stz_2.hasMoreTokens())
				{
					E_year = Stz_2.nextToken();
					E_month = Stz_2.nextToken();
					E_day = Stz_2.nextToken();
				}

				Log.i("LogTag : year ", S_year + "," + E_year);
				Log.i("LogTag : month ",S_month + "," + E_month);
				Log.i("LogTag : day ", S_day + "," + E_day);
				
				//e._id = c.getLong(0);
				Calendar S_calendar = Calendar.getInstance();
				Calendar E_calendar = Calendar.getInstance();
				int y,m,d;
				y = Integer.parseInt(S_year); // 시작년월일을 밀리세컨으로 변환
				m = Integer.parseInt(S_month);
				d = Integer.parseInt(S_day);
				S_calendar.set(y, m-1, d); //원래 월에는 1빼준다고 함.
				y = Integer.parseInt(E_year); // 끝년월일을 밀리세컨으로 변환
				m = Integer.parseInt(E_month);
				d = Integer.parseInt(E_day);
				E_calendar.set(y, m-1, d);
				long S_millisSec = (S_calendar.getTimeInMillis())-65000000; // 날짜가 약간 안맞아 억지로 큰수를 뺴줌. 약 0.8일정도 되는듯??
				long E_millisSec = E_calendar.getTimeInMillis();
				e.dtstart = S_millisSec;//년월일을 밀리세컨으로 고쳐야함
				e.dtend = E_millisSec;
				/*
				e.all_day = c.getInt(3);
				e.title = c.getString(4);
				e.color = c.getInt(5);
				e.calendar_id = c.getLong(6);//c.getLong(6);*/
				e.title = listitem.get(i).getData(0);
				//e.dtstart = Long.valueOf(listitem.get(i).getData(2));
				//e.dtend = Long.valueOf(listitem.get(i).getData(3));
				//e.all_day = (E_millisSec-S_millisSec);
				e.calendar_id = 2;
				e.color = 0xF0EA;
				//3-> Project1이 출력
				//->8,9번 에러남
				//e.desc = c.getString(7);

				if(e.calendar_id == 2) { // 캘린더 2번에다가 쓰겠다는 뜻
					ourEvents.add(e);
					EventsMap.put(e._id, e);

					//Log.i("LogTag : LoadEvents() ", Long.toString(e._id));
					Log.i("LogTag : dtstart()", "" + e.dtstart);
					//Log.i("LogTag : dtstart() ", Long.toString(e.dtstart));
					Log.i("LogTag : dtend() ", Long.toString(e.dtend));
					//Log.i("LogTag : all_day() ", Integer.toString(e.all_day));
					Log.i("LogTag : title() ", (e.title));
					//Log.i("LogTag : color() ", Integer.toString(e.color));
					Log.i("LogTag : calendar_id() ", Long.toString(e.calendar_id));
					//Log.i("LogTag : desc() ", e.desc);
				}
			}
		}
	}

	protected void setExecute(String str){
		String ProjectName;
		String ProjectStartDate;
		String ProjectEndDate;
		//Log.i("INCHEONAAA", "jsonHtml.toString()");
		try{
			JSONObject root = new JSONObject(str);
			JSONArray ja = root.getJSONArray("results"); //get the JSONArray which I made in the php file. the name of JSONArray is "results"

			for(int i=0;i<ja.length();i++){
				JSONObject jo = ja.getJSONObject(i);
				ProjectName = jo.getString("ProjectName");
				ProjectStartDate = jo.getString("StartDate");
				ProjectEndDate = jo.getString("EndDate");
				Log.i("LogTag : test ", ProjectName + "," + ProjectStartDate + "," + ProjectEndDate);
				listitem.add(new ListItem(ProjectName, ProjectStartDate, ProjectEndDate));
			}

		}catch (JSONException e){
			e.printStackTrace();
		}
	}
	
	public void LoadInstances()
	{
		if (true) return; // TODO broken.
		
		Uri.Builder builder = CalendarContract.Instances.CONTENT_URI.buildUpon(); 
	    ContentUris.appendId(builder, Long.MIN_VALUE);
	    ContentUris.appendId(builder, Long.MAX_VALUE); 
	    Uri uri = builder.build();
	    
	    // try http://stackoverflow.com/questions/18158269/android-not-all-instances-of-recurring-event-saved
		
		String[] projection = new String[] {
			Instances._ID,
			Instances.EVENT_ID,
			Instances.BEGIN,
			Instances.START_DAY,
			Instances.START_MINUTE,
			Instances.END,
			Instances.END_DAY,
			Instances.END_MINUTE
		};
		
		int instances = 0;
		
		Cursor c = context.getContentResolver().query(uri, projection, null, null, null);
		
		if (c.moveToFirst())
		{
			do
			{
				Inst e = new Inst();
				
				e._id = c.getLong(0);
				e.event_id = c.getLong(1);
				e.begin_ms = c.getLong(2);
				e.startday_julian = c.getInt(3);
				e.startminute = c.getInt(4);
				e.end_ms = c.getLong(5);
				e.endday_julian = c.getInt(6);
				e.endminute = c.getInt(7);
				
				ourInstances.add(e);
				
			} while (c.moveToNext() && instances++ < 100);
		}
	}
	
	public void LoadCalendars()
	{
		Uri uri = CalendarContract.Calendars.CONTENT_URI;
		
		String[] projection = new String[] {
		       Calendars._ID,
		       Calendars.ACCOUNT_NAME,
		       Calendars.CALENDAR_DISPLAY_NAME,
		       Calendars.NAME,
		       Calendars.CALENDAR_COLOR,
		       Calendars.CALENDAR_TIME_ZONE
		};
		
		Cursor calCursor = context.getContentResolver().query(uri, projection, Calendars.VISIBLE + " = 1",
				null, Calendars._ID + " ASC");
		
		if (calCursor.moveToFirst())
		{
			do
			{
				Clndr cal = new Clndr();
				
				cal._id = calCursor.getLong(0);
				cal.account_name = calCursor.getString(1);
				cal.display_name = calCursor.getString(2);
				cal.name = calCursor.getString(3);
				cal.color = calCursor.getInt(4);
				cal.timezone = calCursor.getString(5);
				
				cal.paint = new Paint();
				cal.paint.setColor(cal.color);
				cal.paint.setStyle(Style.FILL);
				
				ourCalendars.add(cal);
				CalendarsMap.put(cal._id, cal);

			} while (calCursor.moveToNext());
		}
	}
}
