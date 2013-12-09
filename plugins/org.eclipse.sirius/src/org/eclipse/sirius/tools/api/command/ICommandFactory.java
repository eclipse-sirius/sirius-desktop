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
package org.eclipse.sirius.tools.api.command;

import org.eclipse.emf.common.command.Command;
import org.eclipse.sirius.business.api.dialect.command.CreateRepresentationCommand;
import org.eclipse.sirius.tools.api.command.ui.UICallBack;
import org.eclipse.sirius.viewpoint.DRepresentationElement;
import org.eclipse.sirius.viewpoint.DSemanticDecorator;
import org.eclipse.sirius.viewpoint.description.tool.RepresentationCreationDescription;

/**
 * The generic command factory to inherit for each dialect command factory.
 * 
 * @author mchauvin
 * @since 0.9.0
 */
public interface ICommandFactory {

    /**
     * Create a command that is able to create a representation.
     * 
     * @param desc
     *            the tool that describes how to create the representation.
     * @param element
     *            the element from which the representation will be created.
     * @param newRepresentationName
     *            the name of the new representation
     * @return a command that is able to create a representation.
     */
    CreateRepresentationCommand buildCreateRepresentationFromDescription(RepresentationCreationDescription desc, DRepresentationElement element, String newRepresentationName);

    /**
     * Create a command that is able to execute the operations of a
     * {@link RepresentationCreationDescription}.
     * 
     * @param target
     *            the target element.
     * @param desc
     *            the operations.
     * @param newRepresentationName
     *            the name of the new Representation
     * @return the created command.
     */
    Command buildDoExecuteDetailsOperation(DSemanticDecorator target, RepresentationCreationDescription desc, String newRepresentationName);

    /**
     * Defines the UI call back to use.
     * 
     * @param newCB
     *            the new user interface call back.
     */
    void setUserInterfaceCallBack(UICallBack newCB);

    /**
     * Return the UI call back to use.
     * 
     * @return the UI call back to use
     */
    UICallBack getUserInterfaceCallBack();
}
