package com.seproject.reservemac.ui.common;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.seproject.reservemac.R;
import com.seproject.reservemac.model.UserModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;


public class SearchFacilityActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    Button BtnSearchFacility;
    Button BtnDate;
    Button BtnTime;
    TimePickerDialog timePickerDialog;
    TextView header;
    Spinner SpinnerFType;
    String facilityCode = "MR";
    String faclity = "";
    Integer maxDate = 0;
    Calendar c = Calendar.getInstance();
    Boolean flag = Boolean.TRUE;
    String todaysdate="";
    String selectedate ="";
    HashMap<String, String> facilitycode = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_facility);
        SpinnerFType = findViewById(R.id.SpinnerFType);
        SpinnerFType.setSelection(0);
        BtnSearchFacility = findViewById(R.id.BtnSearchFacility);
        BtnDate = findViewById(R.id.EtxDatePicker);
        header = findViewById(R.id.activityheader);
        BtnTime = findViewById(R.id.EtxTimePicker);
        final UserModel usermodel = (UserModel) getIntent().getParcelableExtra("usermodel");

        if (usermodel.getRole().equals("fm")) {
            header.setText("Search Facility FM");
        } else
            header.setText("Search Facility");


        facilitycode.put("4 Multipurpose Rooms", "MR");
        facilitycode.put("5 Indoor Basketball Court", "IBBC");
        facilitycode.put("9 Volleyball Court", "IVBC");
        facilitycode.put("Indoor Soccer Gymnasium", "SCG");
        facilitycode.put("5 Racquetball Courts", "RBC");
        facilitycode.put("10 Badminton Courts", "BMC");
        facilitycode.put("Table Tennis", "TT");
        facilitycode.put("Conference Rooms", "CR");
        facilitycode.put("2 Outdoor Volleyball Courts", "OVBC");
        facilitycode.put("2 Outdoor Basketball Courts", "OBBC");

        SpinnerFType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                faclity = SpinnerFType.getSelectedItem().toString();

                facilityCode = facilitycode.get(faclity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                facilityCode = "MR";
                SpinnerFType.setSelection(0);
            }
        });


        BtnSearchFacility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(SearchFacilityActivity.this, "" + facilityCode, Toast.LENGTH_SHORT).show();
                Intent myintent = new Intent(getBaseContext(), ListOfFacilitiesActivity.class);

                myintent.putExtra("facilitycode", facilityCode);
                myintent.putExtra("date", BtnDate.getText());
                myintent.putExtra("time", BtnTime.getText());
                startActivity(myintent);
            }
        });


        BtnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "date picker");
            }
        });


        BtnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(SearchFacilityActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minutes) {
                        String Fname = SpinnerFType.getSelectedItem().toString();
                        String roundMinutes = "";
                        String roundHours = "";
                        String[] outdoor = new String[]{"2 Outdoor Volleyball Courts", "2 Outdoor Basketball Courts"};
                        if (Arrays.asList(outdoor).contains(Fname)) {
                            flag = Boolean.FALSE;                        /// setting if indoor or outdoor

                        }
                        int day = c.get(Calendar.DAY_OF_WEEK);          // got the day of the week

                        if(flag) {                                                                                                                                  ///check if indoor or not
                            if(hourOfDay>=c.get(Calendar.HOUR) && minutes>=c.get(Calendar.MINUTE) ) {                                                               ///check if after current time

                            if (day >= 2 && day <= 6)                                                                                                               //check if on weekdays
                            {
                                if (hourOfDay >= 6 && hourOfDay <= 23)                                                                                              // check if selected during open hours of weekdays
                                {
                                    roundHours = Integer.toString(hourOfDay);
                                    if (minutes <= 15) {
                                        roundMinutes = "00";
                                    } else if (minutes > 15 && minutes < 59) {
                                        roundMinutes = "30";
                                    }
                                    BtnTime.setText(roundHours + ":" + roundMinutes);
                                }
                                else
                                    Toast.makeText(getApplicationContext(), "MAC is closed at the selected time ", Toast.LENGTH_SHORT).show();              //booked during closed time
                            } else if (day == 1 || day == 7) {                  //check if on weekends
                                if (hourOfDay >= 12 && hourOfDay <= 23) {           //check if selected during open hours of weekends
                                    roundHours = Integer.toString(hourOfDay);
                                    if (minutes <= 15) {
                                        roundMinutes = "00";
                                    } else if (minutes > 15 && minutes < 59) {
                                        roundMinutes = "30";
                                    }
                                    BtnTime.setText(roundHours + ":" + roundMinutes);
                                }     //check if during mac open hours
                                else
                                    Toast.makeText(getApplicationContext(), "MAC is closed at the selected time ", Toast.LENGTH_SHORT).show();              //booking during closed time
                            }
                             }
                            else
                                Toast.makeText(getApplicationContext(), "Can't reserve in the past ", Toast.LENGTH_SHORT).show();
                        }
                        else{                                                                                                                                   //outdoor block
                             int foo = dayCalc(selectedate,todaysdate);
                             if(foo==0){                                                                                                                        //if outdoor today
                                 if(hourOfDay>=c.get(Calendar.HOUR) && minutes>=c.get(Calendar.MINUTE) ) {                                                      ///check if after current time

                                     if (day >= 2 && day <= 6)                                                                                                  //check if on weekdays
                                     {
                                         if (hourOfDay >= 6 && hourOfDay <= 23)                                                                                 //check if during working hours of weekday
                                         {
                                             roundHours = Integer.toString(hourOfDay);
                                             if (minutes <= 15) {
                                                 roundMinutes = "00";
                                             } else if (minutes > 15 && minutes < 59) {
                                                 roundMinutes = "30";
                                             }
                                             BtnTime.setText(roundHours + ":" + roundMinutes);
                                         }     //check if during mac open hours
                                         else
                                             Toast.makeText(getApplicationContext(), "MAC is closed at the selected time ", Toast.LENGTH_SHORT).show();
                                     }
                                     else if (day == 1 || day == 7)                                                                                         //check if on weekend
                                     {
                                         if (hourOfDay >= 12 && hourOfDay <= 23)                                                                            //check if working hours of weekend
                                         {
                                             roundHours = Integer.toString(hourOfDay);
                                             if (minutes <= 15) {
                                                 roundMinutes = "00";
                                             } else if (minutes > 15 && minutes < 59) {
                                                 roundMinutes = "30";
                                             }
                                             BtnTime.setText(roundHours + ":" + roundMinutes);
                                         }
                                         else
                                             Toast.makeText(getApplicationContext(), "MAC is closed at the selected time ", Toast.LENGTH_SHORT).show();
                                     }
                                 }
                                 else
                                     Toast.makeText(getApplicationContext(), "Can't reserve in the past ", Toast.LENGTH_SHORT).show();
                             }
                             else{

                                     if (day >= 2 && day <= 6) {                           //check if on weekdays
                                         if (hourOfDay >= 6 && hourOfDay <= 23) {
                                             roundHours = Integer.toString(hourOfDay);
                                             if (minutes <= 15) {
                                                 roundMinutes = "00";
                                             } else if (minutes > 15 && minutes < 59) {
                                                 roundMinutes = "30";
                                             }
                                             BtnTime.setText(roundHours + ":" + roundMinutes);
                                         }     //check if during mac open hours
                                         else
                                             Toast.makeText(getApplicationContext(), "MAC is closed at the selected time ", Toast.LENGTH_SHORT).show();
                                     } else if (day == 1 || day == 7) {
                                         if (hourOfDay >= 12 && hourOfDay <= 23) {
                                             roundHours = Integer.toString(hourOfDay);
                                             if (minutes <= 15) {
                                                 roundMinutes = "00";
                                             } else if (minutes > 15 && minutes < 59) {
                                                 roundMinutes = "30";
                                             }
                                             BtnTime.setText(roundHours + ":" + roundMinutes);
                                         }     //check if during mac open hours
                                         else
                                             Toast.makeText(getApplicationContext(), "MAC is closed at the selected time ", Toast.LENGTH_SHORT).show();
                                     }

                             }

                        }


                    }
                }, 0, 0, false);
                timePickerDialog.show();
            }
        });
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        flag = Boolean.FALSE;
        Calendar c = Calendar.getInstance();
        String Fname = SpinnerFType.getSelectedItem().toString();
        String[] outdoor = new String[]{"2 Outdoor Volleyball Courts", "2 Outdoor Basketball Courts"};
        if (Arrays.asList(outdoor).contains(Fname)) {
            flag = Boolean.TRUE;

            selectedate = dayOfMonth + " " + month + " " + year;
            todaysdate = c.get(Calendar.DAY_OF_MONTH) + " " + c.get(Calendar.MONTH) + " " + c.get(Calendar.YEAR);
            maxDate = dayCalc(selectedate,todaysdate);

            if (maxDate > 0) {
                if (maxDate > 6) {
                    Toast.makeText(getApplicationContext(), "Outdoor Facilities are limited to just 7 days from today", Toast.LENGTH_SHORT).show();
                } else {
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    c.set(Calendar.MONTH, month);
                    c.set(Calendar.YEAR, year);
                }
            } else
                Toast.makeText(getApplicationContext(), "Outdoor Facilities are can't be booked in the past", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Indoor Facilities are limited to just today's date ", Toast.LENGTH_SHORT).show();
        }
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateString = format1.format(c.getTime());
        BtnDate.setText(currentDateString);
    }

    public int dayCalc(String day1,String day2){
        int foo=0;
        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");
        try {
            Date date1 = myFormat.parse(day1);
            Date date2 = myFormat.parse(day2);
            long diff = date1.getTime() - date2.getTime();
            float days = diff / (1000 * 60 * 60 * 24);
            foo = (int) days;
        } catch (ParseException e) {
            e.printStackTrace();
        }
     return foo;
    }

    public String[] TimeFilter(int hourOfDay, int minutes,Boolean flag){

        String[] ans = new String[2];

        String roundMinutes = "";
        String roundHours = "";
        int day = c.get(Calendar.DAY_OF_WEEK);
        if(hourOfDay>=c.get(Calendar.HOUR) && minutes>=c.get(Calendar.MINUTE)) {
            if (day >= 2 && day <= 6) {
                if (hourOfDay >= 6 && hourOfDay <= 23) {
                    roundHours = Integer.toString(hourOfDay);
                    ans[0]=roundHours;
                    if (minutes <= 15) {
                        roundMinutes = "00";
                        ans[1]=roundMinutes;
//                        BtnTime.setText(roundHours + ":" + roundMinutes);
                    } else if (minutes > 15 && minutes < 59) {
                        roundMinutes = "30";
                        ans[1]=roundMinutes;
//                        BtnTime.setText(roundHours + ":" + roundMinutes);
                    }

                } else
                    Toast.makeText(getApplicationContext(), "MAC is closed at the selected time ", Toast.LENGTH_SHORT).show();

            } else if (day == 1 || day == 7) {

                if (hourOfDay >= 12 && hourOfDay <= 23) {
                    roundHours = Integer.toString(hourOfDay);
                    ans[0]=roundHours;
                    if (minutes <= 15) {
                        roundMinutes = "00";
                        ans[1]=roundMinutes;
//                        BtnTime.setText(roundHours + ":" + roundMinutes);
                    } else if (minutes > 15 && minutes < 59) {
                        roundMinutes = "30";
                        ans[1]=roundMinutes;
                    }
                }
            }
        }
        else
            Toast.makeText(getApplicationContext(), "Can't select time in the past ", Toast.LENGTH_SHORT).show();
        return ans;
    }
}

//
//
//
//    String roundMinutes = "";
//    String roundHours = "";
//    int day = c.get(Calendar.DAY_OF_WEEK);
//                        if(hourOfDay>=c.get(Calendar.HOUR) && minutes>=c.get(Calendar.MINUTE) && flag==Boolean.FALSE) {
//                                if (day >= 2 && day <= 6) {
//                                if (hourOfDay >= 6 && hourOfDay <= 23) {
//                                roundHours = Integer.toString(hourOfDay);
//                                if (minutes <= 15) {
//                                roundMinutes = "00";
//                                } else if (minutes > 15 && minutes < 59) {
//        roundMinutes = "30";
//        }
//        BtnTime.setText(roundHours + ":" + roundMinutes);
//        } else
//        Toast.makeText(getApplicationContext(), "MAC is closed at the selected time ", Toast.LENGTH_SHORT).show();
//
//        } else if (day == 1 || day == 7) {
//
//        if (hourOfDay >= 12 && hourOfDay <= 23) {
//        roundHours = Integer.toString(hourOfDay);
//        if (minutes <= 15) {
//        roundMinutes = "00";
//        } else if (minutes > 15 && minutes < 59) {
//        roundMinutes = "30";
//        }
//        BtnTime.setText(roundHours + ":" + roundMinutes);
//        }
//        }
//        }
//
//        else  if (flag==Boolean.TRUE){
//        if (day >= 2 && day <= 6) {
//        if (hourOfDay >= 6 && hourOfDay <= 23) {
//        roundHours = Integer.toString(hourOfDay);
//        if (minutes <= 15) {
//        roundMinutes = "00";
//        BtnTime.setText(roundHours + ":" + roundMinutes);
//        } else if (minutes > 15 && minutes < 59) {
//        roundMinutes = "30";
//        BtnTime.setText(roundHours + ":" + roundMinutes);
//        }
//        } else
//        Toast.makeText(getApplicationContext(), "MAC is closed at the selected time ", Toast.LENGTH_SHORT).show();
//
//        } else if (day == 1 || day == 7) {
//
//        if (hourOfDay >= 12 && hourOfDay <= 23) {
//        roundHours = Integer.toString(hourOfDay);
//        if (minutes <= 15) {
//        roundMinutes = "00";
//        BtnTime.setText(roundHours + ":" + roundMinutes);
//
//        } else if (minutes > 15 && minutes < 59) {
//        roundMinutes = "30";
//        BtnTime.setText(roundHours + ":" + roundMinutes);
//        }
//
//        }
//        }
//        }
//        else
//        Toast.makeText(getApplicationContext(), "Can't select time in the past ", Toast.LENGTH_SHORT).show();
//
//
//

