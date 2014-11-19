package org.ibratti.sfxutils.tabs

import java.net.URL
import java.util.ResourceBundle
import javafx.fxml.{FXML, FXMLLoader, Initializable}
import javafx.scene.layout.AnchorPane

/**
 * Created by evaldo on 04/10/14.
 */
object SFXTabSampleController {
  def apply() = {
    val resource = classOf[SFXTabSampleController].getResource("SfxtabSampleApp.fxml")
    val root: AnchorPane = FXMLLoader.load(resource).asInstanceOf[AnchorPane]
    root
  }
}

class SFXTabSampleController extends Initializable {
  @FXML var tab: StackableTabPane = _
  override def initialize(p1: URL, p2: ResourceBundle): Unit = {

  }

  def testClick() = {
    tab.newTabFromView(new HelloController)
  }
}

