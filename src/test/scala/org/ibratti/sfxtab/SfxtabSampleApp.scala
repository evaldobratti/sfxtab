package org.ibratti.sfxtab

import javafx.scene.Scene
import javafx.stage.Stage


import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafxml.core.{NoDependencyResolver, FXMLView}

object SfxtabSampleApp extends JFXApp {
  val inicio = System.currentTimeMillis()

  stage = new PrimaryStage
  stage.setTitle("SFXTab Sample")
  stage.setScene(new Scene(FXMLView(getClass.getResource("HelloView.fxml"), NoDependencyResolver )))
  stage.centerOnScreen()
  stage.show()

  println((System.currentTimeMillis() - inicio).toFloat / 1000)

  implicit val mainStage: Stage = stage
}
