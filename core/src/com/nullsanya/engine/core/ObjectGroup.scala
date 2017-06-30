package com.nullsanya.engine.core

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer

import scala.collection.mutable.ListBuffer

/**
  * Created by NullSanya on 28.06.2017.
  */
class ObjectGroup {
	private val children: ListBuffer[GameObject] = ListBuffer()
	
	def addObject(obj: GameObject): Unit = {
		children += obj
	}
	
	def removeObject(obj: GameObject): Unit = {
		children -= obj
	}
	
	def update(): Unit = {
		children.foreach(_.update())
	}
	
	def draw(batch: Batch): Unit = {
		children.foreach(_.draw(batch))
	}
	
	def drawDebug(shape: ShapeRenderer): Unit = {
		children.foreach(_.drawDebug(shape))
	}
}
