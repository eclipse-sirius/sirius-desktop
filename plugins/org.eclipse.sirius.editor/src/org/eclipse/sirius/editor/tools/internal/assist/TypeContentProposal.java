/*******************************************************************************
 * Copyright (c) 2009 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.editor.tools.internal.assist;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.jface.fieldassist.IContentProposal;

/**
 * A proposal for a type content.
 * 
 * @author cbrun
 * 
 */
public class TypeContentProposal implements IContentProposal {

    private final EClassifier proposal;

    private final String incomplete;

    /**
     * Create a new proposal.
     * 
     * @param proposal
     *            the proposed classifier.
     * @param incomplete
     *            the incomplete name.
     */
    public TypeContentProposal(final EClassifier proposal, final String incomplete) {
        this.proposal = proposal;
        this.incomplete = incomplete;
    }

    /**
     * {@inheritDoc}
     */
    public String getContent() {
        final String content = getLabel().substring(incomplete.length());
        return content;
    }

    /**
     * {@inheritDoc}
     */
    public int getCursorPosition() {
        return getLabel().length() + 1;
    }

    /**
     * {@inheritDoc}
     */
    public String getDescription() {
        String description = getLabel();
        final String doc = EcoreUtil.getDocumentation(proposal);
        if (doc != null) {
            description += "\n " + doc;
        }

        final String mmURI = proposal.getEPackage().getNsURI();
        if (mmURI != null) {
            description += "\nin " + mmURI;
        }
        Resource proposalResource = proposal.eResource();
        if (proposalResource != null && proposalResource.getURI() != null) {
            final String resourceURI = proposalResource.getURI().toString();
            if (mmURI != null && !resourceURI.equals(mmURI)) {
                description += "\nlocated at " + resourceURI;
            }
        }
        return description;
    }

    /**
     * {@inheritDoc}
     */
    public String getLabel() {
        String label = proposal.getName();
        if (proposal.getEPackage().getName() != null) {
            String fullyQualifiedProposal = proposal.getEPackage().getName() + "." + proposal.getName();
            if (fullyQualifiedProposal.startsWith(incomplete)) {
                label = fullyQualifiedProposal;
            }
        }
        return label;
    }
}
