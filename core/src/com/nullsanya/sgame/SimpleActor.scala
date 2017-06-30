package com.nullsanya.sgame

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.{Animation, Batch, TextureRegion}
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.scenes.scene2d.Actor
import com.badlogic.gdx.{Gdx, Input}
import com.nullsanya.engine.core.{GameObject, Time}

/**
  * Created by NullSanya on 24.06.2017.
  */
class SimpleActor extends Actor {
	private val texture = new Texture(Gdx.files.internal("gg.png"))
	
	//Animation
	//TODO: сделать удобней
	private val tilesPerSecond : Int = 4
	
	private var stateTime: Float = 0.0f
	
	private var walkDown: Animation[TextureRegion] = _
	private var walkLeft: Animation[TextureRegion] = _
	private var walkRight: Animation[TextureRegion] = _
	private var walkUp: Animation[TextureRegion] = _
	
	private val position: Vector2 = new Vector2(0, 0)
	private val realPosition: Vector2 = new Vector2(0, 0)

	private var currentAnimation: Animation[TextureRegion] = _
	
	setAnimations()
	
	private def setAnimations() = {
		val tmp: Array[Array[TextureRegion]] = TextureRegion.split(texture, 32, 32)
		val down = tmp(0)
		val left = tmp(1)
		val right = tmp(2)
		val up = tmp(3)
		walkDown = setter(down)
		walkLeft= setter(left)
		walkRight = setter(right)
		walkUp = setter(up)
		
		currentAnimation = walkDown
		
		def setter(array: Array[TextureRegion]) : Animation[TextureRegion] = {
			new Animation[TextureRegion](1, array(1), array(0), array(1), array(2))
		}
	}
	
	override def act(delta: Float): Unit = {
		if(!isMoving() && Gdx.input.isKeyPressed(Input.Keys.W)){
			position.add(0, 32)
			currentAnimation = walkUp
		}
		else if(!isMoving() && Gdx.input.isKeyPressed(Input.Keys.S)){
			position.add(0, -32)
			currentAnimation = walkDown
		}
		else if(!isMoving() && Gdx.input.isKeyPressed(Input.Keys.D)){
			position.add(32, 0)
			currentAnimation = walkRight
		}
		else if(!isMoving() && Gdx.input.isKeyPressed(Input.Keys.A)){
			position.add(-32, 0)
			currentAnimation = walkLeft
		}
		updateMove()
		updateAnimation()
	}
	
	def updateMove() : Unit = {
		if(!isMoving())
			return
		val d = Time.deltaTime / 1000f * distancePerSecond
		if(position.x < realPosition.x)
			realPosition.x = Math.max(realPosition.x - d, position.x)
		if(position.x > realPosition.x)
			realPosition.x = Math.min(realPosition.x + d, position.x)
		if(position.y < realPosition.y)
			realPosition.y = Math.max(realPosition.y - d, position.y)
		if(position.y > realPosition.y)
			realPosition.y = Math.min(realPosition.y + d, position.y)
		if(!isMoving()) Gdx.app.debug("", "Made a step")
	}
	
	def updateAnimation() : Unit = {
		if(isMoving()) {
			val t = new Vector2(realPosition).sub(position).len()
			if(t <= 16 && (stateTime == 1 || stateTime == 3)) incrementStateTime()
			else if(t <= 32 && t > 16 && (stateTime == 0 || stateTime == 2)) incrementStateTime()
		}
	}
	
	def isMoving : Boolean = !position.equals(realPosition)
	
	private def incrementStateTime() : Unit = {
		val st = stateTime + 1
		if(st == 4)
			stateTime = 0
		else stateTime = st
		Gdx.app.debug("", stateTime.toString)

	}
	private def distancePerSecond() : Float = tilesPerSecond * 32f
	
	override def draw(batch: Batch, a: Float): Unit = {
		val cf = currentAnimation.getKeyFrame(stateTime, true)
		batch.draw(cf, realPosition.x, realPosition.y)
	}
}
