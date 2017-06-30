package com.nullsanya.engine.core.ecs

import scala.collection.mutable.ListBuffer

/**
  * Created by NullSanya on 17.06.2017.
  */
final class Entity(val engine: Engine) {

	private val components: ListBuffer[Component] = ListBuffer()

	def addComponent(component: Component) = {
		components += component
		component.set(this)
	}

	def getComponent[T : Component](): T = {
		components.find(_.isInstanceOf[T]).get
	}

	def removeComponent(component: Component) = {
		components -= component
	}
	
	def addedComponent(component: Component) = {
		engine.addedComponent(this, component)
	}
	
	def removedComponent(component: Component) = {
		engine.removedComponent(this, component)
	}

	def hasComponent[T : Component](): Boolean = {
		components.exists(_.isInstanceOf[T])
	}

	def on(priority: Priority, callback: Float => Unit) = {
		engine.addListener(priority, callback)
	}

}
