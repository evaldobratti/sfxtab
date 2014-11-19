package org.ibratti.sfxutils.combobox

import javafx.scene.Parent
import javafx.scene.input.KeyCode

import org.junit.Assert._
import org.junit.{Before, Test}
import org.loadui.testfx.GuiTest

class ComboBoxGuiTest extends GuiTest {

  override def getRootNode: Parent = ComboBoxHBox()

  var cb: ComboBoxSearcheable[_] = _

  @Before
  def before() = {
    cb = GuiTest.find[ComboBoxSearcheable[_]]("#searcherCb")
  }

  @Test
  def showOptionsWhileTyping(): Unit = {
    click("#searcherCb")

    assertFalse(cb.isShowing)

    `type`("a")

    assertTrue(cb.isShowing)
    assertEquals(7, cb.getItems.size)
    assertNull(cb.getValue)


    `type`("b")

    assertTrue(cb.isShowing)
    assertEquals(6, cb.getItems.size)
    assertNull(cb.getValue)

    `type`("cde")

    assertTrue(cb.isShowing)
    assertEquals(3, cb.getItems.size)
    assertNull(cb.getValue)

    `type`(KeyCode.DOWN)

    assertEquals(ComboBoxHBox.values(4), cb.getValue)
  }

  @Test
  def whenNothingIsSelectedPressingEscTheOptionsAreHidedAndValueIsNull(): Unit = {
    click("#searcherCb")
    assertFalse(cb.isShowing)
    `type`("a")

    assertTrue(cb.isShowing)
    `type`(KeyCode.ESCAPE)

    assertFalse(cb.isShowing)
    assertNull(cb.getValue)
  }

  @Test
  def afterSelectingAValueAndPreessingEscKeepsTheValue(): Unit = {
    click("#searcherCb")
    `type`("a")

    `type`(KeyCode.DOWN)
    `type`(KeyCode.DOWN)

    assertEquals(ComboBoxHBox.values(1), cb.getValue)
    assertEquals("ab", cb.getEditor.getText)

    `type`(KeyCode.ESCAPE)

    assertEquals(ComboBoxHBox.values(1), cb.getValue)
    assertEquals("ab", cb.getEditor.getText)
  }

  @Test
  def canChangeTextWhileTypingAndSelectingAndTheOptionsUpdates() = {
    click("#searcherCb")

    `type`("a")
    `type`("c")

    assertFalse(cb.isShowing)

    `type`(KeyCode.LEFT)
    `type`("b")

    assertTrue(cb.isShowing)
  }


  @Test
  def selectAValueAndAfterCompletesTheSearch() = {
    click("#searcherCb")

    `type`("a")
    `type`(KeyCode.DOWN)
    `type`(KeyCode.DOWN)
    `type`(KeyCode.DOWN)

    assertEquals("abc", cb.getEditor.getText)
    assertEquals(7, cb.getItems.size())
    assertEquals(ComboBoxHBox.values(2), cb.getValue)

    `type`("d")

    assertEquals(4, cb.getItems.size)
    assertNull(cb.getValue)
  }

  @Test
  def afterSelectingAValueChangeCaretPositionAndChangeSearchCanTraceChanges(): Unit = {
    click("#searcherCb")

    `type`("abc")
    `type`(KeyCode.DOWN)
    `type`(KeyCode.DOWN)

    assertEquals(ComboBoxHBox.values(3), cb.getValue)
    assertEquals("abcd", cb.getEditor.getText)

    `type`(KeyCode.LEFT)
    assertEquals(ComboBoxHBox.values(3), cb.getValue)

    `type`("o")

    assertNull(cb.getValue)
    assertEquals("abcod", cb.getEditor.getText)
    assertFalse(cb.isShowing)

    `type`(KeyCode.BACK_SPACE)

    assertNull(cb.getValue)
    assertTrue(cb.isShowing)
  }
}
