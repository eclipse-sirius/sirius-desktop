/*******************************************************************************
 * Copyright (c) 2007, 2014 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.diagram.ui.tools.internal.menu;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.api.image.DiagramImagesPath;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.NavigateToCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.EMFCommandFactoryUI;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.description.tool.AbstractVariable;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchPart;

import com.google.common.collect.Lists;

/**
 * This menu contribution add a "open" menu in the DDiagram diagram editor. This
 * menu provides navigation links to Model elements or Source files.
 * 
 * @author cbrun
 * 
 */
public class OpenMenuContribution implements IContributionItemProvider {

    private static final String OPEN_REPRESENTATION_GROUP_SEPARATOR = "openRepresentationGroup";

    private static final String MENU_OPEN_REPRESENTATION_ID = "popup.open";

    private AdapterFactory adapterFactory;

    private AdapterFactory getAdapterFactory() {
        if (adapterFactory == null) {
            adapterFactory = DialectUIManager.INSTANCE.createAdapterFactory();
        }
        return adapterFactory;
    }

    /**
     * {@inheritDoc}
     */
    public void contributeToPopupMenu(final IMenuManager menu, final IWorkbenchPart part) {
        if (part instanceof SiriusDiagramEditor) {
            final SiriusDiagramEditor diagrampart = (SiriusDiagramEditor) part;
            final EObject element = diagrampart.getDiagramEditPart().resolveSemanticElement();
            if (element instanceof DSemanticDiagram) {
                final DSemanticDiagram designerDiag = (DSemanticDiagram) element;
                final EditPart editpart = diagrampart.getDiagramGraphicalViewer().getFocusEditPart();
                if (editpart instanceof IGraphicalEditPart) {
                    final IGraphicalEditPart curPart = (IGraphicalEditPart) editpart;
                    final EObject designerObj = curPart.resolveSemanticElement();
                    if (designerObj instanceof DSemanticDecorator) {
                        buildOpenExistingRepresentationsMenu(menu, designerDiag, editpart, designerObj);
                    }
                }
            }
        }
    }

    private void buildOpenExistingRepresentationsMenu(final IMenuManager menu, final DSemanticDiagram designerDiag, final EditPart editpart, final EObject designerObj) {
        final EObject semantic = ((DSemanticDecorator) designerObj).getTarget();
        if (semantic != null) {
            final Session session = SessionManager.INSTANCE.getSession(semantic);
            if (session != null) {
                menu.insertBefore("additions", new Separator("generator")); //$NON-NLS-1$ //$NON-NLS-2$
                // Remove Open action in navigate menu which is not used in
                // Sirius and avoid to have a gray "Open" item menu in Navigate
                // menu
                final IMenuManager navigateManager = (IMenuManager) menu.find("navigateMenu");
                // if the navigate menu is changed or deleted a NPE is avoided
                if (navigateManager != null) {
                    navigateManager.remove("open");
                }
                // Add menus to open existing representations
                final MenuManager openMenuManager = new MenuManager("Open", MENU_OPEN_REPRESENTATION_ID);
                if (!menu.isEmpty()) {
                    menu.insertBefore(menu.getItems()[0].getId(), openMenuManager);
                } else {
                    menu.add(openMenuManager);
                }
                contributeToPopupMenu(openMenuManager, null);
                final TransactionalEditingDomain transDomain = TransactionUtil.getEditingDomain(designerDiag);
                final Collection<DRepresentation> otherRepresentations = DialectManager.INSTANCE.getRepresentations(semantic, session);
                for (final DRepresentation representation : otherRepresentations) {
                    if (!EcoreUtil.equals(designerDiag, representation) && isFromActiveViewpoint(session, representation)) {
                        openMenuManager.add(buildOpenRepresentationAction(session, representation, editpart, transDomain));
                    }
                }
                if (designerObj instanceof DRepresentationElement) {
                    buildOpenableRepresentationsMenu(openMenuManager, designerObj, session, editpart, transDomain);
                }
            }
        }
    }

    /**
     * Tests whether a representation description belongs to a viewpoint which
     * is currently active in the session.
     * 
     * @param session
     *            the current session.
     * @param representationDescription
     *            the representation description to check.
     * @return <code>true</code> if the representation description belongs to a
     *         viewpoint which is currently active in the session.
     */
    private boolean isFromActiveViewpoint(final Session session, final RepresentationDescription representationDescription) {
        final Viewpoint vp = ViewpointRegistry.getInstance().getViewpoint(representationDescription);
        return vp != null && session.getSelectedViewpoints(false).contains(vp);
    }

    /**
     * Tests whether a representation belongs to a viewpoint which is currently
     * active in the session.
     * 
     * @param session
     *            the current session.
     * @param representation
     *            the representation to check.
     * @return <code>true</code> if the representation belongs to a viewpoint
     *         which is currently active in the session.
     */
    private boolean isFromActiveViewpoint(final Session session, final DRepresentation representation) {
        final RepresentationDescription description = DialectManager.INSTANCE.getDescription(representation);
        return isFromActiveViewpoint(session, description);
    }

    private void buildOpenableRepresentationsMenu(final IMenuManager openMenu, final EObject designerObj, final Session session, final EditPart editpart, final TransactionalEditingDomain transDomain) {
        final DRepresentationElement element = (DRepresentationElement) designerObj;
        final Separator createGroup = new Separator(OPEN_REPRESENTATION_GROUP_SEPARATOR);
        openMenu.add(createGroup);
        for (RepresentationNavigationDescription navDesc : element.getMapping().getNavigationDescriptions()) {
            boolean append = true;
            if (!isFromActiveViewpoint(session, navDesc.getRepresentationDescription())) {
                append = false;
            }

            final IInterpreter interpreter = SiriusPlugin.getDefault().getInterpreterRegistry().getInterpreter(element.getTarget());
            Option<DDiagram> diagram = new EObjectQuery(element).getParentDiagram();
            if (diagram.some()) {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, diagram.get());
            } else {
                interpreter.setVariable(IInterpreterSiriusVariables.DIAGRAM, null);
            }

            final Map<AbstractVariable, Object> variables = new HashMap<AbstractVariable, Object>();
            variables.put(navDesc.getContainerVariable(), element.getTarget());
            variables.put(navDesc.getContainerViewVariable(), element);

            final InitInterpreterVariablesTask init = new InitInterpreterVariablesTask(variables, interpreter, new EMFCommandFactoryUI());
            init.execute();

            final String precondition = navDesc.getPrecondition();
            if (append && !StringUtil.isEmpty(precondition)) {
                append = false;

                try {
                    append = interpreter.evaluateBoolean(element.getTarget(), navDesc.getPrecondition());
                } catch (final EvaluationException e) {
                    RuntimeLoggerManager.INSTANCE.error(navDesc, ToolPackage.eINSTANCE.getAbstractToolDescription_Precondition(), e);
                }
            }

            if (append) {
                buildOpenRepresentationActions(openMenu, interpreter, navDesc, element, session, editpart, transDomain);
            }

            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }
    }

    private void buildOpenRepresentationActions(final IMenuManager openMenu, final IInterpreter interpreter, final RepresentationNavigationDescription navDesc, final DRepresentationElement element,
            final Session session, final EditPart editpart, final TransactionalEditingDomain transDomain) {
        final Collection<EObject> candidates = findCandidates(element, navDesc, interpreter);
        for (EObject candidate : candidates) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(candidate, session);
            for (DRepresentation representation : representations) {
                if (navDesc.getRepresentationDescription() != null && navDesc.getRepresentationDescription().equals(DialectManager.INSTANCE.getDescription(representation))) {
                    interpreter.setVariable(navDesc.getRepresentationNameVariable().getName(), representation.getName());
                    String label = new StringBuffer(navDesc.getName()).append(" ").append(representation.getName()).toString();
                    if (!StringUtil.isEmpty(navDesc.getNavigationNameExpression())) {
                        try {
                            label = interpreter.evaluateString(element.getTarget(), navDesc.getNavigationNameExpression());
                        } catch (final EvaluationException e) {
                            RuntimeLoggerManager.INSTANCE.error(navDesc, ToolPackage.eINSTANCE.getRepresentationNavigationDescription_NavigationNameExpression(), e);
                        }
                    }
                    openMenu.appendToGroup(OPEN_REPRESENTATION_GROUP_SEPARATOR, buildOpenRepresentationAction(session, representation, editpart, transDomain, label));
                }
            }
        }
    }

    /**
     * Computes all the candidates for navigation from a starting element using
     * the specified navigation description.
     * 
     * @param element
     *            the starting point.
     * @param navDesc
     *            the navigation description element.
     * @param interpreter
     *            the interpreter to use to compute the expressions in the
     *            navigation description element.
     * @return the semantic elements to which is is valid to navigate to from
     *         the stating element.
     */
    private Collection<EObject> findCandidates(final DRepresentationElement element, final RepresentationNavigationDescription navDesc, final IInterpreter interpreter) {
        final Collection<EObject> candidates;
        if (!StringUtil.isEmpty(navDesc.getBrowseExpression())) {
            final RuntimeLoggerInterpreter safeInterpreter = RuntimeLoggerManager.INSTANCE.decorate(interpreter);
            final EAttribute browseExpressionFeature = ToolPackage.eINSTANCE.getRepresentationNavigationDescription_BrowseExpression();
            candidates = safeInterpreter.evaluateCollection(element.getTarget(), navDesc, browseExpressionFeature);
        } else {
            final ModelAccessor modelAccessor = SiriusPlugin.getDefault().getModelAccessorRegistry().getModelAccessor(element.getTarget());
            candidates = Lists.newArrayList(modelAccessor.eAllContents(element.getTarget()));
        }
        return candidates;
    }

    private IAction buildOpenRepresentationAction(final Session session, final DRepresentation representation, final EditPart editpart, final TransactionalEditingDomain transDomain) {
        String representationName = representation.getName();
        if (StringUtil.isEmpty(representationName)) {
            representationName = "(unnamed)";
            if (representation instanceof DDiagram) {
                representationName += " " + new IdentifiedElementQuery(((DDiagram) representation).getDescription()).getLabel();
            }
        }
        return buildOpenRepresentationAction(session, representation, editpart, transDomain, representationName);
    }

    private IAction buildOpenRepresentationAction(final Session session, final DRepresentation representation, final EditPart editpart, final TransactionalEditingDomain transDomain, final String label) {

        ImageDescriptor imageDescriptor = null;
        final IItemLabelProvider labelProvider = (IItemLabelProvider) getAdapterFactory().adapt(representation, IItemLabelProvider.class);
        if (labelProvider != null) {
            imageDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(representation));
        }
        if (imageDescriptor == null) {
            imageDescriptor = SiriusTransPlugin.getBundledImageDescriptor(DiagramImagesPath.LINK_TO_VIEWPOINT_IMG);
        }

        return new Action(label, imageDescriptor) {
            @Override
            public void run() {
                super.run();
                transDomain.getCommandStack().execute(new NavigateToCommand(session, representation));
            }
        };
    }

    /**
     * {@inheritDoc}
     */
    public void contributeToActionBars(final IActionBars arg0, final IWorkbenchPartDescriptor arg1) {
        // Nothing to contribute
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
