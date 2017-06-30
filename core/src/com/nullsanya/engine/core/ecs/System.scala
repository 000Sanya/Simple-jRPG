package com.nullsanya.engine.core.ecs

import scala.collection.mutable.ListBuffer

/**
  * Created by NullSanya on 17.06.2017.
  */
abstract class System {

	val priority: Priority

	def addedToEngine(engine: Engine)

	def removedFromEngine(engine: Engine)

	def update(delta: Float)

	def checkEntity(entity: Entity): Boolean

	def addedEntity(entity: Entity)

	def removedEntity(entity: Entity)
}

abstract class NodeSystem[N : Node] extends System {
	protected val nodes: ListBuffer[N] = ListBuffer()
	
	def addNode(entity: Entity)
	def removeNode(entity: Entity)
	
	override def addedEntity(entity: Entity): Unit = {
		addNode(entity)
	}
	
	override def removedEntity(entity: Entity): Unit = {
		removeNode(entity)
	}
}
