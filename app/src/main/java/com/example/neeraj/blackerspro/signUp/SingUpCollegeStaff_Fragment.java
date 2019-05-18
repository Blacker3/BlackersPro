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
import com.example.neeraj.blackerspro.addusers.AddHodMainActivity;
import com.example.neeraj.blackerspro.addusers.GetHodInfo;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.Calendar;
import java.util.HashMap;

public class SingUpCollegeStaff_Fragment extends Fragment {
    private View view;
    private Button sign_upBT;
    private TextView sign_inTV;
    private EditText fnameET, lnameET, mobileET, alter_mobileET, emailET, passwordET, confirm_passwordET, dateET, addressET;
    private Spinner user_sign_upSP;
    private ProgressBar progressbar;
    private String name, fname, lname, mobile, alter_mobile, email, password, confirm_password, date, usertype, address;
    private String databaseCollection = "", branch = "";
    private String fire_name, fire_email, fire_date, fire_mobile;
    private String userregAR[] = {"select your user type ", "Principal", "Information Technolgy hod", "Computer Science and Engnering hod", "Electronics hod", "Mechanical Automobiles hod", "Civil hod", "Mechanical Production hod", "Lecturer", "College Staff"};
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private int spinnerCount = 0;
    private HashMap<String, String> hashMap = new HashMap<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference userDetails_document_ref;
    private FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
    private GetHodInfo getHodInfo = new GetHodInfo();

    public SingUpCollegeStaff_Fragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.college_staff, container, false);
        findInit();

        dateofbirth();
        userSelection();
        onClickWork();
        return view;
    }


    // intializing feilds
    private void findInit() {
        sign_upBT = view.findViewById(R.id.sign_upBT);
        user_sign_upSP = view.findViewById(R.id.user_sign_upSP);
        dateET = view.findViewById(R.id.dateET);
        fnameET = view.findViewById(R.id.fnameET);
        lnameET = view.findViewById(R.id.lnameET);
        addressET = view.findViewById(R.id.addressET);
        mobileET = view.findViewById(R.id.mobileET);
        alter_mobileET = view.findViewById(R.id.alter_mobileET);
        emailET = view.findViewById(R.id.emailET);
        passwordET = view.findViewById(R.id.passwordET);
        confirm_passwordET = view.findViewById(R.id.confirm_passwordET);
        sign_inTV = view.findViewById(R.id.sign_inTV);
        user_sign_upSP = view.findViewById(R.id.user_sign_upSP);
        progressbar = view.findViewById(R.id.progressbar);

    }

    //user type slection using spinner

    private void userSelection() {


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, userregAR);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        user_sign_upSP.setAdapter(arrayAdapter);
        user_sign_upSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                spinnerCount = i;

                user_sign_upSP.setSelection(spinnerCount);

                if (spinnerCount != 0) {
                    usertype = adapterView.getItemAtPosition(i).toString().trim();
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }

    // on fragment destroyed spinner working
    @Override
    public void onDestroy() {
        super.onDestroy();
        spinnerCount = 0;
        user_sign_upSP.setSelection(spinnerCount);
    }

// Date picker

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


    //getting text by edit text fields

    protected void getEditText() {

        fname = fnameET.getText().toString().toUpperCase().trim();
        lname = lnameET.getText().toString().toUpperCase().trim();
        mobile = mobileET.getText().toString().trim();
        alter_mobile = alter_mobileET.getText().toString().trim();
        email = emailET.getText().toString().trim();
        address = addressET.getText().toString().trim();
        password = passwordET.getText().toString().trim();
        confirm_password = confirm_passwordET.getText().toString().trim();
        date = dateET.getText().toString().trim();
        name = fname + " " + lname;
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

        firestoreData();


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


    //Getting data from database
    private void firestoreData() {


        // Head of departments details submitting


        if (usertype != null && usertype.equals(userregAR[2]) || usertype.equals(userregAR[3]) || usertype.equals(userregAR[4]) || usertype.equals(userregAR[5]) || usertype.equals(userregAR[6]) || usertype.equals(userregAR[7])) {
            databaseCollection = "Head Of Departments";
            userDetails_document_ref = db.document("User Details" + "/" + databaseCollection + " Documents" + "/" + databaseCollection + "/" + usertype);
            userDetails_document_ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

                    getHodInfo = documentSnapshot.toObject(GetHodInfo.class);

                    fire_name = getHodInfo.getName();
                    fire_date = getHodInfo.getDob();
                    fire_email = getHodInfo.getEmail();
                    fire_mobile = getHodInfo.getPhone();

                    getEditText();

                    if (name.equalsIgnoreCase(fire_name) && date.equals(fire_date) && email.equals(fire_email) && mobile.equals(fire_mobile)) {

                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    hashMap.put("address",address);
                                    hashMap.put("alter_mobile_number",alter_mobile);
                                    userDetails_document_ref.set(hashMap, SetOptions.merge());
                                    Toast.makeText(getContext(), "Registered succesfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getContext(), Login.class);
                                    startActivity(intent);


                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(getContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText(getContext(), "Submited succesfully", Toast.LENGTH_SHORT).show();

                        OnvisiblityBtn();

                    } else {
                        OnvisiblityBtn();
                        Toast.makeText(getContext(), "We  don,t find such details in our database for more info please contact to Principal/Admin.", Toast.LENGTH_LONG).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }

        // Lecture and college staff

      else  if (usertype != null &&usertype.equals(userregAR[8])|| usertype.equals(userregAR[9])) {
            databaseCollection = "College Staff";
            userDetails_document_ref = db.document("User Details" + "/" + databaseCollection + " Documents" + "/" + databaseCollection + "/" + usertype);
            userDetails_document_ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {

                    getHodInfo = documentSnapshot.toObject(GetHodInfo.class);

                    fire_name = getHodInfo.getName();
                    fire_date = getHodInfo.getDob();
                    fire_email = getHodInfo.getEmail();
                    fire_mobile = getHodInfo.getPhone();

                    getEditText();

                    if (name.equalsIgnoreCase(fire_name) && date.equals(fire_date) && email.equals(fire_email) && mobile.equals(fire_mobile)) {

                        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    hashMap.put("address",address);
                                    hashMap.put("alter_mobile_number",alter_mobile);
                                    userDetails_document_ref.set(hashMap, SetOptions.merge());
                                    Toast.makeText(getContext(), "Registered succesfully", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getContext(), Login.class);
                                    startActivity(intent);


                                } else {
                                    if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                        Toast.makeText(getContext(), "You are already registered", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(getContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                        Toast.makeText(getContext(), "Submited succesfully", Toast.LENGTH_SHORT).show();

                        OnvisiblityBtn();

                    } else {
                        OnvisiblityBtn();
                        Toast.makeText(getContext(), "We  don,t find such details in our database for more info please contact to Principal/Admin.", Toast.LENGTH_LONG).show();
                    }

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                }
            });
        }


    }


    //Onclick Working

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
