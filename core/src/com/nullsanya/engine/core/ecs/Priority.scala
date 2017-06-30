package com.nullsanya.engine.core.ecs

/**
  * Created by NullSanya on 18.06.2017.
  */

sealed case class Priority(priority: Int) extends Ordered[Priority] {
	override def compare(that: Priority): Int = {
		if(this.priority == that.priority)
			return 0
		if(this.priority > that.priority)
			return 1
		if(this.priority < that.priority)
			return -1
	}
}

case object PreUpdate extends Priority(1)
case object Update extends Priority(2)
case object PostUpdate extends Priority(3)
case object PreRender extends Priority(4)
case object Render extends Priority(5)
case object PostRender extends Priority(6)