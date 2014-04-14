/*******************************************************************************
 * Copyright (c) 2011 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.synchronizer;

import java.util.Collection;
import java.util.Map;

import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;

/**
 * A refresh plan holds the list of operation : creation, refreshment or
 * removing needed to update the output model.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public class RefreshPlan {

    private SignatureProvider signatureProvider;

    private Map<Signature, OutputDescriptor> outputToCreate;

    private Map<Signature, CreatedOutput> outputToRefresh;

    private Map<Signature, CreatedOutput> outputToUpdateMapping;

    private Multimap<Signature, CreatedOutput> outputToRemove;

    /**
     * Creates a new RefreshPlan.
     * 
     * @param signProvider
     *            the {@link SignatureProvider} to use
     */
    public RefreshPlan(SignatureProvider signProvider) {
        outputToCreate = Maps.newLinkedHashMap();
        outputToRefresh = Maps.newLinkedHashMap();
        outputToRemove = HashMultimap.create();
        outputToUpdateMapping = Maps.newHashMap();
        this.signatureProvider = signProvider;

    }

    public Collection<CreatedOutput> getDescriptorToUpdateMapping() {
        return outputToUpdateMapping.values();
    }

    /**
     * Adds a PreRefreshStatus to the RefreshPlan.
     * 
     * @param pre
     *            the PreRefreshStatus to add to the refresh plan
     */
    public void addPreviousStatus(Iterable<? extends CreatedOutput> pre) {
        for (CreatedOutput desc : pre) {
            shouldBeRemoved(desc);
        }
    }

    private void shouldBeRemoved(CreatedOutput desc) {
        outputToRemove.put(signatureProvider.getSignature(desc.getDescriptor()), desc);
    }

    public void appendOutputDescritorsKeepingTheMostSpecific(Collection<? extends OutputDescriptor> computeDescriptors) {

        for (OutputDescriptor newDescriptor : computeDescriptors) {
            Option<CreatedOutput> wasHere = getDescriptorAlreadyHereWeWantToKeep(newDescriptor);
            if (wasHere.some()) {
                if (wasHere.get().getDescriptor().getIndex() != newDescriptor.getIndex()) {
                    shouldBeReordered(wasHere.get(), newDescriptor.getIndex());
                }
                if (wasHere.get().getDescriptor().getMapping() != newDescriptor.getMapping()) {
                    shouldUpdateTheMapping(wasHere.get(), newDescriptor.getMapping());
                }
                shouldBeRefreshed(wasHere.get());
            } else {
                shouldBeCreated(newDescriptor);
            }
        }
    }

    private void shouldUpdateTheMapping(CreatedOutput createdOutput, Mapping mapping) {
        createdOutput.setNewMapping(mapping);
        Signature signature = signatureProvider.getSignature(createdOutput.getDescriptor());
        // CreatedOutput previousVersionOutput =
        // outputToRemove.remove(signature);
        // previousVersionOutput.setNewMapping(mapping);
        outputToUpdateMapping.put(signature, createdOutput);

    }

    private void shouldBeCreated(OutputDescriptor newDescriptor) {
        /*
         * First let's check if an equivalent descriptor is already registered
         * to be created.
         */
        Signature signature = signatureProvider.getSignature(newDescriptor);
        if (!outputToCreate.containsKey(signature) && !outputToRefresh.containsKey(signature)) {
            outputToCreate.put(signature, newDescriptor);
        }
    }

    private void shouldBeRefreshed(CreatedOutput outputDescriptor) {
        Signature signature = signatureProvider.getSignature(outputDescriptor.getDescriptor());
        Option<CreatedOutput> element = getDescriptorAlreadyHereWeWantToKeep(outputDescriptor.getDescriptor());
        if (element.some()) {
            outputToRemove.remove(signature, element.get());
            if (!outputToRefresh.containsKey(signature)) {
                outputToRefresh.put(signature, element.get());
            }
        }
    }

    private void shouldBeReordered(CreatedOutput newDescriptor, int nextIndex) {
        newDescriptor.setNewIndex(nextIndex);
    }

    private Option<CreatedOutput> getDescriptorAlreadyHereWeWantToKeep(OutputDescriptor newDescriptor) {
        Collection<CreatedOutput> toRemove = outputToRemove.get(signatureProvider.getSignature(newDescriptor));
        if (toRemove.size() > 0) {
            return Options.newSome(toRemove.iterator().next());
        }
        return Options.newNone();
    }

    public Collection<CreatedOutput> getDescriptorsToDelete() {
        return outputToRemove.values();
    }

    public Collection<OutputDescriptor> getDescriptorsToCreate() {
        return outputToCreate.values();
    }

    public Collection<CreatedOutput> getDescriptorsToRefresh() {
        return outputToRefresh.values();
    }

}
