package lab.wasikrafal.movies;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Rafał on 23.05.2017.
 */

public class ListAdapter extends ArrayAdapter
{

    private Context context;

    public ListAdapter(Context context, List items)
    {
        super(context, R.layout.actor_item, items);
        this.context = context;
    }

    private class ViewHolder{
        TextView titleText;
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder holder = null;
        ActorItem item = (ActorItem)getItem(position);
        View viewToUse = null;


        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {

            viewToUse = mInflater.inflate(R.layout.actor_item, null);
            holder = new ViewHolder();
            holder.titleText = (TextView)viewToUse.findViewById(R.id.titleTextView);
            viewToUse.setTag(holder);
        } else {
            viewToUse = convertView;
            holder = (ViewHolder) viewToUse.getTag();
        }

        holder.titleText.setText(item.getName());
        holder.titleText.setTextSize(35);
        return viewToUse;
    }
}