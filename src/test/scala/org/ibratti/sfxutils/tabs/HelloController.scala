package org.ibratti.sfxutils.tabs

import javafx.fxml.FXML
import javafx.scene.control.TextField

class HelloController extends InnerPanel {
  @FXML var selectedValueTxt: TextField = _

  override def name: String = "Hi :)"

  override def fxmlResource: String = "HelloView.fxml"

  def selectButton() = {
    openWindow(new PickOneController, Option("Dolan"))
  }

  override def returningFromWithValue(returnValue: Option[Any]): Unit = {
    selectedValueTxt.setText(returnValue.getOrElse("").toString)
  }

  override def initialize(): Unit = {
    println(selectedValueTxt.getId)
  }
}
