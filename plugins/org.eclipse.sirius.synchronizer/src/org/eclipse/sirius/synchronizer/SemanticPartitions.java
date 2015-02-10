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
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import com.google.common.collect.Lists;

/**
 * A set of classical semantic partitions.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 * 
 */
public class SemanticPartitions {

    public static SemanticPartition eAllContents() {
        return new EAllContentsPartition();
    }

    public static SemanticPartition eAllContents(String domainClass) {
        // TODO Auto-generated method stub
        return null;
    }

    public static EvaluatedSemanticPartition eObjectList(List<EObject> set) {
        return new EObjectList(set);
    }
}

class EAllContentsPartition implements SemanticPartition {

    public EvaluatedSemanticPartition evaluate(EObject root, CreatedOutput parentElement) {
        return new EObjectList(Lists.newArrayList(root.eAllContents()));
    }

}

class EObjectList implements EvaluatedSemanticPartition {

    private Collection<EObject> set;

    public EObjectList(Collection<EObject> set) {
        this.set = set;
    }

    public boolean isElementOf(EObject sem) {
        return set.contains(sem);
    }

    public Iterator<EObject> elements() {
        return set.iterator();
    }
}
