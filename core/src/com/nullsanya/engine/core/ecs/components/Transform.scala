package com.nullsanya.engine.core.ecs.components

import com.badlogic.gdx.math.Vector2
import com.nullsanya.engine.core.Component

/**
  * Created by NullSanya on 18.06.2017.
  */
case class Transform(var position: Vector2, var scale: Vector2 = new Vector2(1, 1)) extends Component
