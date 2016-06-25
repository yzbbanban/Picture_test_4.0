package com.wangban.yzbbanban.picture_test_40.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangban.yzbbanban.picture_test_40.R;
import com.wangban.yzbbanban.picture_test_40.dao.DetialImage;

import java.util.*;

/**
 * Created by YZBbanban on 16/6/5.
 */
public class DetialImageAdapter extends BaseAdapter<DetialImage> {


    protected DetialImageAdapter(Context context, ArrayList<DetialImage> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getLayoutInflater().inflate(R.layout.detial_img_item, null);
            holder.DetailImg = (ImageView) convertView.findViewById(R.id.iv_detial_image);
            convertView.setTag(holder);
        }else {
            holder= (ViewHolder) convertView.getTag();
        }
        //TODO
        return convertView;
    }

    private class ViewHolder {
        ImageView DetailImg;


    }
}
