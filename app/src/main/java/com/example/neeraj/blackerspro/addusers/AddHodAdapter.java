package com.example.neeraj.blackerspro.addusers;

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
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class AddHodAdapter extends FirestoreRecyclerAdapter<GetHodInfo, AddHodAdapter.AddHodViewholder> {

    private Context context;
    private onItemclickListener listener;
    int position;

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public AddHodAdapter(@NonNull FirestoreRecyclerOptions<GetHodInfo> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull final AddHodViewholder holder, int position, @NonNull GetHodInfo model) {
        //setting up the layout fields by there position
        holder.hod_name_TV.setText(model.getName());
        holder.hod_branch_TV.setText(model.getBranch());
        holder.hod_dob_TV.setText(model.getDob());
        holder.hod_phone_TV.setText(model.getPhone());
        holder.hod_email_TV.setText(model.getEmail());

        Picasso.get().load(model.getProfileimageurl()).into(holder.hod_profile_image_IV);


    }


    @NonNull
    @Override
    public AddHodViewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.show_hod_deatils_rv_layout, viewGroup, false);

        return new AddHodViewholder(view);
    }

    public void deleteItem(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();

    }


    class AddHodViewholder extends RecyclerView.ViewHolder {
        //Declaring variables
        TextView hod_name_TV;
        TextView hod_branch_TV;
        TextView hod_dob_TV;
        TextView hod_phone_TV;
        TextView hod_email_TV;
        ImageView options_menu_TV;
        CircleImageView hod_profile_image_IV;

        // initializing variables
        public AddHodViewholder(@NonNull final View itemView) {
            super(itemView);
            hod_name_TV = itemView.findViewById(R.id.staff_name_TV);
            hod_branch_TV = itemView.findViewById(R.id.staff_branch_TV);
            hod_dob_TV = itemView.findViewById(R.id.staff_dob_TV);
            hod_email_TV = itemView.findViewById(R.id.staff_email_TV);
            hod_phone_TV = itemView.findViewById(R.id.staff_phone_TV);
            hod_profile_image_IV = itemView.findViewById(R.id.hod_profile_image_IV);
            options_menu_TV = itemView.findViewById(R.id.options_menu_TV);
            // getting context
            context = itemView.getContext();

            // item click working
            if (position != RecyclerView.NO_POSITION && listener != null) {
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        position = getAdapterPosition();

                        listener.onItemClick(getSnapshots().getSnapshot(position), position);
                    }
                });
            }
// option click working
            options_menu_TV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    position = getAdapterPosition();

                    PopupMenu popupMenu = new PopupMenu(context, options_menu_TV);
                    popupMenu.inflate(R.menu.recycler_item_option_menu);
                    popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if (position != RecyclerView.NO_POSITION && listener != null) {
                                switch (item.getItemId()) {
// for delete item of option menu
                                    case R.id.delete_hod_details:
                                        listener.onPopMenuDelete(position);
                                        break;

                                    //for update item

                                    case R.id.update_hod_details:
                                        listener.onPopMenuUpdate(getSnapshots().getSnapshot(position), position);

                                        break;
                                    default:
                                        Toast.makeText(context, "Something Went wrong !", Toast.LENGTH_LONG).show();

                                        break;

                                }
                            }

                            return false;
                        }

                    });

                    popupMenu.show();

                }

            });


        }


    }

    //Interfaces for option menu

    public interface onItemclickListener {
        //Item click interface
        void onItemClick(DocumentSnapshot documentSnapshot, int position);

        //option delete
        void onPopMenuDelete(int position);

        //option update
        void onPopMenuUpdate(DocumentSnapshot documentSnapshot, int position);
    }


    public void setOnItemClickListner(onItemclickListener listner) {
        this.listener = listner;
    }

}
