/*******************************************************************************
 * Copyright (c) 2014 THALES GLOBAL SERVICES and others. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tests.support.api;

import org.eclipse.sirius.ui.business.api.dialect.DialectEditorDialogFactory;
import org.eclipse.swt.widgets.Shell;

/**
 * A DialectEditorDialogFactory used for test.
 * 
 * @author <a href="mailto:esteban.dugueperoux@obeo.fr">Esteban Dugueperoux</a>
 */
public class DummyDialectEditorDialogFactory implements DialectEditorDialogFactory {

    private int nbEditorWillBeClosedInformationDialogCalls;

    private int nbInformUserOfEventCalls;

    @Override
    public void editorWillBeClosedInformationDialog(Shell parent) {
        nbEditorWillBeClosedInformationDialogCalls++;
    }

    @Override
    public void informUserOfEvent(int severity, String message) {
        nbInformUserOfEventCalls++;
    }

    /**
     * Get the number of call to
     * {DialectEditorDialogFactory#editorWillBeClosedInformationDialog()} done.
     * 
     * @return the number of call to
     *         {DialectEditorDialogFactory#editorWillBeClosedInformationDialog
     *         ()} done
     */
    public int getNbEditorWillBeClosedInformationDialogCalls() {
        return nbEditorWillBeClosedInformationDialogCalls;
    }

    /**
     * Get the number of call to
     * {DialectEditorDialogFactory#informUserOfEvent()} done.
     * 
     * @return the number of call to
     *         {DialectEditorDialogFactory#informUserOfEvent ()} done
     */
    public int getNbInformUserOfEventCalls() {
        return nbInformUserOfEventCalls;
    }

}
