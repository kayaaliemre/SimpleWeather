package com.example.kaya.simpleweather;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView txt_Sehir, txt_Sicaklik, txt_Weather, txt_Aciklama;
    private Spinner spin;
    private ImageView image;
    String sehir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_Sehir = (TextView) findViewById(R.id.txt_sehir);
        txt_Aciklama = (TextView) findViewById(R.id.txt_aciklama);
        txt_Sicaklik = (TextView) findViewById(R.id.txt_sicaklik);
        txt_Weather = (TextView) findViewById(R.id.txt_weather);
        image = (ImageView) findViewById(R.id.imageView);
        spin = (Spinner) findViewById(R.id.spinner);

        ArrayList<String> spinList = new ArrayList<>();
        spinList.add("adana");
        spinList.add("ADIYAMAN");
        spinList.add("AFYON");
        spinList.add("AGRI");
        spinList.add("AMASYA");
        spinList.add("ANKARA");
        spinList.add("ANTALYA");
        spinList.add("ARTVIN");
        spinList.add("BALIKESIR");
        spinList.add("BILECIK");
        spinList.add("BINGOL");
        spinList.add("BITLIS");
        spinList.add("BOLU");
        spinList.add("BURDUR");
        spinList.add("BURSA");
        spinList.add("CANKIRI");
        spinList.add("CORUM");
        spinList.add("DENIZLI");
        spinList.add("DIYARBAKIR");
        spinList.add("EDIRNE");
        spinList.add("ELAZIG");

        spinList.add("ERZINCAN");
        spinList.add("ERZURUM");
        spinList.add("ESKISEHIR");
        spinList.add("GAZIANTEP");
        spinList.add("GIRESUN");
        spinList.add("GUMUSHANE");
        spinList.add("HAKKARI");
        spinList.add("HATY");
        spinList.add("ISPARTA");
        spinList.add("ICEL");
        spinList.add("ISTANBUL");
        spinList.add("IZMIR");
        spinList.add("KARS");
        spinList.add("KASTAMONU");
        spinList.add("KAYSERI");
        spinList.add("KIRKLARELI");
        spinList.add("KIRSEHIR");
        spinList.add("KOCAELI");
        spinList.add("KONYA");
        spinList.add("KUTAHYA");
        spinList.add("MALATYA");
        spinList.add("MANISA");
        spinList.add("KAHRAMANMARAS");
        spinList.add("MARDIN");
        spinList.add("MUGLA");
        spinList.add("MUS");
        spinList.add("NEVSEHIR");
        spinList.add("NIGDE");
        spinList.add("ORDU");
        spinList.add("RIZE");
        spinList.add("SAKARYA");
        spinList.add("SAMSUN");
        spinList.add("SIIRT");
        spinList.add("SINOP");
        spinList.add("SIVAS");
        spinList.add("TEKIRDAG");
        spinList.add("TOKAT");
        spinList.add("TRABZON");
        spinList.add("TUNCELİ");
        spinList.add("SANLIURFA");
        spinList.add("USAK");
        spinList.add("VAN");
        spinList.add("YOZGAT");
        spinList.add("ZONGULDAK");
        spinList.add("AKSARAY");
        spinList.add("BAYBURT");
        spinList.add("KARAMAN");
        spinList.add("KIRIKKALE");
        spinList.add("BATMAN");
        spinList.add("SIRNAK");
        spinList.add("BARTIN");
        spinList.add("ARDAHAN");
        spinList.add("IGDIR");
        spinList.add("YALOVA");
        spinList.add("KARABUK");
        spinList.add("KILIS");
        spinList.add("OSMANIYE");
        spinList.add("DUZCE");



        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinList);
        spin.setAdapter(adapter);


        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                String itemSelected = adapterView.getItemAtPosition(i).toString();
                sehir = String.valueOf(itemSelected);
                new JsonParse ().execute();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    protected class JsonParse extends AsyncTask<Void, Void, Void>{
        String result_main ="";
        String result_description = "";
        String result_icon = "";
        int result_temp;
        String result_city;
        Bitmap bitImage;
        @Override
        protected Void doInBackground(Void... params) {
            String result="";
            try {
                URL weather_url = new URL("http://api.openweathermap.org/data/2.5/weather?q="+sehir+"&appid=5519df78a91952f50079565124888a76");
                BufferedReader bufferedReader = null;
                bufferedReader = new BufferedReader(new InputStreamReader(weather_url.openStream()));
                String line = null;
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }
                bufferedReader.close();

                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("weather");
                JSONObject jsonObject_weather = jsonArray.getJSONObject(0);
                result_main = jsonObject_weather.getString("main");
                result_description = jsonObject_weather.getString("description");
                result_icon = jsonObject_weather.getString("icon");

                JSONObject jsonObject_main = jsonObject.getJSONObject("main");
                Double temp = jsonObject_main.getDouble("temp");

                result_city = jsonObject.getString("name");

                result_temp = (int) (temp-273);

                URL icon_url = new URL("http://openweathermap.org/img/w/"+result_icon+".png");
                bitImage = BitmapFactory.decodeStream(icon_url.openConnection().getInputStream());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            txt_Sicaklik.setText(String.valueOf(result_temp));
            txt_Weather.setText(result_main);
            txt_Sehir.setText(result_city);
            txt_Aciklama.setText(result_description);
            image.setImageBitmap(bitImage);
            super.onPostExecute(aVoid);
        }
    }
}