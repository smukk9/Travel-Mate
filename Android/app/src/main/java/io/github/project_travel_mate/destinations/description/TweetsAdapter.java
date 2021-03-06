package io.github.project_travel_mate.destinations.description;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Objects;

import io.github.project_travel_mate.R;
import objects.Tweet;

class TweetsAdapter extends ArrayAdapter<Tweet> {
    private final Activity context;
    private final List<Tweet> tweets;

    TweetsAdapter(Activity context, List<Tweet> tweets) {
        super(context, R.layout.trip_listitem, tweets);
        this.context = context;
        this.tweets = tweets;
    }

    @NonNull
    @Override
    public View getView(final int position, View view, @NonNull ViewGroup parent) {
        ViewHolder holder;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = Objects.requireNonNull(mInflater).inflate(R.layout.tweet_listitem, (ViewGroup) null);
            holder = new ViewHolder();
            holder.name = view.findViewById(R.id.tagmain);
            view.setTag(holder);
        } else
            holder = (ViewHolder) view.getTag();

        holder.name.setText(tweets.get(position).getName());

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(tweets.get(position).getUrl()));
                context.startActivity(browserIntent);
            }
        });
        return view;
    }

    private class ViewHolder {
        TextView name;
    }
}