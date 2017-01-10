/**
 * Copyright (c) 2017 Obeo
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *  Obeo - initial API and implementation
 */
package org.eclipse.sirius.sample.basicfamily.advanced.design;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.sirius.sample.basicfamily.Person;

/**
 * The services class used by VSM.
 */
public class Services {

    /**
     * See
     * http://help.eclipse.org/neon/index.jsp?topic=%2Forg.eclipse.sirius.doc%
     * 2Fdoc%2Findex.html&cp=24 for documentation on how to write service
     * methods.
     */
    public int getCousinsNumber(Person person) {
        List<Person> cousins = new ArrayList<Person>();
        List<Person> parents = person.getParents();

        for (Person parent : parents) {
            for (Person grandParent : parent.getParents()) {
                for (Person uncleOrAunt : grandParent.getChildren()) {
                    if (!parents.contains(uncleOrAunt)) {
                        for (Person cousin : uncleOrAunt.getChildren()) {
                            if (!cousins.contains(cousin))
                                cousins.add(cousin);
                        }
                    }
                }
            }
        }
        return cousins.size();
    }

}
