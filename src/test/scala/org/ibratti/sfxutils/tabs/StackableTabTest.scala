package org.ibratti.sfxutils.tabs

import java.lang.reflect.Method
import javafx.scene.Parent
import javafx.scene.control.{Label, Separator, TabPane}
import javafx.scene.layout.VBox

import org.scalamock.scalatest.MockFactory
import org.scalatest.{BeforeAndAfter, FunSuite}

class StackableTabTest extends FunSuite with BeforeAndAfter with MockFactory {

  var root: InnerPanel = _
  var rootView: Parent = _

  var over: InnerPanel = _
  var overView: Parent = _
//why does it throw an exception if i use before from trait?
  def beforee() {
  //before {
    root = mock[InnerPanel]
    rootView = new Label()

    root.name _ expects() anyNumberOfTimes()  returning "Root"
    root.view _ expects() anyNumberOfTimes()  returning rootView

    over = mock[InnerPanel]
    overView = new Label()
    over.name _ expects() anyNumberOfTimes()  returning "Over"
    over.view _ expects() anyNumberOfTimes()  returning overView
  }

  test("Creates a tab from a root") {
    beforee()
    val tab: StackableTab = new StackableTab(root)

    assert(tab.getText === "Root")
    assert(tab.whereAmILbl.getText === "Você está em: Root")
    assert(tab.getContent.isInstanceOf[VBox])

    val vbox = tab.getContent.asInstanceOf[VBox]
    assert(vbox.getChildren.get(0).isInstanceOf[Label])
    assert(vbox.getChildren.get(1).isInstanceOf[Separator])
    assert(vbox.getChildren.get(2) === rootView)

    assert(tab.stackedScenes.size === 1)
    assert(tab.stackedScenes.top === root)
  }

  test("Open a new window over the root") {
    beforee()
    val param: Option[Int] = Option(1)
    (over.openingWithValue _).expects(param) returning Unit

    val tab: StackableTab = new StackableTab(root)
    tab.openWindow(over, param)

    assert(tab.stackedScenes.size === 2)
    assert(tab.actualContent === overView)
    assert(tab.getText === "Over")
    assert(tab.whereAmILbl.getText === "Você está em: Root > Over")
  }

  test("When an over window is closed, the previous window gets a message") {
    beforee()
    (over.openingWithValue _) stubs(*) returning Unit
    (root.returningFromWithValue _) expects(Option(22)) returning Unit

    val tab: StackableTab = new StackableTab(root)
    tab.openWindow(over, None)
    tab.closeWindow(Option(22))
  }

  test("Closes tab when closing root view") {
    beforee()
    val tab: StackableTab = new StackableTab(root)
    val tabPane: TabPane = new TabPane
    tabPane.getTabs.add(tab)

    hackTabToHaveATabPane(tab, tabPane)

    tab.closeWindow(Option(1))

    assert(tab.stackedScenes.size === 0)
    assert(tabPane.getTabs.isEmpty)
  }

  def hackTabToHaveATabPane(tab: StackableTab, tabPane: TabPane) {
    val setTabPane: Method = tab.getClass.getSuperclass.getDeclaredMethod("setTabPane", classOf[TabPane])
    setTabPane.setAccessible(true)
    setTabPane.invoke(tab, tabPane)
  }
}
