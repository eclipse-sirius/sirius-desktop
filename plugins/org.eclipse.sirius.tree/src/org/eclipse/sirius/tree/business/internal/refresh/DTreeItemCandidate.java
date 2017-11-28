/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.tree.business.internal.refresh;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.sirius.tree.DTreeItem;
import org.eclipse.sirius.tree.DTreeItemContainer;
import org.eclipse.sirius.tree.description.TreeItemMapping;

/**
 * This class represents a candidate for a DTreeItem, a candidate is a
 * "possible" DTreeItem which has not been confirmed yet by validation and
 * preconditions.
 * 
 * @author nlepine
 * 
 */
public class DTreeItemCandidate {
    private DTreeItemContainer viewContainer;

    private EObject semantic;

    private TreeItemMapping mapping;

    /**
     * The original element from which the candidate has been created. May be
     * null if no element has been used.
     */
    private DTreeItem element;

    /**
     * Create a new candidate.
     * 
     * @param mapping
     *            the line mapping.
     * @param semanticElement
     *            the target semantic element.
     * @param viewContainer
     *            the view container.
     */
    public DTreeItemCandidate(final TreeItemMapping mapping, final EObject semanticElement, final DTreeItemContainer viewContainer) {
        super();
        this.mapping = mapping;
        this.semantic = semanticElement;
        this.viewContainer = viewContainer;
    }

    /**
     * Create a new candidate from a diagram element.
     * 
     * @param treeElement
     *            an existing diagram element.
     */
    public DTreeItemCandidate(final DTreeItem treeElement) {
        this.mapping = treeElement.getActualMapping();
        this.semantic = treeElement.getTarget();
        this.viewContainer = (DTreeItemContainer) treeElement.eContainer();
        this.element = treeElement;
    }

    /**
     * Tells whether this candidate has been created from an existing element or
     * not.
     * 
     * @return true if the candidate has been created from an existing element.
     */
    public boolean comesFromTreeElement() {
        return getActualElement() != null;
    }

    /**
     * Return the original element which has been used for the candidate
     * creation.
     * 
     * @return the original element which has been used for the candidate
     *         creation, null if no element has been used.
     */
    public DTreeItem getActualElement() {
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
        result = prime * result + ((semantic == null) ? 0 : EcoreUtil.getURI(semantic).hashCode());
        result = prime * result + ((viewContainer == null) ? 0 : EcoreUtil.getURI(viewContainer).hashCode());
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
        if (result == null && !(obj instanceof DTreeItemCandidate)) {
            result = false;
        }
        final DTreeItemCandidate other = (DTreeItemCandidate) obj;
        if (result == null && mapping == null) {
            if (other.mapping != null) {
                result = false;
            }
        } else if (result == null && !mapping.getName().equals(other.mapping.getName())) {
            result = false;
        }
        if (result == null && semantic == null) {
            if (other.semantic != null) {
                result = false;
            }
        } else if (result == null && !EcoreUtil.getURI(semantic).equals(EcoreUtil.getURI(other.semantic))) {
            result = false;
        }
        if (result == null && viewContainer == null) {
            if (other.viewContainer != null) {
                result = false;
            }
        } else if (result == null && !EcoreUtil.getURI(viewContainer).equals(EcoreUtil.getURI(other.viewContainer))) {
            result = false;
        }
        if (result == null) {
            result = true;
        }
        return result;
    }

    public TreeItemMapping getMapping() {
        return this.mapping;
    }

    public EObject getSemantic() {
        return this.semantic;
    }

    public DTreeItemContainer getViewContainer() {
        return this.viewContainer;
    }

    public void setMapping(final TreeItemMapping mapping) {
        this.mapping = mapping;
    }

    public void setSemantic(final EObject semantic) {
        this.semantic = semantic;
    }

    public void setViewContainer(final DTreeItemContainer viewContainer) {
        this.viewContainer = viewContainer;
    }

}
