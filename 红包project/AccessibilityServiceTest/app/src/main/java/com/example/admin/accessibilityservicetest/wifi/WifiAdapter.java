package com.example.admin.accessibilityservicetest.wifi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.admin.accessibilityservicetest.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liukan on 2018/4/3.
 */

public class WifiAdapter extends BaseAdapter {
    private Context context;
    private List<String> list = new ArrayList();

    public WifiAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.layout_adapter_wifi, null);
            holder = new ViewHolder();
            holder.tvWifiName = convertView.findViewById(R.id.tvWifiName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvWifiName.setText(list.get(position));
        return convertView;
    }

    public void setList(List<String> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }

    static class ViewHolder {
        private TextView tvWifiName;
    }

}
