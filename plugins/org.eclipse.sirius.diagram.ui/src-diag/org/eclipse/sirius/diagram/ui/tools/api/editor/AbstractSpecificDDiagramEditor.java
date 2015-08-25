/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.api.editor;

import java.util.Collection;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.common.ui.tools.api.util.EclipseUIUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.ui.business.internal.command.CreateAndStoreGMFDiagramCommand;
import org.eclipse.sirius.diagram.ui.business.internal.dialect.DiagramDialectArrangeOperation;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.ui.business.api.editor.SpecificEditor;
import org.eclipse.sirius.ui.business.api.editor.SpecificEditorInputTranformer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;

/**
 * A specific editor you may extend, which includes a session. The session
 * lifecycle is directly linked the the editor. When the editor opens, it
 * automatically creates a session. When the editor closes, it automatically
 * closes the session.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public abstract class AbstractSpecificDDiagramEditor extends DDiagramEditorImpl implements SpecificEditor {

    private SpecificEditorInputTranformer util = new SpecificEditorInputTranformer() {

        @Override
        protected URI getNewEditorInputURI(DRepresentation representation) {
            final Diagram gmfDiagram = createGMFDiagram((DDiagram) representation);
            return EcoreUtil.getURI(gmfDiagram);
        }

        private Diagram createGMFDiagram(final DDiagram diagram) {

            TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
            domain.getCommandStack().execute(new RecordingCommand(domain) {
                @Override
                protected void doExecute() {
                    DialectManager.INSTANCE.refresh(diagram, new NullProgressMonitor());
                }
            });
            domain.getCommandStack().execute(new CreateAndStoreGMFDiagramCommand(session, (DSemanticDiagram) diagram));

            EclipseUIUtil.displayAsyncExec(new Runnable() {
                public void run() {
                    final IEditorPart activeEditor = EclipseUIUtil.getActiveEditor();
                    if (activeEditor != null) {
                        new DiagramDialectArrangeOperation().arrange(activeEditor, diagram);
                    }
                }
            });
            final Collection<EObject> gmfDiags = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, diagram);
            return (Diagram) gmfDiags.iterator().next();
        }

    };

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        super.dispose();
        util.cleanEnvironment();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void init(final IEditorSite site, final IEditorInput input) throws PartInitException {
        util.init(getViewpointURI(), getDiagramDescriptionName());
        super.init(site, util.transformInput(input, getSelection(site), isSessionStoredInWorkspace()));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setInput(IEditorInput input) {
        super.setInput(util.transformInput(input, getSelection(getSite()), isSessionStoredInWorkspace()));
    }

    private ISelection getSelection(IWorkbenchPartSite site) {
        return site.getWorkbenchWindow().getSelectionService().getSelection();
    }
}
