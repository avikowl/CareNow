package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1=
            {
                    {"Doctor Name: John Paul", "Hospital: Memorial", "Exp: 10 years", "Mobile: +1 823 123 0987", "600"},
                    {"Doctor Name: Alice Cooper", "Hospital: City General", "Exp: 8 years", "Mobile: +1 824 234 5678", "650"},
                    {"Doctor Name: Robert Dow", "Hospital: Westside Clinic", "Exp: 15 years", "Mobile: +1 825 345 6789", "700"},
                    {"Doctor Name: Emma Stone", "Hospital: Lakeshore Medical", "Exp: 12 years", "Mobile: +1 826 456 7890", "550"},
                    {"Doctor Name: David Green", "Hospital: Hilltop Health", "Exp: 7 years", "Mobile: +1 827 567 8901", "580"}
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name: Sarah Blake", "Hospital: Eastside Clinic", "Exp: 9 years", "Mobile: +1 828 678 9012", "610"},
                    {"Doctor Name: Michael Scott", "Hospital: Riverfront Medical", "Exp: 11 years", "Mobile: +1 829 789 0123", "620"},
                    {"Doctor Name: Olivia Johnson", "Hospital: Central Health", "Exp: 6 years", "Mobile: +1 830 890 1234", "580"},
                    {"Doctor Name: Henry Clark", "Hospital: Greenfield Medical", "Exp: 14 years", "Mobile: +1 831 901 2345", "710"},
                    {"Doctor Name: Megan White", "Hospital: Southend Health", "Exp: 5 years", "Mobile: +1 832 012 3456", "530"}
            };

    private String[][] doctor_details3 =
            {
                    {"Doctor Name: James King", "Hospital: Northside Hospital", "Exp: 13 years", "Mobile: +1 833 123 4567", "690"},
                    {"Doctor Name: Laura Fox", "Hospital: Bayview Medical", "Exp: 9 years", "Mobile: +1 834 234 5678", "620"},
                    {"Doctor Name: Ethan Morris", "Hospital: Evergreen Clinic", "Exp: 7 years", "Mobile: +1 835 345 6789", "550"},
                    {"Doctor Name: Grace Lee", "Hospital: Oakwood Health", "Exp: 10 years", "Mobile: +1 836 456 7890", "600"},
                    {"Doctor Name: Noah Davis", "Hospital: Seaside Medical", "Exp: 8 years", "Mobile: +1 837 567 8901", "580"}
            };

    private String[][] doctor_details4 =
            {
                    {"Doctor Name: Sophia Taylor", "Hospital: Springfield Medical", "Exp: 12 years", "Mobile: +1 838 678 9012", "670"},
                    {"Doctor Name: Daniel Wright", "Hospital: Redrock Clinic", "Exp: 15 years", "Mobile: +1 839 789 0123", "720"},
                    {"Doctor Name: Chloe Evans", "Hospital: Blue Valley Health", "Exp: 6 years", "Mobile: +1 840 890 1234", "560"},
                    {"Doctor Name: William Brown", "Hospital: Hillcrest Medical", "Exp: 10 years", "Mobile: +1 841 901 2345", "610"},
                    {"Doctor Name: Ella Thompson", "Hospital: Lakeside Health", "Exp: 9 years", "Mobile: +1 842 012 3456", "590"}
            };

    private String[][] doctor_details5 =
            {
                    {"Doctor Name: Jack Harris", "Hospital: Sunset Medical", "Exp: 11 years", "Mobile: +1 843 123 4567", "630"},
                    {"Doctor Name: Isabella Parker", "Hospital: Pine Grove Clinic", "Exp: 7 years", "Mobile: +1 844 234 5678", "570"},
                    {"Doctor Name: Liam Anderson", "Hospital: Brookfield Health", "Exp: 8 years", "Mobile: +1 845 345 6789", "600"},
                    {"Doctor Name: Amelia Adams", "Hospital: Windmill Clinic", "Exp: 10 years", "Mobile: +1 846 456 7890", "650"},
                    {"Doctor Name: Mason Phillips", "Hospital: Maplewood Medical", "Exp: 14 years", "Mobile: +1 847 567 8901", "720"}
            };
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList list;
    SimpleAdapter sa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);
        tv = findViewById(R.id.textViewDDTitle);
        btn = findViewById(R.id.buttonDDBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietitian")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
        if(title.compareTo("Cardiologist")==0)
            doctor_details = doctor_details5;

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDoctorActivity.class));
            }
        });
        list = new ArrayList();
        for(int i=0; i<doctor_details.length; i++)
        {
            item=new HashMap<String, String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5", "Fees:"+doctor_details[i][4]+"$");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list,
                R.layout.multi_lines, new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e});
        ListView lst = findViewById(R.id.listViewDD);
        lst.setAdapter(sa);
    }

}