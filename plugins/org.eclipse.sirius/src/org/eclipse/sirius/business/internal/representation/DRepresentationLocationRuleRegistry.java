/*******************************************************************************
 * Copyright (c) 2017 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.representation;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.sirius.business.api.resource.strategy.ResourceStrategy;
import org.eclipse.sirius.business.api.session.danalysis.DRepresentationLocationRule;
import org.eclipse.sirius.viewpoint.DRepresentation;

/**
 * Manage the available {@link ResourceStrategy}.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public final class DRepresentationLocationRuleRegistry {

    /**
     * The singleton instance of the ResourceStrategyRegistry.
     */
    private static final DRepresentationLocationRuleRegistry INSTANCE = new DRepresentationLocationRuleRegistry();

    private Map<DRepresentationLocationRule, Priority> repLocationRules = new HashMap<>();

    private DRepresentationLocationRuleRegistry() {
    }

    public static DRepresentationLocationRuleRegistry getInstance() {
        return INSTANCE;
    }

    /**
     * Priority used to choose the right extension.
     * 
     * @author lfasani
     *
     */
    public enum Priority {
        /**
         * Core priority. Clients should not use that priority.
         */
        CORE(0),
        /**
         * Normal priority.
         */
        NORMAL(1),
        /**
         * High priority.
         */
        HIGH(2);

        private int priority;

        Priority(int priority) {
            this.priority = priority;
        }

        public int getPriority() {
            return priority;
        }
    }

    /**
     * Add a DRepresentationLocationRule to the registry.
     * 
     * @param repLocationRule
     *            the DRepresentationLocationRule
     * @param priority
     *            the priority
     */
    public void addRepLocationRule(DRepresentationLocationRule repLocationRule, Priority priority) {
        repLocationRules.put(repLocationRule, priority);
    }

    /**
     * Remove a DRepresentationLocationRule to the registry.
     * 
     * @param repLocationRule
     *            the repLocationRule
     */
    public void removeRepLocationRule(DRepresentationLocationRule repLocationRule) {
        repLocationRules.remove(repLocationRule);
    }

    public Set<DRepresentationLocationRule> getRepLocationRules() {
        return repLocationRules.keySet();
    }

    /**
     * Removes all extensions from the registry. This will be called at plugin stopping.
     */
    public void dispose() {
        repLocationRules.clear();
    }

    /**
     * Get the compatible {@link DRepresentationLocationRule} that is one which provides and with highest priority.
     * </br>
     * If several are found, the first is returned.
     * 
     * @param representation
     *            the current representation.
     * @param airdResource
     *            the aird resource from which the representation will be referenced.
     * @return the corresponding {@link ResourceStrategy}
     */
    public Optional<DRepresentationLocationRule> getRepresentationLocationRule(DRepresentation representation, Resource airdResource) {
        Optional<DRepresentationLocationRule> repLocRule = repLocationRules.keySet().stream().filter(locRule -> locRule.providesURI(representation, airdResource)).sorted((locRule1, locRule2) -> {
            return repLocationRules.get(locRule2).getPriority() - repLocationRules.get(locRule1).getPriority();
        }).findFirst();
        return repLocRule;
    }
}
