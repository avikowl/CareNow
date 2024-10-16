package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DiseaseDetailsActivity extends AppCompatActivity {

    private TextView diseaseTitle, symptoms, prevention, treatment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_details);

        diseaseTitle = findViewById(R.id.diseaseTitle);
        symptoms = findViewById(R.id.symptoms);
        prevention = findViewById(R.id.prevention);
        treatment = findViewById(R.id.treatment);
        Button backButton = findViewById(R.id.cardDDBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        diseaseTitle.setText(title);

        setDiseaseInfo(title);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void setDiseaseInfo(String title) {
        switch (title) {
            case "Tuberculosis":
                symptoms.setText("Symptoms:\n" +
                        "- Persistent cough (lasting more than 3 weeks)\n" +
                        "- Chest pain or discomfort\n" +
                        "- Coughing up blood or sputum\n" +
                        "- Unexplained weight loss\n" +
                        "- Fatigue and weakness\n" +
                        "- Fever and chills\n" +
                        "- Night sweats");
                prevention.setText("Prevention:\n" +
                        "- Vaccination (BCG vaccine for children)\n" +
                        "- Ensuring good ventilation in living areas\n" +
                        "- Regular health check-ups in high-risk areas\n" +
                        "- Avoiding close contact with infected individuals");
                treatment.setText("Treatment:\n" +
                        "- Long-term course of antibiotics (usually 6 to 9 months)\n" +
                        "- Directly Observed Therapy (DOT) to ensure adherence to medication\n" +
                        "- Monitoring for side effects and adjusting treatment as needed\n" +
                        "- Nutritional support to help recovery.");
                break;
            case "Pneumonia":
                symptoms.setText("Symptoms:\n" +
                        "- Cough with phlegm or pus\n" +
                        "- Chest pain that worsens with deep breaths\n" +
                        "- Shortness of breath or difficulty breathing\n" +
                        "- High fever (usually above 101°F or 38°C)\n" +
                        "- Sweating and chills\n" +
                        "- Fatigue and loss of appetite");
                prevention.setText("Prevention:\n" +
                        "- Vaccination (Pneumococcal and flu vaccines)\n" +
                        "- Practicing good hygiene (handwashing)\n" +
                        "- Avoiding smoking and exposure to secondhand smoke\n" +
                        "- Keeping a healthy immune system through nutrition and exercise");
                treatment.setText("Treatment:\n" +
                        "- Antibiotics (for bacterial pneumonia)\n" +
                        "- Antiviral medications (for viral pneumonia)\n" +
                        "- Rest and hydration\n" +
                        "- Over-the-counter pain relievers for fever and pain.");
                break;
            case "Malaria":
                symptoms.setText("Symptoms:\n" +
                        "- Fever (often cyclical)\n" +
                        "- Chills and sweating\n" +
                        "- Headaches\n" +
                        "- Nausea and vomiting\n" +
                        "- Fatigue and weakness\n" +
                        "- Muscle and joint pain\n" +
                        "- Anemia (due to destruction of red blood cells)");
                prevention.setText("Prevention:\n" +
                        "- Using insecticide-treated bed nets (ITNs)\n" +
                        "- Taking antimalarial medication if traveling to high-risk areas\n" +
                        "- Using insect repellents on exposed skin\n" +
                        "- Eliminating standing water where mosquitoes breed");
                treatment.setText("Treatment:\n" +
                        "- Antimalarial medications (such as artemisinin-based combination therapies)\n" +
                        "- Supportive care for severe cases (IV fluids, blood transfusions)\n" +
                        "- Monitoring and follow-up to prevent relapses.");
                break;
            case "Dengue":
                symptoms.setText("Symptoms:\n" +
                        "- High fever (often reaching 104°F or 40°C)\n" +
                        "- Severe headaches\n" +
                        "- Pain behind the eyes\n" +
                        "- Joint and muscle pain\n" +
                        "- Fatigue and weakness\n" +
                        "- Skin rash (may appear 3 to 4 days after fever)\n" +
                        "- Mild bleeding (nosebleeds or gum bleeding)");
                prevention.setText("Prevention:\n" +
                        "- Eliminating standing water around homes (where mosquitoes breed)\n" +
                        "- Using mosquito repellents and wearing long sleeves/pants\n" +
                        "- Installing screens on windows and doors\n" +
                        "- Community clean-up campaigns to reduce mosquito habitats");
                treatment.setText("Treatment:\n" +
                        "- Supportive care (hydration and rest)\n" +
                        "- Pain relievers (avoid aspirin and NSAIDs)\n" +
                        "- Monitoring for warning signs of severe dengue (e.g., bleeding, severe abdominal pain)\n" +
                        "- Hospitalization for severe cases.");
                break;
            case "Anemia":
                symptoms.setText("Symptoms:\n" +
                        "- Fatigue and weakness\n" +
                        "- Pale skin and pallor\n" +
                        "- Shortness of breath with exertion\n" +
                        "- Dizziness or lightheadedness\n" +
                        "- Cold hands and feet\n" +
                        "- Chest pain (in severe cases)");
                prevention.setText("Prevention:\n" +
                        "- Eating a balanced diet rich in iron (red meat, beans, spinach)\n" +
                        "- Combining iron-rich foods with vitamin C to enhance absorption\n" +
                        "- Regular health screenings, especially for high-risk groups (children, pregnant women)\n" +
                        "- Avoiding excessive intake of tea or coffee with meals (can inhibit iron absorption)");
                treatment.setText("Treatment:\n" +
                        "- Iron supplements (oral or intravenous, depending on severity)\n" +
                        "- Nutritional counseling to improve diet\n" +
                        "- Treating underlying causes (e.g., blood loss, vitamin deficiencies)\n" +
                        "- Regular monitoring of hemoglobin levels.");
                break;
            default:
                symptoms.setText("No information available.");
                prevention.setText("No information available.");
                treatment.setText("No information available.");
                break;
        }
    }
}
