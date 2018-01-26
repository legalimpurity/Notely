package com.legalimpurity.notely.util.rx

import io.reactivex.Scheduler

/**
 * Created by rkhanna on 25/1/18.
 */
interface SchedulerProvider
{
    fun ui(): Scheduler
    fun computation(): Scheduler
    fun io(): Scheduler
}