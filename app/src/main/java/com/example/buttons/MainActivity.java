package com.example.buttons;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.widget.RadioButton;
//for spinner dropdown
import android.widget.Spinner;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
//for calendar
import java.util.Calendar;
import android.app.DatePickerDialog;
import android.widget.DatePicker;
//for time picker
import android.app.TimePickerDialog;
import android.widget.TimePicker;




import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ToggleButton toggleButton1, toggleButton2;
    private Switch sw1,sw2;
    private TextView txt1, txt2;
    private CheckBox pizza, burger, chicken;
    private AlertDialog.Builder builder;
    private RadioButton rbGender;
    private RadioGroup radioGroup;
    private TextView tvDate, tvTime;
    private Button datebtn;
    private DatePickerDialog datePickerDialog;
    TimePickerDialog picker;
    TimePicker timePicker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton1=(ToggleButton) findViewById(R.id.toggleButton1);
        toggleButton2=(ToggleButton) findViewById(R.id.toggleButton2);
        sw1 = (Switch) findViewById(R.id.switch1);
        sw2 = (Switch) findViewById(R.id.switch2);
        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        pizza = (CheckBox) findViewById(R.id.pizza);
        burger = (CheckBox) findViewById(R.id.burger);
        chicken = (CheckBox) findViewById(R.id.chicken);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
        tvDate = (TextView) findViewById(R.id.textViewdate);
        builder = new AlertDialog.Builder(this);


        //for spinner
        final String[] country = {"Please Select a Country!","USA", "Canada", "Philippines", "Japan",
        "China","OTHER"};
        ArrayAdapter<String> stringArrayAdapter=new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item, country);
        Spinner spinner = (Spinner)findViewById(R.id.spinner);
        spinner.setAdapter(stringArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(getApplicationContext(),country[position] + " selected!", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                // Get the selected Radio Button
                RadioButton rbGender = radioGroup.findViewById(checkedId);
                Toast.makeText(MainActivity.this, rbGender.getText(), Toast.LENGTH_SHORT).show();

            }
        });

        //for date picker(Spinner Style)
        initDatePicker();
        datebtn = findViewById(R.id.btnDatePicker);


        //for timepicker
        timePicker=(TimePicker)findViewById(R.id.timePicker);
        tvTime = findViewById(R.id.tvTimeDisplay);
        tvTime.setText(getCurrentTime());


    }
    //for timepicker
    private String getCurrentTime(){
        String currentTime = timePicker.getCurrentHour() + ":" + timePicker.getCurrentMinute();
        return currentTime;
    }
    public void changeTime(View view){
        tvTime.setText(getCurrentTime());
    }

    public void callTime(View view){
        final Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);
        picker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int sHour, int sMinute) {
                tvTime.setText(sHour + ":" + sMinute);
            }
        }, hour, min, true);
        picker.show();

    }


    //for date picker(Spinner Style)
    private String getTodaysDate(){
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month+1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }



    private void initDatePicker(){
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month+1;
                String date =makeDateString(day, month, year);
                datebtn.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style=AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style,dateSetListener,year,month,day);
    }

    private String makeDateString(int day, int month, int year){
        return getMonthFormat(month) +" " + day + " " + year ;

    }

    private String getMonthFormat(int month){
        if (month == 1)
            return "JAN";
        if (month == 2 )
            return "FEB";
        if (month == 3 )
            return "MAR";
        if (month == 4 )
            return "APR";
        if (month == 5)
            return "MAY";
        if (month == 6 )
            return "JUN";
        if (month == 7 )
            return "JUL";
        if (month == 8 )
            return "AUG";
        if (month == 9 )
            return "SEP";
        if (month == 10 )
            return "OCT";
        if (month == 11 )
            return "NOV";
        if (month == 12 )
            return "DEC";

        //defualt val
        return "JAN";
    }
    public void openDatePicker(View view){
        datePickerDialog.show();
    }
    //end of date spinner button

    public void onClickShow(View view){
        LayoutInflater Li = getLayoutInflater();
        View layout = Li.inflate(R.layout.customtoastlayout,(ViewGroup) findViewById(R.id.custom_toast_layout));

        Toast toast = new Toast(getApplicationContext());
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(layout);
        toast.show();
    }
    public void toggleClick1(View view){
        StringBuilder result = new StringBuilder();
        result.append("ToggleButton1 : ").append(toggleButton1.getText());
        Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_SHORT).show();
    }
    public void toggleClick2(View view){
        StringBuilder result = new StringBuilder();
        result.append("ToggleButton2 : ").append(toggleButton2.getText());
        Toast.makeText(getApplicationContext(), result.toString(),Toast.LENGTH_SHORT).show();
    }
    public void switchClick1(View view){
        String str1;
        if (sw1.isChecked()) {
            str1 = sw1.getTextOn().toString();
            txt1.setText("Switch 1 is " + str1);
        }else {
            str1 = sw1.getTextOff().toString();
            txt1.setText("Switch 1 is " + str1);
        }
        Toast.makeText(getApplicationContext(), "Switch1 -  " + str1 + " \n",Toast.LENGTH_SHORT).show();
    }
    public void switchClick2(View view){
        String str2;
        if (sw2.isChecked()) {
            str2 = sw2.getTextOn().toString();
            txt2.setText("Switch 2 is " + str2);
        }else {
            str2 = sw2.getTextOff().toString();
            txt2.setText("Switch 2 is " + str2);
        }
        Toast.makeText(getApplicationContext(), "Switch2 -  " + str2 + " \n",Toast.LENGTH_SHORT).show();
    }

    public void ShowGender(View view){
        int selectedId = radioGroup.getCheckedRadioButtonId();
        rbGender = (RadioButton) findViewById(selectedId);

        if(selectedId==-1){
            Toast.makeText(MainActivity.this,"Nothing selected", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this,rbGender.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public void orderClick(View view){
        builder.setMessage("You ordered the following:") .setTitle("Pizza Ordering Form");
        int totalamount=0;
        StringBuilder result=new StringBuilder();
        result.append("Selected Items:");
        if(burger.isChecked()){
            builder.setMessage(result.append("\nBurger 60PhP"));
            totalamount+=60;
        }
        if(pizza.isChecked()){
            builder.setMessage(result.append("\nPizza 100PhP"));
            totalamount+=100;
        }
        if(chicken.isChecked()){
            builder.setMessage(result.append("\nChicken 50PhP"));
            totalamount+=50;
        }

        builder.setMessage(result.append("\nTotal Amount "+totalamount));
        builder.setMessage(result.append("\n\n\t\t\t\t\tPay Now? "));

        //Setting message manually and performing action on button click
        builder.setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(getApplicationContext(),"Payment Made!",
                                Toast.LENGTH_SHORT).show();
                        //uncheck the checkbox
                        burger.setChecked(false);
                        pizza.setChecked(false);
                        chicken.setChecked(false);
                        //System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"You chose to order more",
                                Toast.LENGTH_SHORT).show();
                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("Order Confirmation");
        alert.show();
    }

    public void close(View view) {
        //Uncomment the below code to Set the message and title from the strings.xml file
        builder.setMessage(R.string.dialog_message) .setTitle(R.string.dialog_title);
//Setting message manually and performing action on button click
        builder.setMessage("Do you want to close this application?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finish();
                        Toast.makeText(getApplicationContext(),"You choose Yes action for alertbox",
                                Toast.LENGTH_SHORT).show();
                        //System.exit(0);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();
                        Toast.makeText(getApplicationContext(),"You choose no action for alertbox",
                                Toast.LENGTH_SHORT).show();
                    }
                });
//Creating dialog box
        AlertDialog alert = builder.create();
//Setting the title manually
        alert.setTitle("Exit App?z");
        alert.show();
    }

    public void btnPickDate(View view){
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                MainActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int monthofyear, int dayofmonth) {
                        tvDate.setText((monthofyear+1) + "/" + dayofmonth+ "/" + year);
                    }
                },
                year,month,day);
        datePickerDialog.show();
    }


}