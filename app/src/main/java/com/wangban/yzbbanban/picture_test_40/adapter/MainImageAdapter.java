package com.wangban.yzbbanban.picture_test_40.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.wangban.yzbbanban.picture_test_40.R;
import com.wangban.yzbbanban.picture_test_40.contast.Contast;
import com.wangban.yzbbanban.picture_test_40.dao.MainImage;
import com.wangban.yzbbanban.picture_test_40.entity.Image;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YZBbanban on 16/6/5.
 */
public class MainImageAdapter extends BaseAdapter<Image> implements Contast {

    public MainImageAdapter(Context context, ArrayList<Image> data) {
        super(context, data);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Image img = getData().get(position);
        ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = getLayoutInflater().inflate(R.layout.main_img_item, null);
            holder.mianImg = (ImageView) convertView.findViewById(R.id.iv_main_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mianImg.setImageResource(R.drawable.katong);
        //TODO
        ImageAsyncTask task;
        task = tasks.get(convertView);
        if(task != null) {
            task.cancel(true);
            task = null;
        }
        String path = img.getPath();
        task = new ImageAsyncTask(holder.mianImg,img);
        tasks.put(convertView, task);
        task.execute(path);

        return convertView;
    }

    private class ViewHolder {
        ImageView mianImg;
    }

    private Map<View, ImageAsyncTask> tasks = new HashMap<View, MainImageAdapter.ImageAsyncTask>();

    private class ImageAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView imageView;
        private Image img;

        public ImageAsyncTask(ImageView imageView, Image img) {
            this.imageView = imageView;
            this.img = img;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            Bitmap bitmap = null;

            try {
                URLConnection connection = new URL(url).openConnection();
                InputStream is = connection.getInputStream();


                int rate = 1;
                if (getData().get(0).getWidth() > MAX_SIZE && getData().get(0).getHeight() > MAX_SIZE) {
                    if (getData().get(0).getWidth() < getData().get(0).getHeight()) {
                        rate = getData().get(0).getHeight() / MAX_SIZE;
                    } else {
                        rate = getData().get(0).getWidth() / MAX_SIZE;
                    }
                }

                BufferedInputStream bis = new BufferedInputStream(is);
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = rate;

                Bitmap bm = BitmapFactory.decodeStream(bis, null, opts);

            } catch (IOException e) {
                e.printStackTrace();
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            imageView.setImageBitmap(bitmap);
        }
    }

}
