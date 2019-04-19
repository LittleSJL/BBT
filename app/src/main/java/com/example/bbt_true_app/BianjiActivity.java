package com.example.bbt_true_app;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class BianjiActivity extends AppCompatActivity {

    public static final int TAKE_PHOTO = 1;

    public static final int CHOOSE_PHOTO = 2;

    private CircleImageView picture;

    private Uri imageUri;

    private RelativeLayout touxiang;
    private RelativeLayout nichen;
    private RelativeLayout mima;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bianji);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        picture = (CircleImageView)findViewById(R.id.icon_image);
        touxiang = (RelativeLayout)findViewById(R.id.touxiang);
        nichen = (RelativeLayout) findViewById(R.id.nichen);
        mima = (RelativeLayout) findViewById(R.id.mima);

        touxiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSingleChoiceDialog();
            }
        });

        nichen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
                Intent intent_nichen = new Intent(BianjiActivity.this,NichenActivity.class);
                startActivity(intent_nichen);
            }
        });

        mima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_mima = new Intent(BianjiActivity.this,MimaActivity.class);
                startActivity(intent_mima);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private int yourChoice;
    final String[] items = { "拍摄照片","从相册中选择"};

    private void showSingleChoiceDialog(){
        yourChoice = -1;
        AlertDialog.Builder singleChoiceDialog =
                new AlertDialog.Builder(BianjiActivity.this);
        singleChoiceDialog.setTitle("设置头像的方式");
        // 第二个参数是默认选项，此处设置为0
        singleChoiceDialog.setSingleChoiceItems(items, 0,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        yourChoice = which;
                    }
                });
        singleChoiceDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (yourChoice != -1) {
                            if(ContextCompat.checkSelfPermission(BianjiActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
                            {
                                ActivityCompat.requestPermissions(BianjiActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
                            }
                            else
                            {
                                openAlbum();
                            }
                        }
                        else{
                            File outputImage = new File(getExternalCacheDir(),"output_image.jpg");
                            try
                            {
                                if(outputImage.exists())
                                {
                                    outputImage.delete();
                                }
                                outputImage.createNewFile();
                            }catch(IOException e)
                            {
                                e.printStackTrace();
                            }
                            if(Build.VERSION.SDK_INT>=24)
                            {
                                imageUri = FileProvider.getUriForFile(BianjiActivity.this,"com.example.bbt_new_app.fileprovider",outputImage);
                            }
                            else
                            {
                                imageUri = Uri.fromFile(outputImage);
                            }
                            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
                            startActivityForResult(intent,TAKE_PHOTO);
                        }
                    }
                });
        singleChoiceDialog.show();
    }

    private void openAlbum()
    {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults)
    {
        switch (requestCode)
        {
            case 1:
                if(grantResults.length>0&&grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    openAlbum();
                }
                else
                {
                    Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case TAKE_PHOTO:
                if(resultCode==RESULT_OK)
                {
                    try
                    {
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                    }catch(FileNotFoundException e)
                    {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PHOTO:
                if(resultCode==RESULT_OK)
                {
                    if(Build.VERSION.SDK_INT>=19)
                    {
                        handleImageOnKitKat(data);
                    }
                    else
                    {
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data)
    {
        String imagePath = null;
        Uri uri = data.getData();
        if(DocumentsContract.isDocumentUri(this,uri))
        {
            String docID = DocumentsContract.getDocumentId(uri);
            if("com.android.providers.media.documents".equals(uri.getAuthority()))
            {
                String id = docID.split(":")[1];
                String selection = MediaStore.Images.Media._ID+"="+id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,selection);
            }else if ("com.android.providers.downloads.documents".equals(uri.getAuthority()))
            {
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"),Long.valueOf(docID));
                imagePath = getImagePath(contentUri,null);
            }else if("content".equalsIgnoreCase(uri.getScheme()))
            {
                imagePath = getImagePath(uri,null);
            }else if("file".equalsIgnoreCase(uri.getScheme()))
            {
                imagePath = uri.getPath();
            }
        }
        displayImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data)
    {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri,null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection)
    {
        String path = null;
        Cursor cursor = getContentResolver().query(uri,null,selection,null,null);
        if(cursor!=null)
        {
            if(cursor.moveToFirst())
            {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath)
    {
        if(imagePath!=null)
        {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
        }
        else
        {
            Toast.makeText(this,"failed to get image",Toast.LENGTH_SHORT).show();
        }
    }
}
