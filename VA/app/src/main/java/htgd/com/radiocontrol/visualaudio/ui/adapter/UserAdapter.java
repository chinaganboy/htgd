package htgd.com.radiocontrol.visualaudio.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import htgd.com.radiocontrol.visualaudio.pojo.usr.Usr;
import htgd.com.radiocontrol.visualaudio.utils.LogUtils;
import radiocontrol.htgd.com.visualaudio.R;

public class UserAdapter extends BaseAdapter {

    private Context mContext;
    private String Tag = "UserAdapter";
    private ArrayList<Usr> list;
    private ArrayList<String> mSelectedIndex;

    public UserAdapter(Context context, ArrayList<Usr> list) {
        this.mContext = context;
        this.list = list;
        mSelectedIndex = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
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
            viewHolder = new ViewHolder();
            viewHolder.IvUser = (ImageView) view.findViewById(R.id.iv_user);
            viewHolder.name = (TextView) view.findViewById(R.id.name);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

      /*  if (list.size() < 15) {//填充界面
            for (int k = 0; k < 15 - list.size(); k++) {
                list.add(new Usr());
            }
        }*/
        if (list.get(i).getName() == null) {
            viewHolder.name.setText("");
            viewHolder.IvUser.setImageResource(R.mipmap.machine_no);
        } else {
            getState(list.get(i).getStatus(), viewHolder);
            viewHolder.name.setText(list.get(i).getName());
            if (list.get(i).getStatus().equals("1")  && list.get(i).isChoose()) {
                viewHolder.name.setTextColor(mContext.getResources().getColor(R.color.colorBlue));
                viewHolder.IvUser.setImageResource(R.mipmap.machine_all_click_image);
            } else if (list.get(i).getStatus() .equals("1") && !list.get(i).isChoose()) {
                viewHolder.name.setTextColor(mContext.getResources().getColor(R.color.black));
                viewHolder.IvUser.setImageResource(R.mipmap.machine_all_image);
            }
        }
        return view;
    }

    private class ViewHolder {
        private ImageView IvUser;
        private TextView name;
    }

    /**
     * 获取状态值对应的文字
     *
     * @param status 状态值
     * @return 对应的文字
     */
    private String getState(String status, ViewHolder viewHolder) {
        if (!TextUtils.isEmpty(status)) {
            String tmp = "";
            switch (status) {
                case "0":
                    tmp = "离线";

                    viewHolder.IvUser.setImageResource(R.mipmap.machine_has);
                    break;
                case "1":
                    tmp = "在线";
                    LogUtils.setLog(Tag, "在线");
                    viewHolder.IvUser.setImageResource(R.mipmap.machine_all_image);
                    break;
                case "2":
                    tmp = "喊话中";
                    break;
                case "3":
                    tmp = "对讲中";
                    viewHolder.IvUser.setImageResource(R.mipmap.machine_speaking);
                    break;
                case "4":
                    tmp = "呼叫中";
                    viewHolder.IvUser.setImageResource(R.mipmap.machine_calling);
                    break;
                case "5":
                    tmp = "被呼叫中";
                    viewHolder.IvUser.setImageResource(R.mipmap.machine_calling);
                    break;
                case "6":
                    tmp = "广播中";
                    viewHolder.IvUser.setImageResource(R.mipmap.machine_playing_music);
                    break;
                case "7":
                    tmp = "监控中";
                    break;
                default:
                    break;
            }
            return tmp;
        }
        return "";
    }
  /*  *//**
     * 获取当前选中的终端/用户数据集
     *
     * @return 终端/用户数据集
     *//*
    public List<Usr> getCurrentUsr() {
        List<Usr> tmp = new ArrayList<>();
        if (!mSelectedIndex.isEmpty()  ) {
            for (String index : mSelectedIndex) {
                String[] indexs = index.split("&");
             tmp.add()   list.get(Integer.parseInt(indexs[1]));
            }
            return tmp;
        }
        return tmp;
    }*/
    public void refrech(ArrayList<Usr> list) {
        this.list = list;
        this.notifyDataSetChanged();
    }
}
