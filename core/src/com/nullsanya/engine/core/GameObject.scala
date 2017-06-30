package com.nullsanya.engine.core

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

/**
  * Created by NullSanya on 27.06.2017.
  */
class GameObject {
	def update(): Unit = { }
	def draw(batch: Batch): Unit = { }
	def drawDebug(shape: ShapeRenderer): Unit = { }
}
