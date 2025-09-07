package TestRunner;

import Config.BasePage;
import Pages.DragAndDropPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends BasePage {


    @Test
    public void testDragAndDrop() throws InterruptedException {
        driver.get("https://practice.qabrains.com/drag-drop");
        DragAndDropPage dragAndDropPage=new DragAndDropPage(driver);
        boolean success =dragAndDropPage.performDragAndDrop();
        Thread.sleep(3000);
        Assert.assertTrue(success, "Drag-and-drop should succeed and the item must appear in the Dropped! area.");
        Thread.sleep(3000);
        // extra safety: assert isDropped() true
        Assert.assertTrue(dragAndDropPage.isDropped(), "'isDropped()' should return true after successful drag-and-drop.");
        Thread.sleep(3000);
    }
}
