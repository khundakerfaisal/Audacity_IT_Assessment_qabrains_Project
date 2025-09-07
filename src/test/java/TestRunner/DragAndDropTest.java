package TestRunner;

import Config.BasePage;
import Pages.DragAndDropPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DragAndDropTest extends BasePage {

    @Test(priority = 1)
    public void testPageElementsVisibility() {
        DragAndDropPage dragAndDropPage=new DragAndDropPage(driver);
        dragAndDropPage.navigateToPage();

        // Verify drag element is visible
        Assert.assertTrue(dragAndDropPage.isDragElementVisible(),
                "Drag element should be visible");

        // Verify drop target is visible
        Assert.assertTrue(dragAndDropPage.isDropTargetVisible(),
                "Drop target should be visible");

        System.out.println("Drag element text: " + dragAndDropPage.getDragElementText());
        System.out.println("Drop target text: " + dragAndDropPage.getDropTargetText());
    }

    @Test(priority = 2)
    public void testDragAndDropFunctionality() {
        DragAndDropPage dragAndDropPage=new DragAndDropPage(driver);
        dragAndDropPage.navigateToPage();

        // Verify elements are present before performing action
        Assert.assertTrue(dragAndDropPage.isDragElementVisible(),
                "Drag element should be visible before drag operation");
        Assert.assertTrue(dragAndDropPage.isDropTargetVisible(),
                "Drop target should be visible before drag operation");

        // Perform drag and drop
        dragAndDropPage.performDragAndDrop();

        // Verify drag and drop was successful
        Assert.assertTrue(dragAndDropPage.isDragDropSuccessful(),
                "Drag and drop operation should be successful");

        System.out.println("Drag and drop operation completed successfully");
    }

    @Test(priority = 3)
    public void testDragAndDropAlternativeMethod() {
        DragAndDropPage dragAndDropPage=new DragAndDropPage(driver);
        dragAndDropPage.navigateToPage();

        // Test alternative drag and drop method
        dragAndDropPage.performDragAndDropAlternative();

        // Verify success
        Assert.assertTrue(dragAndDropPage.isDragDropSuccessful(),
                "Alternative drag and drop operation should be successful");

        System.out.println("Alternative drag and drop method completed successfully");
    }
}
