package com.example.hoavot.karaokeonline.data.source.api

import retrofit2.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 *
 * @author at-hoavo.
 */
class CustomCallAdapterFactory : CallAdapter.Factory() {
    private var original: RxJava2CallAdapterFactory = RxJava2CallAdapterFactory.create()

    companion object {
        /**
         * Create instance
         */
        fun create(): CallAdapter.Factory = CustomCallAdapterFactory()
    }

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit)
            : CallAdapter<*, *>? {
        val type = CallAdapter.Factory.getParameterUpperBound(0, returnType as? ParameterizedType)
        return RxCallAdapterWrapper(type, retrofit, original.get(returnType, annotations, retrofit))
    }
}
