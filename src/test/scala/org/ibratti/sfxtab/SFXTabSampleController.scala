package org.ibratti.sfxtab

import scalafx.scene.control.TabPane
import scalafxml.core.macros.sfxml

@sfxml
class SFXTabSampleController(val tab: TabPane) {
  println(tab)
  def cadastroClick() = {
    //tab.newTabFromView(getClass.getResource("HelloView.fxml"))
  }
}

