package com.wangban.yzbbanban.picture_test_40.dao;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.wangban.yzbbanban.picture_test_40.contast.Contast;
import com.wangban.yzbbanban.picture_test_40.entity.Image;
import com.wangban.yzbbanban.picture_test_40.ui.MainActivity;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.*;

import java.io.IOException;

/**
 * Created by YZBbanban on 16/6/5.
 */
public class MainImage implements Contast {
    private Context context;
    private String skipPagePath;
    private String path2;
    private int width;
    private int height;
    private ArrayList<Image> images=new ArrayList<>();
    private String webPath;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case HANDLER_PICDAL_SUCCESS:
                    images = (ArrayList<Image>) msg.obj;

                    Log.i(TAG, "handleMessage: "+images.get(0).getPath());

                    MainActivity activity= (MainActivity) context;

                    activity.getDataFromMainImage(images);

                    break;
            }
        }
    };

    public MainImage(Context context, String webPath) {
        this.context = context;
        this.webPath = webPath;
        Thread thread = new InnerThread();
        thread.start();
    }

    private class InnerThread extends Thread {

        @Override
        public void run() {
            try {
               // Log.i(TAG, "run: "+webPath+"\n");
                Document doc = Jsoup.connect(webPath).get();
                Elements e = doc.getElementsByClass("post-thumb");
                for (int i = 0; i < e.size(); i++) {
                    Elements a = e.get(i).getElementsByTag("a");
                    skipPagePath = a.first().attr("href");

                    String width2 = a.first().getElementsByTag("img").attr("width");
                    String height2 = a.first().getElementsByTag("img").attr("height");

                    path2 = a.first().getElementsByTag("img").attr("src");
                    width = Integer.parseInt(width2);
                    height = Integer.parseInt(height2);
                   // Log.i(TAG, "run: " + "path2  " + path2 + "\n" + "width " + width2 + "\nheight " + height2);
                    Image img=new Image();
                    img.setSetSkipPagePath(skipPagePath);
                    img.setPath(path2);
                    img.setHeight(height);
                    img.setWidth(width);
                    img.setLocalPath(webPath);
                    images.add(img);
                    // Log.i(TAG, "getData: path:"+ i+";"+pic.getPath());

                }
                Message msg = Message.obtain();
                msg.what = HANDLER_PICDAL_SUCCESS;
                msg.obj = images;
                handler.sendMessage(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
