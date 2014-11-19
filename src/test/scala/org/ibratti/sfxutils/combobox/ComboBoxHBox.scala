package org.ibratti.sfxutils.combobox

import javafx.scene.control.Button
import javafx.scene.layout.HBox

object ComboBoxHBox {
   def apply() = {
     val searcher: ComboBoxSearcheable[ComplexObject] = new ComboBoxSearcheable[ComplexObject](
       new ComboBoxSearcheableFunctions[ComplexObject] {
         override def search(value: String): List[ComplexObject] = values.filter(_.stringField.startsWith(value))

         override def toString(value: ComplexObject): String = value.stringField
       })
     searcher.setId("searcherCb")
     val button = new Button("Bah")
     val box: HBox = new HBox()
     box.getChildren.addAll(searcher, button)
     searcher.setPrefWidth(500)
     box
   }

   val values = List(
     ComplexObject("a"),
     ComplexObject("ab"),
     ComplexObject("abc"),
     ComplexObject("abcd"),
     ComplexObject("abcde"),
     ComplexObject("abcdef"),
     ComplexObject("abcdefg")
   )
 }
