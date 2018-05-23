package htgd.com.radiocontrol.visualaudio.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import radiocontrol.htgd.com.visualaudio.R;


public class CallDialog implements View.OnClickListener {
    private final Context mContext;
    private final CallDialog.OnViewClickListener listener;
    private View view;


    private Dialog dialog;
    private Button cancel_bt;

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btn_cancel:
                if(listener!=null){
                    listener.cancel();
                }
                break;

        }
    }

    public interface OnViewClickListener {
        public void cancel();
    }
    /***
     * 显示对话框
     */
    public Dialog show() {

        if (dialog != null) {
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            if (!((Activity) mContext).isFinishing() && !dialog.isShowing()) {
                dialog.show();
            }
        }
        return dialog;
    }
    public CallDialog(Context context, CallDialog.OnViewClickListener listener) {
        this.listener = listener;
        this.mContext = context;
        initView();
    }

    public void dismiss(){
        dialog.dismiss();
    }


    private void initView() {
        view= LayoutInflater.from(mContext).inflate(R.layout.layout_dialog_call,null);
        cancel_bt=(Button)view.findViewById(R.id.btn_cancel);
        cancel_bt.setOnClickListener(this);
        dialog = new Dialog(mContext);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.CENTER );
        lp.width=500;
        lp.height=400;
        dialogWindow.setAttributes(lp);
        dialog.setCanceledOnTouchOutside(false);
    }
}
