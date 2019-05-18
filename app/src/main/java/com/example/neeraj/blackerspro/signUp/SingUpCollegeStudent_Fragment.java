package com.example.neeraj.blackerspro.signUp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;

public class SingUpCollegeStudent_Fragment extends Fragment {
    private View view;
    private Button sign_upBT;
    private TextView sign_inTV;
    private EditText fnameET, lnameET, roll_numberET, mobileET, alter_mobileET, emailET, passwordET, confirm_passwordET, dateET, addressET;

    private Spinner branchSP;
    private ProgressBar progressbar;
    private String fname, lname, roll_number, mobile, alter_mobile, email, password, confirm_password, date, branch, address;
    private int spinnerCount = 0;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private String branchAR[] = {"select your branch ", "Information Technology 3rd year", "Information Technology 2nd year", "Information Technology 1st year"};
    private HashMap<String, String> hashMap = new HashMap<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference head_of_departments_ref = db.collection("User Details").document("Head Of Departments Documents").collection("Head Of Departments");


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.college_student, container, false);

        findInit();

        dateofbirth();
        userSelection();
        onClickWork();
        return view;
    }

    private void findInit() {
        sign_upBT = view.findViewById(R.id.sign_upBT);
        dateET = view.findViewById(R.id.dateET);
        fnameET = view.findViewById(R.id.fnameET);
        lnameET = view.findViewById(R.id.lnameET);

        roll_numberET = view.findViewById(R.id.roll_numberET);
        mobileET = view.findViewById(R.id.mobileET);
        alter_mobileET = view.findViewById(R.id.alter_mobileET);
        emailET = view.findViewById(R.id.emailET);
        addressET = view.findViewById(R.id.addressET);
        passwordET = view.findViewById(R.id.passwordET);
        confirm_passwordET = view.findViewById(R.id.confirm_passwordET);
        sign_inTV = view.findViewById(R.id.sign_inTV);
        branchSP = view.findViewById(R.id.branchSP);
        progressbar = view.findViewById(R.id.progressbar);


    }


    private void dateofbirth() {


        dateET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow();
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String date = day + "/" + month + "/" + year;
                dateET.setText(date);
            }
        };


    }


    private void userSelection() {


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, branchAR);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        branchSP.setAdapter(arrayAdapter);
        branchSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                spinnerCount = i;

                branchSP.setSelection(spinnerCount);

                if (spinnerCount != 0) {
                    branch = adapterView.getItemAtPosition(i).toString().trim();
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        spinnerCount = 0;
        branchSP.setSelection(spinnerCount);
    }

    protected void getEditText() {

        fname = fnameET.getText().toString().toUpperCase().trim();
        lname = lnameET.getText().toString().toUpperCase().trim();
        roll_number = roll_numberET.getText().toString().trim();
        mobile = mobileET.getText().toString().trim();
        alter_mobile = alter_mobileET.getText().toString().trim();
        email = emailET.getText().toString().trim();
        address = addressET.getText().toString().trim();
        password = passwordET.getText().toString().trim();
        confirm_password = confirm_passwordET.getText().toString().trim();
        date = dateET.getText().toString().trim();

    }

    private void validateFields() {

        getEditText();
        OnvisibilityProgressBar();
        if (fname.isEmpty()) {
            fnameET.setError("First name is required");
            fnameET.requestFocus();
            OnvisiblityBtn();
            return;
        }


        if (lname.isEmpty()) {
            lnameET.setError("Last name is required");
            lnameET.requestFocus();
            OnvisiblityBtn();

            return;
        }

        if (roll_number.isEmpty()) {
            roll_numberET.setError("Roll number is required");
            roll_numberET.requestFocus();
            OnvisiblityBtn();

            return;
        }
        if (spinnerCount == 0) {
            Toast.makeText(getContext(), "Select a branch !", Toast.LENGTH_LONG).show();
            OnvisiblityBtn();

            return;
        }

        if (date.isEmpty()) {
            dateET.setError("Date of birth required is required");
            dateET.requestFocus();

            return;
        }


        if (mobile.length() != 10) {
            mobileET.setError("Mobile number is invalid please enter a valid one");
            mobileET.requestFocus();
            OnvisiblityBtn();

            return;
        }

        if (alter_mobile.length() != 10) {
            mobileET.setError("Mobile number is invalid please enter a valid one");
            mobileET.requestFocus();
            OnvisiblityBtn();

            return;
        }

        if (alter_mobile.equals(mobile)) {
            alter_mobileET.setError("Not be same as the first number");
            alter_mobileET.requestFocus();
            OnvisiblityBtn();

            return;
        }

        if (email.isEmpty()) {
            emailET.setError("Email is required");
            emailET.requestFocus();
            OnvisiblityBtn();

            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailET.setError("Please enter a valid email");
            emailET.requestFocus();
            OnvisiblityBtn();

            return;
        }

        if (address.isEmpty()) {
            addressET.setError("Please fill address");
            addressET.requestFocus();
            OnvisiblityBtn();
            return;
        }

        if (password.isEmpty()) {
            passwordET.setError("please Enter a password");
            passwordET.requestFocus();
            OnvisiblityBtn();

            return;
        }

        if (confirm_password.isEmpty()) {
            confirm_passwordET.setError("please Enter a password");
            confirm_passwordET.requestFocus();
            OnvisiblityBtn();

            return;
        }
        if (password.length() < 7) {
            passwordET.setError("Password length Should greater then 6");
            passwordET.requestFocus();
            OnvisiblityBtn();

            return;
        }
        if (!password.equals(confirm_password)) {
            confirm_passwordET.setError("Password not matched");
            confirm_passwordET.requestFocus();
            OnvisiblityBtn();

            return;
        }


    }

    //Progress bar visibilty methods
    private void OnvisiblityBtn() {
        progressbar.setVisibility(View.GONE);
        sign_upBT.setVisibility(View.VISIBLE);

    }

    private void OnvisibilityProgressBar() {
        progressbar.setVisibility(View.VISIBLE);
        sign_upBT.setVisibility(View.GONE);
    }

    // On click working
    private void onClickWork() {
        sign_upBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateFields();
            }
        });

        sign_inTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);

            }
        });


    }


}
