/*******************************************************************************
 * Copyright (c) 2007, 2015 THALES GLOBAL SERVICES.
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

import org.eclipse.sirius.diagram.ui.provider.Messages;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.ActivateRulesCommand;
import org.eclipse.sirius.diagram.ui.tools.internal.commands.DeactivateRulesCommand;
import org.eclipse.sirius.viewpoint.description.validation.ValidationRule;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

/**
 * This Property section shows currently activated validation rules and helps in
 * adding/removing new ones.
 * 
 * 
 * 
 * @author cbrun
 * 
 */
public class ValidationPropertySection extends FiltersPropertySection {

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.properties.FiltersPropertySection#getAppliedElements()
     */
    @Override
    protected Collection<?> getAppliedElements() {
        final Collection<ValidationRule> result = new HashSet<ValidationRule>();
        if (getDiagram() != null) {
            result.addAll(getDiagram().getActivatedRules());
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
        final Collection<ValidationRule> result = new HashSet<ValidationRule>();
        if (getDiagram() != null && getDiagram().getDescription() != null && getDiagram().getDescription().getValidationSet() != null) {
            result.addAll(getDiagram().getDescription().getValidationSet().getAllRules());
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

        final Label featureLabel = getWidgetFactory().createLabel(featureComposite, Messages.ValidationPropertySection_activatedRulesLabel, SWT.NONE);
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

        final Label choiceLabel = getWidgetFactory().createLabel(choiceComposite, Messages.ValidationPropertySection_availableRulesLabel, SWT.NONE);
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
        domain.getCommandStack().execute(new ActivateRulesCommand(domain, getDiagram(), Lists.newArrayList(Iterables.filter(newElements, ValidationRule.class))));
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.diagram.ui.tools.internal.properties.FiltersPropertySection#oldElementsRemoved(java.util.Collection)
     */
    @Override
    protected void oldElementsRemoved(final Collection<?> oldElements) {
        domain.getCommandStack().execute(new DeactivateRulesCommand(domain, getDiagram(), Lists.newArrayList(Iterables.filter(oldElements, ValidationRule.class))));
    }

}
