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

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.sirius.table.metamodel.table.DCell;
import org.eclipse.sirius.table.metamodel.table.DColumn;
import org.eclipse.sirius.table.metamodel.table.DLine;
import org.eclipse.sirius.table.metamodel.table.description.ColumnMapping;

/**
 * This class represents a candidate for a DCell, a candidate is a "possible"
 * DCell which has not been confirmed yet by validation and preconditions.
 * 
 * @author cbrun
 * 
 */
public class DCellCandidate {

    private final EObject semantic;

    private final ColumnMapping mapping;

    private final DColumn column;

    private final DLine line;

    /**
     * The original element from which the candidate has been created. May be
     * null if no element has been used.
     */
    private DCell element;

	private final URI semanticURI;

	private final URI lineURI;

	private final URI columnURI;

	private final int hashCode;

    /**
     * Create a new candidate.
     * 
     * @param mapping
     *            the column mapping.
     * @param semanticElement
     *            the target semantic element.
     * @param line
     *            the cell line.
     * @param column
     *            the cell column.
     */
    public DCellCandidate(final ColumnMapping mapping, final EObject semanticElement, final DLine line, final DColumn column) {
        this.mapping = mapping;
        this.semantic = semanticElement;
        this.line = line;
        this.column = column;
    	this.semanticURI = semantic != null ? EcoreUtil.getURI(semantic) : null;
    	this.lineURI = line != null ? EcoreUtil.getURI(line) : null;
    	this.columnURI = column != null ? EcoreUtil.getURI(column) : null;
    	this.hashCode = computeHashCode();
    }

    /**
     * Create a new candidate from a diagram element.
     * 
     * @param tableElement
     *            an existing diagram element.
     */
    public DCellCandidate(final DCell tableElement) {
        this((tableElement.getColumn() != null) ? tableElement.getColumn().getOriginMapping() : null, tableElement.getTarget(), tableElement.getLine(), tableElement.getColumn());
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
    public DCell getOriginalElement() {
        return element;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return hashCode;
    }

	private int computeHashCode() {
	    return KeyCache.DEFAULT.getKey((mapping != null ? mapping.getName() : "") + semanticURI + lineURI + columnURI);
    }

    /**
     * {@inheritDoc}
     */
    public boolean equals(final Object obj) {
        Boolean result = null;
        if (this == obj)
            result = true;
        if (result == null && obj == null)
            result = false;
        if (result == null && !(obj instanceof DCellCandidate))
            result = false;
        final DCellCandidate other = (DCellCandidate) obj;
        if (result == null && mapping == null) {
            if (other.mapping != null)
                result = false;
        } else if (result == null && !mapping.getName().equals(other.mapping.getName()))
            result = false;
        if (result == null && semantic == null) {
            if (other.semantic != null)
                result = false;
        } else if (result == null && !semanticURI.equals(other.semanticURI))
            result = false;
        if (result == null && line == null) {
            if (other.line != null)
                result = false;
        } else if (result == null && !lineURI.equals(other.lineURI))
            result = false;
        if (result == null && column == null) {
            if (other.column != null)
                result = false;
        } else if (result == null && !columnURI.equals(other.columnURI))
            result = false;

        if (result == null)
            result = true;
        return result;
    }

    public ColumnMapping getMapping() {
        return this.mapping;
    }

    public EObject getSemantic() {
        return this.semantic;
    }

    /**
     * return the column.
     * 
     * @return the column
     */
    public DColumn getColumn() {
        return column;
    }

    /**
     * return the line.
     * 
     * @return the line
     */
    public DLine getLine() {
        return line;
    }

}
