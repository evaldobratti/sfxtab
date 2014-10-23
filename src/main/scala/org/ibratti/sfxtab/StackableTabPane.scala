package org.ibratti.sfxtab

import java.net.URL

import scala.reflect.runtime.universe.typeOf
import scalafx.scene.control.TabPane

import scalafxml.core.{DependenciesByType, FXMLView}

class StackableTabPane extends TabPane {

  def newTabFromView(resource: URL) = {
    val tab: StackableTab = new StackableTab()
    tab.openWindow(FXMLView(resource, new DependenciesByType(
      Map(typeOf[StackableTab] -> tab)
    )).asInstanceOf[InnerPanel])
    tabs.add(tab)
    selectionModel.value.select(tab)
  }
}
