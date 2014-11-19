package org.ibratti.sfxutils.tabs

import javafx.scene.control.TabPane

class StackableTabPane extends TabPane {
  def newTabFromView(view: InnerPanel) = {
    val tab: StackableTab = new StackableTab(view)
    getTabs.add(tab)
    getSelectionModel.select(tab)
  }
}
