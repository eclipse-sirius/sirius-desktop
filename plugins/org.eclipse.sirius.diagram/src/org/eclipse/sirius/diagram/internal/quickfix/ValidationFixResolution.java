/*******************************************************************************
 * Copyright (c) 2007, 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.internal.quickfix;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactory;
import org.eclipse.sirius.diagram.tools.api.command.IDiagramCommandFactoryProvider;
import org.eclipse.sirius.ui.business.api.dialect.DialectEditor;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.validation.ValidationFix;
import org.eclipse.sirius.viewpoint.description.validation.ViewValidationRule;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IMarkerResolution;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.ide.IGotoMarker;

/**
 * QuickFix resolution executing a {@link ValidationFix} description.
 * 
 * @author cbrun
 * 
 */
public class ValidationFixResolution implements IMarkerResolution {

    private ValidationFix fix;

    /**
     * Create a new {@link ValidationFixResolution} from a {@link ValidationFix}
     * .
     * 
     * @param fix
     *            {@link ValidationFix} to execute.
     */
    public ValidationFixResolution(ValidationFix fix) {
        this.fix = fix;
    }

    public String getLabel() {
        return fix.getName();
    }

    public void run(IMarker marker) {
        IResource airdFile = marker.getResource();
        if (airdFile instanceof IFile) {
            try {
                tryToOpenEditor(airdFile, marker);
            } catch (PartInitException e) {
                SiriusPlugin.getDefault().error("Can't open editor for " + airdFile, e);
            }
        }
    }

    private void tryToOpenEditor(IResource airdFile, IMarker marker) throws PartInitException {
        IEditorPart editor = IDE.openEditor(PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage(), (IFile) airdFile);
        if (editor instanceof IGotoMarker) {
            ((IGotoMarker) editor).gotoMarker(marker);
            EObject fixTarget = getFixTarget(editor, marker, airdFile);
            if (fixTarget != null) {
                executeFix(editor, fixTarget);
                revalidate(editor);
            }
        }
    }

    private void revalidate(IEditorPart editor) {
        if (editor instanceof DialectEditor) {
            ((DialectEditor) editor).validateRepresentation();
        }

    }

    private EObject getFixTarget(IEditorPart editor, IMarker marker, IResource airdFile) {
        String elementID = marker.getAttribute(org.eclipse.gmf.runtime.common.ui.resources.IMarker.ELEMENT_ID, null);
        URI airdEMFResourceURI = URI.createPlatformResourceURI(airdFile.getFullPath().toString(), true);
        airdEMFResourceURI = airdEMFResourceURI.appendFragment(elementID);
        ResourceSet set = ((EditingDomain) editor.getAdapter(EditingDomain.class)).getResourceSet();
        EObject markerTarget = set.getEObject(airdEMFResourceURI, false);
        if (markerTarget instanceof View) {
            markerTarget = ((View) markerTarget).getElement();
            if (markerTarget instanceof DSemanticDecorator && !isViewValidationRule()) {
                markerTarget = ((DSemanticDecorator) markerTarget).getTarget();
            }
        }
        return markerTarget;
    }

    private DDiagram getDiagram(IEditorPart editor) {
        if (editor instanceof DialectEditor) {
            return (DDiagram) ((DialectEditor) editor).getRepresentation();
        }
        return null;
    }

    private boolean isViewValidationRule() {
        return (fix.eContainer() instanceof ViewValidationRule);
    }

    private void executeFix(IEditorPart editor, EObject fixTarget) {
        TransactionalEditingDomain domain = (TransactionalEditingDomain) editor.getAdapter(TransactionalEditingDomain.class);
        IDiagramCommandFactory commandFactory = getDiagramCommandFactory(editor, domain);

        if (commandFactory != null && fixTarget != null) {
            Command fixCommand = commandFactory.buildQuickFixOperation(fix, fixTarget, getDiagram(editor));

            // Set the RefreshEditorsListener in forceRefresh mode
            EObject semanticTarget = getSemanticTarget(fixTarget);
            Session session = SessionManager.INSTANCE.getSession(semanticTarget);
            if (session != null) {
                session.getRefreshEditorsListener().setForceRefresh(true);
            }

            // Execute the quick fix command
            domain.getCommandStack().execute(fixCommand);
        }
    }

    private EObject getSemanticTarget(EObject fixTarget) {
        // The fix target could be the DDiagramElement or the semantic element
        // (a rule could be a ViewValidationRule or a SemanticValidationRule
        EObject semanticTarget = fixTarget;
        if (semanticTarget instanceof DSemanticDecorator) {
            semanticTarget = ((DSemanticDecorator) semanticTarget).getTarget();
        }
        return semanticTarget;
    }

    private IDiagramCommandFactory getDiagramCommandFactory(IEditorPart editor, TransactionalEditingDomain domain) {
        final Object adapter = editor.getAdapter(IDiagramCommandFactoryProvider.class);
        final IDiagramCommandFactoryProvider diagramCmdFactoryProvider = (IDiagramCommandFactoryProvider) adapter;
        return diagramCmdFactoryProvider.getCommandFactory(domain);
    }

}
