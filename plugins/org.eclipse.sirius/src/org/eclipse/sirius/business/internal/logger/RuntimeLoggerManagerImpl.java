/*******************************************************************************
 * Copyright (c) 2007, 2008 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.business.internal.logger;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.SiriusPlugin;
import org.eclipse.sirius.business.api.logger.RuntimeLogger;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;

/**
 * Implementation of the Runtime Logger Manager.
 * 
 * @author smonnier
 * 
 */
public class RuntimeLoggerManagerImpl implements RuntimeLoggerManager {

    private Collection<RuntimeLogger> loggers = new LinkedHashSet<RuntimeLogger>();

    /**
     * Initialization of the manager.
     * 
     * @return the instance of the manager
     */
    public static RuntimeLoggerManager init() {
        final RuntimeLoggerManagerImpl manager = new RuntimeLoggerManagerImpl();
        if (SiriusPlugin.IS_ECLIPSE_RUNNING) {
            final List<RuntimeLogger> parsedLoggers = EclipseUtil.getExtensionPlugins(RuntimeLogger.class, RuntimeLoggerManager.ID, RuntimeLoggerManager.CLASS_ATTRIBUTE);
            for (final RuntimeLogger logger : parsedLoggers) {
                manager.add(logger);
            }
        }
        return manager;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLoggerManager#decorate(org.eclipse.sirius.common.tools.api.interpreter.IInterpreter)
     */
    public RuntimeLoggerInterpreter decorate(final IInterpreter interpreter) {
        return new RuntimeLoggerInterpreterImpl(interpreter);
    }

    private void add(final RuntimeLogger logger) {
        loggers.add(logger);
    }

    /**
     * Add error entries to the loggers.
     * 
     * @param odesignObject
     *            targeted EObject that contains errors
     * @param feature
     *            element of the targeted EObject that contains errors
     * @param message
     *            error message to display
     */
    public void error(final EObject odesignObject, final EStructuralFeature feature, final String message) {
        for (RuntimeLogger logger : loggers) {
            logger.error(odesignObject, feature, message);
        }
    }

    /**
     * Add info entries to the loggers.
     * 
     * @param odesignObject
     *            targeted EObject that contains errors
     * @param feature
     *            element of the targeted EObject that contains errors
     * @param message
     *            error message to display
     */
    public void info(final EObject odesignObject, final EStructuralFeature feature, final String message) {
        for (RuntimeLogger logger : loggers) {
            logger.info(odesignObject, feature, message);
        }
    }

    /**
     * Add warning entries to the loggers.
     * 
     * @param odesignObject
     *            targeted EObject that contains errors
     * @param feature
     *            element of the targeted EObject that contains errors
     * @param message
     *            error message to display
     */
    public void warning(final EObject odesignObject, final EStructuralFeature feature, final String message) {
        for (RuntimeLogger logger : loggers) {
            logger.warning(odesignObject, feature, message);
        }
    }

    /**
     * Clears all errors in the loggers.
     */
    public void clearAll() {
        for (RuntimeLogger logger : loggers) {
            logger.clearAll();
        }
    }

    /**
     * Clears all logged entries for the EObject.
     * 
     * @param eObject
     *            EObject we want to clearAll logged entries
     */
    public void clear(final EObject eObject) {
        for (RuntimeLogger logger : loggers) {
            logger.clear(eObject);
        }
    }

    /**
     * Add error entries to the loggers.
     * 
     * @param odesignObject
     *            targeted EObject that contains errors
     * @param feature
     *            element of the targeted EObject that contains errors
     * @param exception
     *            a low-level exception, or <code>null</code> if not applicable
     */
    public void error(final EObject odesignObject, final EStructuralFeature feature, final Throwable exception) {
        for (RuntimeLogger logger : loggers) {
            logger.error(odesignObject, feature, exception);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLogger#info(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Throwable)
     */
    public void info(final EObject odesignObject, final EStructuralFeature feature, final Throwable exception) {
        for (RuntimeLogger logger : loggers) {
            logger.info(odesignObject, feature, exception);
        }
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.sirius.business.api.logger.RuntimeLogger#warning(org.eclipse.emf.ecore.EObject,
     *      org.eclipse.emf.ecore.EStructuralFeature, java.lang.Throwable)
     */
    public void warning(final EObject odesignObject, final EStructuralFeature feature, final Throwable exception) {
        for (RuntimeLogger logger : loggers) {
            logger.warning(odesignObject, feature, exception);
        }
    }

}
