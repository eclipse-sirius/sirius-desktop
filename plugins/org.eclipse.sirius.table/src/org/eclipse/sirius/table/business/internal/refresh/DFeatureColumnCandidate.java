/*******************************************************************************
 * Copyright (c) 2007, 2008, 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.business.internal.refresh;

import org.eclipse.sirius.table.metamodel.table.DFeatureColumn;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;

/**
 * This class represente a candidate for a DColumn, a candidate is a "possible"
 * DColumn which has not been confirmed yet by validation and preconditions.
 * 
 * @author cbrun
 * 
 */
public class DFeatureColumnCandidate {

    private final String featureName;

    private final ColumnMapping mapping;

    /**
     * The original element from which the candidate has been created. May be
     * null if no element has been used.
     */
    private DFeatureColumn element;

    /**
     * Create a new candidate.
     * 
     * @param mapping
     *            the column mapping.
     * @param featureName
     *            the target feature name.
     */
    public DFeatureColumnCandidate(final ColumnMapping mapping, final String featureName) {
        super();
        this.mapping = mapping;
        this.featureName = featureName;
    }

    /**
     * Create a new candidate from a diagram element.
     * 
     * @param tableElement
     *            an existing diagram element.
     */
    public DFeatureColumnCandidate(final DFeatureColumn tableElement) {
        this.mapping = tableElement.getOriginMapping();
        this.featureName = tableElement.getFeatureName();
        this.element = tableElement;
    }

    /**
     * Tells wether this candidate has been created from an existing element or
     * not.
     * 
     * @return true if the candidate has been created from an existing element.
     */
    public boolean comesFromTableElement() {
        return getOriginalElement() != null;
    }

    /**
     * Return the original element which has been used for the candidate
     * creation.
     * 
     * @return the original element which has been used for the candidate
     *         creation, null if no element has been used.
     */
    public DFeatureColumn getOriginalElement() {
        return element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((mapping == null || mapping.getName() == null) ? 0 : mapping.getName().hashCode());
        result = prime * result + ((featureName == null) ? 0 : featureName.hashCode());
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object obj) {
        Boolean result = null;
        if (this == obj) {
            result = true;
        }
        if (result == null && obj == null) {
            result = false;
        }
        if (result == null && !(obj instanceof DFeatureColumnCandidate)) {
            result = false;
        }
        final DFeatureColumnCandidate other = (DFeatureColumnCandidate) obj;
        if (result == null && mapping == null) {
            if (other.mapping != null) {
                result = false;
            }
        } else if (result == null && !mapping.getName().equals(other.mapping.getName())) {
            result = false;
        }
        if (result == null && featureName == null) {
            if (other.featureName != null) {
                result = false;
            }
        } else if (result == null && !featureName.equals(other.featureName)) {
            result = false;
        }
        if (result == null) {
            result = true;
        }
        return result;
    }

    public ColumnMapping getMapping() {
        return this.mapping;
    }

    public String getFeatureName() {
        return this.featureName;
    }

}
