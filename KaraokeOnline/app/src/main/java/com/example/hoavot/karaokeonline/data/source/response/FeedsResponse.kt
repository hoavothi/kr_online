package com.example.hoavot.karaokeonline.data.source.response

import com.example.hoavot.karaokeonline.data.model.other.Feed
import com.google.gson.annotations.SerializedName

/**
 *
 * @author at-hoavo.
 */
class FeedsResponse(@SerializedName("feeds") val feeds: MutableList<Feed>)
