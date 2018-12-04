/*******************************************************************************
 * Copyright (c) 2010, 2019 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.layout;

import java.util.List;

import org.eclipse.core.runtime.Platform;
import org.eclipse.gef.ConnectionEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.DiagramPlugin;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.tools.api.preferences.SiriusDiagramPreferencesKeys;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;

/**
 * A predicate to identify pinned/fixed edit-parts.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public class IsPinnedPredicate implements Predicate<IGraphicalEditPart> {

    private final List<IDiagramElementEditPart> elementsToKeepFixed;

    /**
     * Default constructor.
     * 
     * @param elementsToKeepFixed
     *            IDiagramElementEditPart which are not actually pinned but have
     *            to stay fixed.
     */
    protected IsPinnedPredicate(List<IDiagramElementEditPart> elementsToKeepFixed) {
        this.elementsToKeepFixed = elementsToKeepFixed;
    }

    /**
     * {@inheritDoc}
     * 
     * @see com.google.common.base.Predicate#apply(java.lang.Object)
     */
    public boolean apply(final IGraphicalEditPart part) {
        boolean result = false;
        if (part instanceof NoteEditPart) {
            result = applyNote((NoteEditPart) part);
        } else {
            result = isPinnedOrKeepFixed(part);
        }
        return result;
    }

    /**
     * Applies this predicate to the given note.
     * 
     * @param part
     *            the NoteEditPart that the predicate should act on
     * @return the value of this predicate when applied to the input
     *         {@code part}
     */
    private boolean applyNote(final NoteEditPart part) {
        boolean result = false;
        boolean connectedToPinnedElement = false;
        for (ConnectionEditPart sourceConn : Iterables.filter(part.getSourceConnections(), ConnectionEditPart.class)) {
            if (sourceConn.getTarget() instanceof IGraphicalEditPart) {
                connectedToPinnedElement = connectedToPinnedElement || isPinnedOrKeepFixed((IGraphicalEditPart) sourceConn.getTarget());
            }
        }
        for (ConnectionEditPart targetConn : Iterables.filter(part.getTargetConnections(), ConnectionEditPart.class)) {
            if (targetConn.getSource() instanceof IGraphicalEditPart) {
                connectedToPinnedElement = connectedToPinnedElement || isPinnedOrKeepFixed((IGraphicalEditPart) targetConn.getSource());
            }
        }
        if (connectedToPinnedElement) {
            result = true;
        } else if (!Platform.getPreferencesService().getBoolean(DiagramPlugin.ID, SiriusDiagramPreferencesKeys.PREF_MOVE_NOTES_DURING_LATOUT.name(), false, null)) {
            result = true;
        }
        return result;
    }

    private boolean isPinnedOrKeepFixed(IGraphicalEditPart part) {
        boolean isPinnedOrKeepFixed = false;
        if (part.resolveSemanticElement() instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) part.resolveSemanticElement();
            isPinnedOrKeepFixed = new PinHelper().isPinned(dDiagramElement) || (elementsToKeepFixed != null && elementsToKeepFixed.contains(part));
        }
        return isPinnedOrKeepFixed;
    }

}
