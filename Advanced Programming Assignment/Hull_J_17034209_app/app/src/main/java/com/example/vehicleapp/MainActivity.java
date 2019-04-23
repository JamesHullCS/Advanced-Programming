package com.example.vehicleapp;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;

/*----------------------------------------------------------------------- <Created by James Hull, 17034209> --------------------------------------------------------------------*/

/*----------------------------------------------------------------------------- <start of class> -------------------------------------------------------------------------------*/

/*---------------------------------------------------- <The point of this class is to display and delete vehicles> -------------------------------------------------------------*/

public class MainActivity extends AppCompatActivity
{
/*-------------------------------------------------- <Globally declaring these variables so that I can access them in all methods> ---------------------------------------------*/

    String[] vehicleNames = null;
    ListView vehicleList;
    ArrayList<Vehicle> allVehicles = new ArrayList<>();

/*---------------------------------------------------------------------------------- <onCreate> --------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

/*--------------------------------------------------------------------------- <Calling the Async Method> -----------------------------------------------------------------------*/
        getData getData = new getData();
/*------------------------------------------------------- <Executing the method also known as the thread running the tasks> ----------------------------------------------------*/
        getData.execute();
/*------------------------------------------------------------------------- <Initialising the vehicleList> ---------------------------------------------------------------------*/
        vehicleList = findViewById(R.id.vehicleList);

/*----------------- Onclick event that allows me to go to get a description of a vehicle that I have clicked by redirecting to the DetailsActivity class -----------------------*/
        vehicleList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {

/*--------------------------------------------------------- <sends a message that says for example "you pressed 20"> -----------------------------------------------------------*/
                Toast.makeText(MainActivity.this, "you pressed " + allVehicles.get(i).getVehicle_id(), Toast.LENGTH_SHORT).show();

/*-------------------------------------------- <Declares an intent and says to send the user of the app to the DetailsActivity> -----------------------------------------------*/
                Intent intent = new Intent(getApplicationContext(), DetailsActivity.class);

/*-------------------------------- <Put the selected vehicle object in to the intent which will be passed over to the activity that is started> --------------------------------*/
                intent.putExtra("vehicle", allVehicles.get(i));

/*-------------------------------- --------------------------------------- <Runs the Intent/Activity> -------------------------------------------------------------------------*/
                startActivity(intent);
            }
        });

/*------------------------------------------ <An on long click listener that allows me to delete a vehicle from the list> ------------------------------------------------------*/
        vehicleList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view,final int i, long l)
            {

/*----------------------------------- <An alert dialog that checks if the user wants to delete or not> -------------------------------------------------------------------------*/
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setTitle("Dialog on Android");
                dialog.setMessage("Are you sure you want to delete this entry?" );
                dialog.setPositiveButton("Delete", new DialogInterface.OnClickListener()
                {
                    @Override
/*------------------------------------------- <The onClick method that is run when the user wants to delete a vehicle> ---------------------------------------------------------*/
                    public void onClick(DialogInterface dialog, int id)
                    {
/*------------------------------------------- <Gets the id based on the vehicle in the ListView that you'll long click> --------------------------------------------------------*/
                        id = allVehicles.get(i).getVehicle_id();
                        DeleteData deleteData = new DeleteData();
                        /*------------------------------------------------------- <Executing the method also known as the thread running the tasks> ----------------------------------------------------*/
                        deleteData.execute(id);
                    }
                })
/*------------------------------------------- <The onClick method that is run when the user wants to do nothing but go back to the ListView> ------------------------------------*/
                        .setNegativeButton("Cancel ", new DialogInterface.OnClickListener()
                        {
                            @Override

                            public void onClick(DialogInterface dialog, int which)
                            {
/*----------------------------------------------------------- <This is empty so that it will do nothing if you press Cancel> ----------------------------------------------------*/
                            }
                        });

                final AlertDialog alert = dialog.create();
                alert.show();
                return true;
            }
        });


    }

/*--------------------------------------------------------------------------- <changes the stream to a string> -----------------------------------------------------------------*/
    public String convertStreamToString(InputStream is)
    {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

/*----------------------------------------------- <Initialises the action bar menu so that i can then create the action button handler.> ---------------------------------------*/
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return true;
    }

/*---------------------------------------------- <This boolean method allows me to determine whether my action button has been clicked> ----------------------------------------*/
    public boolean onOptionsItemSelected(MenuItem action_favorite)
    {
/*--------------------------------------------- <This switch case allows me to click on the button and use it to insert a new vehicle.> ----------------------------------------*/
        switch (action_favorite.getItemId())
        {
            case R.id.action_favorite:
/*---------------------------------------------- <Declares an intent and says to send the user of the app to the postActivity> -------------------------------------------------*/
                Intent intent = new Intent(getApplicationContext(), postActivity.class);

/*----------------------------------------------------------------------- <Runs the Intent/Activity> ---------------------------------------------------------------------------*/
                startActivity(intent);
                return true;

            default:
/*---------------------------------------- <If we got here, the user's action was not recognized. Invoke the superclass to handle it.> -----------------------------------------*/
                return super.onOptionsItemSelected(action_favorite);
        }
    }
/*------------------------------------------------------ <Async task used for network calls for getting the list data> ---------------------------------------------------------*/
    private class getData extends AsyncTask<Void, Void, String[]>
    {
/*-------------------------------------------------------------- <doInBackground does the get network call on a secondary thread> ------------------------------------------*/
        @Override
        protected String[] doInBackground(Void... v)
        {
/*------------------------------------------------------------------------------- <Making a http call> -------------------------------------------------------------------------*/
            HttpURLConnection urlConnection;
            InputStream in = null;

            try
            {
/*------------------------------------------------------------------------- <The url we wish to connect to> --------------------------------------------------------------------*/
                URL url = new URL("http://10.0.2.2:8005/vehicles/api");
/*------------------------------------------------------------------ <Open the connection to the specified URL> ----------------------------------------------------------------*/
                urlConnection = (HttpURLConnection) url.openConnection();
/*------------------------------------------------------------ <Get the response from the server in an input stream> -----------------------------------------------------------*/
                in = new BufferedInputStream(urlConnection.getInputStream());
            }

            catch (IOException e)
            {
                e.printStackTrace();
            }
/*------------------------------------------------------------------ <Covert the input stream to a string> ---------------------------------------------------------------------*/
                String response = convertStreamToString(in);
/*------------------------------------------------------------- <Print the response to android monitor/log cat> ----------------------------------------------------------------*/
                System.out.println("Server response = " + response);

            try {
/*---------------------------- <Declare a new json array and pass it the string response from the server this will convert the string into a JSON array> -----------------------*/
                JSONArray jsonArray = new JSONArray(response);
/*---------------------------------- <Instantiate the cheeseNames array and set the size to the amount of cheese object returned by the server> --------------------------------*/
                vehicleNames = new String[jsonArray.length()];
/*------------------------------------------------------------ <A for loop to iterate over the JSON array> ---------------------------------------------------------------------*/
                for (int i=0; i < jsonArray.length(); i++)
                {
/*-------------------- <The following line of code will get the name of the cheese from the current JSON object and store it in a string variable called name> -----------------*/
                    int vehicle_id = Integer.parseInt(jsonArray.getJSONObject(i).get("vehicle_id").toString());
                    String make = jsonArray.getJSONObject(i).get("make").toString();
                    String model = jsonArray.getJSONObject(i).get("model").toString();
                    int year = Integer.parseInt(jsonArray.getJSONObject(i).get("year").toString());
                    int price = Integer.parseInt(jsonArray.getJSONObject(i).get("price").toString());
                    String license_number = jsonArray.getJSONObject(i).get("license_number").toString();
                    String colour = jsonArray.getJSONObject(i).get("colour").toString();
                    int number_doors = Integer.parseInt(jsonArray.getJSONObject(i).get("number_doors").toString());
                    String transmission = jsonArray.getJSONObject(i).get("transmission").toString();
                    int mileage = Integer.parseInt(jsonArray.getJSONObject(i).get("mileage").toString());
                    String fuel_type = jsonArray.getJSONObject(i).get("fuel_type").toString();
                    int engine_size = Integer.parseInt(jsonArray.getJSONObject(i).get("engine_size").toString());
                    String body_style = jsonArray.getJSONObject(i).get("body_style").toString();
                    String condition = jsonArray.getJSONObject(i).get("condition").toString();
                    String notes = jsonArray.getJSONObject(i).get("notes").toString();
                    boolean sales = Boolean.parseBoolean(jsonArray.getJSONObject(i).get("sales").toString());

/*----------------------------------------------------- <Add the name of the current cheese to the cheeseNames array> ----------------------------------------------------------*/
                    vehicleNames [i] = make+" "+model+" "+year+ " " +license_number;

/*------------------------------------------------------------------- <Creates the vehicles object> ----------------------------------------------------------------------------*/
                    Vehicle vehicle = new Vehicle(vehicle_id,model,make,year,price,license_number,colour,number_doors,transmission,mileage,fuel_type,engine_size,body_style,condition,notes,sales);
                    allVehicles.add(vehicle);
                }

            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
                return vehicleNames;
        }

/*---------------------- <onPostExecute is used for the array adapter which converts an ArrayList of objects into View items loaded into the ListView container. > ------------*/
        @Override
        protected void onPostExecute (String[] vehicleNames)
        {
            super.onPostExecute(vehicleNames);

            ArrayAdapter arrayAdapter = new ArrayAdapter(MainActivity.this, android.R.layout.simple_list_item_1, vehicleNames);
/*------------------------------------------------------------------------ <Set the adapter to the ListView> -------------------------------------------------------------------*/
            vehicleList.setAdapter(arrayAdapter);

        }
    }

/*------------------------------------------------------------------------ <Async task used for network calls for deleting from the ListView> ---------------------------------*/
    private class DeleteData extends AsyncTask<Integer, Void, Boolean>
                {
                    /*-------------------------------------------------------------- <doInBackground does the delete network call on a secondary thread> ------------------------------------------*/
                    @Override
                    protected Boolean doInBackground(Integer... id)
                    {
                        /*------------------------------------------------------------ <Declaring local variables for the URL and response> ------------------------------------------------------------*/
                        URL url;
                        String response = "";

                        try {
                            /*------------------------------------------- <The url that is used to send/retrieve data from the database using the Servlet api> ---------------------------------------------*/
                            url = new URL("http://10.0.2.2:8005/vehicles/api?id=" + id[0]);

                            /*--------------------------------------------------------------------- <CREATE THE CONNECTION OBJECT> -------------------------------------------------------------------------*/
                            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                            conn.setReadTimeout(15000);
                            conn.setConnectTimeout(15000);
                            conn.setRequestMethod("DELETE");
                            conn.setDoInput(true);
                conn.setDoOutput(true);

/*-------------------------------------------------- <write/send/post data to the connection using output stream and buffered writer> -----------------------------------------*/

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

/*------------------------------------------------------------------- <clear and close the writer> ----------------------------------------------------------------------------*/
                writer.flush();
                writer.close();
/*-------------------------------------------------------------------- <close output stream> ----------------------------------------------------------------------------------*/
                os.close();

/*--------------------------------------- <get the server  response code to determine what to do next (i.e. success or error)> ------------------------------------------------*/
                int responseCode = conn.getResponseCode();
                System.out.println("responseCode = " + responseCode);

/*------------------------------------------------------------ <IF connection successful then the vehicle will be deleted> ----------------------------------------------------*/
                if (responseCode == HttpsURLConnection.HTTP_OK)
                {

                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null)
                    {
                        response += line;
                    }
                }

/*----------------------------------------------------------- <IF connection failed then the vehicle will fail to be deleted> -------------------------------------------------*/
                else
                {
                    response = "";
                }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            System.out.println("response = " + response);
            return true;
        }

/*--------------------------- <Because we cant print toast messages in the doInBackground we do them in onPostExecute by checking if a boolean is true> -----------------------*/
        @Override
        protected void onPostExecute(Boolean Checked)
        {
            if (Checked == true)
            {
                Toast.makeText(getApplicationContext(), "Vehicle deleted :)", Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Error failed to delete vehicle :(", Toast.LENGTH_LONG).show();
            }
        }
    }
}

