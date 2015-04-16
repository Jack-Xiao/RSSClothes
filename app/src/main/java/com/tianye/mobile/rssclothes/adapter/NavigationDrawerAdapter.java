package com.tianye.mobile.rssclothes.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tianye.mobile.rssclothes.R;
import com.tianye.mobile.rssclothes.data.NavigationDrawerObject;
import java.util.ArrayList;
import java.util.List;

import static com.tianye.mobile.rssclothes.data.NavigationDrawerObject.DRAWER_ITEM;

/**
 * Created by lenovo on 2015/4/7.
 */
public class NavigationDrawerAdapter extends BaseAdapter {
    private Context context;
    private List<NavigationDrawerObject> data;
    private LayoutInflater mLayoutInflater;


    public NavigationDrawerAdapter(Context context) {
        this.context = context;
        this.mLayoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        createData();
    }

    private void createData() {
        String [] names = context.getResources().getStringArray(R.array.navigation_drawer_name_list);

        //String[] icons = new String[]{String.valueOf(R.drawable.home),String.valueOf(R.drawable.setting)};
        //int [] icons= new int[]{R.drawable.home,R.drawable.setting};
        String[]icons = context.getResources().getStringArray(R.array.navigation_drawer_icon_list);

        data = new ArrayList<NavigationDrawerObject>();

        int amount = names.length < icons.length ? names.length : icons.length;
        for(int i = 0;i<amount;i++) {
            data.add(new NavigationDrawerObject(names[i],icons[i],NavigationDrawerObject.DRAWER_ITEM));
        }
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder viewHolder;
        final NavigationDrawerObject obj = data.get(position);
        if(convertView == null || obj.getType() != ((ViewHolder)convertView.getTag()).type){
            viewHolder = new ViewHolder();
            switch(obj.getType()){
                case DRAWER_ITEM:
                    convertView = mLayoutInflater.inflate(R.layout.navigation_drawer_list_item_text,parent,false);
                    viewHolder.name = (TextView)convertView.findViewById(R.id.navigation_drawer_item_name);
                    viewHolder.icon = (TextView)convertView.findViewById(R.id.navigation_drawer_item_icon);
                    break;
            }
            viewHolder.type = obj.getType();
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder)convertView.getTag();
        }

        switch(obj.getType()){
            case DRAWER_ITEM:
                Typeface font = Typeface.createFromAsset(context.getAssets(),"octicons.ttf");
                viewHolder.icon.setTypeface(font);
                viewHolder.icon.setText(obj.getImageResource());
                viewHolder.name.setText(obj.getTitle());
                //viewHolder.icon.setImageResource(obj.getImageResource());
                break;
        }
        return convertView;
    }

    private static class ViewHolder{
        int type;
        TextView name;
        TextView icon;
    }
}
