package com.example.hoavot.karaokeonline.ui.playmusic.service

/**
 *
 * @author at-hoavo
 */
enum class Action(val value: String) {
    SONGS("SONGS"), CHOOSE_PLAY("CHOOSE_PLAY"),
    AUTO_NEXT("AUTO_NEXT"), SHUFFLE("SUFFLE"),
    AUTO_NEXT_SELETED("AUTO_NEXT_SELECTED"),
    SHUFFLE_SELECTED("SHUFFLE_SELECTED"),
    PAUSE("PAUSE"), PLAY("PLAY"),
    SEEK("SEEK"), SEEK_TO("SEEK_TO"),
    SEND_POSITION("SEND_POSITION"), NEXT("NEXT"),
    ID_FEED("ID_FEED"),
    STOP_MEDIA("STOP_MEDIA"),
    PREVIOUS("PREVIOUS"), CLOSE_NOTIFICATION("CLOSE_NOTIFICATION"),
    CLICK_NOTIFICATION("CLICK_NOTIFICATION"), CLOSE_ACTIVITY("CLOSE_ACTIVITY")
}
