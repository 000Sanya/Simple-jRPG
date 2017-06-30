package com.nullsanya.sgame

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.{GL20, OrthographicCamera}
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.badlogic.gdx.{Application, Game, Gdx}
import com.nullsanya.engine.core.{ObjectGroup, Time}

/**
  * Created by NullSanya on 15.06.2017.
  */
class SimpleGame extends Game {
	private var stage: Stage= _
	
	override def create(): Unit = {
		Gdx.app.setLogLevel(Application.LOG_DEBUG)
		
		stage = new Stage(new ScreenViewport()  )
		stage.addActor(new SimpleActor)
	}
	
	override def dispose(): Unit = {
		super.dispose()
		stage.dispose()
	}
	
	override def render(): Unit = {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
		Time.updateTime()
		
		stage.act()
		stage.draw()
	}
}
