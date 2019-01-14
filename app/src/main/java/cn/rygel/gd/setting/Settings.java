package cn.rygel.gd.setting;

import com.tencent.mmkv.MMKV;

public class Settings {

    private MMKV mKV = MMKV.defaultMMKV();

    private static final String KEY_KEEP_ALIVE = "KEY_KEEP_ALIVE";
    private static final String KEY_WEEKDAY_OFFSET = "KEY_WEEKDAY_OFFSET";

    public boolean putKeepAlive(boolean state) {
        return mKV.encode(KEY_KEEP_ALIVE, state);
    }

    public boolean putWeekDayOffset(int offset) {
        return mKV.encode(KEY_WEEKDAY_OFFSET, offset);
    }

    public boolean isKeepAlive() {
        return mKV.decodeBool(KEY_KEEP_ALIVE,true);
    }

    public int getWeekdayOffset() {
        return mKV.decodeInt(KEY_WEEKDAY_OFFSET,0);
    }

    public static Settings getInstance(){
        return Instance.sInstance;
    }

    private static class Instance{
        private static Settings sInstance = new Settings();
    }
}
