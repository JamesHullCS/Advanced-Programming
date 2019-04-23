package com.example.vehicleapp;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

/*----------------------------------------------------------------------- <Created by James Hull, 17034209> --------------------------------------------------------------------*/

/*------------------------------------------------------------------------------------ <start of class> ------------------------------------------------------------------------*/

/*-------------------------------------------------- <The point of this class is to get all the details of a specific car > ----------------------------------------------------*/

public class DetailsActivity extends AppCompatActivity
{
/* ---------------------------------------------- <Globally declaring the variable so that I can access them thought the program> ----------------------------------------------*/
    Vehicle theVehicle;

/*---------------------------------------------------------------------------------- <onCreate> --------------------------------------------------------------------------------*/
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

/*--------------------------------------------------------------------------------- <Get the intent> ----------------------------------------------------------------------------*/
        Bundle extras = getIntent().getExtras();

/* ------------ <This is creating an instance of the TextView from activity_details.xml in this class. So that I can use them to place in my Vehicle database parameters.> ------*/
        final TextView vehicle_id = findViewById(R.id.vehicle_id);
        final TextView make = findViewById(R.id.make);
        final TextView model = findViewById(R.id.model);
        final TextView year = findViewById(R.id.year);
        final TextView price = findViewById(R.id.price);
        final TextView license_number = findViewById(R.id.license_number);
        final TextView colour = findViewById(R.id.colour);
        final TextView number_doors = findViewById(R.id.number_doors);
        final TextView transmission = findViewById(R.id.transmission);
        final TextView mileage = findViewById(R.id.mileage);
        final TextView fuel_type = findViewById(R.id.fuel_type);
        final TextView engine_size = findViewById(R.id.engine_size);
        final TextView body_style = findViewById(R.id.body_style);
        final TextView condition = findViewById(R.id.condition);
        final TextView notes = findViewById(R.id.notes);
        final TextView sales = findViewById(R.id.sales);

        theVehicle = (Vehicle) extras.get("vehicle");

/*---- <I am setting the vehicle parameters as Text so that then I can then display them in my TextView. For all values that are not Strings for example an integer.> -----------*/
/*----------------------------------------------------- <I must first convert them to a string to set them as text.> ------------------------------------------------------------*/
        vehicle_id.setText(Integer.toString(theVehicle.getVehicle_id()));
        make.setText(theVehicle.getMake());
        model.setText(theVehicle.getModel());
        year.setText(Integer.toString(theVehicle.getYear()));
        price.setText(Integer.toString(theVehicle.getPrice()));
        license_number.setText(theVehicle.getLicense_number());
        colour.setText(theVehicle.getColour());
        number_doors.setText(Integer.toString(theVehicle.getNumber_doors()));
        transmission.setText(theVehicle.getTransmission());
        mileage.setText(Integer.toString(theVehicle.getMileage()));
        fuel_type.setText(theVehicle.getFuel_type());
        engine_size.setText(Integer.toString(theVehicle.getEngine_size()));
        body_style.setText(theVehicle.getBody_style());
        condition.setText(theVehicle.getCondition());
        notes.setText(theVehicle.getNotes());
        sales.setText(Boolean.toString(theVehicle.getSales()));
    }

/*---------------------------------------------- <Creates the action bar menu so That i can then create the action button handler.> --------------------------------------------*/
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar2, menu);
        return true;
    }

    /*---------------------------------------------- <This boolean method allows me to determine whether my action button has been clicked> ----------------------------------------*/
    public boolean onOptionsItemSelected(MenuItem action_update)
    {
/*--------------------------------------------- <This switch case allows me to click on the button and use it to insert a new vehicle.> -----------------------------------------*/
        switch (action_update.getItemId())
        {
            case R.id.action_update:
/*---------------------------------------------- <Declares an intent and says to send the user of the app to the putActivity> -------------------------------------------------*/
                Intent intent = new Intent(getApplicationContext(), putActivity.class);

/*-------------------------------- <put the selected vehicle object in to the intent which will be passed over to the activity that is started> --------------------------------*/
                intent.putExtra("vehicle",theVehicle);

/*----------------------------------------------------------------------- <Runs the Intent/Activity> --------------------------------------------------------------------------*/
                startActivity(intent);
                return true;

            default:
/* -------------------------------------------<If we got here, the user's action was not recognized. Invoke the superclass to handle it.> --------------------------------------*/
                return super.onOptionsItemSelected(action_update);
        }
    }
}
