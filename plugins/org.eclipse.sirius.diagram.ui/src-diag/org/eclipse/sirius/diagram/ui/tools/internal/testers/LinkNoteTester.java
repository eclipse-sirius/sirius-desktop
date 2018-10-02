/*******************************************************************************
 * Copyright (c) 2018 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Felix Dorner <felix.dorner@gmail.com> - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.testers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.sirius.diagram.ui.internal.edit.parts.SiriusNoteEditPart;

/**
 * Tester to know if an IGraphicalEditPart represents a representation link note.
 */
public class LinkNoteTester extends PropertyTester {

    /**
     * The 'isRepresentationLink' property.
     */
    public static final String PROPERTY_IS_LINK_NOTE = "isRepresentationLink"; //$NON-NLS-1$

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.core.expressions.IPropertyTester#test(java.lang.Object, java.lang.String, java.lang.Object[],
     *      java.lang.Object)
     */
    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (PROPERTY_IS_LINK_NOTE.equals(property)) {
            return receiver instanceof SiriusNoteEditPart && ((SiriusNoteEditPart) receiver).isRepresentationLink();
        }
        throw new IllegalArgumentException("Unknown property: " + property); //$NON-NLS-1$
    }

}
