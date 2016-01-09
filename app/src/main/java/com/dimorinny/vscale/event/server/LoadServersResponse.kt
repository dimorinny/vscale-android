package com.dimorinny.vscale.event.server

import com.dimorinny.vscale.event.ResponseEvent

/**
 * Created by Dimorinny on 07.01.16.
 */
class LoadServersResponse(ok: Boolean, throwable: Throwable?) : ResponseEvent(ok, throwable)