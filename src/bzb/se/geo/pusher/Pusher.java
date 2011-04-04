package bzb.se.geo.pusher;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class Pusher implements Runnable {
	
	private int hubId;
	
	public Pusher (int hubId) {
		this.hubId = hubId;
	}
	
	public void run() {
		upload(grab());
	}
	
	private ArrayList<Reading> grab () {
		ArrayList<Reading> readings = new ArrayList<Reading>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection conn = DriverManager.getConnection("jdbc:mysql:///livewiredb", "triouser", "green");
			PreparedStatement ps = conn.prepareStatement("select * from viewliveconsumption");
			ResultSet result = ps.executeQuery();
			while (result.next()) {
				readings.add(new Reading(result.getString("SensorID"), result.getString("SensorName"), result.getString("Amps"), result.getString("Voltage"), result.getString("ReactivePower"), result.getString("CumulativePower"), result.getString("PowerFactor"), result.getString("LastReading")));
			}
			result.close();
			ps.close();
			conn.close();
			conn = null;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return readings;
	}
	
	private void upload (ArrayList<Reading> readings) {
		System.out.println("Starting upload");
		HttpClient httpClient = new DefaultHttpClient();
		Iterator<Reading> i = readings.iterator();
		while (i.hasNext()) {
			Reading reading = i.next();
			HttpPost httpPost = new HttpPost("http://79.125.20.47:8080/EnergyGraph/EnergyFeed");
			List <NameValuePair> parameters = new ArrayList <NameValuePair>();
			parameters.add(new BasicNameValuePair("hubId",String.valueOf(hubId)));
			parameters.add(new BasicNameValuePair("meterId",reading.getSensorId()));
			parameters.add(new BasicNameValuePair("time",reading.getLastReading()));
			parameters.add(new BasicNameValuePair("temp","0"));
			parameters.add(new BasicNameValuePair("load",reading.getReactivePower()));
	
			UrlEncodedFormEntity sendentity;
			try 
			{
				sendentity = new UrlEncodedFormEntity(parameters, HTTP.UTF_8);
				httpPost.setEntity(sendentity);
				httpClient.execute(httpPost);
			} catch (Exception e)
			{
				e.printStackTrace();		
			}
		}
		System.out.println("Upload done");
	}

}
