package com.nullsanya.engine.core.ecs

import scala.collection.SeqView
import scala.collection.mutable.ListBuffer

/**
  * Created by NullSanya on 17.06.2017.
  */
class Engine {
	private val entities: ListBuffer[Entity] = ListBuffer()
	private var systems: ListBuffer[System] = ListBuffer()

	private val callbacks: Map[Priority, ListBuffer[Float => Unit]] =
		Map(PreUpdate -> ListBuffer(), Update -> ListBuffer(), PostUpdate -> ListBuffer(),
			PreRender -> ListBuffer(), Render -> ListBuffer(), PostRender -> ListBuffer())

	def addEntity(entity: Entity) = {
		entities += entity
		addedEntity(entity)
	}

	def removeEntity(entity: Entity) = {
		removedEntity(entity)
		entities -= entity
	}

	def addSystem(system: System) = {
		systems += system
		systems = systems.sortWith( (s1, s2) => s1.priority < s2.priority )
		system.addedToEngine(this)
	}
	
	def removeSystem(system: System) = {
		systems -= system
		system.removedFromEngine(this)
	}

	private def addedEntity(entity: Entity) = {
		systems.foreach(_.addedEntity(entity))
	}

	private def removedEntity(entity: Entity) = {
		systems.foreach(_.removedEntity(entity))
	}
	
	def addedComponent(entity: Entity, component: Component) = {
		// TODO: сделать это, а ща лень
	}
	def removedComponent(entity: Entity, component: Component) = {
		// TODO: сделать это, а ща лень
	}

	def addListener(eventType: Priority , callback: Float => Unit) = callbacks(eventType) += callback
	def removeListener(eventType: Priority, callback: Float => Unit) = callbacks(eventType) -= callback

	def update(delta: Float) = {
	
	}
}
