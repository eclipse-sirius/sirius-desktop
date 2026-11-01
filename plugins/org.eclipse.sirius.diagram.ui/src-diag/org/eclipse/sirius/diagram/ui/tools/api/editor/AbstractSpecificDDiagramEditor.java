/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.diagram.ui.tools.api.editor;

import java.util.Collection;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gmf.runtime.diagram.ui.editparts.DiagramEditPart;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.gmf.runtime.notation.View;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.sirius.business.api.session.CustomDataConstants;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.internal.dialect.NotYetOpenedDiagramAdapter;
import org.eclipse.sirius.diagram.ui.business.api.view.SiriusLayoutDataManager;
import org.eclipse.sirius.diagram.ui.business.internal.command.CreateAndStoreGMFDiagramCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.editor.DDiagramEditorImpl;
import org.eclipse.sirius.diagram.ui.tools.internal.layout.LayoutUtil;
import org.eclipse.sirius.diagram.ui.tools.internal.part.OffscreenEditPartFactory;
import org.eclipse.sirius.ui.business.api.editor.SpecificEditor;
import org.eclipse.sirius.ui.business.api.editor.SpecificEditorInputTranformer;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

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
            Collection<EObject> gmfDiags = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, diagram);
            boolean isNew = gmfDiags.isEmpty();
            if (isNew) {
                TransactionalEditingDomain domain = session.getTransactionalEditingDomain();
                domain.getCommandStack().execute(new CreateAndStoreGMFDiagramCommand(session, (DSemanticDiagram) diagram));
                gmfDiags = session.getServices().getCustomData(CustomDataConstants.GMF_DIAGRAMS, diagram);
            }

            // creating GMF diagram
            org.eclipse.gmf.runtime.notation.Diagram gmfDiagram = (org.eclipse.gmf.runtime.notation.Diagram) gmfDiags.iterator().next();
            if (gmfDiagram != null) {
                arrangeIfRequired(diagram, gmfDiagram);
            }
            return gmfDiagram;
        }

        public void arrangeIfRequired(final DRepresentation representation, org.eclipse.gmf.runtime.notation.Diagram gmfDiagram) {
            if (representation.eAdapters().contains(NotYetOpenedDiagramAdapter.INSTANCE)) {
                DiagramEditPart editpart = OffscreenEditPartFactory.getInstance().createDiagramEditPart(gmfDiagram, PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell());
                if (editpart != null) {
                    LayoutUtil.arrange(editpart);
                }
                representation.eAdapters().remove(NotYetOpenedDiagramAdapter.INSTANCE);

                // Not to layout
                for (Object child : gmfDiagram.getChildren()) {
                    if (child instanceof View) {
                        ((View) child).eAdapters().add(SiriusLayoutDataManager.INSTANCE.getAdapterMarker());
                    }
                }
            }
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
