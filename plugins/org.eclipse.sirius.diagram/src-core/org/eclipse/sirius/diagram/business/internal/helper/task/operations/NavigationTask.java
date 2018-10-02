/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009, 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.business.internal.helper.task.operations;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.business.internal.helper.task.operations.AbstractOperationTask;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DDiagramElement;
import org.eclipse.sirius.diagram.description.DiagramDescription;
import org.eclipse.sirius.diagram.description.tool.Navigation;
import org.eclipse.sirius.diagram.tools.api.command.view.CreateDiagramWithInitialOperation;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.tools.api.command.CommandContext;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * A task which navigate to diagrams.
 *
 * @author smonnier
 *
 */
public class NavigationTask extends AbstractOperationTask {

    private final Navigation operation;

    private final UICallBack uiCallback;

    /**
     * Default constructor.
     *
     * @param context
     *            the current context.
     * @param extPackage
     *            the extended package.
     * @param operation
     *            the {@link DoubleClickNavigation} operation
     * @param interpreter
     *            the interpreter to use.
     * @param uiCallback
     *            the {@link UICallBack}
     */
    public NavigationTask(CommandContext context, ModelAccessor extPackage, Navigation operation, IInterpreter interpreter, UICallBack uiCallback) {
        super(context, extPackage, interpreter);
        this.operation = operation;
        this.uiCallback = uiCallback;
    }

    @Override
    public void execute() {
        if (operation != null) {
            EObject element = getElement();
            if (element instanceof DDiagramElement) {
                DDiagramElement ddiagramElement = (DDiagramElement) element;
                for (EObject semanticEObject : ddiagramElement.getSemanticElements()) {
                    if (openRepresentation(semanticEObject)) {
                        return;
                    }
                }
            } else {
                openRepresentation(element);
            }
        }
    }

    /**
     * Try to open the representation associated with the diagram element.
     *
     * @param semanticEObject
     * @return <code>true</code> if the representation was opened,
     *         <code>false</code> otherwise
     */
    private boolean openRepresentation(final EObject semanticEObject) {
        DiagramDescription diagramDescriptionToOpen = operation.getDiagramDescription();
        final Session session = SessionManager.INSTANCE.getSession(semanticEObject);

        // Open the new diagram
        Collection<DRepresentation> otherRepresentations = DialectManager.INSTANCE.getRepresentations(semanticEObject, session);
        DRepresentation findOpenableRepresentation = findOpenableRepresentation(otherRepresentations, diagramDescriptionToOpen, session);

        boolean representationOpened = false;

        if (findOpenableRepresentation != null) {
            uiCallback.openRepresentation(session, findOpenableRepresentation);
            representationOpened = true;
        } else if (operation.isCreateIfNotExistent()) {
            // Create a new diagram
            CreateDiagramWithInitialOperation createDiagramWithInitialOperation = new CreateDiagramWithInitialOperation(diagramDescriptionToOpen, semanticEObject, uiCallback,
                    new NullProgressMonitor());
            createDiagramWithInitialOperation.execute();
            // Open the new diagram
            otherRepresentations = DialectManager.INSTANCE.getRepresentations(semanticEObject, session);
            findOpenableRepresentation = findOpenableRepresentation(otherRepresentations, diagramDescriptionToOpen, session);
            if (findOpenableRepresentation != null) {
                uiCallback.openRepresentation(session, findOpenableRepresentation);
                representationOpened = true;
            }
        }
        return representationOpened;
    }

    /**
     * Find a {@link DRepresentation} among otherRepresentations that has the
     * {@link DiagramDescription} diagramDescriptionToOpen
     *
     * @param otherRepresentations
     *            the Collection of {@link DRepresentation} to investigate
     * @param diagramDescriptionToOpen
     *            the type of {@link DiagramDescription} we want to open
     * @param session
     *            the current session
     * @return the matching {@link DRepresentation} if found
     */
    private DRepresentation findOpenableRepresentation(final Collection<DRepresentation> otherRepresentations, final DiagramDescription diagramDescriptionToOpen, final Session session) {
        for (final DRepresentation representation : otherRepresentations) {
            if (representation instanceof DDiagram) {
                DDiagram ddiagram = (DDiagram) representation;
                if (ddiagram.getDescription() != null && ddiagram.getDescription().equals(diagramDescriptionToOpen)) {
                    return representation;
                }
            }
        }
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }

    /**
     * Return the element to move.
     *
     * @return the element to move.
     */
    private EObject getElement() {
        return context.getCurrentTarget();
    }

}
