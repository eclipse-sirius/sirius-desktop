/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.tools.internal.menu;

import java.util.Iterator;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.gmf.runtime.diagram.ui.editparts.NoteEditPart;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.parts.DiagramDocumentEditor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.helper.SiriusUtil;
import org.eclipse.sirius.business.api.query.IEdgeMappingQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagramElementContainer;
import org.eclipse.sirius.diagram.DEdge;
import org.eclipse.sirius.diagram.DNode;
import org.eclipse.sirius.diagram.DNodeListElement;
import org.eclipse.sirius.diagram.description.EdgeMapping;
import org.eclipse.sirius.diagram.description.tool.DiagramCreationDescription;
import org.eclipse.sirius.diagram.tools.internal.actions.CreateRepresentationFromRepresentationCreationDescription;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;

/**
 * This menu contribution add a "create" menu in the DDiagram diagram editor.
 * 
 * @author cbrun
 * 
 */
public class SubDiagramMenu implements IContributionItemProvider {

    private static final String CREATE_REPRESENTATION_GROUP_SEPARATOR = "createRepresentationGroup";

    /**
     * {@inheritDoc}
     */
    public void contributeToActionBars(final IActionBars arg0, final IWorkbenchPartDescriptor arg1) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     */
    public void contributeToPopupMenu(final IMenuManager menu, final IWorkbenchPart part) {

        if (part instanceof DiagramDocumentEditor) {

            final DiagramDocumentEditor diagrampart = (DiagramDocumentEditor) part;

            final EditPart editpart = diagrampart.getDiagramGraphicalViewer().getFocusEditPart();

            if (editpart instanceof IGraphicalEditPart && !(editpart instanceof NoteEditPart)) {
                final IGraphicalEditPart curPart = (IGraphicalEditPart) editpart;
                final EObject eObj = curPart.resolveSemanticElement();
                // Deletion of semantic elements when automatic
                // refresh is off causes exceptions
                // ensuring that semantic element is valid
                if (eObj instanceof DRepresentationElement) {
                    EObject target = ((DRepresentationElement) eObj).getTarget();
                    if (target == null || target.eResource() == null) {
                        return;
                    }
                }
                if (SiriusUtil.isFromSirius(eObj)) {
                    final IMenuManager navigate = (IMenuManager) menu.find("navigateMenu");
                    final Separator createGroup = new Separator(CREATE_REPRESENTATION_GROUP_SEPARATOR);
                    navigate.add(createGroup);
                    if (eObj instanceof DNode) {
                        createDetailsActions((DNode) eObj, navigate, diagrampart.getEditingDomain(), curPart);
                    } else if (eObj instanceof DNodeListElement) {
                        createDetailsActions((DNodeListElement) eObj, navigate, diagrampart.getEditingDomain(), curPart);
                    } else if (eObj instanceof DEdge) {
                        createDetailsActions((DEdge) eObj, navigate, diagrampart.getEditingDomain(), curPart);
                    } else if (eObj instanceof DDiagramElementContainer) {
                        createDetailsActions((DDiagramElementContainer) eObj, navigate, diagrampart.getEditingDomain(), curPart);
                    }
                } else {
                    // no focused edit part
                }
            }
        }

    }

    private void createDetailsActions(final DDiagramElementContainer container, final IMenuManager navigate, final TransactionalEditingDomain editingDomain, final IGraphicalEditPart curPart) {
        if (container.getActualMapping() != null) {
            final Iterator<RepresentationCreationDescription> it = container.getActualMapping().getDetailDescriptions().iterator();
            while (it.hasNext()) {
                final RepresentationCreationDescription desc = it.next();
                final String precondition = desc.getPrecondition();
                boolean append = true;
                final EList<EObject> semanticElements = container.getSemanticElements();
                if (semanticElements != null && !semanticElements.isEmpty()) {
                    final Session session = SessionManager.INSTANCE.getSession(semanticElements.get(0));
                    if (!isFromActiveSirius(session, desc.getRepresentationDescription())) {
                        append = false;
                    }
                }
                if (precondition != null && !StringUtil.isEmpty(precondition.trim())) {
                    append = false;
                    final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(container);
                    try {
                        append = interpreter.evaluateBoolean(container.getTarget(), precondition);
                    } catch (final EvaluationException e) {
                        // do nothing
                    }
                }
                if (append) {
                    navigate.appendToGroup(CREATE_REPRESENTATION_GROUP_SEPARATOR, new CreateRepresentationFromRepresentationCreationDescription(desc, container, editingDomain, curPart));
                }
            }
        }

    }

    private void createDetailsActions(final DNode viewNode, final IMenuManager navigate, final TransactionalEditingDomain editingDomain, final IGraphicalEditPart curPart) {
        if (viewNode.getActualMapping() != null) {
            final Iterator<RepresentationCreationDescription> it = viewNode.getActualMapping().getDetailDescriptions().iterator();
            while (it.hasNext()) {
                final RepresentationCreationDescription repCreation = it.next();
                final RepresentationCreationDescription desc = repCreation;
                final String precondition = desc.getPrecondition();
                boolean append = true;
                final EList<EObject> semanticElements = viewNode.getSemanticElements();
                if (semanticElements != null && !semanticElements.isEmpty()) {
                    final Session session = SessionManager.INSTANCE.getSession(semanticElements.get(0));
                    if (!isFromActiveSirius(session, desc.getRepresentationDescription())) {
                        append = false;
                    }
                }
                if (precondition != null && !StringUtil.isEmpty(precondition.trim())) {
                    append = false;
                    final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(viewNode);
                    try {
                        append = interpreter.evaluateBoolean(viewNode.getTarget(), precondition);
                    } catch (final EvaluationException e) {
                        // do nothing
                    }
                }
                if (append) {
                    navigate.appendToGroup(CREATE_REPRESENTATION_GROUP_SEPARATOR, new CreateRepresentationFromRepresentationCreationDescription(desc, viewNode, editingDomain, curPart));
                }
            }

        }
    }

    private void createDetailsActions(final DNodeListElement viewNodeListElement, final IMenuManager navigate, final TransactionalEditingDomain editingDomain, final IGraphicalEditPart curPart) {
        if (viewNodeListElement.getActualMapping() != null) {
            final Iterator<RepresentationCreationDescription> it = viewNodeListElement.getActualMapping().getDetailDescriptions().iterator();
            while (it.hasNext()) {
                final RepresentationCreationDescription desc = it.next();
                final String precondition = desc.getPrecondition();
                boolean append = true;
                final EList<EObject> semanticElements = viewNodeListElement.getSemanticElements();
                if (semanticElements != null && !semanticElements.isEmpty()) {
                    final Session session = SessionManager.INSTANCE.getSession(semanticElements.get(0));
                    if (!isFromActiveSirius(session, desc.getRepresentationDescription())) {
                        append = false;
                    }
                }
                if (precondition != null && !StringUtil.isEmpty(precondition.trim())) {
                    append = false;
                    final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(viewNodeListElement);
                    try {
                        append = interpreter.evaluateBoolean(viewNodeListElement.getTarget(), precondition);
                    } catch (final EvaluationException e) {
                        // do nothing
                    }
                }
                if (append) {
                    navigate.appendToGroup(CREATE_REPRESENTATION_GROUP_SEPARATOR, new CreateRepresentationFromRepresentationCreationDescription(desc, viewNodeListElement, editingDomain, curPart));
                }
            }

        }
    }

    private boolean isFromActiveSirius(final Session session, final RepresentationDescription description) {
        final Viewpoint vp = ViewpointRegistry.getInstance().getViewpoint(description);
        return vp != null && session.getSelectedViewpoints(false).contains(vp);
    }

    private void createDetailsActions(final DEdge viewedge, final IMenuManager navigate, final TransactionalEditingDomain editingDomain, final IGraphicalEditPart curPart) {
        Option<EdgeMapping> edgeMapping = new IEdgeMappingQuery(viewedge.getActualMapping()).getEdgeMapping();
        if (edgeMapping.some()) {
            final Iterator<RepresentationCreationDescription> it = edgeMapping.get().getDetailDescriptions().iterator();
            while (it.hasNext()) {
                final RepresentationCreationDescription repCreation = it.next();
                if (repCreation instanceof DiagramCreationDescription) {
                    final DiagramCreationDescription desc = (DiagramCreationDescription) repCreation;
                    final String precondition = desc.getPrecondition();
                    boolean append = true;
                    final EList<EObject> semanticElements = viewedge.getSemanticElements();
                    if (semanticElements != null && !semanticElements.isEmpty()) {
                        final Session session = SessionManager.INSTANCE.getSession(semanticElements.get(0));
                        if (!isFromActiveSirius(session, desc.getRepresentationDescription())) {
                            append = false;
                        }
                    } else if (viewedge.getTarget() != null) {
                        final Session session = SessionManager.INSTANCE.getSession(viewedge.getTarget());
                        if (!isFromActiveSirius(session, desc.getRepresentationDescription())) {
                            append = false;
                        }
                    }
                    if (precondition != null && !StringUtil.isEmpty(precondition.trim())) {
                        append = false;
                        final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(viewedge);
                        try {
                            append = interpreter.evaluateBoolean(viewedge.getTarget(), precondition);
                        } catch (final EvaluationException e) {
                            // do nothing
                        }
                    }
                    if (append) {
                        navigate.appendToGroup(CREATE_REPRESENTATION_GROUP_SEPARATOR, new CreateRepresentationFromRepresentationCreationDescription(desc, viewedge, editingDomain, curPart));
                    }
                }
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    public void disposeContributions(final IWorkbenchPartDescriptor arg0) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     */
    public void updateActionBars(final IActionBars arg0, final IWorkbenchPartDescriptor arg1) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     */
    public void addProviderChangeListener(final IProviderChangeListener arg0) {
        // Nothing to contribute

    }

    /**
     * {@inheritDoc}
     */
    public boolean provides(final IOperation arg0) {
        // Always provide
        return true;
    }

    /**
     * {@inheritDoc}
     */
    public void removeProviderChangeListener(final IProviderChangeListener arg0) {
        // Nothing to contribute

    }

}
