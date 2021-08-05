/*******************************************************************************
 * Copyright (c) 2007, 2021 THALES GLOBAL SERVICES.
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
package org.eclipse.sirius.business.internal.logger;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.sirius.business.api.logger.RuntimeLogger;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerInterpreter;
import org.eclipse.sirius.business.api.logger.RuntimeLoggerManager;
import org.eclipse.sirius.common.tools.api.interpreter.IInterpreter;
import org.eclipse.sirius.common.tools.api.util.EclipseUtil;
import org.eclipse.sirius.tools.api.SiriusPlugin;

/**
 * Implementation of the Runtime Logger Manager.
 *
 * @author smonnier
 *
 */
public class RuntimeLoggerManagerImpl implements RuntimeLoggerManager {

    private final Collection<RuntimeLogger> loggers = new LinkedHashSet<RuntimeLogger>();

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

    @Override
    public RuntimeLoggerInterpreter decorate(final IInterpreter interpreter) {
        return new RuntimeLoggerInterpreterImpl(interpreter);
    }

    /**
     * Registers the specified logger.
     *
     * @param logger
     *            a logger.
     * 
     */
    public void add(final RuntimeLogger logger) {
        loggers.add(logger);
    }

    /**
     * Unregisters the specified logger.
     *
     * @param logger
     *            a logger.
     * 
     */
    public void remove(final RuntimeLogger logger) {
        loggers.remove(logger);
    }

    @Override
    public void error(final EObject odesignObject, final EStructuralFeature feature, final String message) {
        for (RuntimeLogger logger : loggers) {
            logger.error(odesignObject, feature, message);
        }
    }

    @Override
    public void info(final EObject odesignObject, final EStructuralFeature feature, final String message) {
        for (RuntimeLogger logger : loggers) {
            logger.info(odesignObject, feature, message);
        }
    }

    @Override
    public void warning(final EObject odesignObject, final EStructuralFeature feature, final String message) {
        for (RuntimeLogger logger : loggers) {
            logger.warning(odesignObject, feature, message);
        }
    }

    @Override
    public void clearAll() {
        for (RuntimeLogger logger : loggers) {
            logger.clearAll();
        }
    }

    @Override
    public void clear(final EObject eObject) {
        for (RuntimeLogger logger : loggers) {
            logger.clear(eObject);
        }
    }

    @Override
    public void error(final EObject odesignObject, final EStructuralFeature feature, final Throwable exception) {
        for (RuntimeLogger logger : loggers) {
            logger.error(odesignObject, feature, exception);
        }
    }

    @Override
    public void info(final EObject odesignObject, final EStructuralFeature feature, final Throwable exception) {
        for (RuntimeLogger logger : loggers) {
            logger.info(odesignObject, feature, exception);
        }
    }

    @Override
    public void warning(final EObject odesignObject, final EStructuralFeature feature, final Throwable exception) {
        for (RuntimeLogger logger : loggers) {
            logger.warning(odesignObject, feature, exception);
        }
    }
}
