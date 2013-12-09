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

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.sirius.business.api.query.EObjectQuery;
import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.RepresentationDescription;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;

/**
 * A Type Assistant provides type names from incomplete names.
 * 
 * @author cbrun
 * 
 */
public class TypeAssistant {

    private final Registry typeRegistry;

    private final AbstractPropertySection section;

    /**
     * Create a new type assistant.
     * 
     * @param registry
     *            the registry to consider to search for names.
     * @param section
     *            the source property section
     */
    public TypeAssistant(final Registry registry, AbstractPropertySection section) {
        this.typeRegistry = registry;
        this.section = section;
    }

    /**
     * Create a new type assistant.
     * 
     * @param registry
     *            the registry to consider to search for names.
     */
    public TypeAssistant(final Registry registry) {
        this(registry, null);
    }

    /**
     * Return proposals of classifiers corresponding to an incomplete name.
     * 
     * @param incompleteName
     *            name of a classifier.
     * @return the proposals for this name.s
     */
    public List<EClassifier> proposal(final String incompleteName) {
        final Set<EClassifier> proposals = new LinkedHashSet<EClassifier>();
        for (final Object value : getEntryPoints()) {
            if (value instanceof EPackage) {
                addProposals(proposals, (EPackage) value, incompleteName);
            } else if (value instanceof EPackage.Descriptor) {
                try {
                    addProposals(proposals, ((EPackage.Descriptor) value).getEPackage(), incompleteName);
                    // CHECKSTYLE:OFF
                } catch (Exception e) {
                    // we don't really know what might go on from now, some
                    // other Eclipse tools might break the registry some time,
                    // we should just go on and ignore any issue.
                    // CHECKSTYLE:ON
                }
            }
        }
        return new ArrayList<EClassifier>(proposals);
    }

    /*
     * If a representation description is found in the current selected
     * element's ancestors, take the chosen metamodels ,else look for the
     * available metamodels.
     */
    private Collection<Object> getEntryPoints() {
        Collection<Object> values;
        Option<RepresentationDescription> desc = getCurrentRepresentationDescription();
        if (desc.some() && !desc.get().getMetamodel().isEmpty()) {
            values = new ArrayList<Object>(desc.get().getMetamodel());
        } else {
            values = new ArrayList<Object>(typeRegistry.values());
        }
        return values;
    }

    private Option<RepresentationDescription> getCurrentRepresentationDescription() {
        RepresentationDescription desc = null;
        if (section != null && section.getSelection() instanceof IStructuredSelection) {
            Object object = ((IStructuredSelection) section.getSelection()).getFirstElement();
            if (object instanceof EObject) {
                if (object instanceof RepresentationDescription) {
                    desc = (RepresentationDescription) object;
                } else {
                    Option<EObject> firstAncestorOfType = new EObjectQuery((EObject) object).getFirstAncestorOfType(DescriptionPackage.eINSTANCE.getRepresentationDescription());
                    if (firstAncestorOfType.some() && firstAncestorOfType.get() instanceof RepresentationDescription) {
                        desc = (RepresentationDescription) firstAncestorOfType.get();
                    }
                }
            }
        }
        return Options.newSome(desc);
    }

    private void addProposals(final Collection<EClassifier> proposals, final EPackage ePackage, final String incompleteName) {
        for (final EClassifier clazz : ePackage.getEClassifiers()) {
            if ((clazz.getName() != null && clazz.getName().startsWith(incompleteName)) || ((ePackage.getName() != null && (ePackage.getName() + "." + clazz.getName()).startsWith(incompleteName)))) {
                proposals.add(clazz);
            }
        }
    }
}
