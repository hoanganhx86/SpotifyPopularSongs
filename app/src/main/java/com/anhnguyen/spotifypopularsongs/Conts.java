package com.anhnguyen.spotifypopularsongs;

public class Conts {

    // ===========================================================
    // Constants
    // ===========================================================

    public static final int HIGH_LIGHTED_COLOR = 0x55DBD7;
    public static final int EXPLORE_TV_DEFAULT_GRID_NUMBER_COLUMN = 3;
    public static final float UMBALA_MAGIC_NUMBER = 12f;
    public static final String DEFAULT_DATE_TIME_FORMAT = "yyyy-MM-dd'T'hh:mm:ssZ";


    public enum PlayVideoMode{
        PlayVideoModeFavoriteTv(1),
        PlayVideoModeGeoChannelTv(2),
        PlayVideoModeHashTagTv(3),
        PlayVideoModeMyTv(4),
        PlayVideoModeMessage(5);

        int value;
        PlayVideoMode(int value){
            this.value = value;
        }

        public int getValue(){
            return value;
        }

        public static PlayVideoMode map(int value){
            switch (value){
                case 1: return PlayVideoModeFavoriteTv;
                case 2: return PlayVideoModeGeoChannelTv;
                case 3: return PlayVideoModeHashTagTv;
                case 4: return PlayVideoModeMyTv;
                case 5: return PlayVideoModeMessage;
            }

            return PlayVideoModeFavoriteTv; // default
        }
    }

}
