/*******************************************************************************
 * Copyright (c) 2010, 2014 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.swtbot.sequence;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.sirius.diagram.sequence.business.internal.layout.LayoutConstants;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.CombinedFragmentEditPart;
import org.eclipse.sirius.diagram.sequence.ui.tool.internal.edit.part.ExecutionEditPart;
import org.eclipse.sirius.tests.swtbot.sequence.condition.CheckNumberOfDescendants;
import org.eclipse.swtbot.eclipse.gef.finder.widgets.SWTBotGefEditPart;
import org.junit.Assert;

/**
 * Tests only zoom and creation with single/double click, others features to
 * test are Junit Plugin Tests.
 * 
 * @author edugueperoux, smonnier
 */
public class CombinedFragmentsWithInclusionCreationTests extends AbstractCombinedFragmentSequenceTests {

    /**
     * Test that Combined Fragment creation with two click covering all existing
     * event ends shift them correctly.
     */
    public void testCombinedFragmentCreationWithTwoClickToCoverLifelineFromAToEOnAllEventBelow() {
        Point start = new Point(instanceRoleEditPartABounds.x - 20, e1Bounds.y - LayoutConstants.EXECUTION_CHILDREN_MARGIN * 2);
        Point finish = new Point(instanceRoleEditPartEBounds.right() + 20, secondCombinedFragmentBounds.bottom() + 40);

        SWTBotGefEditPart newCombinedFragmentBot = createCombinedFragmentWithResult(start, finish);
        bot.waitUntil(new CheckNumberOfDescendants(sequenceDiagramBot, CombinedFragmentEditPart.class, 3));

        Assert.assertNotNull("creation to cover lifeline existing CombinedFragment should be possible", newCombinedFragmentBot);

        // Checks events inside the newly created CF are correctly shifted
        Rectangle newCombinedFragmentBounds = editor.getBounds(newCombinedFragmentBot);
        Assert.assertEquals(start.y, newCombinedFragmentBounds.y, 1);
        Assert.assertEquals(finish.y, newCombinedFragmentBounds.bottom(), 1);

        // Checks events under the newly created IU are correclty shifted
        Point delta = new Point(10, LayoutConstants.COMBINED_FRAGMENT_TITLE_HEIGHT - LayoutConstants.EXECUTION_CHILDREN_MARGIN);

        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(delta), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(delta), editor.getBounds(secondCombinedFragmentBot));

        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds.getTranslated(delta));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds.getTranslated(delta));

        validateOrdering(15);

        // Undo
        undo();
        assertNotChange();

        // Redo
        redo();
        Assert.assertEquals(firstCombinedFragmentBounds.getTranslated(delta), editor.getBounds(firstCombinedFragmentBot));
        Assert.assertEquals(secondCombinedFragmentBounds.getTranslated(delta), editor.getBounds(secondCombinedFragmentBot));

        assertExecutionHasValidScreenBounds((ExecutionEditPart) e1Bot.part(), e1Bounds.getTranslated(delta));
        assertExecutionHasValidScreenBounds((ExecutionEditPart) e2Bot.part(), e2Bounds.getTranslated(delta));

        Assert.assertEquals(newCombinedFragmentBounds, editor.getBounds(newCombinedFragmentBot));
    }

}
