/*******************************************************************************
 * Copyright (c) 2008-2012 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.model.business.internal.metamodel.description.spec;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.sirius.viewpoint.description.DescriptionPackage;
import org.eclipse.sirius.viewpoint.description.impl.DAnnotationImpl;

/**
 * Customization of the generated {@link DAnnotationImpl} class.
 * 
 * @author <a href="mailto:alex.lagarde@obeo.fr">Alex Lagarde</a>
 * 
 */
public class DAnnotationSpec extends DAnnotationImpl {

    /**
     * Overridden to customize the type of the returned Map. {@inheritDoc}
     * 
     * @see org.eclipse.sirius.viewpoint.description.impl.DAnnotationImpl#getDetails()
     * 
     * 
     */
    public EMap<String, String> getDetails() {
        if (details == null) {
            details = new EcoreEMap<String, String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, DescriptionPackage.DANNOTATION__DETAILS) {
                private static final long serialVersionUID = 1L;

                @Override
                protected void ensureEntryDataExists() {
                    if (entryData == null) {
                        // Ensure that this race condition is thread safe; it
                        // doesn't matter who wins the race.
                        //
                        BasicEList<Entry<String, String>>[] result = newEntryData(2 * size + 1);
                        for (Entry<String, String> entry : delegateEList) {
                            int hash = entry.getHash();
                            int index = (hash & 0x7FFFFFFF) % result.length;
                            BasicEList<Entry<String, String>> eList = result[index];
                            if (eList == null) {
                                result[index] = newList();
                                eList = result[index];
                            }
                            eList.add(entry);
                        }
                        entryData = result;
                    }
                }
            };
        }
        return details;
    }
}
