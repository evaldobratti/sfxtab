package org.ibratti.sfxutils.combobox

import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.collections.FXCollections
import javafx.event.EventHandler
import javafx.scene.control.ComboBox
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.util.StringConverter



class ComboBoxSearcheable[T](val functions: ComboBoxSearchableFunctions[T]) extends ComboBox[T] {
  setEditable(true)

  getEditor.setOnKeyReleased(new EventHandler[KeyEvent] {
    override def handle(p1: KeyEvent): Unit = {
      println(getEditor.getText)
      if (p1.getCode.isLetterKey || p1.getCode.isDigitKey
        || p1.getCode == KeyCode.BACK_SPACE || p1.getCode == KeyCode.DELETE) {
        println("search")
        if (getValue != null && (p1.getCode.isLetterKey || p1.getCode.isDigitKey)) {
          val previousCaretPosition = getEditor.getCaretPosition
          val previousString: String = functions.internalToString(getValue)
          setValue(null.asInstanceOf[T])
          val beforeCaret: String = previousString.substring(0, previousCaretPosition -1)
          val afterCaret: String = previousString.substring(previousCaretPosition - 1)

          println(s"before $beforeCaret after $afterCaret")
          getEditor.setText(beforeCaret + p1.getText + afterCaret)
          getEditor.positionCaret(previousCaretPosition)
        }

        val searcher1: List[T] = functions.search(getEditor.getText())

          if (searcher1.nonEmpty) {

            getItems.clear()
            getItems.addAll(FXCollections.observableArrayList(searcher1: _*))
            show()
          } else {
            hide()
          }

      } else if (p1.getCode == KeyCode.UP || p1.getCode == KeyCode.DOWN) {
        println("change value")
        getEditor.positionCaret(getEditor.getText.size)
      } else if (isShowing) {
        val position: Int = getEditor.getCaretPosition
        if (p1.getCode == KeyCode.LEFT) {
          getEditor.positionCaret(position - 1)
        }
        else if (p1.getCode == KeyCode.RIGHT)
          getEditor.positionCaret(position + 1)
      }
    }
  })

  valueProperty().addListener(new ChangeListener[T] {
    override def changed(p1: ObservableValue[_ <: T], p2: T, p3: T): Unit = println(p2, p3)
  })

  setConverter(new StringConverter[T] {
    override def fromString(p1: String): T = getValue
    override def toString(p1: T): String = functions.internalToString(p1)
  })
}
