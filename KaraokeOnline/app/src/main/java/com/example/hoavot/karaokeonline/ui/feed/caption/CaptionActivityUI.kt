package com.example.hoavot.karaokeonline.ui.feed.caption

import android.graphics.Color
import android.graphics.Typeface
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.text.TextUtils
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import com.example.hoavot.karaokeonline.R
import com.example.hoavot.karaokeonline.ui.extensions.circleImageView
import com.example.hoavot.karaokeonline.ui.extensions.enableHighLightWhenClicked
import com.example.hoavot.karaokeonline.ui.extensions.onClickWithAvoidRapidAction
import com.example.hoavot.karaokeonline.ui.utils.AvoidRapidAction
import de.hdodenhof.circleimageview.CircleImageView
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.toolbar
import org.jetbrains.anko.sdk25.coroutines.onClick

/**
 *
 * @author at-hoavo.
 */
class CaptionActivityUI : AnkoComponent<CaptionActivity> {
    internal lateinit var edtCaption: EditText
    internal lateinit var tvTitle: TextView
    internal lateinit var tvUsername: TextView
    internal lateinit var tvFileName: TextView
    internal lateinit var toolbar: Toolbar
    internal lateinit var avatar: CircleImageView
    internal lateinit var imgMusic: CircleImageView
    internal lateinit var pickImage: ImageView

    override fun createView(ui: AnkoContext<CaptionActivity>): View = with(ui) {
        relativeLayout {
            lparams(matchParent, matchParent)
            backgroundColor = Color.WHITE
            toolbar = toolbar {
                lparams(matchParent, dimen(R.dimen.toolBarHeight)) {
                    leftMargin = -dip(15)
                }
                id = R.id.toolBarCaption
                relativeLayout {
                    lparams(matchParent, matchParent)
                    imageView(R.drawable.ic_left_arrow) {
                        id = R.id.captionFragmentToolBarBack
                        topPadding = dimen(R.dimen.toolBarLeftRightIconTopPadding)
                        bottomPadding = dimen(R.dimen.toolBarLeftRightIconBottomPadding)
                        enableHighLightWhenClicked()
                        onClickWithAvoidRapidAction(AvoidRapidAction.DELAY_TIME) {
                            owner.eventOnBackClicked()
                        }
                    }.lparams(dip(40), matchParent)

                    tvTitle = textView(R.string.uploadReviewFragmentToolbarTitle) {
                        id = R.id.captionFragmentToolBarTitle
                        textSize = px2dip(dimen(R.dimen.toolBarMiddleTitleTextSize))
                        maxLines = 1
                        ellipsize = TextUtils.TruncateAt.END
                        textColor = ContextCompat.getColor(ctx, android.R.color.black)
                    }.lparams {
                        centerHorizontally()
                        topMargin = dimen(R.dimen.uploadReviewFragmentToolBarTitleTopPadding)
                    }

                    imageView(R.drawable.ic_complete) {
                        id = R.id.captionFragmentToolBarUpload
                        enableHighLightWhenClicked()
                        onClickWithAvoidRapidAction(AvoidRapidAction.DELAY_TIME) {
                            owner.eventOnCompleteButtonClicked()
                        }
                        topPadding = dimen(R.dimen.toolBarLeftRightIconTopPadding)
                        bottomPadding = dimen(R.dimen.toolBarLeftRightIconBottomPadding)
                    }.lparams(dip(40), matchParent) {
                        alignParentRight()
                    }
                }
            }

            relativeLayout {
                avatar = circleImageView {
                    id = R.id.captionFragmentAvartar
                    borderWidth = dip(0.4f)
                    borderColor = Color.GRAY
                }.lparams(dip(30), dip(30)) {
                    leftMargin = dip(15)
                    verticalMargin = dip(15)
                }

                tvUsername = textView {
                    id = R.id.captionFragmenttvUsername
                    textSize = px2dip(dimen(R.dimen.textSize15))
                    typeface = Typeface.DEFAULT_BOLD
                    textColor = Color.BLACK
                }.lparams {
                    rightOf(R.id.captionFragmentAvartar)
                    sameTop(R.id.captionFragmentAvartar)
                    leftMargin = dip(5)
                }

                edtCaption = editText {
                    backgroundColor = Color.TRANSPARENT
                    backgroundResource = R.drawable.custom_edittext_search_video
                    topPadding = dip(20)
                    hint = resources.getString(R.string.uploadReviewFragmentEdtCaptionHint)
                    gravity = Gravity.TOP or Gravity.START
                    id = R.id.captionFragmentEdtCaption
                    hintTextColor = ContextCompat.getColor(ctx, R.color.colorGrayLight)
                    textColor = ContextCompat.getColor(ctx, android.R.color.black)
                    textSize = px2dip(dimen(R.dimen.captionFragmentEdtCaptionTextSize))
                    horizontalPadding = dip(10)
                }.lparams(matchParent, dip(200)) {
                    horizontalMargin = dip(10)
                    below(R.id.captionFragmentAvartar)
                }

                linearLayout {
                    relativeLayout {
                        textView("Thêm file nhạc") {
                            id = R.id.captionFragmentTvFile
                            textColor=Color.BLACK
                            textSize = px2dip(dimen(R.dimen.textSize15))
                        }.lparams {
                            alignParentTop()
                            topMargin = dip(5)
                            leftMargin = dip(10)
                        }

                        imageView(R.drawable.ic_add_song_feed) {
                            id = R.id.captionFragmenttvFileRecordImage
                            onClick {
                                owner.eventWhenClickedAddFileRecord()
                            }
                        }.lparams(dip(25), dip(25)) {
                            alignParentTop()
                            topMargin = dip(5)
                            leftMargin = dip(5)
                            rightOf(R.id.captionFragmentTvFile)
                        }

                        tvFileName = textView {
                            id = R.id.captionFragmenttvFileName
                            textSize = px2dip(dimen(R.dimen.textSize14))
                            typeface = Typeface.DEFAULT_BOLD
                            textColor = ContextCompat.getColor(context, R.color.colorGrayLight)
                        }.lparams {
                            below(R.id.captionFragmenttvFileRecordImage)
                            horizontalMargin = dip(10)
                        }
                    }.lparams(dip(0), matchParent) {
                        weight = 1f
                    }

                    relativeLayout {
                        imgMusic = circleImageView {
                            id = R.id.captionFragmenImgMusic
                            backgroundResource = R.drawable.bg_play
                        }.lparams(matchParent, matchParent)

                        relativeLayout {
                            backgroundColor = ContextCompat.getColor(context, R.color.colorProfileAvatar)
                            pickImage = imageView(R.drawable.ic_camera_alt_red_300_24dp) {
                            }.lparams {
                                centerInParent()
                            }
                            enableHighLightWhenClicked()
                            onClick {
                                owner.eventOnCameraClick()
                            }
                        }.lparams(matchParent, dip(40)) {
                            alignParentBottom()
                            alignParentRight()
                        }
                    }.lparams(dip(0), matchParent) {
                        weight = 1f
                    }
                }.lparams(matchParent, dip(200)) {
                    below(R.id.captionFragmentEdtCaption)
                    topMargin = dip(10)
                }

            }.lparams(matchParent, matchParent) {
                below(R.id.toolBarCaption)
            }
        }
    }
}
