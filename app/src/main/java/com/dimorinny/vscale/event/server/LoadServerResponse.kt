package com.dimorinny.vscale.event.server

import com.dimorinny.vscale.event.ResponseEvent

/**
 * Created by Dimorinny on 12.01.16.
 */
class LoadServerResponse(ok: Boolean, throwable: Throwable?) : ResponseEvent(ok, throwable)