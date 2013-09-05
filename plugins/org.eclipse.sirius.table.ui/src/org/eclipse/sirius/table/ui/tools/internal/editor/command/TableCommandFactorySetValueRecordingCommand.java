/*******************************************************************************
 * Copyright (c) 2010 THALES GLOBAL SERVICES.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Obeo - initial API and implementation
 *******************************************************************************/
package org.eclipse.sirius.table.ui.tools.internal.editor.command;

import java.util.Collection;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.transaction.RecordingCommand;
import org.eclipse.emf.transaction.TransactionalEditingDomain;

import com.google.common.collect.Lists;

import org.eclipse.sirius.table.tools.api.command.ITableCommandFactory;

/**
 * A Recording Command to call tableCommandFactory.buildSetValue.
 * 
 * @author smonnier
 */
public class TableCommandFactorySetValueRecordingCommand extends RecordingCommand {

    /**
     * Table command factory.
     */
    private ITableCommandFactory tableCommandFactory;

    private Collection<? extends EObject> instances;

    private String name;

    private Object value;

    /**
     * 
     * {@inheritDoc}
     * 
     * @param domain
     *            my domain
     * @param label
     *            my user-friendly label
     * @param tableCommandFactory
     *            Table command factory.
     * @param instances
     *            collection of {@link EObject}.
     * @param name
     *            name of the feature to set.
     * @param value
     *            value to set.
     */
    public TableCommandFactorySetValueRecordingCommand(TransactionalEditingDomain domain, String label, ITableCommandFactory tableCommandFactory, final Collection<? extends EObject> instances,
            final String name, final Object value) {
        super(domain, label);
        this.tableCommandFactory = tableCommandFactory;
        this.instances = instances;
        this.name = name;
        this.value = value;
    }

    /**
     * 
     * {@inheritDoc}
     * 
     * @param domain
     *            my domain
     * @param label
     *            my user-friendly label
     * @param tableCommandFactory
     *            Table command factory.
     * @param instance
     *            current {@link EObject}.
     * @param name
     *            name of the feature to set.
     * @param value
     *            value to set
     */
    public TableCommandFactorySetValueRecordingCommand(TransactionalEditingDomain domain, String label, ITableCommandFactory tableCommandFactory, final EObject instance, final String name,
            final Object value) {
        super(domain, label);
        this.tableCommandFactory = tableCommandFactory;
        this.instances = Lists.newArrayList(instance);
        this.name = name;
        this.value = value;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doExecute() {
        for (EObject instance : instances) {
            tableCommandFactory.buildSetValue(instance, name, value).execute();
        }
    }

}
