/*
 * Copyright (C) 2014 pengjianbo(pengjianbosoft@gmail.com), Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.wd.yiyangyun.mvp.home.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.mvp.home.bean.VideoListBean;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.toolsfinal.DeviceUtils;

/**
 * Desction:
 * Date:15/12/1 下午8:42
 */
public class PicPhotoListAdapter extends BaseAdapter {
    private List<VideoListBean.DataBean.ListBean> mList;
    private int mScreenWidth;
    private Context mContext;
    private Bitmap bitmap;
    private MediaMetadataRetriever retriever;
    private String videoName;

    public PicPhotoListAdapter(Activity activity, List<VideoListBean.DataBean.ListBean> list, Context mContext) {
        this.mList = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView.inflate(mContext,R.layout.adapter_photo_list_item, null);
        ImageView iv_photo = (ImageView) view.findViewById(R.id.shiying_iv_photo);
        ImageView gsy_video_info =  view.findViewById(R.id.gsy_video_info);
        TextView yingshi_item_tv = (TextView) view.findViewById(R.id.yingshi_item_tv);
        String url = mList.get(position).getFileUrl();
        if(url.substring(url.length()-3).equals("mp4") || url.substring(url.length()-3).equals("wmv") ||
            url.substring(url.length()-2).equals("rm") || url.substring(url.length()-5).equals("rmvb") ){
            //增加title
            String[]  strs1= url.split("/");
            for(int i=0; i< strs1.length; i++){
                if(i==strs1.length){
                    videoName = strs1[i].toString();
                    System.out.print("----"+strs1[i].toString());
                }
            }
            Bitmap bitmap = getNetVideoBitmap("http://shenkangyun.com:808/skyapp_ndpt/"+url,videoName);
            gsy_video_info.setImageBitmap(bitmap);
            gsy_video_info.setVisibility(View.VISIBLE);
        }else{
            Glide.with(mContext).load("http://shenkangyun.com:808/skyapp_ndpt/"+mList.get(position).getFileUrl()).into(iv_photo);
        }

        gsy_video_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick.onButtonClick(position,0);
            }
        });

        iv_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonClick.onButtonClick(position,1);
            }
        });
        return view;
    }


    /** 获取视频第一帧 */
    public Bitmap getNetVideoBitmap(String videoPath, String videoName) {
        try {
            bitmap = null;
            retriever = new MediaMetadataRetriever();
            // 获取本地视频的第一帧 //
            retriever.setDataSource(videoPath);
            // 获得第一帧图片
            bitmap = retriever.getFrameAtTime();

            // 视频第一帧的压缩 /*
            FileOutputStream outStream = null;
            outStream = new FileOutputStream(new File(mContext.getExternalCacheDir().getAbsolutePath() + "/" + "视频"+ videoName + ".jpg"));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 10, outStream); outStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
            return bitmap;
        }
    }

    private onButtonClick onButtonClick;

    public void setOnButtonClick(onButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    public interface onButtonClick {
        /**
         * @param position index
         * @param type     0 删除 1 添加量化指标任务 2 时间选择  3 添加量表任务
         */
        void onButtonClick(int position, int type);
    }
}
