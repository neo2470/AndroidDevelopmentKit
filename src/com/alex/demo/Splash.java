package com.alex.demo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.alex.develop.BaseActivity;
import com.alex.develop.R;
import com.alex.develop.adapter.FeatureAdapter;

/**
 * App的启动画面，持续2.5s，可用于<br>
 * 1、展示App品牌LOGO<br>
 * 2、加载程序所需数据<br>
 * 3、介绍软件新特性
 * 4、广告展示
 * 
 * @author Created by alex 2014/11/07
 */
public class Splash extends BaseActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		initialize();

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}
	
	/**
	 * 启动指定的Activity，在App首次安装时，启动画面之后会启动新<br>
	 * 特性介绍Activity，之后每次在启动画面之后，直接进入主界面
	 */
	private void startActivity() {
		final ImageView splash = (ImageView) findViewById(R.id.splash);
		Animation splashAnim = AnimationUtils.loadAnimation(this, R.anim.out_from_left);
		splashAnim.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {
				boolean isFirst = true;
				if (isFirst) {// 启动新特性介绍
					Animation featureAnim = AnimationUtils.loadAnimation(Splash.this, R.anim.in_from_right);
					feature.startAnimation(featureAnim);
					feature.setVisibility(View.VISIBLE);
				} else {
					intent = new Intent(Splash.this, MainActivity.class);
					startActivity(intent);
					exit();
				}
			}

			@Override
			public void onAnimationRepeat(Animation animation) {}

			@Override
			public void onAnimationEnd(Animation animation) {
				splash.setVisibility(View.GONE);
			}
		});
		splash.startAnimation(splashAnim);
	}

	@SuppressLint("InflateParams")
	private void initialize() {
		
		blockBack(true);
		
		LayoutInflater inflater = LayoutInflater.from(this);
		views = new View[]{
				inflater.inflate(R.layout.feature_1, null),
				inflater.inflate(R.layout.feature_2, null),
				inflater.inflate(R.layout.feature_3, null)
		};
		
		feature = (ViewPager) findViewById(R.id.feature);
		feature.setAdapter(new FeatureAdapter(views));
	}

	private Intent intent;// 启动Activity
	private View[] views;// 存储ViewPager中的页面
	private ViewPager feature;// 新特性介绍
	private final int SPLASH_DISPLAY_LENGTH = 2500;//启动画面持续的时间，默认2.5s
}
