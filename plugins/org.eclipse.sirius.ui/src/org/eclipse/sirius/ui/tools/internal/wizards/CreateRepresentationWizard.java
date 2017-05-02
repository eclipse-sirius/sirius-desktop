/*******************************************************************************
 * Copyright (c) 2011, 2017 THALES GLOBAL SERVICES and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.tools.internal.wizards;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.SortedMap;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.dialect.DialectManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.common.tools.api.util.EqualityHelper;
import org.eclipse.sirius.ui.business.internal.viewpoint.ViewpointSelectionCallbackWithConfimationAndDependenciesHandling;
import org.eclipse.sirius.ui.tools.api.views.ViewHelper;
import org.eclipse.sirius.ui.tools.internal.actions.creation.CreateRepresentationAction;
import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointHelper;
import org.eclipse.sirius.ui.tools.internal.views.common.SessionLabelProvider;
import org.eclipse.sirius.ui.tools.internal.views.common.item.RepresentationDescriptionItemImpl;
import org.eclipse.sirius.ui.tools.internal.views.common.item.ViewpointItemImpl;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.RepresentationSelectionWizardPage;
import org.eclipse.sirius.ui.tools.internal.wizards.pages.SemanticElementSelectionWizardPage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Composite;

import com.google.common.collect.Maps;

/**
 * This wizard allows to create a representation by specifying the representation description and the semantic element
 * of session's models. It can contains two pages. One to choose the representation description and one to choose the
 * semantic model element.
 * 
 * There are three ways to use this wizard:
 * 
 * The first is to provide only the session. In this case user has to choose a representation description and a semantic
 * model element from which a representation instance will be created.
 * 
 * The second is to provide the representation description in addition of the session. In this situation, this
 * description will be selected by default and the semantic page will be shown to user directly.
 * 
 * The last one is to provide a semantic element in addition of the session. In this case, the wizard will only have the
 * page allowing to select the representation description because the semantic element is already provided. This page
 * will show only representations descriptions that are compatible with the type of the semantic element.
 * 
 * @author nlepine
 *
 */
public class CreateRepresentationWizard extends Wizard {

    /**
     * The minimum height for which the wizard is considered as ergonomic in Eclipse environment.
     */
    public static final int MIN_PAGE_HEIGHT = 600;

    /**
     * The minimum width for which the wizard is considered as ergonomic in Eclipse environment.
     */
    public static final int MIN_PAGE_WIDTH = 1000;

    /**
     * The session target of the representations creation.
     */
    private final Session session;

    /**
     * The page allowing to select a semantic model element that will be root of the representation description selected
     * in the representation page.
     */
    private SemanticElementSelectionWizardPage selectSemanticElementPage;

    /**
     * The page allowing to select a representation description from which representation instance will be created.
     */
    private RepresentationSelectionWizardPage representationWizardPage;

    /**
     * The representation description to select by default in the representation page.
     */
    private RepresentationDescriptionItemImpl representationDescriptionItem;

    /**
     * True if representation creation has been cancelled by user.
     */
    private boolean creationCancelled;

    /**
     * True if a representation description is provided to this wizard implying that it should be selected in the
     * corresponding representations page and that the semantic element selection page should be selected directly.
     */
    private boolean skipRepresentationsPage;

    /**
     * The semantic element from which a compatible representation description must be chosen by user to create a
     * representation instance with as root element the semantic element.
     */
    private EObject semanticElement;

    /**
     * Initialize the wizard were user has to choose a representation description and a semantic model element from
     * which a representation instance will be created.
     *
     * @param session
     *            origin session.
     */
    public CreateRepresentationWizard(final Session session) {
        this.session = session;
    }

    /**
     * Initialize the wizard were representation description will be selected by default and the semantic page will be
     * shown to user directly.
     * 
     * @param theSession
     *            the session used for representation creation.
     * @param theRepresentationDescriptionItem
     *            the representation descriptor to select by default.
     */
    public CreateRepresentationWizard(Session theSession, RepresentationDescriptionItemImpl theRepresentationDescriptionItem) {
        this.session = theSession;
        this.representationDescriptionItem = theRepresentationDescriptionItem;
        this.skipRepresentationsPage = true;
    }

    /**
     * Initialize the wizard were the semantic element is already provided. Only the representations page will be show
     * to user allowing to choose a representation description compatible with the semantic element.
     * 
     * @param theSession
     *            the session used for representation creation.
     * @param theSemanticElement
     *            The semantic element from which a compatible representation description must be chosen by user to
     *            create a representation instance with as root element the semantic element.
     */
    public CreateRepresentationWizard(Session theSession, EObject theSemanticElement) {
        this.session = theSession;
        this.semanticElement = theSemanticElement;
    }

    /**
     * Initialize the wizard.
     */
    public void init() {
        setWindowTitle(Messages.CreateRepresentationWizard_title);
        setNeedsProgressMonitor(false);
    }

    @Override
    public boolean performFinish() {
        // the semantic element is either the one provided to the wizard or the one chosen by user with semantic page of
        // the wizard.
        EObject semanticElementSource = semanticElement;
        if (semanticElementSource == null) {
            semanticElementSource = selectSemanticElementPage.getSelectedElement();
        }
        if (semanticElementSource != null) {
            ViewpointItemImpl viewpointItem = representationWizardPage.getViewpointItem();
            if (!viewpointItem.isViewpointEnabledInSession()) {
                // if the viewpoint is not enabled in the session we activate it with all its potential dependencies.
                final SortedMap<Viewpoint, Boolean> originalViewpointsMap = Maps.newTreeMap(new ViewpointRegistry.ViewpointComparator());
                Collection<Viewpoint> availableViewpoints = ViewpointHelper.getAvailableViewpoints(session);
                Collection<Viewpoint> selectedViewpoints = session.getSelectedViewpoints(false);
                for (final Viewpoint viewpoint : availableViewpoints) {
                    boolean selected = false;

                    for (Viewpoint selectedViewpoint : selectedViewpoints) {
                        if (EqualityHelper.areEquals(selectedViewpoint, viewpoint)) {
                            selected = true;
                            break;
                        }
                    }
                    originalViewpointsMap.put(viewpoint, Boolean.valueOf(selected));
                }
                SortedMap<Viewpoint, Boolean> newViewpointToSelectionStateMap = Maps.newTreeMap(new ViewpointRegistry.ViewpointComparator());
                newViewpointToSelectionStateMap.putAll(originalViewpointsMap);
                newViewpointToSelectionStateMap.put(viewpointItem.getViewpoint(), true);

                // we retrieve the viewpoints that are dependencies of the viewpoint to
                // activate to activate these also.
                Map<String, Viewpoint> missingViewpointDependencies = ViewpointHelper.getViewpointDependencies(availableViewpoints, selectedViewpoints, viewpointItem.getViewpoint());
                for (Viewpoint viewpoint : missingViewpointDependencies.values().stream().filter(vp -> vp != null).collect(Collectors.toSet())) {
                    newViewpointToSelectionStateMap.put(viewpoint, true);
                }
                ViewpointHelper.applyNewViewpointSelection(originalViewpointsMap, newViewpointToSelectionStateMap, session, true,
                        new ViewpointSelectionCallbackWithConfimationAndDependenciesHandling());
            }
            Optional<RepresentationDescription> loadedInSessionDescription = DialectManager.INSTANCE.getAvailableRepresentationDescriptions(session.getSelectedViewpoints(false), semanticElementSource)
                    .stream().filter(rep -> EqualityHelper.areEquals(rep, representationWizardPage.getRepresentation())).findFirst();
            if (loadedInSessionDescription.isPresent()) {
                CreateRepresentationAction action = new CreateRepresentationAction(session, semanticElementSource, loadedInSessionDescription.get(),
                        new SessionLabelProvider(ViewHelper.INSTANCE.createAdapterFactory()));
                action.run();
            }
        }
        return true;
    }

    @Override
    public void addPages() {
        if (semanticElement == null) {
            representationWizardPage = new RepresentationSelectionWizardPage(session, representationDescriptionItem);
            selectSemanticElementPage = new SemanticElementSelectionWizardPage(session);
            representationWizardPage.setSelectionWizard(selectSemanticElementPage);
            addPage(representationWizardPage);
            addPage(selectSemanticElementPage);
        } else {
            // when a semantic element is provided we don't use the semantic element selection page.
            representationWizardPage = new RepresentationSelectionWizardPage(session, semanticElement);
            addPage(representationWizardPage);
        }
    }

    @Override
    public IWizardPage getStartingPage() {
        if (skipRepresentationsPage) {
            // if a representation description is provided it is selected automatically in the representation
            // description page so we provide to user directly the semantic selection page.
            return selectSemanticElementPage;
        }
        return super.getStartingPage();
    }

    @Override
    public void createPageControls(Composite pageContainer) {
        super.createPageControls(pageContainer);
        if (selectSemanticElementPage != null) {
            selectSemanticElementPage.update();
        }
    }

    @Override
    public void dispose() {
        super.dispose();
        // We don't have a semantic selection page when a semantic element is provided.
        if (selectSemanticElementPage != null) {
            selectSemanticElementPage.dispose();
        }
        representationWizardPage.dispose();
    }

    @Override
    public boolean canFinish() {
        return super.canFinish();
    }

    @Override
    public boolean performCancel() {
        this.creationCancelled = true;
        return true;
    }

    /**
     * Returns true is the creation has been cancelled. False otherwise.
     * 
     * @return true is the creation has been cancelled. False otherwise.
     */
    public boolean isCreationCancelled() {
        return creationCancelled;
    }
}
