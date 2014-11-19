package org.ibratti.sfxutils.combobox

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.layout.HBox
import javafx.stage.Stage

class ComboBoxApp extends Application {

  override def start(stage: Stage): Unit = {
    stage.setTitle("SFXTab Sample")


    stage.setScene(new Scene(ComboBoxHBox()))
    stage.centerOnScreen()
    stage.show()
  }

}




