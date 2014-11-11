package com.alex.develop.adapter;

import com.alex.develop.R;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * 新特性介绍页面（ViewPager）的生成
 */
public class FeatureAdapter extends PagerAdapter implements
		OnPageChangeListener {

	public FeatureAdapter(View[] views) {
		this.views = views;
	}

	@Override
	public int getCount() {
		return views.length;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onPageSelected(int arg0) {
		Log.d("Debug", arg0+"");
		
		// 设置当前页面对应的指示器为激活状态
		ImageView indicator = (ImageView) views[arg0].findViewById(getIndicator(arg0));
		indicator.setImageResource(R.drawable.circle_dot_activited);
		
		// 设置其余的指示器为正常状态
		for(int i=0; i<views.length; ++i) {
			if(i!=arg0) {
				indicator = (ImageView) views[arg0].findViewById(getIndicator(i));
				indicator.setImageResource(R.drawable.circle_dot_normal);
			}
		}
		
		
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		container.addView(views[position]);
		return views[position];
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		container.removeView(views[position]);
	}
	
	private int getIndicator(int index) {
		int resId = -1;
		switch (index) {
			case 0:
				resId = R.id.indicator_1;
				break;
			case 1:
				resId = R.id.indicator_2;
				break;
			case 2:
				resId = R.id.indicator_3;
				break;
		}
		return resId;
	}

	private View[] views;
}
