/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES and others.
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
package org.eclipse.sirius.diagram.ui.tools.internal.properties;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import org.eclipse.sirius.business.api.session.Session;
import org.eclipse.sirius.business.api.session.SessionManager;
import org.eclipse.sirius.diagram.DDiagram;
import org.eclipse.sirius.diagram.DSemanticDiagram;
import org.eclipse.sirius.diagram.business.api.componentization.DiagramComponentizationManager;
import org.eclipse.sirius.diagram.description.tool.BehaviorTool;
import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.ActivateBehaviorToolsCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.DeactivateBehaviorToolsCommand;
import org.eclipse.sirius.viewpoint.description.Viewpoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * Sections that allows the user to choose activated filters.
 *
 * @author ymortier
 */
public class BehaviorsPropertySection extends FiltersPropertySection {
    @Override
    protected Collection<?> getAppliedElements() {
        final Collection<BehaviorTool> result = new HashSet<BehaviorTool>();
        if (getDiagram() != null) {
            result.addAll(getDiagram().getActivateBehaviors());
        }
        return result;
    }

    @Override
    protected Collection<?> getAvailableElements() {
        final Collection<BehaviorTool> result = new HashSet<BehaviorTool>();
        final DDiagram diagram = getDiagram();
        if (diagram != null && diagram.getDescription() != null) {
            Collection<Viewpoint> selectedViewpoints = Collections.emptyList();
            if (diagram instanceof DSemanticDiagram) {
                Session session = SessionManager.INSTANCE.getSession(((DSemanticDiagram) diagram).getTarget());
                if (session != null) {
                    selectedViewpoints = session.getSelectedViewpoints(false);
                }
            }
            final Iterator<?> iterTools = new DiagramComponentizationManager().getAllTools(selectedViewpoints, diagram.getDescription()).iterator();
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

    @Override
    protected Composite createFeatureComposite(final Composite composite) {
        final Composite featureComposite = getWidgetFactory().createComposite(composite, SWT.NONE);
        featureComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        featureComposite.setLayout(new GridLayout());

        final Label featureLabel = getWidgetFactory().createLabel(featureComposite, Messages.BehaviorsPropertySection_activatedBehaviorsLabel, SWT.NONE);
        featureLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
        return featureComposite;
    }

    @Override
    protected Composite createChoiceComposite(final Composite composite) {
        final Composite choiceComposite = getWidgetFactory().createComposite(composite, SWT.NONE);
        choiceComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
        choiceComposite.setLayout(new GridLayout());

        final Label choiceLabel = getWidgetFactory().createLabel(choiceComposite, Messages.BehaviorsPropertySection_availableBehaviorsLabel, SWT.NONE);
        choiceLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        return choiceComposite;
    }

    @Override
    protected void newElementsSelected(final Collection<?> newElements) {
        domain.getCommandStack().execute(new ActivateBehaviorToolsCommand(domain, getDiagram(), Lists.newArrayList(Iterables.filter(newElements, BehaviorTool.class))));
    }

    @Override
    protected void oldElementsRemoved(final Collection<?> oldElements) {
        domain.getCommandStack().execute(new DeactivateBehaviorToolsCommand(domain, getDiagram(), Lists.newArrayList(Iterables.filter(oldElements, BehaviorTool.class))));
    }
}
