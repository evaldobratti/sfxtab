package org.ibratti.sfxtab

import javafx.application.Application
import javafx.scene.Scene
import javafx.stage.Stage

class SfxtabSampleApp extends Application {

  override def start(stage: Stage): Unit = {

    stage.setTitle("SFXTab Sample")
    stage.setScene(new Scene(SFXTabSampleController()))
    stage.centerOnScreen()
    stage.show()

  }

  //implicit val mainStage: Stage = stage
}
