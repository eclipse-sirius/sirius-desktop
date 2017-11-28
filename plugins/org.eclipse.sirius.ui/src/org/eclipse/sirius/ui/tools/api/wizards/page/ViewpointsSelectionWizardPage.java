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
package org.eclipse.sirius.ui.tools.api.wizards.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.sirius.business.api.componentization.ViewpointRegistry;
import org.eclipse.sirius.business.api.query.ViewpointQuery;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.ui.business.api.viewpoint.ViewpointSelection;
import org.eclipse.sirius.ui.tools.internal.viewpoint.ViewpointsSelectionGraphicalHandler;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.sirius.viewpoint.provider.Messages;
import org.eclipse.swt.widgets.Composite;

import com.google.common.base.Predicate;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * A wizard page to select viewpoints.
 *
 * @author mchauvin
 */
public class ViewpointsSelectionWizardPage extends WizardPage {

    /**
     * The list of selected viewpoints
     */
    private List<Viewpoint> viewpoints;

    /**
     * List of viewpoints names that must be activate by default (ie checked in the list).
     */
    private ArrayList<String> viewpointsNamesToActivateByDefault;

    private ViewpointsSelectionGraphicalHandler viewpointsSelectionGraphicalHandler;

    private Session session;

    /**
     * Create a new <code>RepresentationSelectionWizardPage</code>.
     *
     * @param theSession
     *            the session
     */
    public ViewpointsSelectionWizardPage(final Session theSession) {
        super(Messages.ViewpointsSelectionWizardPage_title);
        this.setTitle(Messages.ViewpointsSelectionWizardPage_title);
        this.setMessage(Messages.ViewpointsSelectionWizardPage_message);
        this.viewpoints = new ArrayList<>();
        this.viewpointsNamesToActivateByDefault = new ArrayList<>();
        viewpointsSelectionGraphicalHandler = new ViewpointsSelectionGraphicalHandler();
        this.session = theSession;
    }

    /**
     * Create a new <code>RepresentationSelectionWizardPage</code> with default viewpoints activation. This constructor
     * makes this page optional.
     *
     * @param theSession
     *            the session
     * @param viewpointsNamesToActivateByDefault
     *            list of viewpoints names to activate by default.
     */
    public ViewpointsSelectionWizardPage(final Session theSession, List<String> viewpointsNamesToActivateByDefault) {
        super(Messages.ViewpointsSelectionWizardPage_title);
        this.setTitle(Messages.ViewpointsSelectionWizardPage_title);
        this.setMessage(Messages.ViewpointsSelectionWizardPage_message);
        this.viewpoints = new ArrayList<>();
        this.viewpointsNamesToActivateByDefault = Lists.newArrayList(viewpointsNamesToActivateByDefault);
        viewpointsSelectionGraphicalHandler = new ViewpointsSelectionGraphicalHandler();
        this.session = theSession;
    }

    @Override
    public boolean isPageComplete() {
        String errorMessage = null;
        boolean complete = false;

        if (!viewpoints.isEmpty()) {
            Map<String, Collection<String>> missingDependencies = ViewpointSelection.getMissingDependencies(Sets.newHashSet(viewpoints));
            if (missingDependencies.isEmpty()) {
                complete = true;
            } else {
                errorMessage = ViewpointSelection.getMissingDependenciesErrorMessage(missingDependencies);
            }
        }

        setErrorMessage(errorMessage);
        return complete;
    }

    /**
     * Return the list of selected viewpoints of this page.
     *
     * @return the list of selected viewpoints
     */
    public List<Viewpoint> getViewpoints() {
        return viewpoints;
    }

    @Override
    public void createControl(final Composite parent) {
        initializeDialogUnits(parent);
        viewpointsSelectionGraphicalHandler.createControl(parent, true);
        CheckboxTableViewer tableViewer = viewpointsSelectionGraphicalHandler.getViewer();

        tableViewer.setInput(getAvailableViewpoints());
        if (!viewpointsNamesToActivateByDefault.isEmpty()) {
            // Search the viewpoints to activate by their name
            for (int i = 0; i < tableViewer.getTable().getItemCount(); i++) {
                Object object = tableViewer.getElementAt(i);
                if (object instanceof Viewpoint && viewpointsNamesToActivateByDefault.contains(((Viewpoint) object).getName())) {
                    viewpoints.add((Viewpoint) object);
                }
            }
            if (!viewpoints.isEmpty()) {
                // Check all the default viewpoints
                tableViewer.setCheckedElements(viewpoints.toArray(new Object[0]));
                // Set the focus on the first one
                tableViewer.setSelection(new StructuredSelection(viewpoints.get(0)));
            }
        }

        tableViewer.addCheckStateListener(new ICheckStateListener() {
            @Override
            public void checkStateChanged(final CheckStateChangedEvent event) {
                if (event.getChecked()) {
                    viewpoints.add((Viewpoint) event.getElement());
                } else {
                    viewpoints.remove(event.getElement());
                }
                setPageComplete(isPageComplete());
            }
        });

        setControl(viewpointsSelectionGraphicalHandler.getRootComposite());
    }

    private Collection<Viewpoint> getAvailableViewpoints() {
        ViewpointRegistry registry = ViewpointRegistry.getInstance();

        return Collections2.filter(registry.getViewpoints(), new Predicate<Viewpoint>() {

            @Override
            public boolean apply(Viewpoint viewpoint) {
                for (final String ext : computeSemanticFileExtensions(session)) {
                    if (new ViewpointQuery(viewpoint).handlesSemanticModelExtension(ext)) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    /**
     * compute the semantic file extensions to restrict the choice of viewpoint based on the session.
     *
     * @param theSession
     *            the session
     * @return a collection of file extension
     */
    protected Collection<String> computeSemanticFileExtensions(Session theSession) {
        return viewpointsSelectionGraphicalHandler.computeSemanticFileExtensions(theSession);
    }

    /**
     * return if the page is the current page.
     *
     * @return if the page is the current page.
     */
    public boolean isCurrentPageOnWizard() {
        return super.isCurrentPage();
    }

    /***
     * Set the browser input.A jface like browser viewer would have been better.
     *
     * @param viewpoint
     *            the viewpoint to document
     */
    protected void setBrowserInput(final Viewpoint viewpoint) {
        viewpointsSelectionGraphicalHandler.setBrowserInput(viewpoint);
    }
}
