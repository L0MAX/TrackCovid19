package com.insightnet.trackcovid19.ui.events;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.github.curioustechizen.ago.RelativeTimeTextView;
import com.insightnet.trackcovid19.R;

import java.util.List;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventViewHolder> {
    private List<Event> items;

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        // Card fields
        public TextView event;
        public TextView id;
        public RelativeTimeTextView timestamp;
        public TextView data;

        public EventViewHolder(View v) {
            super(v);
            event = (TextView) v.findViewById(R.id.event);
            id = (TextView) v.findViewById(R.id.id);
            timestamp = (RelativeTimeTextView) v.findViewById(R.id.timestamp);
            data = (TextView) v.findViewById(R.id.data);
        }
    }

    public EventAdapter(List<Event> items) {
        this.items = items;
    }

    public void addEvent(Event event) {
        // Add the event at the beggining of the list
        items.add(0, event);
        // Notify the insertion so the view can be refreshed
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public EventViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.event_row, viewGroup, false);
        return new EventViewHolder(v);
    }

    @Override
    public void onBindViewHolder(EventViewHolder viewHolder, int i) {
        Event event = items.get(i);

        viewHolder.event.setText(event.getName());
        viewHolder.id.setText(event.getId());
        viewHolder.timestamp.setReferenceTime(System.currentTimeMillis());
        viewHolder.data.setText(event.getData());
    }
}