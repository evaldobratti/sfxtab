package org.ibratti.sfxtab

import javafx.scene.control.TabPane
import javafx.stage.Stage

class StackableTabPane extends TabPane {
  def newTabFromView(view: InnerPanel) = {
    val tab: StackableTab = new StackableTab(view)
    getTabs.add(tab)
    getSelectionModel.select(tab)
  }
}
