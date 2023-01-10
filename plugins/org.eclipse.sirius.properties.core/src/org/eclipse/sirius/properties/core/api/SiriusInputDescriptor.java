/*******************************************************************************
 * Copyright (c) 2016, 2022 Obeo.
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
package org.eclipse.sirius.properties.core.api;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.eef.core.api.InputDescriptor;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.properties.core.internal.SiriusContext;

/**
 * An EEF InputDescriptor for elements selected in a Sirius context.
 * 
 * @author Pierre-Charles David <pierre-charles.david@obeo.fr>
 */
public class SiriusInputDescriptor implements InputDescriptor {
    /**
     * The full context, determined from the original input.
     */
    private final SiriusContext context;

    /**
     * The list of all contexts, each determined from the list of all selected elements.
     */
    private List<SiriusContext> contextSelections;

    /**
     * Creates a SiriusInputDescriptor from the specified input.
     * 
     * @param input
     *            the original input.
     * @param rawSelection
     *            the list of all selected elements.
     */
    public SiriusInputDescriptor(Object input, List<Object> rawSelection) {
        this.contextSelections = new ArrayList<>();
        if (!rawSelection.contains(input)) {
            rawSelection.add(0, input);
        }
        for (Object selection : rawSelection) {
            this.contextSelections.add(SiriusContext.from(selection));
        }
        this.context = SiriusContext.from(input);
    }

    /**
     * Creates a SiriusInputDescriptor from the specified input.
     * 
     * @param input
     *            the original input.
     */
    public SiriusInputDescriptor(Object input) {
        this(input, new ArrayList<Object>());
    }

    @Override
    public Object getOriginalSelection() {
        return context.getInput();
    }

    /**
     * The list of original selections before any interpretation or adaptation.
     *
     * @return the original selections.
     */
    public List<Object> getOriginalSelections() {
        List<Object> result = new ArrayList<>();
        for (SiriusContext siriusContext : contextSelections) {
            result.add(siriusContext.getInput());
        }
        return result;
    }

    @Override
    public EObject getSemanticElement() {
        Option<EObject> obj = context.getMainSemanticElement();
        if (obj.some()) {
            return obj.get();
        } else {
            return null;
        }
    }

    /**
     * The list of semantic model elements of whom properties should be displayed, as determined from the original
     * selections.
     *
     * @return the model elements whose properties to display.
     */
    public List<EObject> getSemanticElements() {
        List<EObject> result = new ArrayList<>();
        for (SiriusContext siriusContext : contextSelections) {
            Option<EObject> obj = siriusContext.getMainSemanticElement();
            if (obj.some()) {
                result.add(obj.get());
            }
        }
        return result;
    }

    /**
     * Returns all the semantic model element associated with the current selection, including secondary associated
     * elements if any.
     * 
     * @return all the semantic model element associated with the current selection.
     */
    public Set<EObject> getAllSemanticElements() {
        Set<EObject> result = new LinkedHashSet<>();
        result.add(getSemanticElement());
        Option<List<EObject>> additional = context.getAdditionalSemanticElements();
        if (additional.some()) {
            result.addAll(additional.get());
        }
        return result;
    }

    /**
     * Returns the full Sirius context determined from the original input, which may include addition Sirius-specific
     * information in addition to what can be exposed through the generic {@link InputDescriptor} API.
     * 
     * @return the full Sirius context determined from the original input.
     */
    public SiriusContext getFullContext() {
        return context;
    }

    /**
     * Returns the list of all Sirius contexts, each determined from the list of all the selected elements, which may
     * include addition Sirius-specific information in addition to what can be exposed through the generic
     * {@link InputDescriptor} API.
     * 
     * @return the list of all Sirius contexts, each determined from the list of all the selected elements.
     */
    public List<SiriusContext> getContextSelections() {
        return contextSelections;
    }

}
