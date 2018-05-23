package htgd.com.radiocontrol.visualaudio.utils;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;

import htgd.com.radiocontrol.visualaudio.model.Mp3Info;

public class MediaUtils {


    public static ArrayList getMp3Infos(Context context) {
        ArrayList mp3InfoList = new ArrayList<>();




        return mp3InfoList;
    }

    public static void scannerMusic(Context context) {
        ArrayList mp3List = new ArrayList();
// 查询媒体数据库
        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
// 遍历媒体数据库
        if (null == cursor) {
            LogUtils.setLog("move  to", "last");
            return;
        }

        if (cursor.moveToFirst()) {
            LogUtils.setLog("movetos", "first+1");
            while (!cursor.isAfterLast()) {
                LogUtils.setLog("moveto", "first+1");
// 歌曲编号
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
// 歌曲id
                int trackId = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM_ID));
// 歌曲标题
                String title = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
// 歌曲的专辑名：MediaStore.Audio.Media.ALBUM
                String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
// 歌曲的歌手名： MediaStore.Audio.Media.ARTIST
                String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
// 歌曲文件的路径 ：MediaStore.Audio.Media.DATA
                String url = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
// 歌曲的总播放时长：MediaStore.Audio.Media.DURATION
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
// 歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
                Long size = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
// 歌曲文件显示名字
                String disName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Media.DISPLAY_NAME));
                LogUtils.setLog("music disName=", disName);//打印出歌曲名字

                mp3List.add(new Mp3Info());
                cursor.moveToNext();
            }
            cursor.close();
        }
    }

    public static ArrayList getMusic(Context context) {
        ArrayList mp3List = new ArrayList();

        Cursor cursor = context.getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        LogUtils.setLog("music disName=", "sd"+cursor.getColumnIndex(MediaStore.Audio.Media._ID)+cursor.getCount());
        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
           // cursor.moveToNext();
            LogUtils.setLog("music disName=", "s");
            long id=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media._ID));
            long albumId=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
            long duration=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
            long size=cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
            String title=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
            String artist=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
            String album=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
            String displayName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
            LogUtils.setLog("music disName=", displayName);//打印出歌曲名字
            String url=cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
            int isMusic=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));
            if(isMusic!=0){
            Mp3Info mp3Info = new Mp3Info();
            mp3Info.setId(id);
            mp3Info.setTitle(title);
            mp3Info.setArtist(artist);
            mp3Info.setAlbun(album);
            mp3Info.setDisplayName(displayName);
             mp3Info.setAlbumId(albumId);
            mp3Info.setDuration(duration);
            mp3Info.setSize(size);
            mp3Info.setUrl(url);
            mp3List.add(mp3Info);
            }
            cursor.close();
        }
        return mp3List;
    }
}
