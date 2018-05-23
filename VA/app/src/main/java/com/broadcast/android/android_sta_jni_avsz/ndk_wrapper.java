package com.broadcast.android.android_sta_jni_avsz;

import android.util.Log;


import org.greenrobot.eventbus.EventBus;

import java.io.UnsupportedEncodingException;

import htgd.com.radiocontrol.visualaudio.base.MyApplication;
import htgd.com.radiocontrol.visualaudio.model.EventBusEvent;
import htgd.com.radiocontrol.visualaudio.pojo.AppEvent;
import htgd.com.radiocontrol.visualaudio.utils.AudioUtils;
import htgd.com.radiocontrol.visualaudio.utils.BroadcastUtil;
import htgd.com.radiocontrol.visualaudio.utils.VideoUtils;

/**
 * 与服务器交互的核心类[已封装好网络连接]
 *
 * @author Administrator
 * @date 2018/4/9
 */

public class ndk_wrapper {

    private static final String TAG = ndk_wrapper.class.getSimpleName();

    private static ndk_wrapper sNdkWrapper = null;

    static {
        System.loadLibrary("jni_avsz");
    }

    /**
     * 私有化构造函数
     */
    private ndk_wrapper() {

    }

    /**
     * 获取单例实例（饿汉式[线程安全]）
     *
     * @return 唯一单例
     */
    public synchronized static ndk_wrapper getInstance() {
        if (null == sNdkWrapper) {
            sNdkWrapper = new ndk_wrapper();
        }
        return sNdkWrapper;
    }

    /**
     * 预加载，判断是否已授权，不能跳过！
     *
     * @return 大于0的负数则表示无授权
     */
    public native int avsz_preload();

    /**
     * 从备份中恢复授权
     *
     * @return 0：恢复成功   大于0的负数：恢复失败，授权失败
     */
    public native int avsz_recover_auth_from_bak();

    /**
     * 初始化登录服务器
     *
     * @param ip       服务器IP地址
     * @param port     服务器端口号，默认1220
     * @param usr_name 用户名
     * @param usr_pwd  用户密码
     * @param ver      版本号，用于升级版本，格式为“v1.0_build201804091”
     * @return 会话sess_id，返回-9表示未预调用avsz_preload函数，返回其他大数字的负数表示未授权
     */
    public native int avsz_init(String ip, short port, String usr_name, String usr_pwd, String ver);

    /**
     * 结束会话，从服务器注销
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @return 执行结果[非0为异常]
     */
    public native int avsz_fini(int sess_client);

    /**
     * 结束全部会话
     *
     * @return 执行结果[非0为异常]
     */
    public native int avsz_fini_all();

    /**
     * 发送xml格式的协议内容到服务器
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param xml         xml格式的协议字符串
     * @return 执行结果[非0为异常]
     */
    public native int avsz_send_svr_xml(int sess_client, String xml);

    /**
     * 发送数据至服务器
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param xml         xml格式的协议字符串
     * @param buf_data    发送的数据[音频/视频/图片/串口数据/其它。。。]，数据类型及相关信息由xml内容指定
     * @return 执行结果[非0为异常]
     */
    public native int avsz_send_svr_xd(int sess_client, String xml, byte[] buf_data);

    /**
     * 发送数据至指定的用户
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param buf_data    发送的数据[音频/视频/图片/串口数据/其它。。。]
     * @param to_usr_guid 目标用户的GUID
     * @return 执行结果[非0为异常]
     */
    public native int avsz_send_usr_data(int sess_client, byte[] buf_data, String to_usr_guid);

    /**
     * 发送视频数据（至服务器，由服务器转发）
     *
     * @param ch_idx  通道号，0[摄像头1]  1[摄像头2] 。。。
     * @param strm    码流，主要针对视频码流， 0[第一码流] 1[第二码流] 。。。
     * @param h264    视频帧数据包
     * @param key_frm 关键帧标记，1：关键帧 其他：非关键帧
     * @param width   视频分辨率之宽
     * @param height  视频分辨率之高
     * @param fps     视频帧率
     * @return 执行结果[非0为异常]
     */
    public native int avsz_send_vid(int ch_idx, int strm, byte[] h264, int key_frm, int width,
                                    int height, int fps);

    /**
     * 发送音频数据接（至服务器，由服务器转发）
     *
     * @param ch_idx  通道号，0[摄像头1]  1[摄像头2] 。。。
     * @param strm    码流，主要针对视频码流， 0[第一码流] 1[第二码流] 。。。
     * @param pcm     音频帧数据包
     * @param channel 音频通道数，默认传1
     * @param bitrate 音频比特率，默认传16
     * @param sample  音频采样率
     * @return 执行结果[非0为异常]
     */
    public native int avsz_send_aud(int ch_idx, int strm, byte[] pcm, int channel, int bitrate,
                                    int sample);

    /**
     * 发起监视/听任务
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param callee_guid 被监视/听用户的GUID
     * @param ch_idx      通道号，0[摄像头1]  1[摄像头2] 。。。
     * @param strm        码流，主要针对视频码流， 0[第一码流] 1[第二码流] 。。。
     * @param md_tp       媒体类型，1[视频] 2[音频] 3[音视频]
     * @param proto       传输协议，1[TCP] 2[UDP] 3[UDP组播] 注意：目前UDP和UDP组播只适用于音频，如果包括视频，需选择TCP方式
     * @return 执行结果[非0为异常]
     */
    public native int avsz_start_monitor(int sess_client, String callee_guid, int ch_idx, int
            strm, int md_tp, int proto);

    /**
     * 停止任务
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param tsk_guid    任务的GUID
     * @return 执行结果[非0为异常]
     */
    public native int avsz_stop_task(int sess_client, String tsk_guid);

    /**
     * 确认会话sess，用于被呼叫或被监视/听者多点登录时使用，超时默认选择第一个sess
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param tsk_guid    任务的GUID
     * @param callee_guid 被呼叫或被监视/听者的GUID
     * @param callee_sess 选定的会话sess
     * @return 执行结果[非0为异常]
     */
    public native int avsz_confirm_sess_ret(int sess_client, String tsk_guid, String callee_guid,
                                            int callee_sess);

    /***
     * 发起对讲任务
     * 发送usr_call_req请求服务端后，服务端会反馈usr_call_req_ret消息（包含被呼用户状态信息，例如0表示不在线）
     * 或反馈tk_tsk_status消息表示任务运行状态，该消息中包含tsk_guid, 任务状态如下
     * 0[TSK_STATE_FREE], 1[TSK_STATE_AUTO],
     * 2[TSK_STATE_MANUAL]，3[TSK_STATE_CALL_START]，4[TSK_STATE_CALLING]或5[TSK_STATE_CALL_NO_USR]
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param callee_guid 被呼叫者的GUID
     * @param ch_idx 通道号，0[摄像头1]  1[摄像头2] 。。。
     * @param strm 码流，主要针对视频码流， 0[第一码流] 1[第二码流] 。。。
     * @param md_tp 媒体类型，1[视频] 2[音频] 3[音视频]
     * @param proto 传输协议，1[TCP] 2[UDP] 3[UDP组播] 注意：目前UDP和UDP组播只适用于音频，如果包括视频，需选择TCP方式
     * @return 执行结果[非0为异常]
     */
    public native int avsz_usr_call_req(int sess_client, String callee_guid, int ch_idx, int
            strm, int md_tp, int proto);

    /**
     * 呼叫请求反馈（被呼叫者调用）
     * 发送usr_call_req_ret到服务端后，服务端根据情况可能会返回usr_call_req_ret_fb消息，表示该呼叫已经被接听（already_pickup
     * ）或正在呼叫移动其它用户（calling_other）
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param tsk_guid    任务的GUID
     * @param status      0表示拒绝、1表示正忙、2表示接通
     * @return 执行结果[非0为异常]
     */
    public native int avsz_usr_call_req_ret(int sess_client, String tsk_guid, int status);

    /**
     * 取消呼叫请求任务（发起呼叫者调用）
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param tsk_guid    任务的GUID
     * @return 执行结果[非0为异常]
     */
    public native int avsz_usr_call_req_cancel(int sess_client, String tsk_guid);

    /**
     * 请求设备的关键帧（仅对终端有效，用户无效）
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param tsk_guid    任务的GUID
     * @return 执行结果[非0为异常]
     */
    public native int avsz_req_idr(int sess_client, String tsk_guid);

    /**
     * 添加或编辑定时任务
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param xml         xml格式的协议字符串
     * @return 执行结果[非0为异常]
     */
    public native int avsz_task_add_or_edit(int sess_client, String xml);

    /**
     * 删除定时任务
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param tsk_guid    任务的GUID
     * @return 执行结果[非0为异常]
     */
    public native int avsz_task_del(int sess_client, String tsk_guid);

    /**
     * 运行定时任务
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param tsk_guid    任务的GUID
     * @return 执行结果[非0为异常]
     */
    public native int avsz_task_start(int sess_client, String tsk_guid);

    /**
     * 停止定时任务
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param tsk_guid    任务的GUID
     * @return 执行结果[非0为异常]
     */
    public native int avsz_task_stop(int sess_client, String tsk_guid);

    /**
     * 开启音视频反馈
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param tsk_guid    任务的GUID
     * @param ret         反馈结果：ok、fail、already
     * @param caller_sess 发起任务者（当前登录的用户）的GUID
     * @param sample      音频采样率[未使用]
     * @return 执行结果[非0为异常]
     */
    public native int avsz_start_av_ret(int sess_client, String tsk_guid, String ret, int
            caller_sess, int sample);

    /**
     * 请求文件传输
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param full_path   文件全路径（在服务器的存储路径）
     * @return 执行结果[非0为异常]
     */
    public native int avsz_file_transfer(int sess_client, String full_path);

    /**
     * 取消文件传输
     *
     * @param sess_client 当前会话sess_id，由avsz_init函数返回
     * @param id          文件传输ID，由回调数据中获取
     * @return 执行结果[非0为异常]
     */
    public native int avsz_file_transfer_cancel(int sess_client, int id);

    /**************************以下方法由JNI调用，勿修改函数结构！！！****************************/

    /**
     * 消息回调
     *
     * @param sess_client 当前会话sess_id
     * @param type        消息类型
     * @param key         信息格式
     * @param buf         信息值，需转换成字符串值，格式为GB2312
     * @param buf2        信息值2[未使用]
     */
    public void avsz_callback(int sess_client, String type, String key, byte[] buf, byte[] buf2) {
        try {
            String value = new String(buf, "GB2312");
            String value2 = new String(buf2, "GB2312");
            Log.i(TAG, "[avsz_callback] sess_client: " + sess_client + ", type: " + type + ", " +
                    "key: " + key + ", value: " + value + ", value2: " + value2);

            EventBus.getDefault().post(new EventBusEvent(new AppEvent(sess_client, type, key, value)) );
            // 根据自己的业务逻辑处理数据
          /*  BroadcastUtil.sendLocalBroadcast(MyApplication.getContext(), new AppEvent
                    (sess_client, type, key, value));*/
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 视频帧数据回调
     *
     * @param sess_client 当前会话sess_id
     * @param sender      发送者，用户或终端ID
     * @param sender_type 1（用户），2（CTS终端）
     * @param ch_idx      通道号，0[摄像头1]  1[摄像头2] 。。。
     * @param strm        码流，主要针对视频码流， 0[第一码流] 1[第二码流] 。。。
     * @param buf         视频帧数据
     * @param key_frm     关键帧标识， 1（关键帧），其它（非关键帧）
     * @param width       视频宽
     * @param height      视频高
     * @param fps         视频帧率
     * @param enc_tp      编码类型：奇数：h264   偶数：h265
     */
    public void avsz_cb_h26x(int sess_client, String sender, int sender_type, int ch_idx, int
            strm, byte[] buf, int key_frm, int width, int height, int fps, int enc_tp) {
        Log.i(TAG, "[avsz_cb_h26x] sess_client: " + sess_client + ", sender: " + sender + ", " +
                "sender_type: " + sender_type + ", ch_idx: " + ch_idx + ", strm: " + strm + " " +
                "buf_len: " + buf.length + ", key_frm: " + key_frm + ", width: " + width + ", " +
                "height: " + height + ", fps: " + fps + ", enc_tp: " + enc_tp);
        // 根据自己的业务逻辑处理数据
        VideoUtils.putVideoData(sess_client, enc_tp == 1 ? "video/avc" : "video/hevc", buf,
                width, height, fps);
    }

    /**
     * 音频帧数据回调
     *
     * @param sess_client 当前会话sess_id
     * @param sender      发送者，用户或终端ID
     * @param sender_type 1（用户），2（CTS终端）
     * @param ch_idx      通道号，0[摄像头1]  1[摄像头2] 。。。
     * @param strm        码流，主要针对视频码流， 0[第一码流] 1[第二码流] 。。。
     * @param buf         音频帧数据
     * @param channel     音频通道数
     * @param bitrate     音频比特率
     * @param sample      音频采样率
     * @param flag_sync   同步标记，默认为0不同步，1为同步，收到同步帧时，需把之前的播放缓冲清除
     */
    public void avsz_cb_pcm(int sess_client, String sender, int sender_type, int ch_idx, int
            strm, byte[] buf, int channel, int bitrate, int sample, int flag_sync) {
        Log.i(TAG, "[avsz_cb_pcm] sess_client: " + sess_client + ", sender: " + sender + ", " +
                "sender_type: " + sender_type + ", ch_idx: " + ch_idx + ", strm: " + strm + ", " +
                "buf_len: " + buf.length + ", channel: " + channel + ", bitrate: " + bitrate +
                ", sample: " + sample + ", flag_sync: " + flag_sync);
        // 根据自己的业务逻辑处理数据
        AudioUtils.putAudioData(channel, buf, flag_sync);
    }

    /**
     * 文件传输会回调
     *
     * @param sess_client 当前会话sess_id
     * @param id          文件接收任务ID, avsz_file_transfer请求下载文件后，会生成下载任务ID文件大小
     * @param file_size   文件总大小
     * @param full_path   请求下载的文件全路径[服务端PC上]，同avsz_file_transfer的参数
     * @param key         消息类型 如下：file_recv_start[文件传输开始] file_recv_data[文件数据]
     *                    file_recv_end[文件传输线束] no_file[文件不存在] open_failed[文件打开失败]
     *                    empty_file[空文件]
     * @param buf         文件帧数据
     */
    public void avsz_cb_file(int sess_client, int id, long file_size, byte[] full_path, String
            key, byte[] buf) {
        try {
            String path = new String(full_path, "GB2312");
            Log.i(TAG, "[avsz_cb_file] sess_client: " + sess_client + ", id: " + id + ", " +
                    "file_size: " + file_size + ", full_path: " + path + ", key: " + key + ", " +
                    "buf_len: " + buf.length);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
