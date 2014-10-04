package org.ibratti.sfxtab

import javafx.fxml.FXML
import javafx.scene.control.TextField
import SfxtabSampleApp._

class HelloController extends InnerPanel {
  @FXML var selectedValueTxt: TextField = _

  override def name: String = "Hi :)"

  override def fxmlResource: String = "HelloView.fxml"

  def selectButton() = {
    openWindow(new PickOneController, "Bogs")
  }

  override def returningFromWithValue(returnValue: Any): Unit = {
    selectedValueTxt.setText(returnValue.toString)
  }

  override def initialize(): Unit = {
    showWarningDialog("Bah")
  }
}
