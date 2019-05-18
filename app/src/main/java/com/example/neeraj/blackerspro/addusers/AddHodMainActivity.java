package com.example.neeraj.blackerspro.addusers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.neeraj.blackerspro.MainActivity;
import com.example.neeraj.blackerspro.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class AddHodMainActivity extends AppCompatActivity {
    private Context context = AddHodMainActivity.this;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference head_of_departments_ref = db.collection("User Details").document("Head Of Departments Documents").collection("Head Of Departments");
    private AddHodAdapter addHodAdapter;
    private ProgressBar progressbarRV;
    private FirebaseStorage firebaseStorage;
    private GetHodInfo getHodInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Add Hods");
        setContentView(R.layout.showhod);
        FloatingActionButton floatBT = findViewById(R.id.floatBT);
        firebaseStorage = FirebaseStorage.getInstance();
        progressbarRV = findViewById(R.id.progressbarRV);

        // Add hod floating action button
        floatBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(context, AddHodDetailsActivity.class));
            }
        });

        setUpRecyclerView();

        //Interface of Add hod adapter
        addHodAdapter.setOnItemClickListner(new AddHodAdapter.onItemclickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                Toast.makeText(context, position + "Item Clicked ", Toast.LENGTH_SHORT).show();
            }

            //Delete iterface  predefined method

            @Override
            public void onPopMenuDelete(int position) {
                addHodAdapter.deleteItem(position);
                getHodInfo = addHodAdapter.getItem(position);
                String url = getHodInfo.getProfileimageurl();
                StorageReference storageReference = firebaseStorage.getReferenceFromUrl(url);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(context, " Image and data Deleted!", Toast.LENGTH_SHORT).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }


            //Update iterface  predefined method
            @Override
            public void onPopMenuUpdate(DocumentSnapshot documentSnapshot, int position) {

                Intent intent = new Intent(context, UpdateHodDetails.class);
                getHodInfo = addHodAdapter.getItem(position);
                String url = getHodInfo.getProfileimageurl();
                StorageReference storageReference = firebaseStorage.getReferenceFromUrl(url);

                String collectioRef = documentSnapshot.getReference().getPath();
                intent.putExtra("document snapshot", collectioRef);
                startActivity(intent);

            }
        });


    }

    //Setting recyceler view

    private void setUpRecyclerView() {

        Query query = head_of_departments_ref.orderBy("branch", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<GetHodInfo> getHodInfoFirestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<GetHodInfo>().setQuery(query, GetHodInfo.class).build();
        addHodAdapter = new AddHodAdapter(getHodInfoFirestoreRecyclerOptions);

        RecyclerView show_hod_RV = findViewById(R.id.show_hod_RV);
        show_hod_RV.setHasFixedSize(true);
        show_hod_RV.setLayoutManager(new LinearLayoutManager(this));
        show_hod_RV.setAdapter(addHodAdapter);



    }


    @Override
    protected void onStart() {
        super.onStart();
        addHodAdapter.startListening();
        progressbarRV.setVisibility(View.GONE);
    }


    @Override
    protected void onStop() {
        super.onStop();
        addHodAdapter.stopListening();
        progressbarRV.setVisibility(View.GONE);


    }


}


