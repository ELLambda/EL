package gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;

/**
 * Created by wenxi on 2017/5/10.
 */
public class SettingWinController {

    public Slider BMGVolumeSlider;
    public Slider EffectVolumeSlider;

    @FXML
    void initialize() {

        BMGVolumeSlider.setValue(Music.BGMVolume * 100);
        BMGVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {

            if (Music.getCurrentBGM() != null) {
                Music.getCurrentBGM().setVolume((double) newValue / 100);
                Music.BGMVolume = (double) newValue / 100;
            } else {
                Music.BGMVolume = (double) newValue / 100;
            }
        });


        EffectVolumeSlider.setValue(Music.effectMusicVolume * 100);
        EffectVolumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            Music.effectMusicVolume = (double) newValue / 100;
        });
    }

    public void onBackBtnClick(ActionEvent actionEvent) {
        BMGVolumeSlider.getScene().getWindow().hide();
    }
}
