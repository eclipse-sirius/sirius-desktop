/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.internal.providers;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.gmf.runtime.diagram.core.providers.AbstractViewProvider;
import org.eclipse.gmf.runtime.diagram.core.util.ViewType;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.diagram.ui.internal.view.factories.SiriusNoteViewFactory;

/**
 * Specific view provider for Notes created from the Palette.
 * 
 * @author <a href="mailto:axel.richard@obeo.fr">Axel Richard</a>
 *
 */
public class SiriusNoteViewProvider extends AbstractViewProvider {

    @Override
    protected Class<?> getNodeViewClass(IAdaptable semanticAdapter, View containerView, String semanticHint) {
        // Inspired from
        // org.eclipse.gmf.runtime.diagram.ui.providers.internal.DiagramViewProvider.getNodeViewClass(IAdaptable, View,
        // String) for original GMF NoteViewFactory.
        if (ViewType.NOTE.equals(semanticHint)) {
            return SiriusNoteViewFactory.class;
        }
        return null;
    }
}
