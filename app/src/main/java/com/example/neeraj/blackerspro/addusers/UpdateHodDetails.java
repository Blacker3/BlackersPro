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
import android.support.v7.app.AppCompatActivity;


import android.util.Log;
import android.util.Patterns;
import android.view.View;

import android.webkit.MimeTypeMap;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.example.neeraj.blackerspro.R;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
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

public class UpdateHodDetails extends AppCompatActivity {
    private Context context;

    private EditText fnameET;
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
    private ProgressBar progressBar, profileProgressbar,updatePB;
    private StorageTask uploadTask;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private DocumentReference head_of_departments_document_ref;
    private StorageReference storageReference = FirebaseStorage.getInstance().getReference("profileImages/HodProfileImage");
    ;
    private GetHodInfo getHodInfo;
    private AddHodDetailsActivity addHodDetailsActivity = new AddHodDetailsActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Update Hod Details");
        setContentView(R.layout.update_hod_details);

        findInit();
        genderSelection();

        Intent intent = getIntent();
        String collectionRef = intent.getStringExtra("document snapshot");


        if (collectionRef != null && collectionRef != " ") {
            addHodDetailsActivity.dateofbirth(UpdateHodDetails.this, dateET);
            head_of_departments_document_ref = db.document(collectionRef);
            head_of_departments_document_ref.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    getHodInfo = documentSnapshot.toObject(GetHodInfo.class);
                    name = getHodInfo.getName();

                    email = getHodInfo.getEmail();
                    date = getHodInfo.getDob();
                    mobile = getHodInfo.getPhone();
                    branch = getHodInfo.getBranch();
                    proifle_url = getHodInfo.getProfileimageurl();
                    gender = getHodInfo.getGender();

                    setTheText();
                    updatePB.setVisibility(View.GONE);

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(UpdateHodDetails.this, "There is some error occure while getting data ! ", Toast.LENGTH_SHORT).show();
                }
            });
        }


        hod_profile_image_IV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


        uploadBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinnerCount == 0) {
                    Toast.makeText(UpdateHodDetails.this, "Select a branch !", Toast.LENGTH_LONG).show();

                    return;
                }
                if (uploadTask != null && uploadTask.isInProgress()) {
                    hod_profile_image_IV.setClickable(false);
                    Toast.makeText(UpdateHodDetails.this, "Profile image is uploading......", Toast.LENGTH_SHORT).show();
                } else {
                    uploadFile();


                }
            }
        });

        addhodBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (uploadTask != null && uploadTask.isInProgress()) {
                    hod_profile_image_IV.setClickable(false);
                    Toast.makeText(UpdateHodDetails.this, "Profile image is uploading......", Toast.LENGTH_SHORT).show();
                } else {
                    validateFields();
                }


            }
        });
    }

    // intializing variables

    private void findInit() {

        fnameET = findViewById(R.id.fnameET);

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
        updatePB = findViewById(R.id.updatePB);
    }

// seting the data in the layout fields

    private void setTheText() {
        Picasso.get().load(proifle_url).into(hod_profile_image_IV);
        fnameET.setText(name, TextView.BufferType.EDITABLE);
        for (int i = 0; i < branchAR.length; i++) {
            if (branchAR[i].equalsIgnoreCase(branch)) {
                spinnerCount = i;
                break;
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, branchAR);

        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        facultySP.setEnabled(false);
        facultySP.setClickable(false);
        facultySP.setAdapter(arrayAdapter);
        facultySP.setSelection(spinnerCount);
        dateET.setText(date, TextView.BufferType.EDITABLE);
        mobileET.setText(mobile, TextView.BufferType.EDITABLE);
        emailET.setText(email, TextView.BufferType.EDITABLE);
        if (gender.equalsIgnoreCase("Male")) {
            maleRB.setChecked(true);
        } else if (gender.equalsIgnoreCase("Female")) {
            femaleRB.setChecked(true);
        }


    }







//For Profile image code

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


// Uploading image to firebase storage

    private void uploadFile() {
        if (uriHodProfileImage != null) {
            profileProgressbar.setVisibility(View.VISIBLE);
            //image file  naming
            final StorageReference fileReference = storageReference.child(branch + "_hod_profile_image." + getFileExtension(uriHodProfileImage));
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
                    Toast.makeText(UpdateHodDetails.this, "Profile Image uploaded", Toast.LENGTH_SHORT).show();


                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {

                    Toast.makeText(UpdateHodDetails.this, e.getMessage(), Toast.LENGTH_SHORT).show();

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

    //getting fields data for update

    private void getEditText() {
        email = emailET.getText().toString().trim();
        name = fnameET.getText().toString().toUpperCase().trim();
        date = dateET.getText().toString().trim();
        mobile = mobileET.getText().toString().trim();
    }

    // validating fields and updating data

    private void validateFields() {

        getEditText();

        OnvisibilityProgressBar();

        if (name.isEmpty()) {
            fnameET.setError("First name is required");
            fnameET.requestFocus();
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


        hashMap.put("email", email);
        hashMap.put("name", name);
        hashMap.put("mobile", mobile);
        hashMap.put("date", date);
        hashMap.put("profile", proifle_url);
        hashMap.put("gender", gender);
        hod_profile_image_IV.setClickable(false);
        head_of_departments_document_ref.set(new GetHodInfo(name, email, date, mobile, branch, proifle_url, gender))
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        OnvisiblityBtn();
                        Toast.makeText(UpdateHodDetails.this, "Saved details", Toast.LENGTH_SHORT).show();

                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                OnvisiblityBtn();
                Toast.makeText(UpdateHodDetails.this, "Some error occured while adding user details!", Toast.LENGTH_SHORT).show();
                Log.d("fire error", e.toString());
            }
        });


    }

// progress bar working
    private void OnvisiblityBtn() {
        progressBar.setVisibility(View.GONE);
        addhodBT.setVisibility(View.VISIBLE);

    }

    private void OnvisibilityProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        addhodBT.setVisibility(View.GONE);
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