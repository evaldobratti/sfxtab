package org.ibratti.sfxtab

import javafx.fxml.FXML
import javafx.scene.control.{RadioButton, ToggleGroup}

class PickOneController(private val radioGroup: ToggleGroup,
  private val bogsRadio: RadioButton,
  private val dolanRadio: RadioButton,
  private val goobyRadio: RadioButton,
  val aba: StackableTab) extends InnerPanel {

  override def name: String = "Pick :)"

  override def openingWithValue(param: Any): Unit = {
    val initialString = param.asInstanceOf[String]
    initialString match {
      case "Bogs" => radioGroup.selectToggle(bogsRadio)
      case "Dolan" => radioGroup.selectToggle(dolanRadio)
      case "Gooby" => radioGroup.selectToggle(goobyRadio)
      case _ => radioGroup.selectToggle(bogsRadio)
    }
  }

  def pickButton() = {
    closeWindow(radioGroup.getSelectedToggle.asInstanceOf[RadioButton].getText)
  }
}
