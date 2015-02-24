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
package org.eclipse.sirius.tests.unit.diagram.layoutingmode;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.sirius.business.api.session.SessionStatus;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DiagramPackage;
import org.eclipse.sirius.tests.support.api.SiriusDiagramTestCase;
import org.eclipse.sirius.tests.support.api.TestsUtil;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.ui.IEditorPart;

/**
 * An abstract TestCase providing facilites for manipulating and testing
 * LayoutingMode.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 */
public class AbstractLayoutingModeTest extends SiriusDiagramTestCase {

    protected IEditorPart editor;

    protected DDiagram diagram;

    /**
     * Closes and opens the current editor.
     */
    protected void reopenEditor() {
        DialectUIManager.INSTANCE.closeEditor(editor, false);
        TestsUtil.synchronizationWithUIThread();
        editor = DialectUIManager.INSTANCE.openEditor(session, diagram, new NullProgressMonitor());
        TestsUtil.synchronizationWithUIThread();
    }

    /**
     * Activates/Deactivate the LayoutingMode according to the given value.
     * 
     * @param layoutingModeShouldBeActivated
     *            indicates whether layouting mode should be activated or
     *            disabled
     */
    protected void setLayoutingMode(final boolean layoutingModeShouldBeActivated) {
        TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
        Command cmd = SetCommand.create(domain, diagram, DiagramPackage.Literals.DDIAGRAM__IS_IN_LAYOUTING_MODE, layoutingModeShouldBeActivated);
        domain.getCommandStack().execute(cmd);
    }

    /**
     * Ensures that a tool has been applied or not (according to the given
     * shouldHaveBeenApplied value), by checking the Session's status.
     * 
     * @param shouldHaveBeenApplied
     *            indicates whether a tool should have been applied or not
     */
    protected void assertToolHasBeenApplied(boolean shouldHaveBeenApplied) {
        String message = null;
        if (shouldHaveBeenApplied) {
            message = "Tool should have been applied as Layouting mode is disabled";
        } else {
            message = "Tool should not have been applied as Layouting mode is activated";
        }
        assertEquals(message, shouldHaveBeenApplied, session.getStatus().equals(SessionStatus.DIRTY));
    }
}
