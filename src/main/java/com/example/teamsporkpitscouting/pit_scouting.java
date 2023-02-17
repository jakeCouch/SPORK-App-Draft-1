package com.example.teamsporkpitscouting;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class pit_scouting extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button submit_button;

    CheckBox autonomous_leave, autonomous_docked, autonomous_engaged, endgame_docked, endgame_engaged, endgame_parked, endgame_coopertition, endgame_sustainability, endgame_broken, endgame_disabled;
    //driver station edit text must be removed
    EditText student_name, team_number, pit_number, teleop_top, teleop_middle, teleop_bottom, autonomous_top, autonomous_middle, autonomous_bottom, red_total_points, red_penalty_points, blue_total_points, blue_penalty_points, additional_comments;
    Spinner  penalty_spinner, driver_station_spinner;
    String[] array = new String[27];
    CSVWriter writer;
    String csv, penalty_spinner_value, driver_station_spinner_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pit_scouting);

        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(this);

        student_name = findViewById(R.id.name_editText);
        team_number = findViewById(R.id.team_number_editText);
        pit_number = findViewById(R.id.pit_number_editText);

        teleop_top = findViewById(R.id.teleop_pieces_top_editText);
        teleop_middle = findViewById(R.id.teleop_pieces_middle_editText);
        teleop_bottom = findViewById(R.id.teleop_pieces_bottom_editText);

        autonomous_leave = findViewById(R.id.leave_checkbox);
        autonomous_docked = findViewById(R.id.dock_checkbox);
        autonomous_engaged = findViewById(R.id.engaged_checkbox);

        endgame_docked = findViewById(R.id.endgame_docked_checkbox);
        endgame_engaged = findViewById(R.id.endgame_engaged_checkbox);
        endgame_parked = findViewById(R.id.endgame_parked_checkbox);
        endgame_coopertition = findViewById(R.id.endgame_coopertition_checkbox);
        endgame_sustainability = findViewById(R.id.endgame_sustainability_checkbox);
        endgame_broken = findViewById(R.id.endgame_broken_checkbox);
        endgame_disabled = findViewById(R.id.endgame_disabled_checkbox);

        autonomous_top = findViewById(R.id.autonomous_pieces_top_editText);
        autonomous_middle = findViewById(R.id.autonomous_pieces_middle_editText);
        autonomous_bottom = findViewById(R.id.autonomous_pieces_bottom_editText);

        red_total_points = findViewById(R.id.red_total_points_editText);
        red_penalty_points= findViewById(R.id.red_penalty_points_editText);
        blue_total_points = findViewById(R.id.blue_total_points_editText);
        blue_penalty_points = findViewById(R.id.blue_penalty_points_editText);

        additional_comments = findViewById(R.id.additional_comment);

        penalty_spinner_value = "does not work";
        driver_station_spinner_value = "does not work";

        //Penalty Dropdown
        penalty_spinner = findViewById(R.id.penalty_spinner);
        penalty_spinner.setOnItemSelectedListener(this);

        List<String> penalty_spinner_category = new ArrayList<>();
        penalty_spinner_category.add("Does not pick up");
        penalty_spinner_category.add("Human Player Station");
        penalty_spinner_category.add("On the Field");
        penalty_spinner_category.add("Picks Up Anywhere");

        ArrayAdapter<String> array_adapter_penalty_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, penalty_spinner_category);
        array_adapter_penalty_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        penalty_spinner.setAdapter(array_adapter_penalty_spinner);

        //Driver Station Dropdown
        driver_station_spinner = findViewById(R.id.driver_station_spinner);
        driver_station_spinner.setOnItemSelectedListener(this);

        List<String> driver_station_spinner_category = new ArrayList<>();
        driver_station_spinner_category.add("Red 1");
        driver_station_spinner_category.add("Red 2");
        driver_station_spinner_category.add("Red 3");
        driver_station_spinner_category.add("Blue 1");
        driver_station_spinner_category.add("Blue 2");
        driver_station_spinner_category.add("Blue 3");

        ArrayAdapter<String> array_adapter_driver_station_spinner= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, driver_station_spinner_category);
        array_adapter_driver_station_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driver_station_spinner.setAdapter(array_adapter_driver_station_spinner);

        //troubleshooting
        System.out.println("whtat is the path to the file: " + Environment.getExternalStorageDirectory().getAbsolutePath() + "/TeamSPORK_PITScouting.csv2");

        csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/Documents/3196Asheville.csv");


    }

    @Override
    public void onClick(View v) {
        add_data_to_sheet();
    }

    public void add_data_to_sheet() {
        try{
            writer = new CSVWriter(new FileWriter(csv, true));

            array[0] = student_name.getText().toString().trim();
            array[1] = team_number.getText().toString().trim();
            array[2] = pit_number.getText().toString().trim();
            array[3] = "Delete later";
            array[4] = teleop_top.getText().toString().trim();
            array[5] = teleop_middle.getText().toString().trim();
            array[6] = teleop_bottom.getText().toString().trim();

            array[7] = String.valueOf(autonomous_leave.isChecked());
            array[8] = String.valueOf(autonomous_docked.isChecked());
            array[9] = String.valueOf(autonomous_engaged.isChecked());
            array[10] = String.valueOf(endgame_docked.isChecked());
            array[11] = String.valueOf(endgame_engaged.isChecked());
            array[12] = String.valueOf(endgame_parked.isChecked());
            array[13] = String.valueOf(endgame_coopertition.isChecked());
            array[14] = String.valueOf(endgame_sustainability.isChecked());
            array[15] = String.valueOf(endgame_broken.isChecked());
            array[16] = String.valueOf(endgame_disabled.isChecked());

            array[17] = autonomous_top.getText().toString().trim();
            array[18] = autonomous_middle.getText().toString().trim();
            array[19] = autonomous_bottom.getText().toString().trim();

            array[20] = red_total_points.getText().toString().trim();
            array[21] = red_penalty_points.getText().toString().trim();
            array[22] = blue_total_points.getText().toString().trim();
            array[23] = blue_penalty_points.getText().toString().trim();

            array[24] = additional_comments.getText().toString().trim();
            array[25] = penalty_spinner_value.trim();
            array[26] = driver_station_spinner_value.trim();

            writer.writeNext(array);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        student_name.setText("");
        team_number.setText("");
        pit_number.setText("");
        teleop_top.setText("");
        teleop_middle.setText("");
        teleop_bottom.setText("");

        autonomous_top.setText("");
        autonomous_middle.setText("");
        autonomous_bottom.setText("");

        autonomous_leave.setChecked(false);
        autonomous_engaged.setChecked(false);
        autonomous_docked.setChecked(false);

        endgame_docked.setChecked(false);
        endgame_engaged.setChecked(false);
        endgame_parked.setChecked(false);
        endgame_coopertition.setChecked(false);
        endgame_sustainability.setChecked(false);
        endgame_broken.setChecked(false);
        endgame_disabled.setChecked(false);

        red_total_points.setText("");
        red_penalty_points.setText("");
        blue_total_points.setText("");
        blue_penalty_points.setText("");

        additional_comments.setText("");

        penalty_spinner.setSelection(0);
        driver_station_spinner.setSelection(0);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if (parent == penalty_spinner) {
            penalty_spinner_value = item;
        } else if (parent == driver_station_spinner){
            driver_station_spinner_value = item;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}