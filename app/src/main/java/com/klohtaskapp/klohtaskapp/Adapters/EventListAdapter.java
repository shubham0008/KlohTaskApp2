package com.klohtaskapp.klohtaskapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.klohtaskapp.klohtaskapp.Activites.EventDetailActivity;
import com.klohtaskapp.klohtaskapp.Activites.EventListActivity;
import com.klohtaskapp.klohtaskapp.Models.EventListCardModel;
import com.klohtaskapp.klohtaskapp.R;
import com.klohtaskapp.klohtaskapp.Utility.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class EventListAdapter extends RecyclerView.Adapter<EventListAdapter.EventViewHolder>{
    private ArrayList<EventListCardModel> events;
    private Context context;

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitle;
        ImageView eventPhoto,eventHostPhoto;
        TextView eventTime;
        TextView eventLocation;
        TextView eventSummary;
        CardView eventCard;


        public EventViewHolder(View itemView) {
            super(itemView);
            eventCard = itemView.findViewById(R.id.event_card);
            eventTitle = itemView.findViewById(R.id.evt_title_view);
            eventPhoto = itemView.findViewById(R.id.evt_picture_view);
            eventHostPhoto = itemView.findViewById(R.id.evt_host_picture_view);
            eventTime = itemView.findViewById(R.id.evt_date_view);
            eventLocation = itemView.findViewById(R.id.evt_location_view);
            eventSummary = itemView.findViewById(R.id.evt_summary_view);
        }
    }

    public EventListAdapter(ArrayList<EventListCardModel> events, Context context) {
        this.events = events;
        this.context = context;
    }

    @Override
    public EventListAdapter.EventViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card_layout, parent, false);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, final int position) {

        holder.eventTitle.setText(events.get(position).getEventTitle().replace("\"",""));
        holder.eventTime.setText("  "+events.get(position).getEventTime().replace("\"",""));
        holder.eventSummary.setText(events.get(position).getEventSummary().replace("\"",""));
        holder.eventLocation.setText("@"+events.get(position).getEventLocation().replace("\"",""));
        Picasso.with(context).load(events.get(position).getEventPicUrl().replace("\"","")).into(holder.eventPhoto);
        Picasso.with(context).load(events.get(position).getEventHostPicture().replace("\"","")).transform(new CircleTransform()).into(holder.eventHostPhoto);

        holder.eventCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, EventDetailActivity.class).putExtra("activityId",events.get(position).getEventId().replace("\"","")).setFlags(FLAG_ACTIVITY_NEW_TASK));
            }
        });
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}
