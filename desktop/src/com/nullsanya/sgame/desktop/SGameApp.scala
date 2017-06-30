package com.nullsanya.sgame.desktop

import com.badlogic.gdx.backends.lwjgl.{LwjglApplication, LwjglApplicationConfiguration}
import com.nullsanya.sgame.SimpleGame

/**
  * Created by NullSanya on 15.06.2017.
  */
object SGameApp{
	def main(args: Array[String]) = {
		val config = new LwjglApplicationConfiguration()
		config.title = "Test Game!"
		val app = new LwjglApplication(new SimpleGame(), config)
	}
}
