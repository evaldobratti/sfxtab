package org.ibratti.sfxtab


import javafx.fxml.FXML
import javafx.scene.control.TextField


import scalafxml.core.macros.sfxml

class HelloController extends InnerPanel {

  override val aba: StackableTab = null
  @FXML private var selectedValueTxt: TextField = null
  override def name: String = "Hello :)"
  selectedValueTxt.disableProperty().bind()
  def selectButton() = None
}
