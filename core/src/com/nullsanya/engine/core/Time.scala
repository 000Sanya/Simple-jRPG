package com.nullsanya.engine.core

/**
  * Created by NullSanya on 28.06.2017.
  */
object Time {
	private var prevTime: Long = 0
	private var _deltaTime: Long = 0
	
	def updateTime(): Unit = {
		val ct = System.currentTimeMillis()
		if(prevTime == 0) prevTime = ct
		_deltaTime = ct - prevTime
		prevTime = ct
	}
	
	def deltaTime : Long = _deltaTime
}
