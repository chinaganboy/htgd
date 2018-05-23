package htgd.com.radiocontrol.visualaudio.ui.fragment;



import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import htgd.com.radiocontrol.visualaudio.base.BaseFragment;
import radiocontrol.htgd.com.visualaudio.R;

public class FragmentDuiJiang extends BaseFragment{

    private FrameLayout mFlVdContain;

    public static FragmentDuiJiang newInstance(Context context) {

        FragmentDuiJiang   fgDuiJiang = new FragmentDuiJiang();
        Bundle bundle = new Bundle();
        fgDuiJiang.setArguments(bundle);
        return fgDuiJiang;
    }
    public FragmentDuiJiang(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
      View  view=inflater.inflate(R.layout.fragment_duijiang,container,false);
        mFlVdContain = (FrameLayout)view. findViewById(R.id.activity_main_fl_vd_contain);
        getSurfaceView();
         return view;
    }
    /**
     * 获取播放视频的SurfaceView
     *
     * @return SurfaceView实例
     */
    private SurfaceView getSurfaceView() {
        mFlVdContain.removeAllViews();
        SurfaceView surfaceView = new SurfaceView(this.getActivity());
        surfaceView.setKeepScreenOn(true);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams
                .MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mFlVdContain.addView(surfaceView, params);
        return surfaceView;
    }

}
