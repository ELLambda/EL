package gui;

import javafx.scene.media.MediaPlayer;

public class Music {
    private static final MediaPlayer THEME = MusicManager.getMediaPlayer("../music/theme.mp3");
    private static final MediaPlayer BG1 = MusicManager.getMediaPlayer("../music/bgm1.mp3");
    private static final MediaPlayer BG2 = MusicManager.getMediaPlayer("../music/bgm2.mp3");
    private static final MediaPlayer BG3 = MusicManager.getMediaPlayer("../music/bgm3.mp3");
    private static final MediaPlayer BG4 = MusicManager.getMediaPlayer("../music/bgm4.mp3");
    private static final MediaPlayer BG5 = MusicManager.getMediaPlayer("../music/bgm5.mp3");
    private static final MediaPlayer BG6 = MusicManager.getMediaPlayer("../music/bgm6.mp3");
    private static final MediaPlayer BG7 = MusicManager.getMediaPlayer("../music/bgm7.mp3");
    private static final MediaPlayer BG8 = MusicManager.getMediaPlayer("../music/bgm8.mp3");
    private static final MediaPlayer BG9 = MusicManager.getMediaPlayer("../music/bgm9.mp3");
    private static final MediaPlayer BG10 = MusicManager.getMediaPlayer("../music/bgm10.mp3");
    private static final MediaPlayer BG11 = MusicManager.getMediaPlayer("../music/bgm11.mp3");
    private static final MediaPlayer BG12 = MusicManager.getMediaPlayer("../music/bgm12.mp3");
    private static final MediaPlayer BG13 = MusicManager.getMediaPlayer("../music/effect4.mp3");
    private static final MediaPlayer BG14 = MusicManager.getMediaPlayer("../music/effect1.mp3");
    private static final MediaPlayer BG15 = MusicManager.getMediaPlayer("../music/bgm15.mp3");
    private static final MediaPlayer EFFECT1 = MusicManager.getMediaPlayer("../music/effect.mp3");
    private static final MediaPlayer EFFECT2 = MusicManager.getMediaPlayer("../music/eliminate1.mp3");
    private static final MediaPlayer EFFECT3 = MusicManager.getMediaPlayer("../music/change.mp3");
    private static final MediaPlayer[] effectMusic = {EFFECT1, EFFECT2, EFFECT3};
    private static final MediaPlayer[] bgMusic = {THEME, BG1, BG2, BG3, BG4, BG5, BG6, BG7, BG8, BG9, BG10, BG11, BG12, BG13, BG14, BG15};
    public static boolean isBgMusic = true;
    public static boolean isEffectMusic = true;
    public static double BGMVolume = 0.5;
    //private static final MediaPlayer EFFECT4 = MusicManager.getMediaPlayer("../music/open.mp3");
    public static double effectMusicVolume = 1.0;
    private static MediaPlayer currentBGM = null;

//public static void main(String[] args) {
//	playBgMusic(12);
//	playBgMusic(0);
//	playEffectMusic(0);
//	}

    /**
     * bg music
     * 0: theme
     */
    public static void playBgMusic(int num) {
        if (isBgMusic) {
            stopBgMusic();
            bgMusic[num].play();
            bgMusic[num].setCycleCount(MediaPlayer.INDEFINITE);
            currentBGM = bgMusic[num];//更新当前音乐
            currentBGM.setVolume(BGMVolume);
            //System.out.println("volume:"+currentBGM.getVolume());
        }
    }

    public static void stopBgMusic() {
        if (isBgMusic) {
            for (int i = 0; i < bgMusic.length; i++) {
                bgMusic[i].stop();
            }
        }
    }

    /**
     * effect music
     */
    public static void playEffectMusic(int num) {
        if (isEffectMusic) {
            effectMusic[num].seek(effectMusic[num].getStartTime());
            effectMusic[num].setVolume(effectMusicVolume);
            effectMusic[num].play();
        }
    }

    public static void stopEffectMusic() {
        if (isBgMusic) {
            for (int i = 0; i < effectMusic.length; i++) {
                effectMusic[i].stop();
            }
        }
    }

    public static MediaPlayer getCurrentBGM() {
        return currentBGM;
    }
}

