package com.qxy.miniTiktok;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import com.bytedance.sdk.open.aweme.authorize.model.Authorization;
import com.bytedance.sdk.open.aweme.base.ImageObject;
import com.bytedance.sdk.open.aweme.base.MediaContent;
import com.bytedance.sdk.open.aweme.base.MixObject;
import com.bytedance.sdk.open.aweme.base.VideoObject;
import com.bytedance.sdk.open.aweme.share.Share;
import com.bytedance.sdk.open.douyin.DouYinOpenApiFactory;

import com.bytedance.sdk.open.douyin.ShareToContact;
import com.bytedance.sdk.open.douyin.api.DouYinOpenApi;
import com.bytedance.sdk.open.douyin.model.ContactHtmlObject;
import com.bytedance.sdk.open.douyin.model.OpenRecord;
import com.qxy.miniTiktok.util.UriUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.ArrayList;

public class DemoMainAct extends AppCompatActivity {

    public static final String CODE_KEY = "code";
    public static Boolean isBoe = false;

    DouYinOpenApi douYinOpenApi;

    OpenRecord.Request request = new OpenRecord.Request();

    String[] mPermissionList = new String[] {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE};

    Button mShareToDouyin;


    EditText mMediaPathList;


    Button mClearMedia;

    EditText mSetDefaultHashTag;
    EditText mSetDefaultHashTag2;

    private boolean useFileProvider = true;


    static final int PHOTO_REQUEST_GALLERY = 10;
    static final int SET_SCOPE_REQUEST = 11;

    int currentShareType = Share.UNKNOWN;

    private ArrayList<String> mUri = new ArrayList<>();

    private String mScope = "user_info," +
            "following.list, " +
            "fans.list, " +
            //"trial.whitelist, " +
            "fans.check, " +
            "renew_refresh_token," +
            "data.external.item," +
            "video.list," +
            "video.data," +
            "discovery.ent";
    private String mOptionalScope1 = "friend_relation";
    private String mOptionalScope2 = "message";

    public static boolean IS_AUTH_BY_M = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 设置状态栏透明
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        douYinOpenApi = DouYinOpenApiFactory.create(this);

        findViewById(R.id.go_to_auth).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 如果本地未安装抖音或者抖音的版本过低，会直接自动调用 web页面 进行授权
                sendAuth();
            }
        });

        findViewById(R.id.go_to_system_picture).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(DemoMainAct.this, mPermissionList, 100);
            }
        });
        findViewById(R.id.require_mobile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Authorization.Request request = new Authorization.Request();
                request.scope = "user_info,mobile";                          // 用户授权时必选权限
                request.state = "ww";                                   // 用于保持请求和回调的状态，授权请求后原样带回给第三方。
                douYinOpenApi.authorize(request);
            }
        });

        findViewById(R.id.open_record).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (douYinOpenApi != null && douYinOpenApi.isSupportOpenRecordPage()) {
                    request.mState = "state";
                    douYinOpenApi.openRecordPage(request);
                } else {
                    Toast.makeText(DemoMainAct.this, "抖音版本不支持", Toast.LENGTH_LONG).show();
                }

            }
        });

        findViewById(R.id.option_mobile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Authorization.Request request = new Authorization.Request();
                request.scope = "user_info";// 用户授权时必选权限
                request.optionalScope0 = "mobile";
                request.state = "ww";                                   // 用于保持请求和回调的状态，授权请求后原样带回给第三方。
                douYinOpenApi.authorize(request);
            }
        });

        findViewById(R.id.is_support_mix_share_button).setOnClickListener((v)->{
            if(douYinOpenApi.isAppSupportMixShare()) {
                Toast.makeText(this,"支持混合分享",Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this,"不支持混合分享",Toast.LENGTH_SHORT).show();
            }
        });

        findViewById(R.id.share_to_contact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (douYinOpenApi.isAppSupportShareToContacts()) {
                    shareToContact();
                } else {
                    Toast.makeText(DemoMainAct.this, "当前抖音版本不支持", Toast.LENGTH_LONG).show();
                }
            }
        });

        findViewById(R.id.share_to_contact_html).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (douYinOpenApi.isAppSupportShareToContacts()) {
                    shareToContactHtml();
                } else {
                    Toast.makeText(DemoMainAct.this, "当前抖音版本不支持", Toast.LENGTH_LONG).show();
                }
            }
        });

        mShareToDouyin = findViewById(R.id.share_to_tiktok);
        mSetDefaultHashTag = findViewById(R.id.set_default_hashtag);
        mSetDefaultHashTag2 = findViewById(R.id.set_default_hashtag1);
        mMediaPathList = findViewById(R.id.media_text);
        mClearMedia = findViewById(R.id.clear_media);


        mClearMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUri.clear();
                mMediaPathList.setText("");
            }
        });

        mShareToDouyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(currentShareType);
            }
        });
        String useFileProviderText = this.getString(R.string.share_user_file_provider);
        Button useFileProviderBt = findViewById(R.id.use_file_provider);
        useFileProviderBt.setText(useFileProviderText+useFileProvider);
        useFileProviderBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                useFileProvider = !useFileProvider;
                useFileProviderBt.setText(useFileProviderText+useFileProvider);
            }
        });

    }

    private void createTikTokImplApi(int targetApp) {
        douYinOpenApi = DouYinOpenApiFactory.create(this);
    }

    private void shareToContact() { // image
        ImageObject cImage = new ImageObject();
        cImage.mImagePaths = useFileProvider?convert2FileProvider():mUri;
        MediaContent mediaContent = new MediaContent();
        mediaContent.mMediaObject = cImage;
        ShareToContact.Request request = new ShareToContact.Request();
        request.mMediaContent = mediaContent;
        request.mState = "ww";
        douYinOpenApi.shareToContacts(request);
    }

    private void shareToContactHtml() {
        ContactHtmlObject htmlObject = new ContactHtmlObject();
        htmlObject.setHtml("https://open.douyin.com/platform/");
        htmlObject.setDiscription("bbbbbbbb");
        htmlObject.setTitle("title");
        htmlObject.setThumbUrl(
                "https://tpc.googlesyndication" +
                        ".com/simgad/16034773615176939809?sqp=4sqPyQQ7QjkqNxABHQAAtEIgASgBMAk4A0DwkwlYAWBfcAKAAQGIAQGdAQAAgD" +
                        "-oAQGwAYCt4gS4AV_FAS2ynT4&rs=AOga4qnz29EViShgiSFixrRkn77Pu29abA");
        ShareToContact.Request request = new ShareToContact.Request();
        request.htmlObject = htmlObject;
        douYinOpenApi.shareToContacts(request);

    }

    private boolean sendAuth() {
        Authorization.Request request = new Authorization.Request();
        request.scope = mScope;                          // 用户授权时必选权限
        request.optionalScope0 = "mobile";     // 用户授权时可选权限（默认选择）
//        request.optionalScope0 = mOptionalScope1;    // 用户授权时可选权限（默认不选）
        request.state = "ww";                                   // 用于保持请求和回调的状态，授权请求后原样带回给第三方。
        return douYinOpenApi.authorize(request);               // 优先使用抖音app进行授权，如果抖音app因版本或者其他原因无法授权，则使用wap页授权

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                boolean writeExternalStorage = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                boolean readExternalStorage = grantResults[1] == PackageManager.PERMISSION_GRANTED;
                if (grantResults.length > 0 && writeExternalStorage && readExternalStorage) {
                    openSystemGallery();
                } else {
                    Toast.makeText(this, "请设置必要权限", Toast.LENGTH_SHORT).show();
                }

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PHOTO_REQUEST_GALLERY:
                    Uri uri = data.getData();
                    mUri.add(UriUtil.convertUriToPath(this, uri));
                    mMediaPathList.setVisibility(View.VISIBLE);
                    mSetDefaultHashTag.setVisibility(View.VISIBLE);
                    mSetDefaultHashTag2.setVisibility(View.VISIBLE);
                    mMediaPathList.setText(mMediaPathList.getText().append("\n").append(uri.getPath()));
                    mShareToDouyin.setVisibility(View.VISIBLE);
                    mClearMedia.setVisibility(View.VISIBLE);
                    break;

            }
        }
    }

    private void openSystemGallery() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.add_photo_video)
                .setNegativeButton(R.string.video, (dialog, which) -> {
                    if (currentShareType == Share.IMAGE) {
                        currentShareType = Share.MIX;
                    } else {
                        currentShareType = Share.VIDEO;
                    }
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("video/*");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                })
                .setPositiveButton(R.string.image, (dialog, which) -> {
                    if (currentShareType == Share.VIDEO) {
                        currentShareType = Share.MIX;
                    } else {
                        currentShareType = Share.IMAGE;
                    }
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                    startActivityForResult(intent, PHOTO_REQUEST_GALLERY);
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean share(int shareType) {
        Share.Request request = new Share.Request();
        switch (shareType) {
            case Share.IMAGE:
                ImageObject imageObject = new ImageObject();
                imageObject.mImagePaths = useFileProvider?convert2FileProvider():mUri;
                MediaContent mediaContent = new MediaContent();
                mediaContent.mMediaObject = imageObject;
                ArrayList<String> hashtags = new ArrayList<>();

                if (!TextUtils.isEmpty(mSetDefaultHashTag.getText())) {
                    hashtags.add(mSetDefaultHashTag.getText().toString());
                }

                if (!TextUtils.isEmpty(mSetDefaultHashTag2.getText())) {
                    hashtags.add(mSetDefaultHashTag2.getText().toString());

                }
                request.mHashTagList = hashtags;

                request.mMediaContent = mediaContent;
                request.mState = "ww";
                break;
            case Share.VIDEO:
                VideoObject videoObject = new VideoObject();
                videoObject.mVideoPaths = useFileProvider?convert2FileProvider():mUri;
                ArrayList<String> hashtagsVideo = new ArrayList<>();

                if (!TextUtils.isEmpty(mSetDefaultHashTag.getText())) {
                    hashtagsVideo.add(mSetDefaultHashTag.getText().toString());
                }

                if (!TextUtils.isEmpty(mSetDefaultHashTag2.getText())) {
                    hashtagsVideo.add(mSetDefaultHashTag2.getText().toString());

                }
                request.mHashTagList = hashtagsVideo;
                MediaContent content = new MediaContent();
                content.mMediaObject = videoObject;
                request.mMediaContent = content;
                request.mState = "ss";
                break;
            case Share.MIX:
                MixObject mixObject = new MixObject();
                mixObject.mMediaPaths = useFileProvider?convert2FileProvider():mUri;
                ArrayList<String> hashtagsMix = new ArrayList<>();

                if (!TextUtils.isEmpty(mSetDefaultHashTag.getText())) {
                    hashtagsMix.add(mSetDefaultHashTag.getText().toString());
                }

                if (!TextUtils.isEmpty(mSetDefaultHashTag2.getText())) {
                    hashtagsMix.add(mSetDefaultHashTag2.getText().toString());

                }
                request.mHashTagList = hashtagsMix;
                MediaContent mixContent = new MediaContent();
                mixContent.mMediaObject = mixObject;
                request.mMediaContent = mixContent;
                request.mState = "ss";
                break;
        }

        return douYinOpenApi.share(request);
    }

    private ArrayList<String> convert2FileProvider(){
        ArrayList<String> result = new ArrayList<>();
        for (String path : mUri){
            try {
                String[] uriParts = path.split("\\.");
                if (uriParts.length >0){
                    String suffix = uriParts[uriParts.length - 1];
                    File file = new File(this.getExternalFilesDir(null), "/newMedia");
                    file.mkdirs();
                    File tempFile = File.createTempFile("share_demo", "."+suffix, file);
                    if (copyFile(new File(path), tempFile)){
                        Uri uri = FileProvider.getUriForFile(this, this.getPackageName() + ".fileProvider", tempFile);
                        this.grantUriPermission("com.ss.android.ugc.aweme", uri, Intent.FLAG_GRANT_READ_URI_PERMISSION);
                        result.add(uri.toString());
                    }
                }
            }catch (Exception e){

            }
        }
        return result;
    }

    private boolean copyFile(File src, File dest){
        boolean result = false;
        if (src == null || dest == null){
            return false;
        }
        if (dest.exists()){
            dest.delete();
        }

        FileChannel srcChannel  = null;
        FileChannel destChannel = null;
        try {
            dest.createNewFile();
            srcChannel= new FileInputStream(src).getChannel();
            destChannel = new FileOutputStream(dest).getChannel();
            srcChannel.transferTo(0, srcChannel.size(), destChannel);
            result = true;
        }catch (Exception e){

        }finally {
            try {
                if (srcChannel != null ){
                    srcChannel.close();
                }
                if (srcChannel != null){
                    destChannel.close();
                }
            }catch (Exception e){
            }
        }

        return result;
    }
}