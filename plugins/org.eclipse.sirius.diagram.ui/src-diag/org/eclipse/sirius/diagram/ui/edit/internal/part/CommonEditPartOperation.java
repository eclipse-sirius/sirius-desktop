/*******************************************************************************
 * Copyright (c) 2009, 2014 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.edit.internal.part;

import java.util.Collections;
import java.util.function.Supplier;

import org.eclipse.draw2d.Graphics;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gef.DragTracker;
import org.eclipse.gef.Request;
import org.eclipse.gef.RequestConstants;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.commands.CompoundCommand;
import org.eclipse.gmf.runtime.diagram.ui.commands.ICommandProxy;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.tools.api.layout.PinHelper;
import org.eclipse.sirius.diagram.tools.internal.commands.PinElementsCommand;
import org.eclipse.sirius.diagram.ui.edit.api.part.IDiagramElementEditPart;
import org.eclipse.sirius.diagram.ui.provider.DiagramUIPlugin;
import org.eclipse.sirius.diagram.ui.tools.api.command.GMFCommandWrapper;
import org.eclipse.sirius.diagram.ui.tools.internal.preferences.SiriusDiagramUiInternalPreferencesKeys;
import org.eclipse.sirius.diagram.ui.tools.internal.render.SiriusRenderedMapModeGraphics;

/**
 * Common operations for edit parts.
 * 
 * @author mchauvin
 */
public final class CommonEditPartOperation {

    /**
     * Avoid instantiation.
     */
    private CommonEditPartOperation() {
    }

    /**
     * Tell the SVG graphics backend what id to use for traceability purposes.
     * 
     * @param graphics
     *            the graphics.
     * @param element
     *            a supplier current semantic element. Will only get invoked if the graphics supports traceability.
     */
    public static void setGraphicsTraceabilityId(Graphics graphics, Supplier<EObject> element) {
        if (graphics instanceof SiriusRenderedMapModeGraphics) {
            ((SiriusRenderedMapModeGraphics) graphics).setGraphicsTraceabilityId(element);
        }
    }

    /**
     * Appends a "pin element" command to the normally returned command if appropriate.
     * 
     * @param self
     *            the edit part
     * @param request
     *            the request which was sent to the edit part
     * @param cmd
     *            the normal command the edit-part returned for the request.
     * @return a command which execute <code>cmd</code> and then optionally marks the edit-part as pinned if this is an
     *         interactive move and auto-pin-on-move is enabled.
     */
    public static Command handleAutoPinOnInteractiveMove(final IDiagramElementEditPart self, final Request request, final Command cmd) {
        Command result = cmd;
        EObject semanticElement = self.resolveSemanticElement();
        if (semanticElement instanceof DDiagramElement) {
            DDiagramElement dDiagramElement = (DDiagramElement) semanticElement;
            if (RequestConstants.REQ_MOVE.equals(request.getType()) && !new PinHelper().isPinned(dDiagramElement) && CommonEditPartOperation.autoPinOnMoveEnabled()
                    && CommonEditPartOperation.isInteractiveMove()) {

                if (PinHelper.allowsPinUnpin(dDiagramElement)) {

                    CompoundCommand cc = new CompoundCommand();
                    cc.add(cmd);

                    PinElementsCommand emfCommand = new PinElementsCommand(Collections.singleton(dDiagramElement));
                    Command pinCmd = new ICommandProxy(new GMFCommandWrapper(self.getEditingDomain(), emfCommand));
                    cc.add(pinCmd);

                    result = cc.unwrap();
                }
            }
        }
        return result;
    }

    private static boolean autoPinOnMoveEnabled() {
        return DiagramUIPlugin.getPlugin().getPreferenceStore().getBoolean(SiriusDiagramUiInternalPreferencesKeys.PREF_AUTO_PIN_ON_MOVE.name());
    }

    /*
     * WARNING. This code tries to detect interactive moves, as opposed to move requests initiated from automatic
     * layout. It is ugly, brittle, and inefficient, but it seems to work and I do not see any other way to do this.
     * Another approach might be to use custom drag trackers to set a flag, but these things are complex and touching
     * them seems like it could have bad side effects if it is not 100% right.
     */
    private static boolean isInteractiveMove() {
        final RuntimeException re = new RuntimeException();
        final StackTraceElement[] stack = re.getStackTrace();
        // We look at several different stack elements in an attempt to make the
        // code more resilient to changes.
        for (int i = 3; i < 6 && i < stack.length; i++) {
            try {
                if (DragTracker.class.isAssignableFrom(Class.forName(stack[i].getClassName()))) {
                    return true;
                }
            } catch (final ClassNotFoundException e) {
                break;
            }
        }
        return false;
    }
}
