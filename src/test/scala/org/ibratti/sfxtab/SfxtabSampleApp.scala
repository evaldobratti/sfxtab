package org.ibratti.sfxtab

import javafx.scene.Scene
import javafx.stage.Stage


import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

object SfxtabSampleApp extends JFXApp {
  stage = new PrimaryStage
  stage.setTitle("SFXTab Sample")
  stage.setScene(new Scene(SFXTabSampleController()))
  stage.centerOnScreen()
  stage.show()

  implicit val mainStage: Stage = stage
}
