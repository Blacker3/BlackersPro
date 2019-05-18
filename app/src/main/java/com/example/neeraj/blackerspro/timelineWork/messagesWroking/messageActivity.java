package com.example.neeraj.blackerspro.timelineWork.messagesWroking;

import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


import java.util.Calendar;
import java.util.HashMap;

public class messageActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
     EditText sentmsgET;
    private ImageButton sentIMGBT;
    private String sentmsg,userName,time;
    private FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private HashMap<String, String> hashMap = new HashMap<>();
    private CollectionReference chat_ref = db.collection("Chat");
    private MessageAdapter messageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        setTitle("Chat");
        sentmsgET = findViewById(R.id.sentmsgET);
        sentIMGBT = findViewById(R.id.sentIMGBT);

        sentIMGBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               sentMsg();
            }
        });
setUpRecyclerView();

    }

    private void sentMsg() {
        sentmsg = sentmsgET.getText().toString();
        time = Calendar.getInstance().getTime().toString();
        userName ="Anonymous";
      //  userName = firebaseUser.getEmail().toString();
        if (!sentmsg.equals("") && !sentmsg.equals(null)) {

            hashMap.put("msg", sentmsg);
             hashMap.put("user",userName );
            hashMap.put("time",time );

            chat_ref.document("msg_" + Calendar.getInstance().getTime().toString()).set(new Messages(sentmsg,time,userName))
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    sentmsgET.setText("");
                }
            });

        } else {
            Toast.makeText(this, "Type a message!", Toast.LENGTH_SHORT).show();
        }

    }


    private void setUpRecyclerView() {

        Query query = chat_ref.orderBy("time", Query.Direction.ASCENDING);
        FirestoreRecyclerOptions<Messages> messagesFirestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<Messages>().setQuery(query, Messages.class).build();
        messageAdapter = new MessageAdapter(messagesFirestoreRecyclerOptions);

        RecyclerView msg_RV = findViewById(R.id.msg_RV);
        msg_RV.setHasFixedSize(true);
        msg_RV.setLayoutManager(new LinearLayoutManager(this));
        msg_RV.setAdapter(messageAdapter);


    }


    @Override
    protected void onStart() {
        super.onStart();
        messageAdapter.startListening();

    }


    @Override
    protected void onStop() {
        super.onStop();
        messageAdapter.stopListening();



    }
}
