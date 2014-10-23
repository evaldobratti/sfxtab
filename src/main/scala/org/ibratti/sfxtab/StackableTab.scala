package org.ibratti.sfxtab

import javafx.geometry.{Insets, Orientation}
import javafx.scene.control.{Separator, Label, Tab}
import javafx.scene.layout.{VBox, HBox}

import scala.collection.mutable.Stack

class StackableTab extends Tab {
  val stackedScenes = Stack[InnerPanel]()
  val vbox = new VBox()
  vbox.setPadding(new Insets(padding, padding, padding, padding))
  vbox.setSpacing(5)
  private val whereAmILbl: Label = new Label()
  vbox.getChildren.add(whereAmILbl)
  vbox.getChildren.add(new Separator(Orientation.HORIZONTAL))
  //vbox.getChildren.add(root.view)
  setContent(vbox)

  refresh()

  def refresh() = {
    whereAmILbl.setText("Você está em: " + stackedScenes.map(_.name).mkString(" > "))
    setText(stackedScenes.top.name)
  }

  def openWindow(innerView: InnerPanel) {
    stackedScenes.push(innerView)
    refresh()
  }

  def openWindow(janela: InnerPanel, params: Any) {
    janela.openingWithValue(params)
    openWindow(janela)
    refresh()
  }

  def closeWindow(valorRetorno: Any) {
    val top = stackedScenes.pop()
    if (stackedScenes.isEmpty)
      getTabPane.getTabs.remove(this)
    else {
      stackedScenes.top.returningFromWithValue(valorRetorno)
      //vbox.getChildren.remove(top.view)
      //vbox.getChildren.add(stackedScenes.top.view)
      refresh()
    }
  }

  def padding = 10
}
