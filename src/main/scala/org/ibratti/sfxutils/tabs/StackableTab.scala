package org.ibratti.sfxutils.tabs

import javafx.geometry.{Insets, Orientation}
import javafx.scene.control.{Label, Separator, Tab}
import javafx.scene.layout.VBox

import scala.collection.mutable.Stack

class StackableTab(val root: InnerPanel) extends Tab {
  val stackedScenes = Stack[InnerPanel]()
  val vbox = new VBox()
  vbox.setPadding(new Insets(padding, padding, padding, padding))
  vbox.setSpacing(5)
  val whereAmILbl: Label = new Label()
  whereAmILbl.setId("whereAmILbl")
  vbox.getChildren.add(whereAmILbl)
  vbox.getChildren.add(new Separator(Orientation.HORIZONTAL))
  vbox.getChildren.add(root.view)
  setContent(vbox)

  stackedScenes.push(root)

  refresh()
  root.aba = this

  def refresh() = {
    whereAmILbl.setText("Você está em: " + stackedScenes.reverse.map(_.name).mkString(" > "))
    setText(stackedScenes.top.name)
  }

  def openWindow(janela: InnerPanel, arg: Option[Any]) {
    vbox.getChildren.remove(stackedScenes.top.view)
    vbox.getChildren.add(janela.view)
    janela.openingWithValue(arg)
    janela.aba = this
    stackedScenes.push(janela)
    refresh()
  }

  def closeWindow(valorRetorno: Option[Any]) {
    val top = stackedScenes.pop()
    if (stackedScenes.isEmpty)
      getTabPane.getTabs.remove(this)
    else {
      stackedScenes.top.returningFromWithValue(valorRetorno)
      vbox.getChildren.remove(top.view)
      vbox.getChildren.add(stackedScenes.top.view)
      refresh()
    }
  }

  def actualContent = getContent.asInstanceOf[VBox].getChildren.get(2)
  def padding = 10
}
