package com.example.myself.myapplication;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import static java.lang.System.in;

public class WeatherForecast extends Activity {


    protected static final String ACTIVITY_NAME = "Weather Forecast";
    private ProgressBar progressBar;
    private ImageView weatherImage;
    private TextView currentTempText;
    private TextView minTempText;
    private TextView maxTempText;
    private TextView windSpeedText;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);
        progressBar = (ProgressBar) findViewById(R.id.progressBarId);
        progressBar.setVisibility(View.VISIBLE);
        weatherImage = (ImageView) findViewById(R.id.imageViewCurrentWeatherid);
        currentTempText = (TextView) findViewById(R.id.textviewCurrentTemid);
        minTempText = (TextView) findViewById(R.id.textViewMinTemId);
        maxTempText = (TextView) findViewById(R.id.textViewMaxTemId);
        windSpeedText = (TextView) findViewById(R.id.wind);
        ForecastQuery forecast = new ForecastQuery();
        forecast.execute();

    }


    //inner class

    public class ForecastQuery extends AsyncTask<String, Integer, String> {

        private String minTemp;
        private String maxTemp;
        private String windSpeed;
        private String currentTemp;
        private String iconName;
        private Bitmap bitmap;

        @Override
        protected String doInBackground(String... args) {
            try {

                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000); //in milliseconds
                conn.setConnectTimeout(15000); //in milliseconds
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                //test
                Log.d(ACTIVITY_NAME, "connecting with url..");
                //Starts the query
                conn.connect();
                //test
                InputStream stream = conn.getInputStream();
                Log.d(ACTIVITY_NAME, "reading stream");
                //test
                Log.d(ACTIVITY_NAME, "stream is: " + stream);
                XmlPullParser parser = Xml.newPullParser();
                parser.setInput(stream, null);
                while (parser.next() != XmlPullParser.END_DOCUMENT) {
                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                        continue;
                    }

                    if (parser.getName().equals("temperature")) {
                        currentTemp = parser.getAttributeValue(null, "value");
                        android.os.SystemClock.sleep(500);//to slow down progress bar loading display
                        minTemp = parser.getAttributeValue(null, "min");
                        maxTemp = parser.getAttributeValue(null, "max");
                        publishProgress(25,50,75);
                        windSpeed = parser.getAttributeValue(null, "wind");

                    }

                    if (parser.getName().equals("speed")) {
                        windSpeed = parser.getAttributeValue(null, "value");

                    }
                    if (parser.getName().equals("weather")) {
                        iconName = parser.getAttributeValue(null, "icon");
                    }

                }
                conn.disconnect();
                String iconFile = iconName + ".png";
                if (fileExistence(iconFile)) {
                    FileInputStream fis = null;
                    try {
                        fis = new FileInputStream(getBaseContext().getFileStreamPath(iconFile));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    bitmap = BitmapFactory.decodeStream(fis);
                    Log.i(ACTIVITY_NAME, "Read the image from file");
                } else {
                    URL imageUrl = new URL("http://openweathermap.org/img/w/" + iconName + ".png");
                    conn = (HttpURLConnection) imageUrl.openConnection();
                    conn.connect();
                    stream = conn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(stream);
                    FileOutputStream outputStream = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                    outputStream.flush();
                    outputStream.close();
                    Log.i(ACTIVITY_NAME, "If the image is not exist. add from the file");
                }
                Log.i(ACTIVITY_NAME, "The name of the file " + iconFile);
                //publishProgress(100);
            } catch (FileNotFoundException fne) {
                Log.e(ACTIVITY_NAME, fne.getMessage());
            } catch (XmlPullParserException parserException) {
                Log.e(ACTIVITY_NAME, parserException.getMessage());
            } catch (IOException e) {
                Log.e(ACTIVITY_NAME, e.getMessage());
            }
            return null;
        }
//onProgressUpdate

        @Override

        protected void onProgressUpdate(Integer... value) {
            progressBar.setVisibility(View.VISIBLE);
            progressBar.setProgress(value[0]);
            Log.i(ACTIVITY_NAME, "In onProgressUpdate");
        }

        @Override

        protected void onPostExecute(String result) {
            progressBar.setVisibility(View.INVISIBLE);
            minTempText.setText("The Minimum Temperature "+minTemp+ "\u2103");
            maxTempText.setText("The Maximum Temperature " + maxTemp+ "\u2103");
            currentTempText.setText("The Current Temperature "+currentTemp+ "\u2103");
            windSpeedText.setText("The Wind Speed "+windSpeed+ " KHP");
            weatherImage.setImageBitmap(bitmap);
        }

        public boolean fileExistence(String fname) {
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }


    }
}

//connecting to url and reading data input stream





























//connecting to url and reading data input stream
//
//public class WeatherForecast extends Activity {
//    ProgressBar progressBar;
//    TextView textViewweather;
//    TextView textViewwind;
//
//    TextView textViewTempMin;
//    TextView textViewTemMax;
//    ImageView imageView;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_weather_forecast);
//        progressBar = (ProgressBar) findViewById(R.id.progressBarId);
//        progressBar.setVisibility(View.VISIBLE);
//        textViewweather=(TextView)findViewById(R.id.textviewCurrentTemid);
//        textViewTempMin=(TextView)findViewById(R.id.textViewMinTemId);
//        textViewTemMax=(TextView)findViewById(R.id.textViewMaxTemId);
//       textViewwind=(TextView)findViewById(R.id.wind);
//        ForecastQuery fquery = new ForecastQuery();
//        fquery.execute("http://api.openweathermap.org/data/2.5/weather?q=ottawa" +
//                ",ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric");
//
//    }
//
//    private class ForecastQuery extends AsyncTask<String, Integer, String> {
//        String windSpead, maxTemp, minTemp, currentTemp, iconName;/////////////////////////
//        Bitmap bitmap;
//
//        @Override
//        protected void onPostExecute(String s) {
//        textViewTemMax.setText(maxTemp);
//        textViewTempMin.setText(minTemp);
//        textViewweather.setText(currentTemp);
//        imageView.setImageBitmap(bitmap);
//        textViewwind.setText(windSpead);
//        progressBar.setVisibility(View.INVISIBLE);
//
//
//        }
//
//        @Override
//        protected void onProgressUpdate(Integer... values) {
//          //progressBar.setVisibility();
//            progressBar.setProgress(values[0]);
//        }
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//                URL url = new URL(strings[0]);
//
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//
//                InputStream inputStream = conn.getInputStream();
//
//
//                //try {
//                XmlPullParser parser = Xml.newPullParser();
//                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
//                parser.setInput(in, null);
//                int type;
//                while (parser.next() != XmlPullParser.END_DOCUMENT) {
//                    //  String name = parser.getName();
////                    Bitmap bitmap= new BitmapFactory();
//                    if (parser.getEventType() != XmlPullParser.START_TAG)
//                        continue;
//                    // if (name.equals("speed")) minTemp = parser.getAttributeValue("null", "min");
//                    if (parser.getName().equals("temperature"))
//                        currentTemp = parser.getAttributeValue("null", "value");
//                    maxTemp = parser.getAttributeValue("null", "max");
//                    minTemp = parser.getAttributeValue("null", "min");
//
//                    if (parser.getName().equals("speed"))
//                        windSpead = parser.getAttributeValue("null", "value");
//                    if (parser.getName().equals("weather"))
//                        iconName = parser.getAttributeValue("null", "icon");
//                    // String urlString = "http://openweathermap.org/img/w/" + iconName + ".png";
//                    //bitmap = getImage(urlString);
//                    iconName = parser.getAttributeValue(null, "icon");
//                    String iconFile = iconName + ".png";
////                        Bitmap image  = HTTPUtils.getImage(ImageURL));
//                    if (!(fileExistance(iconFile))) {
//                        FileOutputStream outputStream = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
//                        bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
//                        try {
//                            outputStream.flush();
//                            outputStream.close();
//                        } catch (IOException e1) {
//                            e1.printStackTrace();
//                        }
//
//                    }
//
//                    if (parser.getName().equals("icon"))
//                        windSpead = parser.getAttributeValue(String.valueOf(url), "name").toString();
//                    maxTemp = parser.getAttributeValue(String.valueOf(url), "");
//                 //   Log.i("", windSpead);
//                    publishProgress(100);
//                }
//
//                parser.next();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            publishProgress(25, 50, 75);
//
//                    return "";
//
//
//                }
//            }
//
//
//    public boolean fileExistance(String fname) {
//        File file = getBaseContext().getFileStreamPath(fname);
//        return file.exists();
//
//    }
//
//    public static Bitmap getImage(String urlString) {
//        try {
//            URL url = new URL(urlString);
//            return getImage(url);
//        } catch (MalformedURLException e) {
//            return null;
//        }
//    }
//
//    public static Bitmap getImage(URL url) {
//        HttpURLConnection connection = null;
//        try {
//            connection = (HttpURLConnection) url.openConnection();
//            connection.connect();
//            int responseCode = connection.getResponseCode();
//            if (responseCode == 200) {
//                return BitmapFactory.decodeStream(connection.getInputStream());
//            } else
//                return null;
//        } catch (Exception e) {
//            return null;
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//        }
//    }
//
//}
