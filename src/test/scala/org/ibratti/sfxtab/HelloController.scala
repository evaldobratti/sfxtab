package org.ibratti.sfxtab

import SfxtabSampleApp._

import scalafx.scene.control.TextField

class HelloController(private val selectedValueTxt: TextField) {
  println(selectedValueTxt)
  def selectButton() = None
}
