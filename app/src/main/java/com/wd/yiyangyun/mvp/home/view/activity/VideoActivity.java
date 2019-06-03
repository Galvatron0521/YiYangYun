package com.wd.yiyangyun.mvp.home.view.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.home.adapter.PicPhotoListAdapter;
import com.wd.yiyangyun.mvp.home.bean.VideoListBean;
import com.wd.yiyangyun.mvp.mine.bean.PersonlIconBean;
import com.wd.yiyangyun.mvp.mine.view.activity.MainActivity;
import com.wd.yiyangyun.okutils.DoctorNetService;
import com.wd.yiyangyun.okutils.NetWorkRequestInterface;
import com.wd.yiyangyun.okutils.OkHttpUtils;
import com.wd.yiyangyun.utils.CompressImageUtil;
import com.wd.yiyangyun.utils.GlideCircleTransUtils;
import com.wd.yiyangyun.utils.HcUtils;
import com.wd.yiyangyun.utils.LogUtil;
import com.wd.yiyangyun.utils.UrlUtils;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;

import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.finalteam.galleryfinal.model.PhotoInfo;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import static com.wd.yiyangyun.mvp.mine.view.activity.DoctorInfoActivity.saveBitmapFile;

public class VideoActivity extends BaseActivity {

    private ImageView title_back;
    private TextView title_name;
    private ImageView image_right;
    private String path = Environment.getExternalStorageDirectory() + "/yiYangYun/"+System.currentTimeMillis()+".png";
    private final static int REQUEST_CODE_TAKE_PICTURE = 2001;
    private final static int REQUEST_CODE_TAKE_PICTURE1 = 2002;
    private File file;
    private PicPhotoListAdapter mChoosePhotoListAdapter;
    private List<VideoListBean.DataBean.ListBean> mPhotoList;
    private VideoListBean videoListBean;
    private ListView video_list;

    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_vieo;
    }

    @Override
    protected void initViews() {
        super.initViews();
        title_name = findViewById(R.id.title_name);
        title_back = findViewById(R.id.title_back);
        image_right = findViewById(R.id.image_right);
        video_list = findViewById(R.id.video_list);
        title_name.setText("影视资料");
        title_name.setVisibility(View.VISIBLE);
        title_back.setVisibility(View.VISIBLE);
        image_right.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initData() {
        super.initData();


        mPhotoList = new ArrayList<>();
        mChoosePhotoListAdapter = new PicPhotoListAdapter(this, mPhotoList, mContext);
        video_list.setAdapter(mChoosePhotoListAdapter);
        requestPicList();

    }

    /**
     * 请求图片列表
     */
    private void requestPicList() {
        showWaitDialog();
        Map<String, Object> map = new HashMap<>();
        map.put("hospitalID", DoctorBaseAppliction.spUtil.getString(Constants.HOSPITAL_ID,""));
        DoctorNetService.requestPicList(Constants.SELECT_FILE_SIGN_LIST_NEW, map, new
                NetWorkRequestInterface() {
                    @Override
                    public void onError(Throwable throwable) {
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object info) {
                        hideWaitDialog();
                        videoListBean = (VideoListBean) info;
                        mPhotoList.addAll(videoListBean.getData().getList());
                        mChoosePhotoListAdapter.notifyDataSetChanged();
                    }
                });
    }


    @Override
    protected void initListener() {
        super.initListener();
        mChoosePhotoListAdapter.setOnButtonClick(new PicPhotoListAdapter.onButtonClick() {
            @Override
            public void onButtonClick(int position, int type) {
                switch (type) {
                    case 0:
                        LogUtil.e("-----------------------");
                        Intent intent = new Intent(VideoActivity.this,VideoPlayActivity.class);
                        String video = "http://shenkangyun.com:808/skyapp_ndpt/" + videoListBean.getData().getList().get(position).getFileUrl();
                        intent.putExtra("video",video);
                        startActivity(intent);
                        break;
                    case 1:
                        LogUtil.e("----123123------------------");

                        break;
                }

            }
        });
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        image_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelect();
            }
        });
    }

    public void showSelect() {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("上传图片", "上传视频")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        if (index == 0) {
                            //图片
                            startPhotoAlbum();
                        } else {
                            //视频
                            startVideo();
                        }
                    }
                })
                .show();
    }


    private void startVideo() {

//        Intent intent = new Intent();
//        intent.setType("video/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.addCategory(Intent.CATEGORY_OPENABLE);
//        startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);

        //批量上传
        Matisse.from(VideoActivity.this)
                .choose(MimeType.ofVideo(),true) // 选择 mime 的类型
                .showSingleMediaType(true)
                .countable(true)
                .maxSelectable(9) // 图片选择的最多数量
                .gridExpectedSize(240)//图片显示表格的大小getResources()
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                .imageEngine(new GlideEngine()) // 使用的图片加载引擎
                .thumbnailScale(0.85f) // 缩略图的比例
                .forResult(REQUEST_CODE_TAKE_PICTURE); // 设置作为标记的请求码
    }

    private void startPhotoAlbum() {
        //相册导入
        Intent intent5 = new Intent(this, MultiImageSelectorActivity.class);
        // whether show camera
        intent5.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, false);
        // max select image amount
        intent5.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
        // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent5.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        // default select images (support array list)
        ArrayList<String> imageList = new ArrayList<>();
        intent5.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, imageList);
        startActivityForResult(intent5, REQUEST_CODE_TAKE_PICTURE1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE_TAKE_PICTURE && resultCode == this.RESULT_OK) {
            List<Uri> mSelected = Matisse.obtainResult(data);
//            Uri uri = data.getData();
//            String path = uri.getPath();
            ArrayList<String> newPic = new ArrayList<>();
            for(int i=0; i< mSelected.size(); i++){
                String path = UrlUtils.getPath(this, mSelected.get(i));
                Log.e("上传视频11111", "压缩之前: "+path);
                newPic.add(path);
            }
            upLoadPic(newPic);
            hideWaitDialog();
        }
        //相册
        if (requestCode == REQUEST_CODE_TAKE_PICTURE1 && resultCode == this.RESULT_OK) {
            List<String> resultList = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
            ArrayList<String> newPic = new ArrayList<>();
             for(int i=0; i< resultList.size(); i++){
                 String a = resultList.get(i);
                 Log.e("tttttttttt", "压缩之前: "+a);
                 Bitmap bitmap = CompressImageUtil.getimage(a);
                 String galleryPath= Environment.getExternalStorageDirectory() + "/xxDoctor/"+System.currentTimeMillis()+".png";
                 File b = saveBitmapFile(bitmap,galleryPath);
                 newPic.add(b.getPath());
             }
            upLoadPic(newPic);
            hideWaitDialog();

        }

    }




    /**
     * 上传图片
     */
    public void upLoadPic(final List<String> files) {
        showWaitDialog();
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String urlStr = Constants.UPVIDEO +
                        "&userID=" + DoctorBaseAppliction.spUtil.getString
                        (Constants.USER_ID, "")
                        + "&hospitalID="+DoctorBaseAppliction.spUtil.getString
                        (Constants.HOSPITAL_ID, "")
                        + "&modules=pic"
                        + "&mobileType=" + Constants.MOBILE_TYPE;
                MultipartBody.Builder builder = new MultipartBody.Builder();
                MultipartBody.Builder builder1 = builder.setType(MultipartBody.FORM);
                for (String photoInfo : files) { //ConcurrentModificationException
                    File file = new File(photoInfo);
                    LogUtil.e(file.getAbsoluteFile() + "");
                    byte[] content = new byte[0];
                    try {
                        content = HcUtils.readStream(file);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    builder1.addFormDataPart("file", file.getName(), RequestBody.create(MediaType
                            .parse("application/octet-stream"), content));
                }

                    RequestBody requestBody = builder.build();
                    String result = OkHttpUtils.initUpLoad(urlStr, requestBody);
                    Gson gson = new Gson();
                    Log.e("上传头像 ----", "call: "+result);
//                    PersonlIconBean personlIconBean = gson.fromJson(result, PersonlIconBean.class);
//                    personlIcon = personlIconBean.getPhotoUrl();
//                    LogUtil.e("上传头像 -- " + personlIcon);
                    subscriber.onNext(true);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).

                subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
//                        Glide.with(VideoActivity.this)
//                                .load(personlIcon)
//                                .transform(new GlideCircleTransUtils(VideoActivity.this))
//                                .into(touxiang_iv);
//                        //Picasso.with(getActivity()).load(file).transform(new CircleTransform(getActivity())).into(my_info_logo_iv);
//                        DoctorBaseAppliction.spUtil.putString(Constants.PHOTO_URL, personlIcon);
//                        Map<String, Object> map = new HashMap<>();
//                        map.put(Constants.EVENTBUS_TYEPE, UPDATE_ICON);
//                        if(personlIcon==null){
//                            String origIcon = DoctorBaseAppliction.spUtil.getString(Constants.PHOTO_URL, "");
//                            map.put("personlIcon", origIcon);
//                        }else{
//                            map.put("personlIcon", personlIcon);
//                        }
//                        EventBus.getDefault().post(map);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object result) {
                    }
                });
    }


    @Override
    public Context context() {
        return null;
    }
}
