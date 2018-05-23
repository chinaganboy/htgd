package htgd.com.radiocontrol.visualaudio.ui.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ViewPagerMusicAdapter extends PagerAdapter {
    private ArrayList<View> viewList;
    private Context mContext;

    public ViewPagerMusicAdapter(Context context,ArrayList<View> viewList){
        this.mContext = context;
        this.viewList = viewList;
    }

    @Override
    public int getCount() {
        return viewList==null?0:viewList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        if (view == object) return true;
        else return false;
    }
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view =viewList.get(position);
        container.addView(view);
        return view;
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(viewList.get(position));
    }
}
