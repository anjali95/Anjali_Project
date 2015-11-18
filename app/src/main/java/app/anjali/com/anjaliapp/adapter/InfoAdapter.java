package app.anjali.com.anjaliapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


import app.anjali.com.anjaliapp.R;
import app.anjali.com.anjaliapp.model.TrainSchedule;

/**
 * Created by GB on 11/18/2015.
 */
public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.ViewHolder> {

    List<TrainSchedule> mItems;
    Context context;

    public InfoAdapter(Context context,List<TrainSchedule> pc) {
        super();
        mItems = pc;
        this.context=context;


    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.custom_row, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
       final TrainSchedule ts = mItems.get(i);
        viewHolder.tvstationame.setText(ts.getStationname());
        viewHolder.tvarrival.setText(ts.getArrivaltime());
        viewHolder.tvdeparture.setText(ts.getDeparturetime());
        viewHolder.tvdistance.setText(ts.getDistance());


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {


        public TextView tvstationame;
        public TextView tvarrival;
        public TextView tvdeparture;
        public TextView tvdistance;


        public ViewHolder(View itemView) {
            super(itemView);
           tvstationame = (TextView) itemView.findViewById(R.id.tvstationname);
          tvarrival = (TextView) itemView.findViewById(R.id.tvarrival);
            tvdeparture = (TextView) itemView.findViewById(R.id.tvdeparture);
            tvdistance=(TextView)itemView.findViewById(R.id.tvdistance);

        }
    }
}

