package com.example.bmi;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	String gender,message;
	int age=25, weight=60,height=180,error;
	float bmi;
	RelativeLayout male, female;
	TextView userWeight,userAge,userHeight;

	Button btnBmiCalc;
	ImageView weightDec, weightInc, ageDec, ageInc;
	SeekBar heightSeek;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		male = findViewById(R.id.male);
		female = findViewById(R.id.female);
		userWeight = findViewById(R.id.currentweight);
		userAge = findViewById(R.id.currentage);
		userHeight = findViewById(R.id.currentheight);
		btnBmiCalc = findViewById(R.id.calculatebmi);
		weightInc = findViewById(R.id.incremetweight);
		weightDec = findViewById(R.id.decrementweight);
		ageInc = findViewById(R.id.incrementage);
		ageDec = findViewById(R.id.decrementage);
		heightSeek = findViewById(R.id.seekbarforheight);

		heightSeek.setMax(300);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
			heightSeek.setMin(150);
		}
		heightSeek.setProgress(180);
		heightSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				height = progress;
				userHeight.setText(String.valueOf(height));
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});
		weightInc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				weight += 1;
				userWeight.setText(String.valueOf(weight));
			}
		});
		weightDec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				weight -= 1;
				userWeight.setText(String.valueOf(weight));
			}
		});
		ageInc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				age += 1;
				userAge.setText(String.valueOf(age));
			}
		});
		ageDec.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				age -= 1;
				userAge.setText(String.valueOf(age));
			}
		});
		male.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				gender = "MALE";
				male.setBackgroundColor(getResources().getColor(R.color.green));
				female.setBackgroundColor(getResources().getColor(R.color.counter_fg));


			}
		});
		female.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				gender = "FEMALE";
				System.out.println(gender);
				female.setBackgroundColor(getResources().getColor(R.color.green));
				male.setBackgroundColor(getResources().getColor(R.color.counter_fg));
			}
		});
		btnBmiCalc.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				check();
				openpopup();

			}
		});





	}

	private void openpopup() {
		AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		if(error==1){
			builder.setMessage("" + message);
		}else{
			builder.setMessage("Your BMI is " + bmi + ". You are " + message);
		}
		builder.setTitle("BMI Calculator Result")
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {

					}
				});
		AlertDialog dialog = builder.create();
		dialog.show();
	}


	public void check(){
		if (age == 0 || gender == null || height < 150 || weight < 20 || weight > 300) {
			message = "Invalid Data or Empty Data";
			error = 1;
		} else {
			double h1 = height / 100.0;
			double heightinm = Math.pow(h1, 2);
			bmi = (float) (weight / heightinm);

			if (age < 18) {
				message = "Not applicable for minors";
				error = 1;
			} else if (gender.equalsIgnoreCase("MALE")) {
				if (bmi < 18.5) {
					message = "underweight";
				} else if (bmi < 25) {
					message = "normal weight";
				} else if (bmi < 30) {
					message = "overweight";
				} else {
					message = "obese";
				}
			} else if (gender.equalsIgnoreCase("FEMALE")) {
				if (bmi < 18.5) {
					message = "underweight";
				} else if (bmi < 24) {
					message = "normal weight";
				} else if (bmi < 29) {
					message = "overweight";
				} else {
					message = "obese";
				}
			} else {
					message = "Invalid gender";
				}
			}

		}


	}



