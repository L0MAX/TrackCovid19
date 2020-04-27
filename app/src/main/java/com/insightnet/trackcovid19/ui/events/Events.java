package com.insightnet.trackcovid19.ui.events;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.gson.Gson;
import com.insightnet.trackcovid19.R;
import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionStateChange;

import java.util.ArrayList;
import java.util.List;

public class Events extends AppCompatActivity {
    private RecyclerView.LayoutManager lManager;
    private EventAdapter adapter;
    private Pusher pusher = new Pusher("134c645cc814ab914097");
    private static final String CHANNEL_NAME = "events_to_be_shown";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        // Get the RecyclerView
        RecyclerView recycler = (RecyclerView) findViewById(R.id.recycler_view);

        // Use LinearLayout as the layout manager
        lManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lManager);

        // Set the custom adapter
        List<Event> eventList = new ArrayList<>();
        adapter = new EventAdapter(eventList);
        recycler.setAdapter(adapter);

        // Subscribe to a channel
        Channel channel = pusher.subscribe(CHANNEL_NAME);

        // Action to be executed when an event is received
        SubscriptionEventListener eventListener = new SubscriptionEventListener() {
            @Override
            public void onEvent(String channel, final String event, final String data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Received event with data: " + data);
                        Gson gson = new Gson();
                        Event evt = gson.fromJson(data, Event.class);
                        evt.setName(event + ":");
                        adapter.addEvent(evt);
                        ((LinearLayoutManager)lManager).scrollToPositionWithOffset(0, 0);
                    }

                });
            }
        };
        // Bind to listen for events sent to the channel
        channel.bind("created", eventListener);
        channel.bind("updated", eventListener);
        channel.bind("deleted", eventListener);

        // connect to the Pusher API
        pusher.connect(new ConnectionEventListener() {
            @Override
            public void onConnectionStateChange(ConnectionStateChange change) {
                System.out.println("State changed to " + change.getCurrentState() +
                        " from " + change.getPreviousState());
            }

            @Override
            public void onError(String message, String code, Exception e) {
                System.out.println("There was a problem connecting!");
                e.printStackTrace();
            }
        });
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        pusher.disconnect();
    }
}
