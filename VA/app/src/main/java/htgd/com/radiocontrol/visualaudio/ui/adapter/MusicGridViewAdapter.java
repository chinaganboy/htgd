package htgd.com.radiocontrol.visualaudio.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


import htgd.com.radiocontrol.visualaudio.pojo.prog.Prog;

import radiocontrol.htgd.com.visualaudio.R;

public class MusicGridViewAdapter extends BaseAdapter{
    private   ArrayList<Prog> musicList;
    private   Context mContext;

    public MusicGridViewAdapter(Context context, ArrayList<Prog>  musicList) {
        this.musicList = musicList;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return musicList.size();
    }

    @Override
    public Object getItem(int i) {
        return musicList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
       ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_machine, null);
            viewHolder = new  ViewHolder();
            viewHolder.IvUser = (ImageView) view.findViewById(R.id.iv_user);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(viewHolder);
        } else {
            viewHolder = ( ViewHolder) view.getTag();
        }


        if (musicList.get(i).getName() == null) {
            viewHolder.name.setText("");
            viewHolder.IvUser.setImageResource(R.mipmap.music_no);
        }else{
            viewHolder.name.setText(musicList.get(i).getName());
            if(musicList.get(i).isChoose) {
                viewHolder.name.setTextColor(mContext.getResources().getColor(R.color.colorBuleDark));

            }else{
                viewHolder.name.setTextColor(mContext.getResources().getColor(R.color.black));
                viewHolder.IvUser.setImageResource(R.mipmap.music_have);
            }
        }


        return view;
    }

    public class  ViewHolder{
        private  TextView  name;
        private  ImageView  IvUser;

    }

    public void refrech(ArrayList<Prog> list) {
        this.musicList = list;
        this.notifyDataSetChanged();
    }
}
