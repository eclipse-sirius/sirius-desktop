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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.transaction.TransactionalEditingDomain;
import org.eclipse.emf.transaction.util.TransactionUtil;
import org.eclipse.gef.EditPart;
import org.eclipse.gmf.runtime.common.core.service.IOperation;
import org.eclipse.gmf.runtime.common.core.service.IProviderChangeListener;
import org.eclipse.gmf.runtime.common.ui.services.action.internal.contributionitem.IContributionItemProvider;
import org.eclipse.gmf.runtime.common.ui.util.IWorkbenchPartDescriptor;
import org.eclipse.gmf.runtime.diagram.ui.editparts.IGraphicalEditPart;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.helper.task.InitInterpreterVariablesTask;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.query.EObjectQuery;
import org.eclipse.sirius.diagram.ui.part.SiriusDiagramEditor;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.emf.EMFCommandFactoryUI;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ui.business.api.dialect.DialectUIManager;
import org.eclipse.sirius.ui.tools.internal.editor.MenuHelper;
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

/**
 * This menu contribution add a "open" menu in the DDiagram diagram editor. This
 * menu provides navigation links to Model elements or Source files.
 * 
 * @author cbrun
 * 
 */
public class OpenMenuContribution implements IContributionItemProvider {

    private static final String MENU_OPEN_REPRESENTATION_ID = "popup.open"; //$NON-NLS-1$

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
                final IMenuManager navigateManager = (IMenuManager) menu.find("navigateMenu"); //$NON-NLS-1$
                // if the navigate menu is changed or deleted a NPE is avoided
                if (navigateManager != null) {
                    navigateManager.remove("open"); //$NON-NLS-1$
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
                        openMenuManager.add(MenuHelper.buildOpenRepresentationAction(session, representation, getAdapterFactory()));
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
        final Separator createGroup = new Separator(MenuHelper.OPEN_REPRESENTATION_GROUP_SEPARATOR);
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
                MenuHelper.buildOpenRepresentationActions(openMenu, interpreter, navDesc, element, session, getAdapterFactory());
            }

            interpreter.unSetVariable(IInterpreterSiriusVariables.DIAGRAM);
        }
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
