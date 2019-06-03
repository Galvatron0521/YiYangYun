package com.wd.yiyangyun.mvp.mine.view.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baoyz.actionsheet.ActionSheet;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wd.yiyangyun.R;
import com.wd.yiyangyun.app.Constants;
import com.wd.yiyangyun.app.DoctorBaseAppliction;
import com.wd.yiyangyun.base.BaseActivity;
import com.wd.yiyangyun.mvp.mine.bean.PersonlIconBean;
import com.wd.yiyangyun.okutils.OkHttpUtils;
import com.wd.yiyangyun.utils.CompressImageUtil;
import com.wd.yiyangyun.utils.GlideCircleTransUtils;
import com.wd.yiyangyun.utils.HcUtils;
import com.wd.yiyangyun.utils.LogUtil;
import com.wd.yiyangyun.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class DoctorInfoActivity extends BaseActivity {

    private LinearLayout touxiang_ll;
    private ImageView touxiang_iv;
    private TextView name_tv;
    private TextView sex_tv;
    private TextView zhicheng_tv;
    private TextView shanchang_tv;
    private TextView jianjie_tv;
    private TextView didian_tv;
    private TextView yewu_tv;
    private TextView fangwenliang_tv;
    private TextView pingjialiang_tv;
    private TextView pingjia_tv;
    private TextView manyidu_tv;
    private String path = Environment.getExternalStorageDirectory() + "/xxDoctor/"+System.currentTimeMillis()+".png";
    private final static int REQUEST_CODE_TAKE_PICTURE = 2001;
    private final static int REQUEST_CODE_TAKE_PICTURE1 = 2002;
    public final static String UPDATE_ICON = "update_icon";
    private String personlIcon;
    private TextView title_right_tv;
    private ImageView title_back;


    @Override
    protected Activity provideBindView() {
        return this;
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_doctor_info;
    }

    @Override
    protected void initViews() {
        super.initViews();
        touxiang_ll = findViewById(R.id.touxiang_ll);
        touxiang_iv = findViewById(R.id.touxiang_iv);
        name_tv = findViewById(R.id.name_tv);
        title_back = findViewById(R.id.title_back);
        title_right_tv = findViewById(R.id.title_right_tv);
        sex_tv = findViewById(R.id.sex_tv);
        zhicheng_tv = findViewById(R.id.zhicheng_tv);
        shanchang_tv = findViewById(R.id.shanchang_tv);
        jianjie_tv = findViewById(R.id.jianjie_tv);
        didian_tv = findViewById(R.id.didian_tv);
        yewu_tv = findViewById(R.id.yewu_tv);
        fangwenliang_tv = findViewById(R.id.fangwenliang_tv);
        pingjialiang_tv = findViewById(R.id.pingjialiang_tv);
        pingjia_tv = findViewById(R.id.pingjia_tv);
        manyidu_tv = findViewById(R.id.manyidu_tv);
    }

    @Override
    protected void initData() {
        super.initData();


        String a = DoctorBaseAppliction.spUtil.getString(Constants.PHOTO_URL, "");
        if(a==null || a.equals("") || a==""){
            touxiang_iv.setImageResource(R.drawable.doctor_icon);
        }else{
            Log.e("444444", "initData: "+a);
            Glide.with(this)
                    .load(a)
                    .transform(new GlideCircleTransUtils(this))
                    .into(touxiang_iv);
//            Picasso.with(getActivity()).load(Uri.parse(a)).transform(new CircleTransform(getActivity())).into(my_info_logo_iv);
        }
    }

    @Override
    protected void initListener() {
        super.initListener();
        title_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        touxiang_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelect();
//                if (ContextCompat.checkSelfPermission(DoctorInfoActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
//                }else{
//                    //否则去请求相机权限
//                    ToastUtils.showMessage(DoctorInfoActivity.this,"请打开相机权限");
//                    ActivityCompat.requestPermissions(DoctorInfoActivity.this,new String[]{Manifest.permission.CAMERA},100);
//
//                }
            }
        });
    }


    /**
     * 设置用户头像
     */
    public void showSelect() {
        ActionSheet.createBuilder(this, getSupportFragmentManager())
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("拍照", "从手机相册选择")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {

                    }

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        if (index == 0) {
                            startcamera();
                        } else {
                            startPhotoAlbum();
                        }
                    }
                })
                .show();
    }

    private void startcamera() {

        //1.打开相机  MediaStore.ACTION_IMAGE_CAPTURE 打开相机的动作
        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //将图片存入sdcard  1.存入   2.在Sdcard创建文件
        it.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(path)));
        //启动
        startActivityForResult(it, REQUEST_CODE_TAKE_PICTURE);
    }

    private void startPhotoAlbum() {
        //调用相册
        //Intent.ACTION_PICK打开相册
        Intent it = new Intent(Intent.ACTION_PICK);
        //设置图片的格式
        it.setType("image/*");
        //启动
        startActivityForResult(it, REQUEST_CODE_TAKE_PICTURE1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //2.判断请求码和结果码  结果码系统提供 RESULT_OK
        if (requestCode == REQUEST_CODE_TAKE_PICTURE && resultCode == this.RESULT_OK) {
            //点击完成后调取的裁剪功能  com.android.camera.action.CROP裁剪的Action
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到图片   1.得到Sdcard的下存的图片，如果有就拿到，如果没有就创建
            it.setDataAndType(Uri.fromFile(new File(path)), "image/*");
            //设置是否支持裁剪
            it.putExtra("CROP", true);
            //设置裁剪框的比例
            it.putExtra("aspaceX", 1);
            it.putExtra("aspaceY", 1);
            //设置图片输入的大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //让图片返回intent接受
            it.putExtra("return-data", true);
            //启动
            Log.e("上传", "onActivityResult: "+path);
            startActivityForResult(it, 200);
        } //3.将图片设置展示

        if (requestCode == REQUEST_CODE_TAKE_PICTURE1 && resultCode == this.RESULT_OK) {
            //得到图片
            Uri data2 = data.getData();
            //调取裁剪功能
            Intent it = new Intent("com.android.camera.action.CROP");
            //得到图片设置格式
            it.setDataAndType(data2, "image/*");
            //设置是否支持裁剪
            it.putExtra("CROP", true);
            //设置宽高比
            it.putExtra("aspectX", 10);
            it.putExtra("aspectY", 10);
            //设置输出图片的大小
            it.putExtra("outputX", 250);
            it.putExtra("outputY", 250);
            //返回
            it.putExtra("return-data", true);
            startActivityForResult(it, 201);
        }
        if (requestCode == 200 && resultCode == this.RESULT_OK) {
            Uri uri = data.getData();
            String path = uri.getPath();
            Log.e("上传头像11111", "onActivityResult: " + path);
            File filePath = new File(path);
            if (!filePath.exists()) {
                filePath.mkdirs();
            }
            CompressImageUtil.compressImage(this,filePath.getPath());
            upLoadPic(filePath);
        }
        if (requestCode == 201 && resultCode == this.RESULT_OK) {
            Bitmap bitmap = data.getParcelableExtra("data");
            File file = saveBitmapFile(bitmap, Environment.getExternalStorageDirectory() + "/xxDoctor/"+System.currentTimeMillis()+".png");
            Log.e("上传头像22222", "onActivityResult: " + file.getPath());
            if (!file.exists()) {
                file.mkdirs();
            }
            upLoadPic(file);
        }

    }

    /**
     * 上传图片
     *
     * @param file
     */
    public void upLoadPic(final File file) {
        showWaitDialog();
        Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                String urlStr = Constants.USER_ICON +
                        "&userID=" + DoctorBaseAppliction.spUtil.getString
                        (Constants.USER_ID, "")
                        + "&modules=headdoctor"
                        + "&mobileType=" + Constants.MOBILE_TYPE;
                try {
                    LogUtil.e(file.getAbsoluteFile() + "");
                    byte[] content;
                    content = HcUtils.readStream(file);
                    MultipartBody.Builder builder = new MultipartBody.Builder();
                    builder.setType(MultipartBody.FORM).addFormDataPart("file", file.getName
                            (), RequestBody.create(MediaType.parse
                            ("application/octet-stream"), content));

                    RequestBody requestBody = builder.build();
                    String result = OkHttpUtils.initUpLoad(urlStr, requestBody);
                    Gson gson = new Gson();
                    Log.e("上传头像 ----", "call: "+result);
                    PersonlIconBean personlIconBean = gson.fromJson(result, PersonlIconBean.class);
                    personlIcon = personlIconBean.getPhotoUrl();
                    LogUtil.e("上传头像 -- " + personlIcon);
                    subscriber.onNext(true);
                } catch (Exception e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).

                subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        Glide.with(DoctorInfoActivity.this)
                                .load(personlIcon)
                                .transform(new GlideCircleTransUtils(DoctorInfoActivity.this))
                                .into(touxiang_iv);
                        //Picasso.with(getActivity()).load(file).transform(new CircleTransform(getActivity())).into(my_info_logo_iv);
                        DoctorBaseAppliction.spUtil.putString(Constants.PHOTO_URL, personlIcon);
                        Map<String, Object> map = new HashMap<>();
                        map.put(Constants.EVENTBUS_TYEPE, UPDATE_ICON);
                        if(personlIcon==null){
                            String origIcon = DoctorBaseAppliction.spUtil.getString(Constants.PHOTO_URL, "");
                            map.put("personlIcon", origIcon);
                        }else{
                            map.put("personlIcon", personlIcon);
                        }
                        EventBus.getDefault().post(map);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                        hideWaitDialog();
                    }

                    @Override
                    public void onNext(Object result) {
                        hideWaitDialog();
                    }
                });
    }

    /**
     * 把batmap 转file
     *
     * @param bitmap
     * @param filepath
     */
    public static File saveBitmapFile(Bitmap bitmap, String filepath) {
        File file = new File(filepath);//将要保存图片的路径
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 80, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }


    @Override
    public Context context() {
        return null;
    }
}
