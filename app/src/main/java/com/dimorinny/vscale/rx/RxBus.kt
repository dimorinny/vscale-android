package com.dimorinny.vscale.rx

import rx.Observable
import rx.subjects.PublishSubject
import rx.subjects.SerializedSubject

/**
 * Created by Dimorinny on 25.01.16.
 */

public class RxBus {
    private val bus = SerializedSubject<Any, Any>(PublishSubject.create());

    public fun post(event: Any) {
        bus.onNext(event)
    }

    public fun <T> events(type: Class<T>): Observable<T> {
        return events().ofType(type)
    }

    public fun events(): Observable<Any> {
        return bus.asObservable()
    }
}