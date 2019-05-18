package com.example.neeraj.blackerspro.timelineWork.tutorials;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.neeraj.blackerspro.R;



import java.util.List;

public class TutorialAdapter extends RecyclerView.Adapter<TutorialAdapter.ViewHolder> {


    private List<TutorialsTitle> tutorialsTitles;
    private OnItemClickListener listner;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView countTV, tutorials_headingTV;

        public ViewHolder(final View view) {
            super(view);
            countTV = view.findViewById(R.id.countTV);
            tutorials_headingTV = view.findViewById(R.id.tutorials_headingTV);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listner != null ){
                        int position = getAdapterPosition();
                        if(position!= RecyclerView.NO_POSITION){
                            listner.onItemClick(position);
                        }
                    }
                }
            });



        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tutorials_rv_layout, viewGroup, false);




        return new ViewHolder(itemView);

    }

    public TutorialAdapter(List<TutorialsTitle> tutorialsTitles,Context context) {
        this.tutorialsTitles = tutorialsTitles;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {

        TutorialsTitle tutorialsTitle = tutorialsTitles.get(i);
        viewHolder.tutorials_headingTV.setText(tutorialsTitle.getTittle());
        viewHolder.countTV.setText(tutorialsTitle.getPosition());


    }


    @Override
    public int getItemCount() {
        return tutorialsTitles.size();
    }


    public interface  OnItemClickListener{
        void onItemClick(int position);
    }
       public void setOnItemClickListner(OnItemClickListener listner){
        this.listner = listner;
       }

}
