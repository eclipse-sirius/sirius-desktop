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
import org.eclipse.sirius.common.tools.api.interpreter.EvaluationException;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreterSiriusVariables;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.synchronizer.CreatedOutput;
import org.eclipse.sirius.synchronizer.EvaluatedSemanticPartition;
import org.eclipse.sirius.synchronizer.OutputDescriptor;
import org.eclipse.sirius.synchronizer.SemanticPartition;
import org.eclipse.sirius.synchronizer.SemanticPartitions;
import org.eclipse.sirius.tree.business.internal.helper.TreeHelper;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;

/**
 * A {@link SemanticPartition} based on a domanClass for a specified
 * {@link GlobalContext}.
 * 
 * @author cbrun
 */
public class MappingBasedPartition implements SemanticPartition {

    private static final String TREE = "tree"; //$NON-NLS-1$

    private String domainClass;

    private Option<String> semanticCandidate;

    private GlobalContext ctx;

    private Option<? extends EObject> specificationAttachment;

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
    public MappingBasedPartition(GlobalContext ctx, String domainClass, Option<String> semanticCandidate, Option<? extends EObject> specificationAttachment) {
        this.domainClass = domainClass;
        this.ctx = ctx;
        this.semanticCandidate = semanticCandidate;
        this.specificationAttachment = specificationAttachment;
    }

    @Override
    public EvaluatedSemanticPartition evaluate(EObject root, CreatedOutput parentElement) {
        setTreeElementVariables(parentElement);
        Iterator<EObject> elements = Collections.<EObject> emptyList().iterator();
        if (semanticCandidate.some()) {
            try {
                elements = ctx.getInterpreter().evaluateCollection(root, semanticCandidate.get()).iterator();
            } catch (EvaluationException e) {
                ctx.getSpecifierFeedBack().warning("Error while evaluating semantic candidate expression", e, specificationAttachment);
            }
        } else {
            elements = allEObjectsOfTheSession();
        }
        unSetTreeElementVariables();
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
        Collection<Resource> semanticResources = ctx.getSemanticResources();
        if (semanticResources == null) {
            semanticResources = Collections.emptyList();
        }
        Collection<EObject> semanticRoots = Lists.newArrayList();
        for (Resource semanticResource : semanticResources) {
            semanticRoots.addAll(semanticResource.getContents());
        }
        return semanticRoots;
    }

    private void setTreeElementVariables(CreatedOutput element) {
        if (element != null) {
            OutputDescriptor descriptor = element.getDescriptor();
            if (descriptor != null && descriptor.getSourceElement() != null) {
                ctx.getInterpreter().setVariable(IInterpreterSiriusVariables.CONTAINER, descriptor.getSourceElement());
            }
            EObject createdElement = element.getCreatedElement();
            if (createdElement != null) {
                ctx.getInterpreter().setVariable(IInterpreterSiriusVariables.CONTAINER_VIEW, createdElement);
                ctx.getInterpreter().setVariable(TREE, TreeHelper.getTree(createdElement));
            }
        }
    }

    private void unSetTreeElementVariables() {
        ctx.getInterpreter().unSetVariable(IInterpreterSiriusVariables.CONTAINER);
        ctx.getInterpreter().unSetVariable(IInterpreterSiriusVariables.CONTAINER_VIEW);
        ctx.getInterpreter().unSetVariable(TREE);
    }
}
