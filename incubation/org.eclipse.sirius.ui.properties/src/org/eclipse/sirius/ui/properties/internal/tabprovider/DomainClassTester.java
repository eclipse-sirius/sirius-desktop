/*******************************************************************************
 * Copyright (c) 2015, 2016 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.ui.properties.internal.tabprovider;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import com.google.common.base.Predicate;

/**
 * This class is used to filter EObjects using a domain class.
 * 
 * @author pcdavid
 * @author sbegaudeau
 */
public class DomainClassTester implements Predicate<EObject> {
    private static final Pattern SEPARATOR = Pattern.compile("(::?|\\.)");

    private final String packageName;

    private final String className;

    /**
     * The constructor.
     * 
     * @param domainClassName
     *            A domain class "packageName::className" or
     *            "packageName.className".
     */
    public DomainClassTester(String domainClassName) {
        if (domainClassName != null) {
            Matcher m = SEPARATOR.matcher(domainClassName);
            if (m.find()) {
                packageName = domainClassName.substring(0, m.start());
                className = domainClassName.substring(m.end());
            } else {
                packageName = null;
                className = domainClassName;
            }
        } else {
            packageName = null;
            className = null;
        }

    }

    @Override
    public boolean apply(EObject input) {
        boolean apply = false;
        if (className == null && packageName == null) {
            apply = true;
        } else if (input != null) {
            EClass klass = input.eClass();
            boolean packageMatch = packageName == null || packageName.equals(klass.getEPackage().getName());
            apply = packageMatch && (className == null || className.equals(klass.getName()));
        }
        return apply;
    }

}
