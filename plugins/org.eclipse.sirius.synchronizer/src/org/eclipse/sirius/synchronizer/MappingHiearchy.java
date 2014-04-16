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

import java.util.Iterator;

import org.eclipse.sirius.ext.base.Option;
import org.eclipse.sirius.ext.base.Options;

import com.google.common.collect.AbstractIterator;

/**
 * Mappings might be combined in a hierarchy. This class represents this
 * Hierarchy.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public class MappingHiearchy {

    private Mapping mostSpecific;

    /**
     * Creates a new MappingHiearchy.
     * 
     * @param mostSpecificMapping
     *            the most specific mapping
     */
    public MappingHiearchy(Mapping mostSpecificMapping) {
        this.mostSpecific = mostSpecificMapping;
    }

    /**
     * Computes an iterator corresponding to the Mapping Hierarchy, from Most
     * Specific Mapping to most general.
     * 
     * @return an iterator corresponding to the Mapping Hierarchy, from Most
     *         Specific Mapping to most general
     */
    public Iterator<Mapping> fromMostSpecificToMostGeneral() {
        return new HiearchyIterator(mostSpecific);
    }

    private static class HiearchyIterator extends AbstractIterator<Mapping> {

        private Option<? extends Mapping> cur;

        public HiearchyIterator(Mapping start) {
            this.cur = Options.newSome(start);
        }

        @Override
        protected Mapping computeNext() {
            if (cur.some()) {
                Mapping toReturn = cur.get();
                cur = toReturn.getSuper();
                return toReturn;
            }
            endOfData();
            return null;

        }
    };

}
