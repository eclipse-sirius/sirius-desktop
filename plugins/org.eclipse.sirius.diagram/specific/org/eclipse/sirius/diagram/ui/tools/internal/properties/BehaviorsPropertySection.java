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
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import org.eclipse.sirius.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.ActivateBehaviorToolsCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.DeactivateBehaviorToolsCommand;
import org.eclipse.sirius.viewpoint.DDiagram;
import org.eclipse.sirius.viewpoint.DSemanticDiagram;
import org.eclipse.sirius.viewpoint.description.tool.BehaviorTool;

/**
 * Sections that allows the user to choose activated filters.
 * 
 * @author ymortier
 */
public class BehaviorsPropertySection extends FiltersPropertySection {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.properties.FiltersPropertySection#getAppliedElements()
     */
    @Override
    protected Collection<?> getAppliedElements() {
        final Collection<BehaviorTool> result = new HashSet<BehaviorTool>();
        if (getDiagram() != null) {
            result.addAll(getDiagram().getActivateBehaviors());
        }
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.properties.FiltersPropertySection#getAvailableElements()
     */
    @Override
    protected Collection<?> getAvailableElements() {
        final Collection<BehaviorTool> result = new HashSet<BehaviorTool>();
        final DDiagram diagram = getDiagram();
        if (diagram != null && diagram.getDescription() != null) {
            Session session = null;
            if (diagram instanceof DSemanticDiagram) {
                session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) diagram).getTarget());
            }
            final Iterator<?> iterTools = new DiagramComponentizationManager().getAllTools(session.getSelectedSiriuss(false), diagram.getDescription()).iterator();
            while (iterTools.hasNext()) {
                final Object currentTool = iterTools.next();
                if (currentTool instanceof BehaviorTool) {
                    result.add((BehaviorTool) currentTool);
                }
            }
        }
        result.removeAll(getAppliedElements());
        return result;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.properties.FiltersPropertySection#createFeatureComposite(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Composite createFeatureComposite(final Composite composite) {
        final Composite featureComposite = getWidgetFactory().createComposite(composite, SWT.NONE);
        featureComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        featureComposite.setLayout(new GridLayout());

        final Label featureLabel = getWidgetFactory().createLabel(featureComposite, "Activated Behaviors", SWT.NONE);
        featureLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        return featureComposite;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.properties.FiltersPropertySection#createChoiceComposite(org.eclipse.swt.widgets.Composite)
     */
    @Override
    protected Composite createChoiceComposite(final Composite composite) {
        final Composite choiceComposite = getWidgetFactory().createComposite(composite, SWT.NONE);
        choiceComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        choiceComposite.setLayout(new GridLayout());

        final Label choiceLabel = getWidgetFactory().createLabel(choiceComposite, "Available Behaviors", SWT.NONE);
        choiceLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        return choiceComposite;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.properties.FiltersPropertySection#newElementsSelected(java.util.Collection)
     */
    @Override
    protected void newElementsSelected(final Collection<?> newElements) {
        domain.getCommandStack().execute(new ActivateBehaviorToolsCommand(domain, getDiagram(), Lists.newArrayList(Iterables.filter(newElements, BehaviorTool.class))));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.properties.FiltersPropertySection#oldElementsRemoved(java.util.Collection)
     */
    @Override
    protected void oldElementsRemoved(final Collection<?> oldElements) {
        domain.getCommandStack().execute(new DeactivateBehaviorToolsCommand(domain, getDiagram(), Lists.newArrayList(Iterables.filter(oldElements, BehaviorTool.class))));
    }
}
