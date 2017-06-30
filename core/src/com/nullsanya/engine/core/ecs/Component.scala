package com.nullsanya.engine.core.ecs

/**
  * Created by NullSanya on 17.06.2017.
  */
abstract class Component() {
	private[this] var _entity: Entity = null

	private[core] def set(entity: Entity) = {
		this._entity = entity
	}

	def entity = _entity
}
