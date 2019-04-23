package com.example.vehicleapp;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

/*--------------------------------------------------------------------------------- <Created by James Hull, 17034209> --------------------------------------------------------*/

/*------------------------------------------------------------------------------------------<start of class> -----------------------------------------------------------------*/

/*-------------------------------------------------------------------------- <This class is used for updating vehicles> -----------------------------------------------------*/

public class putActivity extends AppCompatActivity
{
/*-------------------------------------------------------------------------- <Creates global variables for theVehicle and the HashMap> --------------------------------------*/
    Vehicle theVehicle;
    final HashMap<String, String> params = new HashMap<>();

/*-------------------------------------------------------------------------------------------- <onCreate> --------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_put);

/*-------------------------------------------------------------------------------------- <Gets the intent> -------------------------------------------------------------------*/
        Bundle extras = getIntent().getExtras();
/*----------------------------------------- <Creates an instance of the EditText from the main activity so it can be manipulated in the program.> ----------------------------*/
        final EditText txtvehicle_id = findViewById(R.id.txtvehicle_id);
        final EditText txtmake = findViewById(R.id.txtmake);
        final EditText txtmodel = findViewById(R.id.txtmodel);
        final EditText txtyear = findViewById(R.id.txtyear);
        final EditText txtprice = findViewById(R.id.txtprice);
        final EditText txtlicense_number = findViewById(R.id.txtlicense_number);
        final EditText txtcolour = findViewById(R.id.txtcolour);
        final EditText txtnumber_doors = findViewById(R.id.txtnumber_doors);
        final EditText txttransmission = findViewById(R.id.txttransmission);
        final EditText txtmileage = findViewById(R.id.txtmileage);
        final EditText txtfuel_type = findViewById(R.id.txtfuel_type);
        final EditText txtengine_size = findViewById(R.id.txtengine_size);
        final EditText txtbody_style = findViewById(R.id.txtbody_style);
        final EditText txtcondition = findViewById(R.id.txtcondition);
        final EditText txtnotes = findViewById(R.id.txtnotes);
        final EditText txtsales = findViewById(R.id.txtsales);

/*-------------------------------------------------------------------- <Creates an instance of the button> -------------------------------------------------------------------*/
        Button btn = findViewById(R.id.btn);

/*-------------------------------------------------------------------- <Initialises the intent extra> ------------------------------------------------------------------------*/
        theVehicle = (Vehicle) extras.get("vehicle");

        txtvehicle_id.setText(Integer.toString(theVehicle.getVehicle_id()));
        txtmake.setText(theVehicle.getMake());
        txtmodel.setText(theVehicle.getModel());
        txtyear.setText(Integer.toString(theVehicle.getYear()));
        txtprice.setText(Integer.toString(theVehicle.getPrice()));
        txtlicense_number.setText(theVehicle.getLicense_number());
        txtcolour.setText(theVehicle.getColour());
        txtnumber_doors.setText(Integer.toString(theVehicle.getNumber_doors()));
        txttransmission.setText(theVehicle.getTransmission());
        txtmileage.setText(Integer.toString(theVehicle.getMileage()));
        txtfuel_type.setText(theVehicle.getFuel_type());
        txtengine_size.setText(Integer.toString(theVehicle.getEngine_size()));
        txtbody_style.setText(theVehicle.getBody_style());
        txtcondition.setText(theVehicle.getCondition());
        txtnotes.setText(theVehicle.getNotes());
        txtsales.setText(Boolean.toString(theVehicle.getSales()));


/*--------------------------------------------------------------------- <Event listener for the button> ----------------------------------------------------------------------*/
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*---------------------------------------------------------------------- <Declaring the use of a GSON> -----------------------------------------------------------------------*/
                Gson gson = new Gson();
/*------------------------------------------------ <Setting all the vehicle parameters as (Integer, String or Boolean)> ------------------------------------------------------*/
                Integer vehicle_idS = Integer.valueOf(txtvehicle_id.getText().toString());
                String txtmakeS = txtmake.getText().toString();
                String txtmodelS = txtmodel.getText().toString();
                Integer yearS = Integer.valueOf(txtyear.getText().toString());
                Integer priceS = Integer.valueOf(txtprice.getText().toString());
                String txtlicense_numberS = txtlicense_number.getText().toString();
                String colourS = txtcolour.getText().toString();
                Integer number_doorsS = Integer.valueOf(txtnumber_doors.getText().toString());
                String transmissionS = txttransmission.getText().toString();
                Integer mileageS = Integer.valueOf(txtmileage.getText().toString());
                String fuel_typeS = txtfuel_type.getText().toString();
                Integer engine_sizeS = Integer.valueOf(txtengine_size.getText().toString());
                String body_styleS = txtbody_style.getText().toString();
                String conditionS = txtcondition.getText().toString();
                String notesS = txtnotes.getText().toString();
                Boolean salesS = Boolean.valueOf(txtsales.getText().toString());

/*------------------------------------------------------------------- <Creating the vehicle object> ---------------------------------------------------------------------------*/
                Vehicle v = new Vehicle(vehicle_idS, txtmakeS, txtmodelS, yearS, priceS, txtlicense_numberS,
                        colourS, number_doorsS, transmissionS, mileageS, fuel_typeS, engine_sizeS,
                        body_styleS, conditionS, notesS, salesS);

/*------------------------------------------------------------- <Converting the vehicles objects into Json format> ------------------------------------------------------------*/
                String VehicleJson = gson.toJson(v);
                System.out.println(VehicleJson);
                params.put("json", VehicleJson);
                //String url = "http://10.0.2.2:8005/vehicles/api";
                //performPostCall(url, params);


                PutData putData = new PutData();
                putData.execute(params);
            }
        });
    }
/*--------------------------------------------------------------------<Method for connecting to the servlet api> ----------------------------------------------------------*/
    public Boolean performPutCall(String requestURL, HashMap<String, String> postDataParams) {
/*------------------------------------------------------------ <Declaring local variables for the URL and response> ------------------------------------------------------------*/
        URL url;
        String response = "";
        try
        {
            url = new URL(requestURL);

/*--------------------------------------------------------------------- <CREATE THE CONNECTION OBJECT> -------------------------------------------------------------------------*/
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("PUT");
            conn.setDoInput(true);
            conn.setDoOutput(true);

/*-------------------------------------------------- <write/send/post data to the connection using output stream and buffered writer> ------------------------------------------*/
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

/*------------------------------------------------------- <Write/send/POST key/value data (url encoded) to the server> ---------------------------------------------------------*/
            writer.write(getPutDataString(postDataParams));

/*--------------------------------------------------------------------- <Clear the writer> -------------------------------------------------------------------------------------*/
            writer.flush();
            writer.close();
/*------------------------------------------------------------------- <Close output stream> ------------------------------------------------------------------------------------*/
            os.close();

/*------------------------------------------- <Get the server response code to determine what to do next (i.e. success or error)> ----------------------------------------------*/
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode = " + responseCode);

/*------------------------------------------------------------ <IF connection successful then the vehicle will be UPDATED> -----------------------------------------------------*/
            if (responseCode == HttpsURLConnection.HTTP_OK)
            {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null)
                {
                    response += line;
                }
            }

/*----------------------------------------------------------- <IF connection failed then the vehicle will fail to be UPDATED> --------------------------------------------------*/
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

    private String getPutDataString(HashMap<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet())
        {
            if (first)
            {
                first = false;
            }
            else
            {
                result.append("&");
            }
            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }
        return result.toString();
    }

    private class PutData extends AsyncTask<HashMap<String, String>, Integer, Boolean>
    {
        @Override
        protected Boolean doInBackground(HashMap<String, String>... postDataParams)
        {
                String url = ("http://10.0.2.2:8005/vehicles/api");
                return performPutCall(url, postDataParams[0]);
            }

            protected void onPostExecute(Boolean Checked)
            {
                if (Checked == true)
                {
                    Toast.makeText(putActivity.this, "Vehicle Update :)", Toast.LENGTH_LONG).show();
                } else
                    {
                    Toast.makeText(putActivity.this, "Error failed to updating vehicle :(", Toast.LENGTH_LONG).show();
                }
            }
    }
}

