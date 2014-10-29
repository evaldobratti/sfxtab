package org.ibratti.sfxtab

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXMLLoader, Initializable}
import javafx.scene.Parent
import javafx.scene.control.{TextField, Dialogs}
import javafx.scene.layout.VBox
import javafx.stage.Stage



trait InnerPanel extends Initializable{
  private var _view: Option[Parent] = None

  def view: Parent = _view match{
    case None =>
      val loader = new FXMLLoader(classOf[InnerPanel].getResource(fxmlResource))
      loader.setController(this)
      _view = Option(loader.load().asInstanceOf[Parent])
      _view.get
    case Some(v) => v
  }

  var aba: StackableTab = _

  def initialize(p1: URL, p2: ResourceBundle) = initialize()

  def initialize() = {
  }

  def returningFromWithValue(returnValue: Any) = {}

  def openingWithValue(param: Option[Any]) = {}

  def openWindow(janela: InnerPanel, param: Option[Any]) = {
    aba.openWindow(janela, param)
  }

  def closeWindow(param: Option[Any]) = {
    aba.closeWindow(param)
  }

  def name: String

  def fxmlResource: String

  def showWarningDialog(message: String)(implicit stage: Stage) = Dialogs.showWarningDialog(stage , message, "Atenção", "Atenção")

  def showConfirmDialog(message: String)(implicit stage: Stage) = Dialogs.showConfirmDialog(stage, message, "Confirmação", "Confirmação", Dialogs.DialogOptions.YES_NO) match {
      case Dialogs.DialogResponse.YES => true
      case _ => false
    }
}
