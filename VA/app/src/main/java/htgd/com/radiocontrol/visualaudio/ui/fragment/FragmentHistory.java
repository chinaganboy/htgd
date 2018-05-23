package htgd.com.radiocontrol.visualaudio.ui.fragment;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import htgd.com.radiocontrol.visualaudio.base.BaseFragment;
import radiocontrol.htgd.com.visualaudio.R;

public class FragmentHistory extends BaseFragment {

    private View view;

    public FragmentHistory(){

      }

    public  static FragmentHistory newInstance(Context context){
        FragmentHistory fgSetting=new FragmentHistory();
        Bundle bundle = new Bundle();
        fgSetting.setArguments(bundle);
        return  fgSetting;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
       view=inflater.inflate(R.layout.fragment_history,container,false);
        return view;
    }




}
