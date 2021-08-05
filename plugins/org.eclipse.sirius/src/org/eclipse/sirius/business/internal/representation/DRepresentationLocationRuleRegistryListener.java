/*******************************************************************************
 * Copyright (c) 2017, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.representation;

import java.util.Collection;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.IRegistryEventListener;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.business.api.session.danalysis.DRepresentationLocationRule;
import org.eclipse.sirius.business.internal.representation.DRepresentationLocationRuleRegistry.Priority;
import org.eclipse.sirius.tools.api.Messages;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * This listener will allow to be aware of contribution changes against the dRepresentationLocationRule extension point.
 * 
 * @author <a href="mailto:laurent.fasani@obeo.fr">Laurent Fasani</a>
 */
public class DRepresentationLocationRuleRegistryListener implements IRegistryEventListener {

    /** Name of the extension point to parse for extensions. */
    public static final String REP_LOCATION_RULE_EXTENSION_POINT = SiriusPlugin.ID + ".dRepresentationLocationRule"; //$NON-NLS-1$

    /** Name of the extension point's "dRepresentationLocationRule" tag. */
    private static final String REP_LOCATION_RULE_TAG_EXTENSION = "dRepresentationLocationRule"; //$NON-NLS-1$

    /** Name of the dRepresentationLocationRule extension point's tag "class" attribute. */
    private static final String REP_LOCATION_RULE_CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    /** Name of the dRepresentationLocationRule extension point's tag "priority" attribute. */
    private static final String REP_LOCATION_RULE_CLASS_PRIORITY = "priority"; //$NON-NLS-1$

    /**
     * Register this listener and parse initial contributions.
     */
    public void init() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.addListener(this, REP_LOCATION_RULE_EXTENSION_POINT);
        parseInitialContributions();
    }

    @Override
    public void added(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            parseExtension(extension);
        }
    }

    @Override
    public void added(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
    }

    @Override
    public void removed(IExtension[] extensions) {
        for (IExtension extension : extensions) {
            final IConfigurationElement[] configElements = extension.getConfigurationElements();
            for (IConfigurationElement elem : configElements) {
                if (REP_LOCATION_RULE_TAG_EXTENSION.equals(elem.getName())) {
                    final String extensionClassName = elem.getAttribute(REP_LOCATION_RULE_CLASS_ATTRIBUTE);
                    Collection<DRepresentationLocationRule> repLocationRules = DRepresentationLocationRuleRegistry.getInstance().getRepLocationRules();
                    for (DRepresentationLocationRule repLocationRule : repLocationRules) {
                        if (extensionClassName.equals(repLocationRule.getClass().getName())) {
                            DRepresentationLocationRuleRegistry.getInstance().removeRepLocationRule(repLocationRule);
                        }
                    }
                }
            }
        }
    }

    @Override
    public void removed(IExtensionPoint[] extensionPoints) {
        // no need to listen to this event
    }

    /**
     * Though this listener reacts to the extension point changes, there could have been contributions before it's been
     * registered. This will parse these initial contributions.
     */
    public void parseInitialContributions() {
        final IExtensionRegistry registry = Platform.getExtensionRegistry();

        for (IExtension extension : registry.getExtensionPoint(REP_LOCATION_RULE_EXTENSION_POINT).getExtensions()) {
            parseExtension(extension);
        }
    }

    /**
     * Parses a single extension contribution.
     * 
     * @param extension
     *            Parses the given extension and adds its contribution to the registry.
     */
    private void parseExtension(IExtension extension) {
        final IConfigurationElement[] configElements = extension.getConfigurationElements();
        for (IConfigurationElement elem : configElements) {
            try {
                Object contribution = elem.createExecutableExtension(REP_LOCATION_RULE_CLASS_ATTRIBUTE); // $NON-NLS-1$
                if (contribution instanceof DRepresentationLocationRule) {
                    String priorityStr = elem.getAttribute(REP_LOCATION_RULE_CLASS_PRIORITY);
                    Priority priority = DRepresentationLocationRuleRegistry.Priority.valueOf(priorityStr.toUpperCase());
                    DRepresentationLocationRuleRegistry.getInstance().addRepLocationRule((DRepresentationLocationRule) contribution, priority);
                }
            } catch (CoreException | IllegalArgumentException | NullPointerException e) {
                SiriusPlugin.getDefault().getLog().log(new Status(Status.WARNING, SiriusPlugin.ID, Messages.AbstractSiriusMigrationService_contributionInstantiationErrorMsg, e));
            }
        }
    }

    /**
     * Remove this listener and flush the associated registry.
     */
    public void dispose() {
        IExtensionRegistry registry = Platform.getExtensionRegistry();
        registry.removeListener(this);
        DRepresentationLocationRuleRegistry.getInstance().dispose();
    }

}
