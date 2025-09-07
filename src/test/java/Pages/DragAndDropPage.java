package Pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.NoSuchElementException;

public class DragAndDropPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Locators
    private final By sourceLocator = By.xpath("//div[text()='Drag Me' and contains(@class,'cursor-move')]");
    private final By dropContainerLocator = By.xpath("//h3[text()='Dropped!']");
    private final By droppedItemLocator = By.xpath("//h3[text()='Dropped!']/parent::div//div[text()='Drag Me']");

    public DragAndDropPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(60));
    }

    /**
     * Main method: attempts multiple strategies and returns true when the item is detected inside the drop area.
     */
    public boolean performDragAndDrop() {
        WebElement source = wait.until(ExpectedConditions.visibilityOfElementLocated(sourceLocator));
        WebElement target = wait.until(ExpectedConditions.visibilityOfElementLocated(dropContainerLocator));
//
//        // Ensure element is in view and make draggable in case it's disabled in DOM
//        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center'});", source);
//        ((JavascriptExecutor) driver).executeScript(
//                "arguments[0].setAttribute('draggable','true'); arguments[0].style.pointerEvents='auto';", source);
//
//        // Try 1: Selenium Actions.dragAndDrop
//        try {
//            new Actions(driver).dragAndDrop(source, target).pause(Duration.ofSeconds(60)).perform();
//            if (waitForDropped(2)) {
//                System.out.println("Succeeded with Actions.dragAndDrop()");
//                return true;
//            }
//        } catch (Exception e) {
//            System.out.println("Actions.dragAndDrop() failed: " + e.getMessage());
//        }


//        // Try 2: clickAndHold -> moveToElement -> release
//        try {
//            new Actions(driver).moveToElement(source).pause(Duration.ofMillis(200))
//                    .clickAndHold().pause(Duration.ofMillis(200))
//                    .moveToElement(target).pause(Duration.ofMillis(200))
//                    .release().perform();
//            if (waitForDropped(2)) {
//                System.out.println("Succeeded with clickAndHold -> moveToElement");
//                return true;
//            }
//        } catch (Exception e) {
//            System.out.println("clickAndHold->moveToElement failed: " + e.getMessage());
//        }
//
//        // Try 3: clickAndHold -> moveByOffset (calculated from element centers)
//        try {
//            // compute center points
//            Point srcLoc = source.getLocation();
//            Dimension srcSize = source.getSize();
//            int srcCenterX = srcLoc.getX() + srcSize.getWidth() / 2;
//            int srcCenterY = srcLoc.getY() + srcSize.getHeight() / 2;
//
//            Point tgtLoc = target.getLocation();
//            Dimension tgtSize = target.getSize();
//            int tgtCenterX = tgtLoc.getX() + tgtSize.getWidth() / 2;
//            int tgtCenterY = tgtLoc.getY() + tgtSize.getHeight() / 2;
//
//            int offsetX = tgtCenterX - srcCenterX;
//            int offsetY = tgtCenterY - srcCenterY;
//
//            Actions actions = new Actions(driver);
//            actions.moveToElement(source).clickAndHold().pause(Duration.ofMillis(200))
//                    .moveByOffset(offsetX, offsetY).pause(Duration.ofMillis(200))
//                    .release().perform();
//
//            if (waitForDropped(2)) {
//                System.out.println("Succeeded with moveByOffset (offsetX=" + offsetX + ", offsetY=" + offsetY + ")");
//                return true;
//            }
//        } catch (Exception e) {
//            System.out.println("moveByOffset attempt failed: " + e.getMessage());
//        }
//
//        // Try 4: JavaScript HTML5 simulated drag events (fallback)
        try {
            String js = """
                (function(src, dest){
                  // create dataTransfer mock
                  var dataTransfer = {
                    data: {},
                    setData: function(format, value){ this.data[format] = value; },
                    getData: function(format){ return this.data[format]; }
                  };

                  function createEvent(type) {
                    var event = document.createEvent('Event');
                    event.initEvent(type, true, true);
                    event.dataTransfer = dataTransfer;
                    return event;
                  }

                  // dispatch dragstart on source
                  var dragStart = createEvent('dragstart');
                  src.dispatchEvent(dragStart);

                  // dispatch drop on destination
                  var drop = createEvent('drop');
                  drop.dataTransfer = dragStart.dataTransfer;
                  dest.dispatchEvent(drop);

                  // dispatch dragend on source
                  var dragEnd = createEvent('dragend');
                  dragEnd.dataTransfer = dragStart.dataTransfer;
                  src.dispatchEvent(dragEnd);
                })(arguments[0], arguments[1]);
                """;

            ((JavascriptExecutor) driver).executeScript(js, source, target);

            if (waitForDropped(3)) {
                System.out.println("Succeeded with JS HTML5 event simulation");
                return true;
            }
        } catch (Exception e) {
            System.out.println("JS HTML5 simulation failed: " + e.getMessage());
        }

        // Nothing worked
        System.out.println("All drag-and-drop strategies failed.");
        return false;
}

    /**
     * Waits up to given seconds for the dropped element to appear inside the drop zone.
     */
    private boolean waitForDropped(int seconds) {
        try {
            new WebDriverWait(driver, Duration.ofSeconds(seconds))
                    .until(ExpectedConditions.visibilityOfElementLocated(droppedItemLocator));
            return true;
        } catch (TimeoutException te) {
            return false;
        }
    }

    // Verify element is dropped (text inside target container)
    public boolean isDropped() {
        try {
            return driver.findElements(droppedItemLocator).size() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}