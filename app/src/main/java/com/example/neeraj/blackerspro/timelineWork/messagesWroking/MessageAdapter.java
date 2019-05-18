package com.example.neeraj.blackerspro.timelineWork.messagesWroking;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.neeraj.blackerspro.R;
import com.example.neeraj.blackerspro.addusers.GetHodInfo;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class MessageAdapter extends FirestoreRecyclerAdapter<Messages, MessageAdapter.MessageViewholder> {
    public static final int MSG_LEFT = 0;
    public static final int MSG_RIGHT = 1;

    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MessageAdapter(@NonNull FirestoreRecyclerOptions<Messages> options) {
        super(options);    }

    @Override
    protected void onBindViewHolder(@NonNull final MessageViewholder holder, int position, @NonNull Messages model) {
        //setting up the layout fields by there position
        holder.msgTV.setText(model.getMsg());
        if(model.getUsername()!=null) {
            holder.user_nameTV.setText(model.getUsername());
        }

    }


    @NonNull
    @Override
    public MessageViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        if (i == MSG_RIGHT) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_msg_right, viewGroup, false);

        return new MessageViewholder(view);}
        else {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chat_msg_left, viewGroup, false);

            return new MessageViewholder(view);
        }
    }




    class MessageViewholder extends RecyclerView.ViewHolder {
        //Declaring variables
        TextView msgTV;
        TextView user_nameTV;


        // initializing variables
        public MessageViewholder(@NonNull final View itemView) {
            super(itemView);

            msgTV = itemView.findViewById(R.id.msgTV);
            user_nameTV = itemView.findViewById(R.id.user_nameTV);


        }


    }

    @Override
    public int getItemViewType(int position) {

            if (!firebaseUser.getEmail().equalsIgnoreCase(getItem(position).username)){
                return MSG_LEFT;
            }
            else {
                return MSG_RIGHT;
            }

    }
}
