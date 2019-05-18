package com.example.neeraj.blackerspro.addusers;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.Patterns;


import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import android.widget.Toast;


import com.example.neeraj.blackerspro.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;


import java.util.Calendar;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddHodDetailsActivity extends AppCompatActivity {
    private Context context;
    private EditText editText;
    private Intent intent;
    private CircleImageView circleImageView;
    private EditText fnameET;
    private EditText lnameET;
    private EditText mobileET;
    private EditText emailET;
    private EditText dateET;
    private CircleImageView hod_profile_image_IV;
    private Spinner facultySP;
    private Button addhodBT;
    private RadioButton maleRB, femaleRB;
    private RadioGroup genderRG;
    private Button uploadBT;
    private static final int PICK_IMAGE_REQUEST = 1;
    private String date;
    private String email;
    private String fname;

    private String lname;

    private String mobile;

    private String name;
    private int spinnerCount;
    private String branch;
    private String gender;
    private String proifle_url;
    Uri uriHodProfileImage;
    int count = 1;
    private HashMap<String, String> hashMap = new HashMap<>();

    private String branchAR[] = {"select your branch ", "Information Technolgy", "Computer Science and Engnering", "Electronics", "Mechanical Automobiles", "Civil", "Mechanical Production"};
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private ProgressBar progressBar, profileProgressbar;
    private StorageTask uploadTask;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference head_of_departments_ref = db.collection("User Details").document("Head Of Departments Documents").collection("Head Of Departments");
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference("profileImages/HodProfileImage");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addhod);
        findInit();
        userSelection(AddHodDetailsActivity.this);
        dateofbirth(AddHodDetailsActivity.this, dateET);
        genderSelection();
//profile is image uloading button  click work
        uploadBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinnerCount == 0) {
                    Toast.makeText(AddHodDetailsActivity.this, "Select a branch !", Toast.LENGTH_LONG).show();

                    return;
                }
                if (uploadTask != null && uploadTask.isInProgress()) {
                    hod_profile_image_IV.setClickable(false);
                    Toast.makeText(AddHodDetailsActivity.this, "Profile image is uploading......", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();

                }
            }
        });

        // Hod info uplaoding to firestore

        addhodBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count == 1) {
                    Toast.makeText(AddHodDetailsActivity.this, "Upload a image!", Toast.LENGTH_SHORT).show();

                } else if (uploadTask != null && uploadTask.isInProgress()) {
                    hod_profile_image_IV.setClickable(false);
                    Toast.makeText(AddHodDetailsActivity.this, "Profile image is uploading......", Toast.LENGTH_SHORT).show();

                } else {
                    validateFields();

                }
            }
        });

        // hod profile image selector

        hod_profile_image_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });

    }


    // Branch selectionn

    private void userSelection(Context mcontext) {
        context = mcontext;


        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, branchAR);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        facultySP.setAdapter(arrayAdapter);
        facultySP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerCount = i;

                facultySP.setSelection(spinnerCount);

                if (spinnerCount != 0) {
                    branch = adapterView.getItemAtPosition(i).toString().trim();
                }

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });

    }

    // on activity destroyed spinner work
    @Override
    protected void onDestroy() {
        super.onDestroy();
        spinnerCount = 0;
        facultySP.setSelection(spinnerCount);

    }


    //Date of birth  selection

    public void dateofbirth(Context mcontext, EditText meditText) {

        context = mcontext;
        editText = meditText;
        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(context, android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener, year, month, day);
                dialog.getWindow();
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                String date = day + "/" + month + "/" + year;
                editText.setText(date);


            }
        };


    }

    private void findInit() {

        fnameET = findViewById(R.id.fnameET);
        lnameET = findViewById(R.id.lnameET);
        mobileET = findViewById(R.id.mobileET);
        emailET = findViewById(R.id.emailET);
        dateET = findViewById(R.id.dateET);
        facultySP = findViewById(R.id.facultySP);
        uploadBT = findViewById(R.id.uploadBT);
        addhodBT = findViewById(R.id.addhodBT);
        progressBar = findViewById(R.id.progressbar);
        hod_profile_image_IV = findViewById(R.id.hod_profile_image_IV);
        maleRB = findViewById(R.id.maleRB);
        femaleRB = findViewById(R.id.femaleRB);
        genderRG = findViewById(R.id.genderRG);
        profileProgressbar = findViewById(R.id.profileProgressbar);

    }

    // fields validation check

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
            Toast.makeText(this, "Select a branch !", Toast.LENGTH_LONG).show();
            OnvisiblityBtn();
            return;
        }

        if (date.isEmpty()) {
            dateET.setError("Date of birth required is required");
            dateET.requestFocus();
            OnvisiblityBtn();
            return;
        }


        if (mobile.length() != 10) {
            mobileET.setError("Mobile number is invalid please enter a valid one");
            mobileET.requestFocus();
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

        if (!maleRB.isChecked() && !femaleRB.isChecked()) {
            Toast.makeText(this, "Please Select the gender!", Toast.LENGTH_SHORT).show();
            OnvisiblityBtn();
            return;
        }

        // Data is adding to hash map

        name = fname + " " + lname;
        hashMap.put("email", email);
        hashMap.put("name", name);
        hashMap.put("mobile", mobile);
        hashMap.put("date", date);
        hashMap.put("profile", proifle_url);
        hashMap.put("gender", gender);
        hod_profile_image_IV.setClickable(false);
        head_of_departments_ref.document(branch + " hod").set(new GetHodInfo(name, email, date, mobile, branch, proifle_url, gender))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        OnvisiblityBtn();
                        Toast.makeText(AddHodDetailsActivity.this, "Saved details", Toast.LENGTH_SHORT).show();

                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                OnvisiblityBtn();
                Toast.makeText(AddHodDetailsActivity.this, "Some error occured while adding user details!", Toast.LENGTH_SHORT).show();
                Log.d("fire error", e.toString());
            }
        });


    }

    // Getting data from edit text fields

    private void getEditText() {
        email = emailET.getText().toString().trim();
        fname = fnameET.getText().toString().toUpperCase().trim();
        lname = lnameET.getText().toString().toUpperCase().trim();
        date = dateET.getText().toString().trim();
        mobile = mobileET.getText().toString().trim();


    }
    //Progress bar visibilty methods
    private void OnvisiblityBtn() {
        progressBar.setVisibility(View.GONE);
        addhodBT.setVisibility(View.VISIBLE);

    }

    private void OnvisibilityProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        addhodBT.setVisibility(View.GONE);
    }

    // Opening images for uploading

    protected void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            uriHodProfileImage = data.getData();
            Picasso.get().load(uriHodProfileImage).into(hod_profile_image_IV);
        }
    }

    protected String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));


    }

    private void uploadFile() {
        if (uriHodProfileImage != null) {

            profileProgressbar.setVisibility(View.VISIBLE);
            //file name and extension coding
            final StorageReference fileReference = storageReference.child(branch + "_hod_profile_image." + getFileExtension(uriHodProfileImage));

            //uploading  working

            uploadTask = fileReference.putFile(uriHodProfileImage).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    fileReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            proifle_url = uri.toString();
                            profileProgressbar.setVisibility(View.INVISIBLE);
                            hod_profile_image_IV.setClickable(true);

                        }
                    });
                    ++count;
                    Toast.makeText(AddHodDetailsActivity.this, "Profile Image uploaded", Toast.LENGTH_SHORT).show();


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(AddHodDetailsActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    profileProgressbar.setVisibility(View.VISIBLE);
                    double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot.getTotalByteCount());
                    hod_profile_image_IV.setClickable(false);
                    profileProgressbar.setProgress((int) progress);
                }
            });
        } else {
            Toast.makeText(this, "No image Selected!", Toast.LENGTH_SHORT).show();
        }

    }

    // male female selection

    private void genderSelection() {

        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = group.findViewById(checkedId);
                if (radioButton != null && checkedId > -1) {

                    gender = radioButton.getText().toString();

                }


            }
        });
    }

}
