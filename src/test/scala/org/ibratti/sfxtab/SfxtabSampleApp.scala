package org.ibratti.sfxtab

import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage


import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafxml.core.{DependenciesByType, NoDependencyResolver, FXMLView}

object SfxtabSampleApp extends JFXApp {
  val inicio = System.currentTimeMillis()

  stage = new PrimaryStage
  stage.setTitle("SFXTab Sample")
  stage.setScene(FXMLLoader.load(getClass.getResource("HelloView.fxml")))
  stage.centerOnScreen()
  stage.show()

  println((System.currentTimeMillis() - inicio).toFloat / 1000)

  implicit val mainStage: Stage = stage
}
