package com.ht.mynote.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.ht.mynote.activities.R;
import com.ht.mynote.pojo.Notes;

import java.util.List;

/**
 * Created by IntelliJ IDEA
 * Project: com.ht.mynote.adapters
 * Author: 安诺爱成长
 * Email: 1399487511@qq.com
 * Date: 2015/5/2
 */
public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<Notes> list;

    public MyAdapter(Context context, List<Notes> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listview_layout, null);
            viewHolder = new ViewHolder();
            viewHolder.title = (TextView) view.findViewById(R.id.title);
            viewHolder.time = (TextView) view.findViewById(R.id.time);
            view.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) view.getTag();
        viewHolder.title.setText(list.get(i).getTitle());
        viewHolder.time.setText(list.get(i).getTime());
        return view;
    }

    private class ViewHolder {
        private TextView title;
        private TextView time;
    }
}
