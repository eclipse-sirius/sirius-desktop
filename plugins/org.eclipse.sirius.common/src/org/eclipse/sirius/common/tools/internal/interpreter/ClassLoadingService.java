/*******************************************************************************
 * Copyright (c) 2015 Obeo.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.common.tools.internal.interpreter;

import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.sirius.common.tools.DslCommonPlugin;
import org.eclipse.sirius.common.tools.api.interpreter.ClassLoading;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;

/**
 * Runtime-wide configuration for ClassLoading used by Interpreter instances.
 * instances.
 * 
 * @author Cedric Brun <cedric.brun@obeo.fr>
 *
 */
public final class ClassLoadingService {

    /**
     * A default class loading utility which is suitable for deployed modeler.
     */
    public static final ClassLoading DEFAULT = new BundleClassLoading();

    /**
     * ClassLoading service extension point ID.
     */
    private static final String ID = "org.eclipse.sirius.common.classloading_override"; //$NON-NLS-1$

    /**
     * Extension point attribute to get the {@link ClassLoading} class.
     */
    private static final String CLASS_ATTRIBUTE = "class"; //$NON-NLS-1$

    private ClassLoadingService() {

    }

    /**
     * return the class loading utility to use.
     * 
     * @return the class loading utility to use.
     */
    public static ClassLoading getClassLoading() {
        final List<ClassLoading> providedClassLoadings = EclipseUtil.getExtensionPlugins(ClassLoading.class, ID, CLASS_ATTRIBUTE);
        Iterator<ClassLoading> it = providedClassLoadings.iterator();
        ClassLoading picked = null;
        while (it.hasNext()) {
            if (picked == null) {
                picked = it.next();
            } else {
                final IStatus status = new Status(IStatus.WARNING, DslCommonPlugin.PLUGIN_ID, "Several overrides are contributed for the class loading override,  " + it.next().getClass().getName()
                        + " will be ignored");
                DslCommonPlugin.getDefault().getLog().log(status);
            }
        }
        if (picked == null) {
            picked = DEFAULT;
        }
        return picked;
    }

}
