/*******************************************************************************
 * Copyright (c) 2014 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.editor;

import java.util.Collection;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.business.api.query.IdentifiedElementQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.StringUtil;
import org.eclipse.sirius.common.ui.SiriusTransPlugin;
import org.eclipse.sirius.ecore.extender.business.api.accessor.ModelAccessor;
import org.eclipse.sirius.ui.tools.api.image.ImagesPath;
import org.eclipse.sirius.ui.tools.internal.actions.session.OpenRepresentationAction;
import org.eclipse.sirius.viewpoint.DRepresentation;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.SiriusPlugin;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationNavigationDescription;
import org.eclipse.sirius.viewpoint.description.tool.ToolPackage;

import com.google.common.collect.Lists;

/**
 * Menus/actions common to all Sirius dialect editors.
 * 
 * @author <a href="mailto:laurent.redor@obeo.fr">Laurent Redor</a>
 */
public final class MenuHelper {

    /**
     * The separator name for navigate representation menu.
     */
    public static final String OPEN_REPRESENTATION_GROUP_SEPARATOR = "openRepresentationGroup"; //$NON-NLS-1$

    /**
     * Private default constructor to avoid instantiation.
     */
    private MenuHelper() {
    }

    /**
     * Build the menus and corresponding actions for this <code>element</code>
     * and this navigation description <code>navDesc</code>.
     * 
     * @param openMenu
     *            The menu manager in which to add the actions
     * @param interpreter
     *            The current interpreter
     * @param navDesc
     *            The representation navigation description used to navigate
     * @param element
     *            The representation element from which to navigate
     * @param session
     *            The current session.
     * @param adapterFactory
     *            The adapter factory.
     * @return true if at least one actions is added, false otherwise
     */
    public static boolean buildOpenRepresentationActions(final IMenuManager openMenu, final IInterpreter interpreter, final RepresentationNavigationDescription navDesc,
            final DRepresentationElement element, final Session session, final AdapterFactory adapterFactory) {
        boolean atLeastOneRepresentationActionsWasCreated = false;
        Collection<EObject> candidates = findCandidates(element, navDesc, interpreter);
        for (EObject candidate : candidates) {
            Collection<DRepresentation> representations = DialectManager.INSTANCE.getRepresentations(candidate, session);
            for (DRepresentation representation : representations) {
                if (navDesc.getRepresentationDescription() != null && navDesc.getRepresentationDescription().equals(DialectManager.INSTANCE.getDescription(representation))) {
                    interpreter.setVariable(navDesc.getRepresentationNameVariable().getName(), representation.getName());
                    String label = new StringBuffer().append(new IdentifiedElementQuery(navDesc).getLabel()).append(representation.getName()).toString();
                    if (!StringUtil.isEmpty(navDesc.getNavigationNameExpression())) {
                        try {
                            label = interpreter.evaluateString(element.getTarget(), navDesc.getNavigationNameExpression());
                        } catch (final EvaluationException e) {
                            RuntimeLoggerManager.INSTANCE.error(navDesc, ToolPackage.eINSTANCE.getRepresentationNavigationDescription_NavigationNameExpression(), e);
                        }
                    }
                    openMenu.appendToGroup(OPEN_REPRESENTATION_GROUP_SEPARATOR, buildOpenRepresentationAction(session, representation, label, adapterFactory));
                    atLeastOneRepresentationActionsWasCreated = true;
                }
            }
        }
        return atLeastOneRepresentationActionsWasCreated;
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
     * @return the semantic elements to which it is valid to navigate to from
     *         the stating element.
     */
    private static Collection<EObject> findCandidates(final DRepresentationElement element, final RepresentationNavigationDescription navDesc, final IInterpreter interpreter) {
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

    /**
     * Build an action to open this <code>representation</code>.
     * 
     * @param session
     *            The current session.
     * @param representation
     *            The representation to open
     * @param adapterFactory
     *            The adapter factory
     * @return The new action to open this <code>representation</code>.
     */
    public static IAction buildOpenRepresentationAction(final Session session, final DRepresentation representation, final AdapterFactory adapterFactory) {
        String representationName = representation.getName();
        if (StringUtil.isEmpty(representationName)) {
            representationName = "(unnamed)";
            RepresentationDescription representationDescription = DialectManager.INSTANCE.getDescription(representation);
            if (representationDescription != null) {
                representationName += " " + new IdentifiedElementQuery(representationDescription).getLabel(); //$NON-NLS-1$
            }
        }
        return buildOpenRepresentationAction(session, representation, representationName, adapterFactory);
    }

    private static IAction buildOpenRepresentationAction(final Session session, final DRepresentation representation, final String label, final AdapterFactory adapterFactory) {

        ImageDescriptor imageDescriptor = null;
        final IItemLabelProvider labelProvider = (IItemLabelProvider) adapterFactory.adapt(representation, IItemLabelProvider.class);
        if (labelProvider != null) {
            imageDescriptor = ExtendedImageRegistry.getInstance().getImageDescriptor(labelProvider.getImage(representation));
        }
        if (imageDescriptor == null) {
            imageDescriptor = SiriusTransPlugin.getBundledImageDescriptor(ImagesPath.LINK_TO_VIEWPOINT_IMG);
        }

        return new OpenRepresentationAction(label, imageDescriptor, representation, session);

    }
}
