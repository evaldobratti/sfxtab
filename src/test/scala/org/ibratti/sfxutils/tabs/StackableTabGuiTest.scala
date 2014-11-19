package org.ibratti.sfxutils.tabs

import javafx.scene.control.{Label, RadioButton, TextField}
import javafx.scene.{Node, Parent}

import com.google.common.base.Predicate
import org.junit.Test
import org.loadui.testfx.{Assertions, GuiTest}

class StackableTabGuiTest extends GuiTest {

  @Test
  def integration() = {
    click(".menu")
    click("#testMenuItem")

    verifyThat[Label]("#whereAmILbl", _.getText equals "Você está em: Hi :)")

    click("#selectBtn")

    verifyThat[RadioButton]("#dolanRadio", _.isSelected)
    verifyThat[RadioButton]("#bogsRadio", !_.isSelected)
    verifyThat[RadioButton]("#goobyRadio", !_.isSelected)

    verifyThat[Label]("#whereAmILbl", _.getText equals "Você está em: Hi :) > Pick :)")
    click("#goobyRadio")
    click("#pickButton")

    verifyThat[TextField]("#selectedValueTxt", _.getText equals "Gooby")
  }

  override def getRootNode: Parent = SFXTabSampleController()

  def verifyThat[T <: Node](identifier: String, predicate: T => Boolean): Unit = {
    Assertions.verifyThat(identifier, new Predicate[T] {
      override def apply(p1: T): Boolean = predicate(p1)
    })
  }

}
