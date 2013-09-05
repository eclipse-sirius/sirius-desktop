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
package org.eclipse.sirius.tree.business.internal.dialect.common.viewpoint;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.synchronizer.EvaluatedSemanticPartition;
import org.eclipse.sirius.synchronizer.Maybe;
import org.eclipse.sirius.synchronizer.SemanticPartition;
import org.eclipse.sirius.synchronizer.SemanticPartitions;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;

/**
 * TODO .
 * 
 * @author cbrun
 */
public class MappingBasedPartition implements SemanticPartition {

    private String domainClass;

    private Maybe<String> semanticCandidate;

    private GlobalContext ctx;

    private Maybe<? extends EObject> specificationAttachment;

    /**
     * Creates a new MappingBasedPartition.
     * 
     * @param ctx
     *            the global contact
     * @param domainClass
     *            a String identifying the domain Class
     * @param semanticCandidate
     *            a String identifying the semantic candidate
     * @param specificationAttachment
     *            the specification attachment
     */
    public MappingBasedPartition(GlobalContext ctx, String domainClass, Maybe<String> semanticCandidate, Maybe<? extends EObject> specificationAttachment) {
        this.domainClass = domainClass;
        this.ctx = ctx;
        this.semanticCandidate = semanticCandidate;
        this.specificationAttachment = specificationAttachment;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.tree.internal.business.dialect.common.SemanticPartition#evaluate(org.eclipse.emf.ecore.EObject)
     */
    public EvaluatedSemanticPartition evaluate(EObject root) {
        Iterator<EObject> elements = Collections.<EObject> emptyList().iterator();
        if (semanticCandidate.some()) {
            try {
                elements = ctx.getInterpreter().evaluateCollection(root, semanticCandidate.value()).iterator();
            } catch (EvaluationException e) {
                ctx.getSpecifierFeedBack().warning("Error while evaluating semantic candidate expression", e, specificationAttachment);
            }
        } else {
            elements = allEObjectsOfTheSession();
        }
        return SemanticPartitions.eObjectList(ImmutableList.copyOf(Iterators.filter(elements, new Predicate<EObject>() {

            public boolean apply(EObject input) {
                return ctx.getModelAccessor().eInstanceOf(input, domainClass);
            }
        })));

    }

    private Iterator<EObject> allEObjectsOfTheSession() {
        final Collection<Iterator<EObject>> iterators = Lists.newArrayList();
        for (EObject obj : getAllSessionSemanticRoots()) {
            iterators.add(obj.eAllContents());
        }
        return Iterators.concat(iterators.iterator());
    }

    private Collection<EObject> getAllSessionSemanticRoots() {
        if (ctx.getSession().some()) {
            Collection<EObject> semanticRoots = Lists.newArrayList();
            for (Resource resource : ctx.getSession().get().getSemanticResources()) {
                semanticRoots.addAll(resource.getContents());
            }
            return semanticRoots;
        } else {
            return Collections.emptyList();
        }
    }
}
