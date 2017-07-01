package ch04

import java.awt.Window
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener

// create Impl object with object expression
val mouseListenerImpl = object : MouseListener {
	override fun mouseReleased(e: MouseEvent?) {
	}

	override fun mouseEntered(e: MouseEvent?) {
	}

	override fun mouseExited(e: MouseEvent?) {
	}

	override fun mousePressed(e: MouseEvent?) {
	}

	override fun mouseClicked(e: MouseEvent?) {
	}
}

fun onClick(window: Window) {
	var clickCount = 0

	// use Impl object directly
	// but has no access to local variables in the scope
	window.addMouseListener(mouseListenerImpl)

	// object expression is used here
	// to replace usage of anonymous class
	window.addMouseListener(object : MouseAdapter() {
		override fun mouseClicked(e: MouseEvent?) {
			super.mouseClicked(e)
			// can update variable in the scope
			// where object is defined
			clickCount++
		}
	})
}

