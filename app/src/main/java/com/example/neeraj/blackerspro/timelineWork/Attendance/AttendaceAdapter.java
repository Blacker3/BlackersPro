package com.example.neeraj.blackerspro.timelineWork.Attendance;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.neeraj.blackerspro.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class AttendaceAdapter extends RecyclerView.Adapter<AttendaceAdapter.AttendanceAdapterViewHolder> {


    List<GetAttendanceData> getAttendanceDatas;
    Context context;

    public AttendaceAdapter(List<GetAttendanceData> getAttendanceDatas, Context context) {
        this.getAttendanceDatas = getAttendanceDatas;
        this.context = context;
    }

    @NonNull
    @Override
    public AttendanceAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.attendacelayout,viewGroup,false);
        return new AttendanceAdapterViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AttendanceAdapterViewHolder ViewHolder, int i) {

    GetAttendanceData getAttendanceData = getAttendanceDatas.get(i);
    ViewHolder.studentName.setText(getAttendanceData.getName());
    ViewHolder.studentImage.setImageResource(getAttendanceData.getImage());

    }

    @Override
    public int getItemCount() {
        return getAttendanceDatas.size();
    }

    public class AttendanceAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView studentName;
        CircleImageView studentImage;
        public AttendanceAdapterViewHolder(@NonNull View itemView) {

            super(itemView);

            studentImage = itemView.findViewById(R.id.studentImage);
            studentName = itemView.findViewById(R.id.studentName);
        }
    }
}
