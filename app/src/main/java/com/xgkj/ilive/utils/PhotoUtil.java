package com.xgkj.ilive.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.xgkj.ilive.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 相机 或者相册选取类
 * @author liuml
 *
 */
public class PhotoUtil {
	// 相册，拍照，取消
	private TextView camera, photo, back;
	private AlertDialog dialog;
	private Context context;
	// 创建一个以当前时间为名称的文件
	public static final int CAMRA_SETRESULT_CODE = 0;// 相册返回码
	public static final int PHOTO_SETRESULT_CODE = 1;// 拍照返回码
	public static final int PHOTO_CORPRESULT_CODE = 2;// 裁剪返回码
    private TextView photo_camera;
    private TextView local_pictures;
    private TextView tv_cancel;

    public PhotoUtil(Context context) {
		this.context = context;
		
	}
	public void showDialog(){
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
            View view1 = View.inflate(context, R.layout.dialog_pick_item, null);

            photo_camera = (TextView) view1.findViewById(R.id.photo_camera);
            local_pictures = (TextView) view1.findViewById(R.id.local_pictures);
            tv_cancel = (TextView) view1.findViewById(R.id.tv_cancel);

			dialog = new AlertDialog.Builder(context).setView(view1).create();
			dialog.show();
            addListener();
		} else {
			Toast.makeText(context, "请插入内存卡", Toast.LENGTH_SHORT).show();
		}
	}



	private void addListener() {
        tv_cancel.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});
        local_pictures.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_PICK,
						MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				((Activity) context).startActivityForResult(intent,
						CAMRA_SETRESULT_CODE);
				dialog.dismiss();

			}
		});
        photo_camera.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
				intent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(new File(getPhotoPath())));
				((Activity) context).startActivityForResult(intent,
						PHOTO_SETRESULT_CODE);
				dialog.dismiss();

			}
		});
	}


	// 拍照使用系统当前日期加以调整作为照片的名称
	private static String getPhotoFileName() {
		Date date = new Date(System.currentTimeMillis());
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"'IMG'_yyyyMMdd_HHmmss");
		return dateFormat.format(date) + ".jpg";
	}

	// 拍照路径
	public String getPhotoPath() {
		File file = new File(Environment.getExternalStorageDirectory(), "/I智播/images/");
		if (!file.exists()) {
			file.mkdirs();
		}
		String path = file.getAbsolutePath() + "photo.jpg";
		return path;
	}

	// file转换成BitMap
	public static Bitmap readBitmapAutoSize(String filePath) {
		// outWidth和outHeight是目标图片的最大宽度和高度，用作限制
		Bitmap bm = null;
		try {

			BitmapFactory.Options opt = new BitmapFactory.Options();
			opt.inJustDecodeBounds = true;
			// 设置只是解码图片的边距，此操作目的是度量图片的实际宽度和高度
			BitmapFactory.decodeFile(filePath, opt);
			opt.inDither = false;
			opt.inPreferredConfig = Bitmap.Config.RGB_565;

			// 设置加载图片的颜色数为16bit，默认是RGB_8888，表示24bit颜色和透明通道，但一般用不上
			// opt.inSampleSize = 1;
			opt.inSampleSize = computeSampleSize(opt, -1, 900 * 900);
			opt.inJustDecodeBounds = false;
			bm = BitmapFactory.decodeFile(filePath, opt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bm;
	}

	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);
		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}
		return roundedSize;
	}

	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;
		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));
		if (upperBound < lowerBound) {
			return lowerBound;
		}
		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	// bitmap转换成字节流
	public static String bitmaptoString(Bitmap bitmap) {
		// 将Bitmap转换成字符串
		String result = "";
		ByteArrayOutputStream bStream = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bStream);
		byte[] bytes = bStream.toByteArray();
		byte[] bb = Base64.encode(bytes, Base64.DEFAULT);
		try {
			result = new String(bb, "UTF-8").replace("+", "%2B");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
		}
		return result;
	}

	// 得到相册路径
	public String getCameraPath(Intent data) {
		Uri originalUri = data.getData();
		String[] proj = { MediaStore.Images.Media.DATA };

		// 好像是android多媒体数据库的封装接口，具体的看Android文档     数据库

		Cursor cursor = ((Activity) context).managedQuery(originalUri, proj,
				null, null, null);

		// 获取游标

		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

		// 将光标移至开头 ，这个很重要，不小心很容易引起越界

		cursor.moveToFirst();

		// 最后根据索引值获取图片路径

		String path = cursor.getString(column_index);
		return path;
	}

}